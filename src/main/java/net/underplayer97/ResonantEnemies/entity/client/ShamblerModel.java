package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.ShamblerEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ShamblerModel extends AnimatedGeoModel<ShamblerEntity> {
    @Override
    public Identifier getModelLocation(ShamblerEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/shambler.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ShamblerEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/shambler/shambler.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ShamblerEntity animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/shambler.animation.json");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void setLivingAnimations(ShamblerEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("h_head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * (float) Math.PI / 180f);
        }
    }

}
