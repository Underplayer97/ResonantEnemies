package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.CrawlerEntity;
import net.underplayer97.ResonantEnemies.entity.custom.DrifterEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class DrifterModel extends AnimatedGeoModel<DrifterEntity> {

    @Override
    public Identifier getModelLocation(DrifterEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/entity/drifter.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DrifterEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/drifter.png");
    }

    @Override
    public Identifier getAnimationFileLocation(DrifterEntity animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/drifter.animation.json");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void setLivingAnimations(DrifterEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * (float) Math.PI / 180f);
        }
    }

}
