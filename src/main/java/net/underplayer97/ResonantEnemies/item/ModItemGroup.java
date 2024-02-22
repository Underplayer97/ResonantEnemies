package net.underplayer97.ResonantEnemies.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;

public class ModItemGroup {

    public static final ItemGroup RESONANCE = FabricItemGroupBuilder.build(new Identifier(ResonantMain.MOD_ID, "resonance"),
            () -> new ItemStack(ModItems.MUSIC_DISC_EYE_OF_THREE));
}
