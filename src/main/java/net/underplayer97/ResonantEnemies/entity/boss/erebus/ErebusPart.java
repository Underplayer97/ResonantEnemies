package net.underplayer97.ResonantEnemies.entity.boss.erebus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.underplayer97.ResonantEnemies.entity.boss.EntityPart;
import net.underplayer97.ResonantEnemies.entity.boss.ErebusEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ErebusPart extends EntityPart {

    //FUCK MULTI HITBOXES. This stuff is going to be unused.

    public final ErebusEntity owner;
    private float heightMod = 1;
    private float widthMod = 1;
    private final float damageMultiplier;

    public ErebusPart(ErebusEntity owner) {
        this(owner, 1);
    }

    public ErebusPart(ErebusEntity owner, float damageMultiplier) {
        super(owner, 1, 1);
        this.owner = owner;
        this.damageMultiplier = damageMultiplier;
        calculateDimensions();
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return super.getDimensions(pose).scaled(widthMod, heightMod);
    }


    @Override
    public boolean damage(DamageSource source, float amount) {
        return super.damage(source, amount * damageMultiplier);
    }

    public void setScale(float destinationHeight, float destinationWidth) {
        destinationWidth *= owner.getScaleFactor();
        destinationHeight *= owner.getScaleFactor();
        calculateDimensions();
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public void setScale(Vec2f scale) {
        setScale(scale.x, scale.y);
    }

    @Override
    public void setRelativePos(double x, double y, double z) {
        setRelativePos(x * owner.getScaleFactor(), y * owner.getScaleFactor(), z * owner.getScaleFactor(), 0, owner.getYaw());
    }

    public void setRelativePos(Vec3f vec3f) {
        setRelativePos(vec3f.getX(), vec3f.getY(), vec3f.getZ());
    }

    //@Override
    //public List<VoxelShape> getColliders() {
    //    BlockPos pos = getBlockPos();
    //    double x = pos.getX();
    //    double y = pos.getY();
    //    double z = pos.getZ();

    //    return List.of(
    //            VoxelShapes.cuboid(x, y, z, x+.5, y+.5, z+.5),
    //            VoxelShapes.cuboid(x-.5, y+.5, z-.5, x, y+1+Math.abs(Math.sin(getWorld().getTime()/20f)*3), z)
    //    );
    //}
}
