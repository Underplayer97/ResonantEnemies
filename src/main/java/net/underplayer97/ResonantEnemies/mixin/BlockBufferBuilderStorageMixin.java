package net.underplayer97.ResonantEnemies.mixin;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.chunk.BlockBufferBuilderStorage;
import net.underplayer97.ResonantEnemies.client.ModRenderLayers;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(BlockBufferBuilderStorage.class)
public class BlockBufferBuilderStorageMixin {

    @Shadow
    @Final
    private Map<RenderLayer, BufferBuilder> builders;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(CallbackInfo callbackInfo) {
        this.builders.put(ModRenderLayers.getPortalSlab(), new BufferBuilder(ModRenderLayers.getPortalSlab().getExpectedBufferSize()));
    }

}
