package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.SirenEntity;
import net.underplayer97.ResonantEnemies.entity.custom.WendigoEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WendigoRenderer extends GeoEntityRenderer<WendigoEntity> {
    public WendigoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new WendigoModel());

    }

    @Override
    public Identifier getTextureLocation(WendigoEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/crow/crow.png");
    }
    
}
