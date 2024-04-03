package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.ButchererEntity;
import net.underplayer97.ResonantEnemies.entity.custom.SirenEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ButchererRenderer extends GeoEntityRenderer<ButchererEntity> {
    public ButchererRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new ButchererModel());

    }

    @Override
    public Identifier getTextureLocation(ButchererEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/crow/crow.png");
    }
    
}
