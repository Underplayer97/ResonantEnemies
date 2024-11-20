package net.underplayer97.ResonantEnemies.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import net.underplayer97.ResonantEnemies.entity.boss.EntityPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThreadedAnvilChunkStorage.class)
public abstract class ThreadedAnvilChunkStorageMixin {

    @Inject(method = "loadEntity(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    private void cancelEntityPartLoad(Entity entity, CallbackInfo ci) {
        if (entity instanceof EntityPart) ci.cancel();
    }

}
