package net.underplayer97.ResonantEnemies.particle.custom;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class SpitterSpitParticle extends ExplosionSmokeParticle {
    public SpitterSpitParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider);
        this.gravityStrength = 0.5F;
        this.red = 0.0f;
        this.green = 0.0f;
        this.blue = 0.0f;
    }

    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new SpitterSpitParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
