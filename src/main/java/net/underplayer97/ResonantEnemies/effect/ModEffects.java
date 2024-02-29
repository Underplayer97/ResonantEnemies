package net.underplayer97.ResonantEnemies.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;

public class ModEffects {

    public static StatusEffect ENSHROUDED;

    public static StatusEffect registerStatusEffect(String name) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(ResonantMain.MOD_ID, name),
                new EnshroudedEffect(StatusEffectCategory.HARMFUL, 0xA9A9A9));
    }

    public static void registerEffects() {
        ENSHROUDED = registerStatusEffect("enshrouded");
    }


}
