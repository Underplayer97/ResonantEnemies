package net.underplayer97.ResonantEnemies;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.underplayer97.ResonantEnemies.entity.ModEntities;
import net.underplayer97.ResonantEnemies.entity.client.ShamblerRenderer;
import net.underplayer97.ResonantEnemies.entity.client.armor.AegisLegsArmorRenderer;
import net.underplayer97.ResonantEnemies.entity.client.armor.BlackHatArmorRenderer;
import net.underplayer97.ResonantEnemies.entity.client.armor.PurpleHatArmorRenderer;
import net.underplayer97.ResonantEnemies.item.ModItems;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ResonantClientMain implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.SHAMBLER, ShamblerRenderer::new);

        GeoArmorRenderer.registerArmorRenderer(new PurpleHatArmorRenderer(), ModItems.PURPLE_TOPHAT);
        GeoArmorRenderer.registerArmorRenderer(new BlackHatArmorRenderer(), ModItems.BLACK_TOPHAT);
        GeoArmorRenderer.registerArmorRenderer(new AegisLegsArmorRenderer(), ModItems.AEGIS_LEGS);


    }
}
