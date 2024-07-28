package net.underplayer97.ResonantEnemies;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.underplayer97.ResonantEnemies.blocks.ModBlocks;
import net.underplayer97.ResonantEnemies.blocks.custom.EndPortalSlab;
import net.underplayer97.ResonantEnemies.blocks.entity.EndPortalSlabEntityRenderer;
import net.underplayer97.ResonantEnemies.blocks.entity.ModBlockEntities;
import net.underplayer97.ResonantEnemies.client.ModRenderLayers;
import net.underplayer97.ResonantEnemies.entity.ModEntities;
import net.underplayer97.ResonantEnemies.entity.client.CrowRenderer;
import net.underplayer97.ResonantEnemies.entity.client.ShamblerRenderer;
import net.underplayer97.ResonantEnemies.entity.client.armor.*;
import net.underplayer97.ResonantEnemies.item.ModItems;
import net.underplayer97.ResonantEnemies.network.screenshake.PositionedScreenshakePacket;
import net.underplayer97.ResonantEnemies.network.screenshake.ScreenshakePacket;
import net.underplayer97.ResonantEnemies.particle.ModParticles;
import net.underplayer97.ResonantEnemies.particle.custom.CoatedParticle;
import net.underplayer97.ResonantEnemies.particle.custom.EnshroudedParticle;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ResonantClientMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.SHAMBLER, ShamblerRenderer::new);
        EntityRendererRegistry.register(ModEntities.CROW, CrowRenderer::new);

        BlockEntityRendererRegistry.register(ModBlockEntities.END_PORTAL_SLAB_BLOCKENTITY, EndPortalSlabEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.END_PORTAL_SLAB, ModRenderLayers.getPortalSlab());

        GeoArmorRenderer.registerArmorRenderer(new PurpleHatArmorRenderer(), ModItems.PURPLE_TOPHAT);
        GeoArmorRenderer.registerArmorRenderer(new BlackHatArmorRenderer(), ModItems.BLACK_TOPHAT);
        GeoArmorRenderer.registerArmorRenderer(new AegisLegsArmorRenderer(), ModItems.AEGIS_LEGS);
        GeoArmorRenderer.registerArmorRenderer(new WoundedAegisLegsArmorRenderer(), ModItems.WOUNDED_AEGIS_LEGS);
        GeoArmorRenderer.registerArmorRenderer(new CrowArmorRenderer(), ModItems.CROW_ARMOR);
        GeoArmorRenderer.registerArmorRenderer(new NimhsyEarsArmorRenderer(), ModItems.NIMHSY_EARS);


        ParticleFactoryRegistry.getInstance().register(ModParticles.ENSHROUDED_PARTICLE, EnshroudedParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticles.COATED_PARTICLE, CoatedParticle.Factory::new);

        ClientPlayNetworking.registerGlobalReceiver(ScreenshakePacket.ID, (client, handler, buf, responseSender) -> new ScreenshakePacket(buf).apply(client.getNetworkHandler()));
        ClientPlayNetworking.registerGlobalReceiver(PositionedScreenshakePacket.ID, (client, handler, buf, responseSender) -> PositionedScreenshakePacket.fromBuf(buf).apply(client.getNetworkHandler()));

    }
}
