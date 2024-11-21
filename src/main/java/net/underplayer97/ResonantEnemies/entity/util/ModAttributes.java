package net.underplayer97.ResonantEnemies.entity.util;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.underplayer97.ResonantEnemies.ResonantMain;

public class ModAttributes {

    public static final EntityAttribute EREBUS_PRIMARY_ATTACK_COOLDOWN =
            register("attribute.name.primary_attack_cooldown", 0, 2048, 0, true);

    public static final EntityAttribute EREBUS_SPECIAL_ATTACK_COOLDOWN =
            register("attribute.name.special_attack_cooldown", 0, 2048, 0, true);


    private static EntityAttribute register(String id, float min, float max, float fallback, boolean tracked) {
        //return (EntityAttribute)Registry.register(Registry.ATTRIBUTE, id, attribute);
        return Registry.register(Registry.ATTRIBUTE, ResonantMain.MOD_ID + (id), new ClampedEntityAttribute(
                "attribute.name." + id, fallback, min, max).setTracked(tracked));
    }




}
