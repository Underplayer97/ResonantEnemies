package net.underplayer97.ResonantEnemies.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.CrawlerEntity;
import net.underplayer97.ResonantEnemies.entity.custom.CrowEntity;
import net.underplayer97.ResonantEnemies.entity.custom.DrifterEntity;
import net.underplayer97.ResonantEnemies.entity.custom.ShamblerEntity;

public class ModEntities {

    public static final EntityType<ShamblerEntity> SHAMBLER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "shambler"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ShamblerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f,1.9f)).build());

    public static final EntityType<CrowEntity> CROW = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "crow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f,0.6f)).build());

    public static final EntityType<CrawlerEntity> CRAWLER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "crawler"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrawlerEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f,0.9f)).build());

    public static final EntityType<DrifterEntity> DRIFTER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "drifter"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DrifterEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f,0.9f)).build());

}
