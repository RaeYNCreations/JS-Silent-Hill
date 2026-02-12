package com.raeyncreations.silenthill.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SilentBloodParticle extends TextureSheetParticle {
    private final SpriteSet sprites;

    protected SilentBloodParticle(ClientLevel level, double x, double y, double z, 
                                  double xSpeed, double ySpeed, double zSpeed, SpriteSet sprites) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed);
        this.sprites = sprites;
        this.friction = 0.6F;
        this.gravity = 0.8F;
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;
        this.quadSize = 0.2F;
        this.lifetime = 25;
        this.setSpriteFromAge(sprites);
        
        // Dark red blood color with alpha fade
        this.rCol = 0.35F + this.random.nextFloat() * 0.1F;
        this.gCol = 0.08F + this.random.nextFloat() * 0.05F;
        this.bCol = 0.08F + this.random.nextFloat() * 0.05F;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
        
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
            return new SilentBloodParticle(level, x, y, z, xSpeed, ySpeed, zSpeed, this.sprites);
        }
    }
}
