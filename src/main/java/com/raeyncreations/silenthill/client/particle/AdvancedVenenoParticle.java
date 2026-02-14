package com.raeyncreations.silenthill.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * Advanced poison/venom particle matching Bedrock Edition:
 * - Sphere emitter with outward direction
 * - Size reduction over time
 * - Collision with high drag and low restitution
 * - Red poison gradient (bright to transparent)
 */
@OnlyIn(Dist.CLIENT)
public class AdvancedVenenoParticle extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final float initialSize;
    private float spinSpeed;
    
    // Bedrock parameters from veneno.json
    private static final float COLLISION_DRAG = 20.0F;
    private static final float RESTITUTION = 0.2F;
    private static final float COLLISION_RADIUS = 0.01F;
    
    // Color gradient (#FF850808 to #ff000000)
    private static final int START_COLOR = 0xFF850808; // Bright red poison
    private static final int END_COLOR = 0x00000000;   // Transparent
    
    protected AdvancedVenenoParticle(ClientLevel level, double x, double y, double z,
                                    double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;
        
        // Random velocity (Math.random(1,2))
        float speed = 1.0F + this.random.nextFloat();
        this.xd = xSpeed * speed;
        this.yd = ySpeed * speed;
        this.zd = zSpeed * speed;
        
        // No gravity for poison cloud (floats in air)
        this.gravity = 0.0F;
        this.friction = 0.91F; // From Bedrock JSON
        
        // Random size variation (0.2 + variable.particle_random_1*0.2)
        this.initialSize = 0.2F + this.random.nextFloat() * 0.2F;
        this.quadSize = initialSize;
        
        // Short lifetime (Math.random(0.2, 0.8))
        this.lifetime = (int)(this.random.nextFloat() * 6.0F + 2.0F);
        
        // Random spin (Math.random(10))
        this.spinSpeed = this.random.nextFloat() * 10.0F;
        
        this.setSpriteFromAge(sprites);
        
        // Initial color (bright red poison)
        updateColor(0.0F);
    }
    
    @Override
    public void tick() {
        super.tick();
        
        // Update sprite
        this.setSpriteFromAge(this.sprites);
        
        // Rotation
        this.oRoll = this.roll;
        this.roll += spinSpeed * 0.1F;
        
        // Size reduction over time (from Bedrock: size - 0.1 * variable.particle_age)
        float agePercent = (float)this.age / (float)this.lifetime;
        this.quadSize = initialSize * (1.0F - agePercent * 0.5F);
        
        // Handle collision with high drag
        handleCollision();
        
        // Update color (red to transparent)
        updateColor(agePercent);
    }
    
    /**
     * Handle collision with high drag coefficient from Bedrock
     */
    private void handleCollision() {
        if (this.onGround) {
            // Apply collision drag
            float dragFactor = 1.0F - (COLLISION_DRAG / 100.0F);
            this.xd *= dragFactor;
            this.yd = Math.max(this.yd * RESTITUTION, 0);
            this.zd *= dragFactor;
            
            // If moving very slowly, stop
            if (Math.abs(this.xd) < 0.01F && Math.abs(this.zd) < 0.01F) {
                this.xd = 0;
                this.zd = 0;
            }
        }
    }
    
    /**
     * Linear gradient from bright red poison to transparent
     */
    private void updateColor(float t) {
        // Extract ARGB from start color
        int a1 = (START_COLOR >> 24) & 0xFF;
        int r1 = (START_COLOR >> 16) & 0xFF;
        int g1 = (START_COLOR >> 8) & 0xFF;
        int b1 = START_COLOR & 0xFF;
        
        // Interpolate to transparent
        this.alpha = (a1 * (1.0F - t)) / 255.0F;
        this.rCol = (r1 * (1.0F - t * 0.5F)) / 255.0F; // Keep some red longer
        this.gCol = (g1 * (1.0F - t)) / 255.0F;
        this.bCol = (b1 * (1.0F - t)) / 255.0F;
    }
    
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }
    
    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;
        
        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }
        
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                      double x, double y, double z,
                                      double xSpeed, double ySpeed, double zSpeed) {
            return new AdvancedVenenoParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
