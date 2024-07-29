package net.underplayer97.ResonantEnemies.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.blocks.custom.ResonantPortalBlock;
import net.underplayer97.ResonantEnemies.item.ModItemGroup;

public class ModBlocks {

    public static final Block RESONANT_PORTAL_BLOCK = registerBlock("resonant_portal_block",
            new ResonantPortalBlock(FabricBlockSettings.of(Material.PORTAL, MapColor.BLACK).noCollision().luminance(15)
                    .strength(-1.0F, 3600000).dropsNothing()), ModItemGroup.RESONANCE);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(ResonantMain.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group) {
        return Registry.register(Registry.ITEM, new Identifier(ResonantMain.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }


    public static void registerModBlocks() {

    }

}
