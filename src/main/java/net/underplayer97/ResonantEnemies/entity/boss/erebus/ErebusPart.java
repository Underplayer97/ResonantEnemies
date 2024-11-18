package net.underplayer97.ResonantEnemies.entity.boss.erebus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.entity.ModEntities;
import net.underplayer97.ResonantEnemies.entity.boss.AbstractPart;
import net.underplayer97.ResonantEnemies.entity.boss.ErebusEntity;

public class ErebusPart extends AbstractPart {

    private final float baseRadius, baseOffsetY, baseSizeX, baseSizeY;

    public ErebusPart(EntityType<?> t, World world) {
        super(t, world);
        this.baseRadius = 0;
        this.baseOffsetY = 0;
        this.baseSizeX = 0;
        this.baseSizeY = 0;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return null;
    }


    public ErebusPart(EntityType<?> type, ErebusPart erebus, float baseRadius, float angleYaw, float baseOffsetY, float baseSizeX, float baseSizeY, float damageMultiplier) {
        super(type,erebus,baseRadius,angleYaw,baseOffsetY,baseSizeX,baseSizeY,damageMultiplier);
        this.baseRadius = baseRadius;
        this.baseOffsetY = baseOffsetY;
        this.baseSizeX = baseSizeX;
        this.baseSizeY = baseSizeY;
    }

    public ErebusPart(ErebusEntity parent, float baseRadius, float angleYaw, float baseOffsetY, float baseSizeX, float baseSizeY, float damageMultiplier) {
        super(ModEntities.EREBUS_MULTIPART, parent,baseRadius, angleYaw, baseOffsetY, baseSizeX, baseSizeY, damageMultiplier);
        this.baseRadius = baseRadius;
        this.baseOffsetY = baseOffsetY;
        this.baseSizeX = baseSizeX;
        this.baseSizeY = baseSizeY;
    }

    public void updateScale(float scale) {
        this.radius = this.baseRadius * scale;
        this.offsetY = this.baseOffsetY * scale;
        this.setScaleX(this.baseSizeX * scale);
        this.setScaleY(this.baseSizeY * scale);
    }

    @Override
    public void collideWithNearbyEntities() {

    }
}
