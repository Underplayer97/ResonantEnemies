package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.HandEntity;
import net.underplayer97.ResonantEnemies.entity.custom.SpitterEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SpitterRenderer extends GeoEntityRenderer<SpitterEntity> {
    public SpitterRenderer(EntityRendererFactory.Context renderManager) {

        super(renderManager, new SpitterModel());
    }

    @Override
    public Identifier getTextureLocation(SpitterEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/spitter.png");
    }
}
