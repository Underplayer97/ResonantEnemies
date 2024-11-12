package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.CrawlerEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class CrawlerRenderer extends GeoEntityRenderer<CrawlerEntity> {
    public CrawlerRenderer(EntityRendererFactory.Context renderManager) {

        super(renderManager, new CrawlerModel());
    }

    @Override
    public Identifier getTextureLocation(CrawlerEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/crawler.png");
    }
}
