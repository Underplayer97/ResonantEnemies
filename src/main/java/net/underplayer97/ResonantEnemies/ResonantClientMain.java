package net.underplayer97.ResonantEnemies;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.underplayer97.ResonantEnemies.blocks.ModBlocks;
import net.underplayer97.ResonantEnemies.blocks.entity.ResonantPortalBlockEntityRenderer;
import net.underplayer97.ResonantEnemies.blocks.entity.ModBlockEntities;
import net.underplayer97.ResonantEnemies.client.ModRenderLayers;
import net.underplayer97.ResonantEnemies.configs.ResonantConfig;
import net.underplayer97.ResonantEnemies.entity.ModEntities;
import net.underplayer97.ResonantEnemies.entity.client.*;
import net.underplayer97.ResonantEnemies.entity.client.armor.*;
import net.underplayer97.ResonantEnemies.item.ModItems;
import net.underplayer97.ResonantEnemies.network.screenshake.PositionedScreenshakePacket;
import net.underplayer97.ResonantEnemies.network.screenshake.ScreenshakePacket;
import net.underplayer97.ResonantEnemies.particle.ModParticles;
import net.underplayer97.ResonantEnemies.particle.custom.CoatedParticle;
import net.underplayer97.ResonantEnemies.particle.custom.EnshroudedParticle;
import net.underplayer97.ResonantEnemies.particle.custom.SpitterSpitParticle;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ResonantClientMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Config Impl
        ResonantConfig.init(ResonantMain.MOD_ID, ResonantConfig.class);

        //Entity Impl
        EntityRendererRegistry.register(ModEntities.SHAMBLER, ShamblerRenderer::new);
        EntityRendererRegistry.register(ModEntities.CROW, CrowRenderer::new);
        EntityRendererRegistry.register(ModEntities.CRAWLER, CrawlerRenderer::new);
        EntityRendererRegistry.register(ModEntities.DRIFTER, DrifterRenderer::new);
        EntityRendererRegistry.register(ModEntities.HAND, HandRenderer::new);
        EntityRendererRegistry.register(ModEntities.SPITTER, SpitterRenderer::new);
        EntityRendererRegistry.register(ModEntities.SPITTER_SPIT, SpitterSpitRenderer::new);
        EntityRendererRegistry.register(ModEntities.EREBUS, ErebusRenderer::new);


        //Block Entity Impl
        BlockEntityRendererRegistry.register(ModBlockEntities.RESONANT_PORTAL_BLOCKENTITY, ResonantPortalBlockEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.RESONANT_PORTAL_BLOCK, ModRenderLayers.getPortalBlock());

        //Armor Impl
        GeoArmorRenderer.registerArmorRenderer(new PurpleHatArmorRenderer(), ModItems.PURPLE_TOPHAT);
        GeoArmorRenderer.registerArmorRenderer(new BlackHatArmorRenderer(), ModItems.BLACK_TOPHAT);
        GeoArmorRenderer.registerArmorRenderer(new AegisLegsArmorRenderer(), ModItems.AEGIS_LEGS);
        GeoArmorRenderer.registerArmorRenderer(new WoundedAegisLegsArmorRenderer(), ModItems.WOUNDED_AEGIS_LEGS);
        GeoArmorRenderer.registerArmorRenderer(new CrowArmorRenderer(), ModItems.CROW_ARMOR);
        GeoArmorRenderer.registerArmorRenderer(new NimhsyEarsArmorRenderer(), ModItems.NIMHSY_EARS);

        //Particle Impl
        ParticleFactoryRegistry.getInstance().register(ModParticles.ENSHROUDED_PARTICLE, EnshroudedParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.COATED_PARTICLE, CoatedParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.SPITTER_SPIT_PARTICLE, SpitterSpitParticle.Factory::new);

        //Screenshake
        ClientPlayNetworking.registerGlobalReceiver(ScreenshakePacket.ID, (client, handler, buf, responseSender) -> new ScreenshakePacket(buf).apply(client.getNetworkHandler()));
        ClientPlayNetworking.registerGlobalReceiver(PositionedScreenshakePacket.ID, (client, handler, buf, responseSender) -> PositionedScreenshakePacket.fromBuf(buf).apply(client.getNetworkHandler()));

    }
}
