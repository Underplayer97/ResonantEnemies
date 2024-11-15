package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.boss.ErebusEntity;
import net.underplayer97.ResonantEnemies.entity.custom.DrifterEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ErebusModel extends AnimatedGeoModel<ErebusEntity> {

    @Override
    public Identifier getModelLocation(ErebusEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/entity/erebus.geo.json");
    }

    @Override
    public Identifier getTextureLocation(ErebusEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/erebus/erebus.png");
    }

    @Override
    public Identifier getAnimationFileLocation(ErebusEntity animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/armor.animation.json");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void setLivingAnimations(ErebusEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Hat");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * (float) Math.PI / 180f);
        }
    }

}
