package net.underplayer97.ResonantEnemies.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.underplayer97.ResonantEnemies.entity.boss.EntityPart;
import net.underplayer97.ResonantEnemies.entity.boss.MultipartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "onSpawnPacket", at = @At("TAIL"))
    private void setPartId(EntitySpawnS2CPacket packet, CallbackInfo ci) {
        if (this instanceof MultipartEntity multipartEntity) {
            for (int i = 0; i < multipartEntity.getParts().length; i++) {
                EntityPart part = multipartEntity.getParts()[i];
                part.setId(part.owner.getId() + i);
            }
        }
    }

}
