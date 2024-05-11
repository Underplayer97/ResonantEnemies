package net.underplayer97.ResonantEnemies;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.underplayer97.ResonantEnemies.entity.ModEntities;
import net.underplayer97.ResonantEnemies.entity.client.CrowRenderer;
import net.underplayer97.ResonantEnemies.entity.client.ShamblerRenderer;
import net.underplayer97.ResonantEnemies.entity.client.armor.*;
import net.underplayer97.ResonantEnemies.item.ModItems;
import net.underplayer97.ResonantEnemies.particle.ModParticles;
import net.underplayer97.ResonantEnemies.particle.custom.CoatedParticle;
import net.underplayer97.ResonantEnemies.particle.custom.EnshroudedParticle;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ResonantClientMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.SHAMBLER, ShamblerRenderer::new);
        EntityRendererRegistry.register(ModEntities.CROW, CrowRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(new PurpleHatArmorRenderer(), ModItems.PURPLE_TOPHAT);
        GeoArmorRenderer.registerArmorRenderer(new BlackHatArmorRenderer(), ModItems.BLACK_TOPHAT);
        GeoArmorRenderer.registerArmorRenderer(new AegisLegsArmorRenderer(), ModItems.AEGIS_LEGS);
        GeoArmorRenderer.registerArmorRenderer(new WoundedAegisLegsArmorRenderer(), ModItems.WOUNDED_AEGIS_LEGS);
        GeoArmorRenderer.registerArmorRenderer(new CrowArmorRenderer(), ModItems.CROW_ARMOR);

        ParticleFactoryRegistry.getInstance().register(ModParticles.ENSHROUDED_PARTICLE, EnshroudedParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.COATED_PARTICLE, CoatedParticle.Factory::new);

    }
}
