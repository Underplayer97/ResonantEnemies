package net.underplayer97.ResonantEnemies.entity.boss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.entity.ai.ErebusAttackGoal;
import net.underplayer97.ResonantEnemies.entity.boss.erebus.ErebusPart;
import net.underplayer97.ResonantEnemies.entity.custom.ShamblerEntity;
import net.underplayer97.ResonantEnemies.entity.projectile.SpitterSpitEntity;
import net.underplayer97.ResonantEnemies.entity.util.ModAttributes;
import net.underplayer97.ResonantEnemies.sound.ModSounds;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.RawAnimation;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.function.Function;

public class ErebusEntity extends AbstractBossEntity implements IAnimatable {

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public int ticksSinceDeath;
    private float lastScale;
    private static final TrackedData<Integer> PRIMARY_ATTACK_COOLDOWN = DataTracker.registerData(ErebusEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> SPECIAL_ATTACK_COOLDOWN = DataTracker.registerData(ErebusEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Integer> ATTACK_TYPE = DataTracker.registerData(ErebusEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public int primaryAttackDuration;
    public int specialAttackDuration = 20;
    boolean shoot;
    boolean isDead = false;
    boolean summoned;

    public ErebusEntity(EntityType<? extends ErebusEntity> entityType, World world) {
        super(entityType, world);
        this.setHealth(this.getMaxHealth());

        primaryAttackDuration = 10;

    }

    //TODO: Add spawn and attack animations. Custom beam attacks with animation setup - MIGHT NOT HAVE ENOUGH TIME

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0f)
                .add(EntityAttributes.GENERIC_ARMOR, 15.0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 4.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(ModAttributes.EREBUS_SPECIAL_ATTACK_COOLDOWN, 6.0f)
                .add(ModAttributes.EREBUS_PRIMARY_ATTACK_COOLDOWN, 2.0f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0f);
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new ErebusAttackGoal(this, 250));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));

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

    //public void shootAt(LivingEntity target) {
    //    if (getWorld().isClient()) return;
    //    setSpecialAttackCooldown(getMaxSpecialAtackCooldown());

    //    for (int i = 0; i < 5; ++i) {
    //        SpitterSpitEntity shootEntity = new SpitterSpitEntity(getWorld(), this);
    //        double x = target.getX() - this.getX();
    //        double y = target.getBodyY(0.3333333333333333) - shootEntity.getY();
    //        double z = target.getZ() - this.getZ();
    //        double g = Math.sqrt(x * x + z * z) * 0.20000000298023224;

    //        shootEntity.setVelocity(x, y + g, z, 1.5f, 10.0f);
    //        this.world.spawnEntity(shootEntity);
    //        this.shoot = true;
    //    }
    //}

    void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public void meleeAttack(LivingEntity target) {
        setPrimaryAttackCooldown(getMaxPrimaryAttackCooldown());
        setAttackType(random.nextInt(3)+1);
        if (target != null) {
            Box targetBox = target.getBoundingBox();
            if (doesCollide(targetBox, getBoundingBox())) tryAttack(target);
        }
    }

    @Override
    protected void updatePostDeath() {
        ++this.ticksSinceDeath;
        this.isDead = true;
        if (this.ticksSinceDeath >= 180 && this.ticksSinceDeath <= 200) {
            float f = (this.random.nextFloat() - 0.5F) * 8.0F;
            float g = (this.random.nextFloat() - 0.5F) * 4.0F;
            float h = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() + (double)f, this.getY() + 2.0 + (double)g, this.getZ() + (double)h, 0.0, 0.0, 0.0);
        }

        if (this.ticksSinceDeath == 200 && this.world instanceof ServerWorld) {

            this.remove(RemovalReason.KILLED);
        }
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

    @Override
    public void onSummoned() {
        summoned = true;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimationSpeed(1);
        if (isDead){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.erebus.defeat", false));
            return PlayState.CONTINUE;

        } else if (this.getVelocity().getX() !=0 || this.getVelocity().getZ()!=0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.erebus.move", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.erebus.idle", true));
        return PlayState.CONTINUE;

    }

    private <T extends IAnimatable> PlayState attackPredicate(AnimationEvent<T> event) {

        if (isAttacking()) {
            event.getController().setAnimationSpeed(2);
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.erebus.slam", false));
            //attacking = false;
            //return PlayState.CONTINUE;
        }

        event.getController().markNeedsReload();
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "attackPredicate",
                0, this::attackPredicate));
        animationData.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public boolean doesCollide(Box box1, Box box2) {
        VoxelShape voxelShape = VoxelShapes.cuboid(box1);
        VoxelShape voxelShape2 = VoxelShapes.cuboid(box2);
        return VoxelShapes.matchesAnywhere(voxelShape2, voxelShape, BooleanBiFunction.AND);
    }

    //@Override
    //public void tick() {
    //    super.tick();
    //    updateChildParts();
    //}

    //@Override
    //public EntityPart[] getParts() {
    //    return parts;
    //}

    //public void updateChildParts() {
    //    Vec3f headPos;
    //    Vec3f topRightHandPos;
    //    Vec3f topLeftHandPos;
    //    Vec3f bottomRightHand;
    //    Vec3f bottomLeftHand;

    //    headPos = new Vec3f(0f, getHeight() + 5.0f, 0f);
    //    topRightHandPos = new Vec3f(0f, 0f, 0f);
    //    topLeftHandPos = new Vec3f(0f, 0f, 0f);
    //    bottomRightHand = new Vec3f(0f, 0f, 0f);
    //    bottomLeftHand = new Vec3f(0f, 0f, 0f);

    //    headPart.setRelativePos(headPos);
    //    headPart.setScale(5.0f, 5.0f);

    //    topRightHandPart.setRelativePos(topRightHandPos);
    //    topLeftHandPart.setRelativePos(topLeftHandPos);
    //    bottomRightHandPart.setRelativePos(bottomRightHand);
    //    bottomLeftHandPart.setRelativePos(bottomLeftHand);
    //}



}
