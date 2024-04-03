package net.underplayer97.ResonantEnemies.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;

public class ModParticles {

    public static final DefaultParticleType ENSHROUDED_PARTICLE = FabricParticleTypes.simple();
    public static final DefaultParticleType COATED_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticle() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(ResonantMain.MOD_ID, "enshrouded_particle"),
                ENSHROUDED_PARTICLE);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(ResonantMain.MOD_ID, "coated_particle"),
                COATED_PARTICLE);
    }

}
