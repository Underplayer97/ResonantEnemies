package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import net.underplayer97.ResonantEnemies.entity.projectile.SpitterSpitEntity;

public class SpitterSpitModel<T extends Entity> extends SinglePartEntityModel<T> {

    private static final String MAIN = "main";
    private final ModelPart root;

    public SpitterSpitModel(ModelPart root) {
        this.root = root;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        //int i = true;
        modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F).cuboid(0.0F, -4.0F, 0.0F, 2.0F, 2.0F, 2.0F).cuboid(0.0F, 0.0F, -4.0F, 2.0F, 2.0F, 2.0F).cuboid(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F).cuboid(2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F).cuboid(0.0F, 2.0F, 0.0F, 2.0F, 2.0F, 2.0F).cuboid(0.0F, 0.0F, 2.0F, 2.0F, 2.0F, 2.0F), ModelTransform.NONE);
        return TexturedModelData.of(modelData, 64, 32);
    }

    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }

    public ModelPart getPart() {
        return this.root;
    }

}
