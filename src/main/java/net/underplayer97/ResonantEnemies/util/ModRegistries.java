package net.underplayer97.ResonantEnemies.util;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.underplayer97.ResonantEnemies.entity.ModEntities;
import net.underplayer97.ResonantEnemies.entity.boss.EntityPart;
import net.underplayer97.ResonantEnemies.entity.boss.ErebusEntity;
import net.underplayer97.ResonantEnemies.entity.boss.MultipartEntity;
import net.underplayer97.ResonantEnemies.entity.custom.*;
import net.underplayer97.ResonantEnemies.entity.util.WorldMultipartHelper;

public class ModRegistries {

    public static void registerModStuffs() {
        registerAttributes();
        registerEntityRegistry();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.SHAMBLER, ShamblerEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CROW, CrowEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CRAWLER, CrawlerEntity.setAttribues());
        FabricDefaultAttributeRegistry.register(ModEntities.DRIFTER, DrifterEntity.setAttribues());
        FabricDefaultAttributeRegistry.register(ModEntities.HAND, HandEntity.setAttribues());
        FabricDefaultAttributeRegistry.register(ModEntities.SPITTER, SpitterEntity.setAttribues());
        FabricDefaultAttributeRegistry.register(ModEntities.EREBUS, ErebusEntity.setAttributes());

    }

    private static void registerEntityRegistry() {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof MultipartEntity multipartEntity) {
                Int2ObjectMap<EntityPart> partMap = ((WorldMultipartHelper)world).getPMEPartMap();
                for (EntityPart part: multipartEntity.getParts()) partMap.put(part.getId(), part);
            }});
        ServerEntityEvents.ENTITY_UNLOAD.register((entity, world) -> {
            if (entity instanceof MultipartEntity multipartEntity) {
                Int2ObjectMap<EntityPart> partMap = ((WorldMultipartHelper)world).getPMEPartMap();
                for (EntityPart part: multipartEntity.getParts()) partMap.remove(part.getId());
            }});
    }


}
