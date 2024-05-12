package net.underplayer97.ResonantEnemies.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.ModEntities;
import net.underplayer97.ResonantEnemies.item.custom.*;
import net.underplayer97.ResonantEnemies.sound.ModSounds;

import static software.bernie.example.registry.RegistryUtils.registerItem;

public class ModItems {

    public static final Item SHAMBLER_SPAWN_EGG = registerItem("shambler_spawn_egg",
            new SpawnEggItem(ModEntities.SHAMBLER,44975, 0x65000b,
                    new FabricItemSettings().group(ModItemGroup.RESONANCE).maxCount(64)));

    public static final Item CROW_SPAWN_EGG = registerItem("crow_spawn_egg",
            new SpawnEggItem(ModEntities.CROW,0x090909, 0xfcd720,
                    new FabricItemSettings().group(ModItemGroup.RESONANCE).maxCount(64)));

    public static final Item MUSIC_DISC_EYE_OF_THREE = registerItem("music_disc_eye_of_three",
            new ModMusicDiscItem(7, ModSounds.MUSIC_DISC_EYE_OF_THREE,
                    new FabricItemSettings().rarity(Rarity.RARE).group(ModItemGroup.RESONANCE).maxCount(1)));

    public static final Item PURPLE_TOPHAT = registerItem("purple_tophat",
            new PurpleHatArmorItem(ModArmorMaterials.DECORATIVE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.RESONANCE)));

    public static final Item BLACK_TOPHAT = registerItem("black_tophat",
            new BlackHatArmorItem(ModArmorMaterials.DECORATIVE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.RESONANCE)));

    public static final Item AEGIS_LEGS = registerItem("aegis_legs",
            new AegisLegsArmorItem(ModArmorMaterials.DECORATIVE, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.RESONANCE)));

    public static final Item WOUNDED_AEGIS_LEGS = registerItem("wounded_aegis_legs",
            new WoundedAegisLegsArmorItem(ModArmorMaterials.DECORATIVE, EquipmentSlot.CHEST,
                    new FabricItemSettings().group(ModItemGroup.RESONANCE)));

    public static final Item NIMHSY_EARS = registerItem("nimhsy_ears",
            new NimhsyEarsArmorItem(ModArmorMaterials.DECORATIVE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.RESONANCE)));

    public static final Item CROW_ARMOR = registerItem("crow_armor",
            new CrowArmorItem(ModArmorMaterials.DECORATIVE, EquipmentSlot.HEAD,
                    new FabricItemSettings().group(ModItemGroup.RESONANCE)));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ResonantMain.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ResonantMain.LOGGER.info("Registering Mod Items for " + ResonantMain.MOD_ID);
    }

}
