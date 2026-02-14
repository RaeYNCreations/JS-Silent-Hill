package com.raeyncreations.silenthill.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * Advanced blood particle matching Bedrock Edition behavior:
 * - 4-point color gradient
 * - Velocity-based direction and rotation
 * - Collision detection with bounce
 * - Linear acceleration and drag coefficient
 */
@OnlyIn(Dist.CLIENT)
public class AdvancedSilentBloodParticle extends TextureSheetParticle {
    private final SpriteSet sprites;
    private final float initialSpeed;
    private float spinSpeed;
    
    // Bedrock particle parameters
    private static final float GRAVITY_ACCELERATION = -20.0F;
    private static final float DRAG_COEFFICIENT = 2.5F;
    private static final float COLLISION_RADIUS = 0.05F;
    private static final float RESTITUTION = 0.3F; // Bounce coefficient
    
    // Color gradient (Bedrock format: #FF5B1414, #FF5A1818, #8C3F0F0F, #42601414)
    private static final int[] GRADIENT_COLORS = {
        0xFF5B1414, // 0.0 - Bright dark red
        0xFF5A1818, // 0.33 - Medium dark red
        0x8C3F0F0F, // 0.67 - Fading red
        0x42601414  // 1.0 - Very faded
    };
    private static final float[] GRADIENT_TIMES = {0.0F, 0.33F, 0.67F, 1.0F};
    
    protected AdvancedSilentBloodParticle(ClientLevel level, double x, double y, double z,
                                          double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;
        
        // Set initial velocity with random variation (Math.random(3,5))
        this.initialSpeed = 3.0F + this.random.nextFloat() * 2.0F;
        this.xd = xSpeed * initialSpeed;
        this.yd = ySpeed * initialSpeed;
        this.zd = zSpeed * initialSpeed;
        
        // Bedrock parameters
        this.friction = 1.0F - (DRAG_COEFFICIENT / 100.0F); // Convert drag to friction
        this.gravity = GRAVITY_ACCELERATION / 100.0F; // Scale for Minecraft
        this.quadSize = 0.2F;
        this.lifetime = 25; // From Bedrock JSON
        
        // Initial spin (Math.random(10))
        this.spinSpeed = this.random.nextFloat() * 10.0F;
        
        this.setSpriteFromAge(sprites);
        
        // Initial color from gradient
        updateColor(0.0F);
    }
    
    @Override
    public void tick() {
        super.tick();
        
        // Update sprite animation
        this.setSpriteFromAge(this.sprites);
        
        // Apply rotation based on velocity (derive_from_velocity)
        float speed = (float)Math.sqrt(this.xd * this.xd + this.yd * this.yd + this.zd * this.zd);
        if (speed > 0.2F) { // min_speed_threshold from Bedrock
            this.oRoll = this.roll;
            this.roll += spinSpeed * 0.1F;
        }
        
        // Apply collision detection
        handleCollision();
        
        // Update color gradient based on lifetime
        float agePercent = (float)this.age / (float)this.lifetime;
        updateColor(agePercent);
    }
    
    /**
     * Handle collision with blocks (Bedrock collision_radius: 0.05)
     */
    private void handleCollision() {
        if (this.onGround) {
            // Bounce with restitution coefficient
            this.yd *= -RESTITUTION;
            this.xd *= 0.7F; // Sliding friction
            this.zd *= 0.7F;
            
            if (Math.abs(this.yd) < 0.02F) {
                // Stop bouncing when velocity is very low
                this.yd = 0;
                this.xd *= 0.9F;
                this.zd *= 0.9F;
            }
        }
    }
    
    /**
     * Update particle color using 4-point gradient interpolation from Bedrock
     */
    private void updateColor(float t) {
        // Find surrounding gradient points
        int index = 0;
        for (int i = 0; i < GRADIENT_TIMES.length - 1; i++) {
            if (t >= GRADIENT_TIMES[i] && t <= GRADIENT_TIMES[i + 1]) {
                index = i;
                break;
            }
        }
        
        // Bounds check to prevent array index out of bounds
        if (index >= GRADIENT_TIMES.length - 1) {
            index = GRADIENT_TIMES.length - 2;
        }
        
        // Interpolate between two gradient points
        float deltaTime = GRADIENT_TIMES[index + 1] - GRADIENT_TIMES[index];
        // Prevent division by zero
        if (deltaTime < 0.0001F) {
            deltaTime = 0.0001F;
        }
        float localT = (t - GRADIENT_TIMES[index]) / deltaTime;
        int color1 = GRADIENT_COLORS[index];
        int color2 = GRADIENT_COLORS[Math.min(index + 1, GRADIENT_COLORS.length - 1)];
        
        // Extract ARGB components
        int a1 = (color1 >> 24) & 0xFF;
        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;
        
        int a2 = (color2 >> 24) & 0xFF;
        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;
        
        // Interpolate
        this.alpha = (a1 + (a2 - a1) * localT) / 255.0F;
        this.rCol = (r1 + (r2 - r1) * localT) / 255.0F;
        this.gCol = (g1 + (g2 - g1) * localT) / 255.0F;
        this.bCol = (b1 + (b2 - b1) * localT) / 255.0F;
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
            return new AdvancedSilentBloodParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
