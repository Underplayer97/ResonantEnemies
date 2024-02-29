package net.underplayer97.ResonantEnemies;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.registry.Registry;
import net.underplayer97.ResonantEnemies.effect.ModEffects;
import net.underplayer97.ResonantEnemies.item.ModItems;
import net.underplayer97.ResonantEnemies.sound.ModSounds;
import net.underplayer97.ResonantEnemies.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class ResonantMain implements ModInitializer {

	public static final String MOD_ID = "resonantenemies";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Resonant Enemies: ONLINE!");

		ModRegistries.registerModStuffs();

		ModItems.registerModItems();

		GeckoLib.initialize();

		ModSounds.initBecauseThingDontWannaWork();

		ModEffects.registerEffects();

	}
}
