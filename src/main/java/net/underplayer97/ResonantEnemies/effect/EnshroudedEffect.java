package net.underplayer97.ResonantEnemies.effect;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.impl.networking.ClientSidePacketRegistryImpl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.FogShape;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.scoreboard.ScoreboardCriterion;
import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;

public class EnshroudedEffect extends StatusEffect {

    protected EnshroudedEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    @Environment(value=EnvType.CLIENT)
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

        //BackgroundRenderer.applyFog(new Camera(), BackgroundRenderer.FogType.FOG_TERRAIN, 10, true);
        //BackgroundRenderer.setFogBlack();
        //RenderSystem.setShaderFogStart(0.0f);
        //RenderSystem.setShaderFogEnd(10.0f);
        //RenderSystem.setShaderFogShape(FogShape.CYLINDER);


        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
