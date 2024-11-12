package net.underplayer97.ResonantEnemies.entity.boss.erebus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.entity.boss.ErebusEntity;

public class ErebusPart extends Entity {
    public final ErebusEntity owner;
    public final String name;
    private final EntityDimensions partDimensions;

    public ErebusPart(ErebusEntity owner, String name, float width, float height) {
        super(owner.getType(), owner.world);
        this.partDimensions = EntityDimensions.changing(width, height);
        this.calculateDimensions();
        this.owner = owner;
        this.name = name;
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return null;
    }

    public boolean collides() {
        return true;
    }

    //public boolean damage(DamageSource source, float amount) {
    //    return this.isInvulnerableTo(source) ? false : this.owner.damagePart(this, source, amount);
    //}

    public boolean isPartOf(Entity entity) {
        return this == entity || this.owner == entity;
    }

    //public Packet<?> createSpawnPacket(){
    //    throw new UnsupportedOperationException();
    //}

    public EntityDimensions getDimensions(EntityPose pose) {
        return this.partDimensions;
    }

    public boolean shouldSave() {
        return false;
    }

}
