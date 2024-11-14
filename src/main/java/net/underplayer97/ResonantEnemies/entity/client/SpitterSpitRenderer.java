package net.underplayer97.ResonantEnemies.entity.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LlamaSpitEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.LlamaSpitEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.entity.custom.SpitterEntity;
import net.underplayer97.ResonantEnemies.entity.projectile.SpitterSpitEntity;

public class SpitterSpitRenderer extends EntityRenderer<SpitterSpitEntity> {

    private static final Identifier TEXTURE = new Identifier("textures/entity/spit.png");
    private final SpitterSpitModel<SpitterSpitEntity> model;

    public SpitterSpitRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new SpitterSpitModel(ctx.getPart(EntityModelLayers.LLAMA_SPIT));
    }

    public void render(SpitterSpitEntity spitterSpitEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0, 0.15000000596046448, 0.0);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(g, spitterSpitEntity.prevYaw, spitterSpitEntity.getYaw()) - 90.0F));
        matrixStack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(g, spitterSpitEntity.prevPitch, spitterSpitEntity.getPitch())));
        this.model.setAngles(spitterSpitEntity, g, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(TEXTURE));
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        super.render(spitterSpitEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(SpitterSpitEntity entity) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/spit.png");
    }

}
