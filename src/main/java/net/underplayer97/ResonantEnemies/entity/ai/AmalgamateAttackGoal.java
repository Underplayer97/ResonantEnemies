package net.underplayer97.ResonantEnemies.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.underplayer97.ResonantEnemies.entity.boss.AmalgamateEntity;
import net.underplayer97.ResonantEnemies.entity.boss.GrimsleyEntity;

import java.util.EnumSet;

public class AmalgamateAttackGoal extends Goal {
    private final AmalgamateEntity entity;
    private LivingEntity target;
    private final double maxSearchDistance;

    public AmalgamateAttackGoal(AmalgamateEntity entity, double maxSearchDistance) {
        this.entity = entity;
        this.maxSearchDistance = maxSearchDistance;
        setControls(EnumSet.of(Control.MOVE, Control.LOOK));
    }

    @Override
    public void start() {
        entity.setPrimaryAttackCooldown(Math.max(entity.getPrimaryAttackCooldown(), 20));
        target = entity.getTarget();
    }

    @Override
    public boolean canStart() {
        if (!entity.canTarget(entity.getTarget())){
            entity.setTarget(null);
            return false;
        }

        target = entity.getTarget();
        return target != null && (entity.squaredDistanceTo(target) < maxSearchDistance);
    }

    @Override
    public boolean shouldContinue() {
        if (target == null) return false;
        if (!target.isAlive()) return false;
        return !entity.getNavigation().isIdle() || canStart();
    }

    @Override
    public void stop() {
        target = null;
        entity.setTarget(null);
        entity.getNavigation().stop();
    }

    @Override
    public boolean shouldRunEveryTick() {
        return false;
    }

    @Override
    public void tick() {
        if (target.isRemoved()) {
            stop();
            return;
        }

        entity.setSprinting(true);
        entity.getLookControl().lookAt(target);
        double attackDistance = entity.getWidth() * 2.0f * (entity.getWidth() * 2.0f);
        double distance = entity.squaredDistanceTo(target);
        entity.getNavigation().startMovingTo(target, 1);
        boolean doesCollide = entity.doesCollide(entity.getBoundingBox(), target.getBoundingBox());

        //if(entity.getSpecialAttackCooldown() == 0 && (distance > attackDistance * 4 || distance < attackDistance)) {
        //    entity.getLookControl().lookAt(target);
        //    //entity.shoot();
        //}

        if (doesCollide) entity.meleeAttack(target);

    }
}
