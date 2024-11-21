package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.boss.AmalgamateEntity;
import net.underplayer97.ResonantEnemies.entity.boss.GrimsleyEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AmalgamateRenderer extends GeoEntityRenderer<AmalgamateEntity> {

    public AmalgamateRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new AmalgamateModel());
    }

    @Override
    public Identifier getTextureLocation(AmalgamateEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/amalgamate.png");
    }
}

