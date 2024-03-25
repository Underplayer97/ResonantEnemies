package net.underplayer97.ResonantEnemies.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.MathHelper;
import net.underplayer97.ResonantEnemies.effect.ModEffects;

@Environment(value= EnvType.CLIENT)
public class ModBackgroundRenderer extends BackgroundRenderer {

    public static void render(Camera camera, float tickDelta, ClientWorld world, int i2, float f) {

        float t = ((float)camera.getPos().y - (float)world.getBottomY()) * world.getLevelProperties().getHorizonShadingRatio();
        if (camera.getFocusedEntity() instanceof LivingEntity && ((LivingEntity)camera.getFocusedEntity()).hasStatusEffect(StatusEffects.BLINDNESS)) {
            int y = ((LivingEntity)camera.getFocusedEntity()).getStatusEffect(ModEffects.ENSHROUDED).getDuration();
            t = y < 20 ? 1.0f - (float)y / 20.0f : 0.0f;
        }


    }


    public static void applyFog(Camera camera, FogType fogType, float viewDistance, boolean thickFog) {
        float g;
        float f;
        Entity entity = camera.getFocusedEntity();
        if (entity instanceof LivingEntity && ((LivingEntity) entity).hasStatusEffect(ModEffects.ENSHROUDED)) {
            int i = ((LivingEntity) entity).getStatusEffect(ModEffects.ENSHROUDED).getDuration();
            float h = MathHelper.lerp(Math.min(1.0f, (float) i / 20.0f), viewDistance, 5.0f);
            if (fogType == FogType.FOG_SKY) {
                f = 0.0f;
                g = h * 0.8f;
            }
        }
    }


}
