package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.ShamblerEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ShamblerRenderer extends GeoEntityRenderer<ShamblerEntity> {
    public ShamblerRenderer(EntityRendererFactory.Context renderManager) {

        super(renderManager, new ShamblerModel());
    }

    @Override
    public Identifier getTextureLocation(ShamblerEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/shambler/shambler.png");
    }

    //Model and textures made by Grim_Player <-- Silly guy

}
