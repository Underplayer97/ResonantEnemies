package net.underplayer97.ResonantEnemies.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.VertexBuffer;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.underplayer97.ResonantEnemies.effect.ModEffects;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {

    @Shadow private @Nullable VertexBuffer darkSkyBuffer;
    @Shadow private @Nullable VertexBuffer starsBuffer;

    @Shadow protected abstract void renderEndSky(MatrixStack matrices);

    @Shadow @Final private static Identifier SUN;
    @Shadow @Final private static Identifier MOON_PHASES;
    @Nullable
    private ClientWorld world;
    private final MinecraftClient client;
    @Nullable
    private VertexBuffer lightSkyBuffer;

    protected WorldRendererMixin(MinecraftClient client, BufferBuilderStorage bufferBuilders) {
        this.client = client;

    }

    @Inject(method = "renderSky*", at = @At("TAIL"))
    public void renderSky(MatrixStack matrices, Matrix4f projectionMatrix, float tickDelta, Camera camera, boolean bl, Runnable runnable, CallbackInfo ci) {

        CameraSubmersionType cameraSubmersionType = camera.getSubmersionType();
        if (cameraSubmersionType == CameraSubmersionType.POWDER_SNOW ||
                cameraSubmersionType == CameraSubmersionType.LAVA ||
                camera.getFocusedEntity() instanceof LivingEntity livingEntity && (
                        livingEntity.hasStatusEffect(StatusEffects.BLINDNESS) ||
                        livingEntity.hasStatusEffect(ModEffects.ENSHROUDED))) {
                return;
        }
        RenderSystem.disableTexture();

    }

}