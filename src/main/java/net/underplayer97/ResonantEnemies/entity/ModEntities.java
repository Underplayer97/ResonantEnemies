package net.underplayer97.ResonantEnemies.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.ShamblerEntity;

public class ModEntities {

    public static final EntityType<ShamblerEntity> SHAMBLER = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "shambler"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ShamblerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f,1.9f)).build());


}
