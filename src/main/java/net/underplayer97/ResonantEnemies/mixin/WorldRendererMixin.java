package net.underplayer97.ResonantEnemies.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.underplayer97.ResonantEnemies.effect.ModEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    private final MinecraftClient client;

    protected WorldRendererMixin(MinecraftClient client, BufferBuilderStorage bufferBuilders) {
        this.client = client;

    }


    @Inject(method = "renderSky*", at = @At("HEAD"))
    public void renderSky(MatrixStack matrices, Matrix4f projectionMatrix, float tickDelta, Camera camera, boolean bl, Runnable runnable, CallbackInfo ci) {
        runnable.run();
        if (!bl) {
            label59:  {
                CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
                if (cameraSubmersionType != CameraSubmersionType.POWDER_SNOW && cameraSubmersionType != CameraSubmersionType.LAVA) {
                    Entity var9 = camera.getFocusedEntity();
                    LivingEntity livingEntity = (LivingEntity)var9;

                    if (!livingEntity.hasStatusEffect(ModEffects.ENSHROUDED)) {
                        break label59;
                    }
                }

                return;
            }
        }

    }


}