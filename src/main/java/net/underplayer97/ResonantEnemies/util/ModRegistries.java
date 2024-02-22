package net.underplayer97.ResonantEnemies.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.underplayer97.ResonantEnemies.entity.ModEntities;
import net.underplayer97.ResonantEnemies.entity.custom.ShamblerEntity;

public class ModRegistries {

    public static void registerModStuffs() {
        registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.SHAMBLER, ShamblerEntity.setAttributes());
    }


}
