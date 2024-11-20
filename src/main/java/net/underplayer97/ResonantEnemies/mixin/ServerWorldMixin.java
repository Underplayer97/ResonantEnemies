package net.underplayer97.ResonantEnemies.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.underplayer97.ResonantEnemies.entity.util.WorldMultipartHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin implements WorldMultipartHelper {
    @Inject(method = "getDragonPart", at = @At("RETURN"), cancellable = true)
    public void getEntityParts(int id, CallbackInfoReturnable<Entity> cir) {
        Entity entity = cir.getReturnValue();
        if (entity == null) cir.setReturnValue(getPMEPartMap().get(id));
    }
}
