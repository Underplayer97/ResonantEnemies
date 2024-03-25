package net.underplayer97.ResonantEnemies;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.underplayer97.ResonantEnemies.client.renderer.ModBackgroundRenderer;
import net.underplayer97.ResonantEnemies.entity.ModEntities;
import net.underplayer97.ResonantEnemies.entity.client.CrowRenderer;
import net.underplayer97.ResonantEnemies.entity.client.ShamblerRenderer;
import net.underplayer97.ResonantEnemies.entity.client.armor.BlackHatArmorRenderer;
import net.underplayer97.ResonantEnemies.entity.client.armor.PurpleHatArmorRenderer;
import net.underplayer97.ResonantEnemies.item.ModItems;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ResonantClientMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.SHAMBLER, ShamblerRenderer::new);
        EntityRendererRegistry.register(ModEntities.CROW, CrowRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(new PurpleHatArmorRenderer(), ModItems.PURPLE_TOPHAT);
        GeoArmorRenderer.registerArmorRenderer(new BlackHatArmorRenderer(), ModItems.BLACK_TOPHAT);



    }
}
