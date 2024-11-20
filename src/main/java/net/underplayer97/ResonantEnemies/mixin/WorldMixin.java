package net.underplayer97.ResonantEnemies.mixin;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.entity.boss.EntityPart;
import net.underplayer97.ResonantEnemies.entity.util.WorldMultipartHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Predicate;

@Mixin(World.class)
public abstract class WorldMixin implements WorldMultipartHelper {

    @Unique
    private final Int2ObjectMap<EntityPart> URDragonParts = new Int2ObjectOpenHashMap<>();

    public Int2ObjectMap<EntityPart> getPMEPartMap() {
        return URDragonParts;
    }

    @Inject(method = "getOtherEntities", at = @At("TAIL"))
    private void getEntityParts(Entity except, Box box, Predicate<? super Entity> predicate, CallbackInfoReturnable<List<Entity>> cir) {
        for (EntityPart part: getPMEParts()) {
            if (part != null && part != except && part.getBoundingBox().intersects(box) && predicate.test(part)) cir.getReturnValue().add(part);
        }
    }

    @Inject(method = "getEntitiesByType", at = @At("TAIL"))
    private <T extends Entity> void getEntityPartsByType(TypeFilter<Entity, T> filter, Box box, Predicate<? super T> predicate, CallbackInfoReturnable<List<T>> cir) {
        for (EntityPart part: getPMEParts()) {
            T type = filter.downcast(part);
            if (type != null && part.getBoundingBox().intersects(box) && predicate.test(type)) cir.getReturnValue().add(type);
        }
    }


}
