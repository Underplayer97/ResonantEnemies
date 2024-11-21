package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.boss.ErebusEntity;
import net.underplayer97.ResonantEnemies.entity.boss.GrimsleyEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class GrimsleyRenderer extends GeoEntityRenderer<GrimsleyEntity> {

    public GrimsleyRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GrimsleyModel());
    }

    @Override
    public Identifier getTextureLocation(GrimsleyEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/grimsley.png");
    }
}

