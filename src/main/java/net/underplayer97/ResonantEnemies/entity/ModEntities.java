package net.underplayer97.ResonantEnemies.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.boss.ErebusEntity;
import net.underplayer97.ResonantEnemies.entity.boss.erebus.ErebusPart;
import net.underplayer97.ResonantEnemies.entity.custom.*;
import net.underplayer97.ResonantEnemies.entity.projectile.SpitterSpitEntity;

public class ModEntities {

    public static final EntityType<ErebusPart> EREBUS_MULTIPART = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "erebus_multipart"),
            FabricEntityTypeBuilder.<ErebusPart>create(SpawnGroup.MISC, ErebusPart::new)
                    .dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build());

    public static final EntityType<SpitterSpitEntity> SPITTER_SPIT = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "spitter_spit"),
            FabricEntityTypeBuilder.<SpitterSpitEntity>create(SpawnGroup.MISC, SpitterSpitEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F,0.25F)).build());

    public static final EntityType<ShamblerEntity> SHAMBLER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "shambler"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ShamblerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f,1.9f)).build());

    public static final EntityType<ErebusEntity> EREBUS = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "erebus"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ErebusEntity::new)
                    .dimensions(EntityDimensions.fixed(7.0f,7.0f)).build());

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
                    .dimensions(EntityDimensions.fixed(1.2f,2.5f)).build());

    public static final EntityType<HandEntity> HAND = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "hand"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HandEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f,2.0f)).build());

    public static final EntityType<SpitterEntity> SPITTER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "spitter"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SpitterEntity::new)
                        .dimensions(EntityDimensions.fixed(1.0f,2.0f)).build());

}
