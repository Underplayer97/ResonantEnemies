package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.boss.AmalgamateEntity;
import net.underplayer97.ResonantEnemies.entity.boss.GrimsleyEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class AmalgamateModel extends AnimatedGeoModel<AmalgamateEntity> {

    @Override
    public Identifier getModelLocation(AmalgamateEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/entity/amalgamate.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AmalgamateEntity object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/amalgamate.png");
    }

    @Override
    public Identifier getAnimationFileLocation(AmalgamateEntity animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/amalgamate.animation.json");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public void setLivingAnimations(AmalgamateEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("Head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * (float) Math.PI / 180f);
        }
    }

}
