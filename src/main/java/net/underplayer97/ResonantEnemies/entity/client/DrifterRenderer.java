package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.CrawlerEntity;
import net.underplayer97.ResonantEnemies.entity.custom.DrifterEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DrifterRenderer extends GeoEntityRenderer<DrifterEntity> {
    public DrifterRenderer(EntityRendererFactory.Context renderManager) {

        super(renderManager, new DrifterModel());
    }

    @Override
    public Identifier getTextureLocation(DrifterEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/drifter.png");
    }
}
