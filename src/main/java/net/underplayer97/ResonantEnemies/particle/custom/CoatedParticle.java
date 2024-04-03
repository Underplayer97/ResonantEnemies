package net.underplayer97.ResonantEnemies.particle.custom;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class CoatedParticle extends SpriteBillboardParticle {

    private final SpriteProvider spriteProvider;

    protected CoatedParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.maxAge = 4;
        this.gravityStrength = 0.008f;
        this.scale = 0.1f * (this.random.nextFloat() * 0.2f + 0.5f);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    @Override
    public float getSize(float tickDelta) {
        float f = ((float)this.age + tickDelta) / (float)this.maxAge;
        f = 1.0f - f;
        f *= f;
        f = 1.0f - f;
        return this.scale * f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getBrightness(float tint) {
        int i = super.getBrightness(tint);
        float f = (float)this.age / (float)this.maxAge;
        f *= f;
        f *= f;
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        if ((k += (int)(f * 15.0f * 16.0f)) > 240) {
            k = 240;
        }
        return j | k << 16;
    }

    @Environment(value= EnvType.CLIENT)
    public static class Factory
            implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new CoatedParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }

}
