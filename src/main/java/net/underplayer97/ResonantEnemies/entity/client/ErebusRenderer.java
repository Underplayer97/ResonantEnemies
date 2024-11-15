package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.boss.ErebusEntity;
import net.underplayer97.ResonantEnemies.entity.custom.DrifterEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ErebusRenderer extends GeoEntityRenderer<ErebusEntity> {

    public ErebusRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ErebusModel());
    }

    @Override
    public Identifier getTextureLocation(ErebusEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/erebus/erebus.png");
    }
}

