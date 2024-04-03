package net.underplayer97.ResonantEnemies.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;

public class ModEffects {

    public static StatusEffect ENSHROUDED = new EnshroudedEffect(StatusEffectCategory.HARMFUL, 0xA9A9A9);
    public static final StatusEffect COATED = new CoatedEffect(StatusEffectCategory.HARMFUL, 0xc30010);


    public static void registerEffects() {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(ResonantMain.MOD_ID, "enshrouded"), ENSHROUDED);

        Registry.register(Registry.STATUS_EFFECT, new Identifier(ResonantMain.MOD_ID, "coated"), COATED);

    }



}
