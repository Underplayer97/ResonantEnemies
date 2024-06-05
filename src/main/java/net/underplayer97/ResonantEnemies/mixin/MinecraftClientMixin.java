package net.underplayer97.ResonantEnemies.mixin;

import net.minecraft.client.MinecraftClient;
import net.underplayer97.ResonantEnemies.handlers.ScreenshakeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.underplayer97.ResonantEnemies.ResonantMain.RANDOM;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void resonantenemies$clientTick(CallbackInfo ci) {
        ScreenshakeHandler.clientTick(MinecraftClient.getInstance().gameRenderer.getCamera(), RANDOM);
    }


}
