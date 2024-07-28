package net.underplayer97.ResonantEnemies.blocks.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.blocks.ModBlocks;

public class ModBlockEntities {

    public static BlockEntityType<EndPortalSlabEntity> END_PORTAL_SLAB_BLOCKENTITY;

    public static void registerBlockEntities() {
        END_PORTAL_SLAB_BLOCKENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "end_portal_slab"),
                FabricBlockEntityTypeBuilder.create(EndPortalSlabEntity::new, ModBlocks.END_PORTAL_SLAB).build(null));
    }


}
