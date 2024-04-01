package net.underplayer97.ResonantEnemies.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.minecraft.sound.SoundEvents;

public class ModSounds {

    public static SoundEvent SHAMBLER_AMBIENT = registerSoundEvent("shambler_ambient");
    public static SoundEvent SHAMBLER_ATTACK = registerSoundEvent("shambler_attack");
    public static SoundEvent SHAMBLER_DEATH = registerSoundEvent("shambler_death");
    public static SoundEvent SHAMBLER_HURT = registerSoundEvent("shambler_hurt");

    //Shambler voices is by Jonono_ <-- Silly guy

    public static SoundEvent MUSIC_DISC_EYE_OF_THREE = registerSoundEvent("music_disc_eye_of_three"); //Made by HandfulJake

    public static SoundEvent CROW_AMBIENT = registerSoundEvent("crow_ambient");

    public static SoundEvent CROW_DEATH = registerSoundEvent("crow_death");

    public static SoundEvent CROW_HURT = registerSoundEvent("crow_hurt");

    public static SoundEvent CROW_HOLA = registerSoundEvent("crow_hola");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(ResonantMain.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void initBecauseThingDontWannaWork() {

    }

}
