package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.CrowEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.layer.LayerGlowingAreasGeo;

public class CrowRenderer extends GeoEntityRenderer<CrowEntity> {
    public CrowRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CrowModel());

        this.addLayer(new LayerGlowingAreasGeo<>(this,
                crowEntity -> getGeoModelProvider().getTextureLocation(crowEntity),
                crowEntity -> getGeoModelProvider().getModelLocation(crowEntity),
                RenderLayer::getEyes));

    }

    @Override
    public Identifier getTextureLocation(CrowEntity instance) {
        return new Identifier(ResonantMain.MOD_ID, "textures/models/armor/playercrow.png");
    }

    @Override
    protected float getSwingMotionAnimThreshold() {
        return 0.01f;
    }
}
