package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.DrifterEntity;
import net.underplayer97.ResonantEnemies.entity.custom.HandEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class HandRenderer extends GeoEntityRenderer<HandEntity> {
    public HandRenderer(EntityRendererFactory.Context renderManager) {

        super(renderManager, new HandModel());
    }

    @Override
    public Identifier getTextureLocation(HandEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/hand.png");
    }
}
