package net.underplayer97.ResonantEnemies.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.FogShape;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.underplayer97.ResonantEnemies.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static java.awt.Color.*;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    private static float red;
    private static float green;
    private static float blue;

    @Inject(method = "render", at = @At("TAIL"))
    private static void render(Camera camera, float tickDelta, ClientWorld world, int i, float f, CallbackInfo ci) {
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        Entity entity = camera.getFocusedEntity();



        float t = ((float)camera.getPos().y - (float)world.getBottomY()) * world.getLevelProperties().getHorizonShadingRatio();
        if (camera.getFocusedEntity() instanceof LivingEntity && ((LivingEntity)camera.getFocusedEntity()).hasStatusEffect(ModEffects.ENSHROUDED)) {
            int y = ((LivingEntity)camera.getFocusedEntity()).getStatusEffect(ModEffects.ENSHROUDED).getDuration();
            t = y < 20 ? 1.0f - (float)y / 20.0f : 0.0f;
        }
        if (t < 1.0f && cameraSubmersionType != CameraSubmersionType.LAVA && cameraSubmersionType != CameraSubmersionType.POWDER_SNOW) {
            if (t < 0.0f) {
                t = 0.0f;
            }
            t *= t;
            red *= t;
            green *= t;
            blue *= t;
        }
        if (f > 0.0f) {
            red = red * (1.0f - f) + red * 0.7f * f;
            green = green * (1.0f - f) + green * 0.6f * f;
            blue = blue * (1.0f - f) + blue * 0.6f * f;
        }

        RenderSystem.clearColor(red, green, blue, 0.0f);

    }


    @Inject(method = "applyFog", at = @At("TAIL"))
    private static void applyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, CallbackInfo ci) {
        float g;
        float f;
        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        Entity entity = camera.getFocusedEntity();
        FogShape fogShape = FogShape.SPHERE;

        if (entity instanceof LivingEntity && ((LivingEntity)entity).hasStatusEffect(ModEffects.ENSHROUDED)) {
            int i = ((LivingEntity)entity).getStatusEffect(ModEffects.ENSHROUDED).getDuration();
            float h = MathHelper.lerp(Math.min(1.0f, (float)i / 20.0f), viewDistance, 5.0f);
            if (fogType == BackgroundRenderer.FogType.FOG_SKY) {
                f = 0.0f;
                g = h * 0.8f;
            } else {
                f = cameraSubmersionType == CameraSubmersionType.WATER ? -4.0f : h * 0.25f;
                g = h;
            }
        } else {
            float j = MathHelper.clamp(viewDistance / 10.0f, 4.0f, 64.0f);
            f = viewDistance - j;
            g = viewDistance;
            fogShape = FogShape.CYLINDER;
        }
        RenderSystem.setShaderFogStart(f);
        RenderSystem.setShaderFogEnd(g);
        RenderSystem.setShaderFogShape(fogShape);

    }


}
