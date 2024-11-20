package net.underplayer97.ResonantEnemies.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.underplayer97.ResonantEnemies.entity.boss.EntityPart;
import net.underplayer97.ResonantEnemies.entity.boss.MultipartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    @Inject(method = "renderHitbox", at = @At("HEAD"))
    private static void renderDragonPart(MatrixStack matrices, VertexConsumer vertices, Entity entity, float tickDelta, CallbackInfo ci) {
        if (entity instanceof MultipartEntity multipartEntity) {
            double x = -MathHelper.lerp(tickDelta, entity.lastRenderX, entity.getX());
            double y = -MathHelper.lerp(tickDelta, entity.lastRenderY, entity.getY());
            double z = -MathHelper.lerp(tickDelta, entity.lastRenderZ, entity.getZ());
            EntityPart[] parts = multipartEntity.getParts();

            for (EntityPart part: parts) {
                matrices.push();
                double g = x + MathHelper.lerp(tickDelta, part.lastRenderX, entity.getX());
                double h = y + MathHelper.lerp(tickDelta, part.lastRenderY, entity.getY());
                double i = z + MathHelper.lerp(tickDelta, part.lastRenderZ, entity.getZ());
                matrices.translate(g, h, i);
                WorldRenderer.drawBox(matrices, vertices,
                        part.getBoundingBox().offset(-part.getX(), -part.getY(), -part.getZ()), 0.25F, 1.0F, 0.0F, 1.0F);
                matrices.pop();
            }

        }
    }

}
