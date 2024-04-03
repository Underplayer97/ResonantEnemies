package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.SirenEntity;
import net.underplayer97.ResonantEnemies.entity.custom.WendigoEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class WendigoModel extends AnimatedGeoModel<WendigoEntity> {
    @Override
    public Identifier getModelLocation(WendigoEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/entity/crow.geo.json");
    }

    @Override
    public Identifier getTextureLocation(WendigoEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/crow/crow.png");
    }

    @Override
    public Identifier getAnimationFileLocation(WendigoEntity animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/crow.animation.json");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void setLivingAnimations(WendigoEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * (float) Math.PI / 180f);
        }
    }

}
