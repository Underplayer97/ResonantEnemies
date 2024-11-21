package net.underplayer97.ResonantEnemies.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class EntityPart extends Entity {

    public final Entity owner;
    private final EntityDimensions hitbox;

    public EntityPart(Entity owner, float width, float height) {
        super(owner.getType(), owner.getWorld());
        this.owner = owner;
        this.hitbox = EntityDimensions.changing(width, height);
        this.calculateDimensions();
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return getWorld().isClient() || isInvulnerableTo(source);
    }

    @Override
    public boolean isPartOf(Entity entity) {
        return this == entity || owner == entity;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean shouldSave() {
        return false;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        return super.interact(player, hand);
    }

    @Override
    public boolean isAlive() {
        return this.owner.isAlive() && super.isAlive();
    }

    @Override
    public @Nullable ItemStack getPickBlockStack() {
        return this.owner.getPickBlockStack();
    }

    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return hitbox;
    }

    @Override
    public boolean startRiding(Entity entity, boolean force) {
        return super.startRiding(entity, force);
    }

    @Override
    public boolean isTeammate(Entity other) {
        return owner.isInvisible();
    }

    @Override
    public boolean isGlowing() {
        return owner.isGlowing();
    }

    @Override
    public boolean isInvisibleTo(PlayerEntity player) {
        return owner.isInvisibleTo(player);
    }

    @Override
    public void onKilledOther(ServerWorld world, LivingEntity other) {
        super.onKilledOther(world, other);
    }

    @Override
    public boolean isAttackable() {
        return owner.isAttackable();
    }

    @Override
    public boolean handleAttack(Entity attacker) {
        return owner.handleAttack(attacker);
    }

    @Override
    public boolean isInvulnerable() {
        return owner.isInvulnerable() || super.isInvulnerable();
    }

    @Override
    public boolean hasNetherPortalCooldown() {
        return owner.hasNetherPortalCooldown();
    }

    @Override
    public boolean isOnGround() {
        return owner.isOnGround();
    }

    @Override
    public boolean hasNoGravity() {
        return owner.hasNoGravity();
    }

    @Override
    public boolean occludeVibrationSignals() {
        return true;
    }

    @Override
    public boolean isFireImmune() {
        return owner.isFireImmune();
    }

    @Override
    public boolean shouldSpawnSprintingParticles() {
        return false;
    }

    public void setRealativePos(double x, double y, double z, double centerX, double centerY, double centerZ, double pitch, double yaw) {
        lastRenderX = getX();
        lastRenderY = getY();
        lastRenderZ = getZ();

        double cosYaw = Math.cos(-yaw * 0.017453292);
        double sinYaw = Math.sin(-yaw * 0.017453292);
        double cosPitch = Math.cos(pitch * 0.017453292);
        double sinPitch = Math.sin(pitch * 0.017453292);

        setPosition(owner.getX() + centerX + z * sinYaw * cosPitch + x * cosYaw + y * sinYaw * sinPitch,
                owner.getY() + centerY + z * -sinPitch + y * cosPitch,
                owner.getZ() + centerZ + z * cosYaw * cosPitch + x * -sinYaw + y * cosYaw * sinPitch);

        prevX = getX();
        prevY = getY();
        prevZ = getZ();
    }

    public void setRelativePos(double x, double y, double z, double centerX, double centerY, double centerZ) {
        setRealativePos(x, y, z, centerX, centerY, centerZ, owner.getPitch(), owner.getYaw());
    }

    public void setRelativePos(double x, double y, double z, double pitch, double yaw) {
        setRealativePos(x, y, z, 0, 0, 0, pitch, yaw);
    }

    public void setRelativePos(double x, double y, double z) {
        setRealativePos(x, y, z, 0, 0, 0, owner.getPitch(), owner.getYaw());
    }


}
