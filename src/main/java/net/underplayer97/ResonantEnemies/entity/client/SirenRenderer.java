package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.CrowEntity;
import net.underplayer97.ResonantEnemies.entity.custom.SirenEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

public class SirenRenderer extends GeoEntityRenderer<SirenEntity> {
    public SirenRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SirenModel());

    }

    @Override
    public Identifier getTextureLocation(SirenEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/crow/crow.png");
    }
    
}
