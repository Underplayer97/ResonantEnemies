package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.ButchererEntity;
import net.underplayer97.ResonantEnemies.entity.custom.WendigoEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ButchererModel extends AnimatedGeoModel<ButchererEntity> {
    @Override
    public Identifier getModelLocation(ButchererEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/entity/crow.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ButchererEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/crow/crow.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ButchererEntity animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/crow.animation.json");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void setLivingAnimations(ButchererEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * (float) Math.PI / 180f);
        }
    }

}
