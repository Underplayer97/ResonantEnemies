package net.underplayer97.ResonantEnemies.blocks.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.blocks.ModBlocks;

public class ModBlockEntities {

    public static BlockEntityType<ResonantPortalBlockEntity> RESONANT_PORTAL_BLOCKENTITY;

    public static void registerBlockEntities() {
        RESONANT_PORTAL_BLOCKENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(ResonantMain.MOD_ID, "resonant_portal_block"),
                FabricBlockEntityTypeBuilder.create(ResonantPortalBlockEntity::new, ModBlocks.RESONANT_PORTAL_BLOCK).build(null));
    }


}
