package com.raeyncreations.silenthill.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VenenoParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected VenenoParticle(ClientLevel level, double x, double y, double z, 
                            double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;
        this.friction = 0.91F;
        this.gravity = 0.0F;
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
        
        // Random size variation
        this.quadSize = 0.2F + this.random.nextFloat() * 0.2F;
        
        // Short lifetime like poison puff
        this.lifetime = (int)(this.random.nextFloat() * 6.0F + 2.0F);
        this.setSpriteFromAge(sprites);
        
        // Red poison color
        this.rCol = 0.52F + this.random.nextFloat() * 0.1F;
        this.gCol = 0.03F + this.random.nextFloat() * 0.02F;
        this.bCol = 0.03F + this.random.nextFloat() * 0.02F;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        
        // Shrink over time with minimum size to prevent negative values
        this.quadSize = Math.max(0.01F, this.quadSize - 0.01F);
        
        // Fade out over lifetime
        this.alpha = 1.0F - ((float)this.age / (float)this.lifetime);
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
            return new VenenoParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
