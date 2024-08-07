package net.underplayer97.ResonantEnemies;

import net.fabricmc.api.ModInitializer;
import net.underplayer97.ResonantEnemies.blocks.ModBlocks;
import net.underplayer97.ResonantEnemies.blocks.entity.ModBlockEntities;
import net.underplayer97.ResonantEnemies.effect.ModEffects;
import net.underplayer97.ResonantEnemies.item.ModItems;
import net.underplayer97.ResonantEnemies.particle.ModParticles;
import net.underplayer97.ResonantEnemies.sound.ModSounds;
import net.underplayer97.ResonantEnemies.util.ModRegistries;
import net.underplayer97.ResonantEnemies.world.gen.ModWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

import java.util.Random;
import java.util.random.RandomGenerator;

public class ResonantMain implements ModInitializer {

	public static final String MOD_ID = "resonantenemies";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Random RANDOM = new Random();

	@Override
	public void onInitialize() {

		LOGGER.info("Resonant Enemies: ONLINE!");

		ModRegistries.registerModStuffs();
		ModItems.registerModItems();
		ModSounds.registerModSounds();
		ModEffects.registerEffects();
		ModParticles.registerParticle();
		ModBlockEntities.registerBlockEntities();
		ModBlocks.registerModBlocks();

		ModWorldGen.generateModWorldGen();

		GeckoLib.initialize();

	}
}
