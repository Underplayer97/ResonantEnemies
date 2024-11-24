package net.underplayer97.ResonantEnemies.entity.boss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.Box;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.entity.ai.AmalgamateAttackGoal;
import net.underplayer97.ResonantEnemies.entity.ai.ErebusAttackGoal;
import net.underplayer97.ResonantEnemies.entity.util.ModAttributes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.awt.*;

public class AmalgamateEntity extends AbstractBossEntity implements IAnimatable {

    boolean isDead = false;
    public int ticksSinceDeath;
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private static final TrackedData<Integer> PRIMARY_ATTACK_COOLDOWN = DataTracker.registerData(AmalgamateEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> SPECIAL_ATTACK_COOLDOWN = DataTracker.registerData(AmalgamateEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Integer> ATTACK_TYPE = DataTracker.registerData(AmalgamateEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public int primaryAttackDuration;
    public int specialAttackDuration = 20;
    boolean shoot;
    boolean summoned;

    public AmalgamateEntity(EntityType<? extends AbstractBossEntity> entityType, World world) {
        super(entityType, world);
        primaryAttackDuration = 5;

    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AmalgamateAttackGoal(this, 250));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));

    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0f)
                .add(EntityAttributes.GENERIC_ARMOR, 15.0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 20.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 4.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0f)
                .add(ModAttributes.EREBUS_SPECIAL_ATTACK_COOLDOWN, 6.0f)
                .add(ModAttributes.EREBUS_PRIMARY_ATTACK_COOLDOWN, 2.0f);
    }


    public void meleeAttack(LivingEntity target) {
        setPrimaryAttackCooldown(getMaxPrimaryAttackCooldown());
        setAttackType(random.nextInt(3)+1);
        if (target != null) {
            Box targetBox = target.getBoundingBox();
            if (doesCollide(targetBox, getBoundingBox())) tryAttack(target);
        }
    }

    public boolean doesCollide(Box box1, Box box2) {
        VoxelShape voxelShape = VoxelShapes.cuboid(box1);
        VoxelShape voxelShape2 = VoxelShapes.cuboid(box2);
        return VoxelShapes.matchesAnywhere(voxelShape2, voxelShape, BooleanBiFunction.AND);
    }


    @Override
    public boolean canTarget(@Nullable LivingEntity target) {
        if (target == null) return false;
        return super.canTarget(target);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        dataTracker.startTracking(PRIMARY_ATTACK_COOLDOWN, 0);
        dataTracker.startTracking(SPECIAL_ATTACK_COOLDOWN, 0);
        dataTracker.startTracking(ATTACK_TYPE, 1);
    }

    public int getAttackType() {
        return dataTracker.get(ATTACK_TYPE);
    }

    public void setAttackType(int state) {
        dataTracker.set(ATTACK_TYPE,state);
    }

    public boolean isPrimaryAttack() {
        return getPrimaryAttackCooldown() > getMaxPrimaryAttackCooldown() - primaryAttackDuration;
    }

    public int getPrimaryAttackCooldown() {
        return dataTracker.get(PRIMARY_ATTACK_COOLDOWN);
    }

    public void setPrimaryAttackCooldown(int state) {
        dataTracker.set(PRIMARY_ATTACK_COOLDOWN, state);
    }

    public int getMaxPrimaryAttackCooldown() {
        return (int) (getAttributeValue(ModAttributes.EREBUS_PRIMARY_ATTACK_COOLDOWN));
    }

    public boolean isSpecialAttack() {
        return getSpecialAttackCooldown() > getMaxSpecialAtackCooldown() - specialAttackDuration;
    }

    public int getSpecialAttackCooldown() {
        return dataTracker.get(SPECIAL_ATTACK_COOLDOWN);
    }

    public void setSpecialAttackCooldown(int state) {
        dataTracker.set(PRIMARY_ATTACK_COOLDOWN, state);
    }

    public int getMaxSpecialAtackCooldown() {
        return (int) (getAttributeValue(ModAttributes.EREBUS_SPECIAL_ATTACK_COOLDOWN));
    }

    @Override
    public void tick() {
        super.tick();

        if (getPrimaryAttackCooldown() > 0) setPrimaryAttackCooldown(getPrimaryAttackCooldown() - 1);
        if (getSpecialAttackCooldown() > 0) setSpecialAttackCooldown(getSpecialAttackCooldown() - 1);
    }

    @Override
    public BossBar.Color getBossColor() {
        return BossBar.Color.PURPLE;
    }

    @Override
    public int getInvulTime() {
        return 0;
    }

    @Override
    public Text getSpawnMessage() {
        return null;
    }

    @Override
    public Text getKillMessage() {
        return null;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.getVelocity().getX() !=0 || this.getVelocity().getZ()!=0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.amal.move", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.amal.idle", true));
        return PlayState.CONTINUE;
    }

    private <T extends IAnimatable> PlayState attackPredicate(AnimationEvent<T> event) {
        if (this.handSwinging) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.amal.swing", false));
        }

        event.getController().markNeedsReload();
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        animationData.addAnimationController(new AnimationController(this, "AttackController",
                0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
