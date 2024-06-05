package net.underplayer97.ResonantEnemies.mixin;

import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import net.underplayer97.ResonantEnemies.handlers.ScreenshakeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.underplayer97.ResonantEnemies.ResonantMain.RANDOM;

@Mixin(Camera.class)
public class CameraMixin {

    @Inject(method = "update", at = @At("RETURN"))
    private void resonantEnemiesScreenshake(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        ScreenshakeHandler.cameraTick((Camera) (Object) this, RANDOM);
    }

}
