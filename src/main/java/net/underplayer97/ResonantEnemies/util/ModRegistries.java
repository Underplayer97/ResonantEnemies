package net.underplayer97.ResonantEnemies.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.underplayer97.ResonantEnemies.entity.ModEntities;
import net.underplayer97.ResonantEnemies.entity.boss.ErebusEntity;
import net.underplayer97.ResonantEnemies.entity.custom.*;

public class ModRegistries {

    public static void registerModStuffs() {
        registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.SHAMBLER, ShamblerEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CROW, CrowEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CRAWLER, CrawlerEntity.setAttribues());
        FabricDefaultAttributeRegistry.register(ModEntities.DRIFTER, DrifterEntity.setAttribues());
        FabricDefaultAttributeRegistry.register(ModEntities.HAND, HandEntity.setAttribues());
        FabricDefaultAttributeRegistry.register(ModEntities.SPITTER, SpitterEntity.setAttribues());
        FabricDefaultAttributeRegistry.register(ModEntities.EREBUS, ErebusEntity.setAttribues());

    }


}
