package net.underplayer97.ResonantEnemies.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.underplayer97.ResonantEnemies.entity.ModEntities;

public class ModEntitySpawn {

    public static void addEntitySpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.PLAINS, Biome.Category.ICY, Biome.Category.DESERT, Biome.Category.FOREST,
                        Biome.Category.SAVANNA),
                SpawnGroup.MONSTER, ModEntities.SHAMBLER, 35, 1, 3);

        SpawnRestrictionAccessor.callRegister(ModEntities.SHAMBLER, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HostileEntity::canSpawnInDark);

    }

}
