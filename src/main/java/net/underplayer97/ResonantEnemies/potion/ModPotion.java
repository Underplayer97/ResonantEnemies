package net.underplayer97.ResonantEnemies.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.effect.ModEffects;

public class ModPotion {
    public static void registerPotion () {
        Registry.register(Registry.POTION, new Identifier(ResonantMain.MOD_ID, "crimson_potion"),
                new Potion(new StatusEffectInstance(ModEffects.COATED, 2500, 0)));
    }


}
