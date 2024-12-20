package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.DrifterEntity;
import net.underplayer97.ResonantEnemies.entity.custom.HandEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class HandModel extends AnimatedGeoModel<HandEntity> {

    @Override
    public Identifier getModelLocation(HandEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/entity/hand.geo.json");
    }

    @Override
    public Identifier getTextureLocation(HandEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/hand.png");
    }

    @Override
    public Identifier getAnimationFileLocation(HandEntity animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/hand.animation.json");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void setLivingAnimations(HandEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("FullModel");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * (float) Math.PI / 180f);
        }
    }

}
