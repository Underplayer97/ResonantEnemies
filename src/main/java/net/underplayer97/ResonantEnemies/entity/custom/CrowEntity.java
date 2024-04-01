package net.underplayer97.ResonantEnemies.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.scoreboard.AbstractTeam;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import net.underplayer97.ResonantEnemies.particle.ModParticles;
import net.underplayer97.ResonantEnemies.sound.ModSounds;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.UUID;

public class CrowEntity extends TameableEntity implements IAnimatable, Flutterer {


    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    private float flapSpeed = 1.0f;
    private float field_28640 = 1.0f;



    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public CrowEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.setTamed(false);
        this.moveControl = new FlightMoveControl(this, 10, false);
        this.experiencePoints = 7;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SITTING, false);
    }


    public static DefaultAttributeContainer.Builder setAttributes() {
        return TameableEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0f)
                .add(EntityAttributes.GENERIC_ARMOR, 2.0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.4f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 3.0f)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6f);

    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AttackGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(0, new SitGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true));
        this.targetSelector.add(2, new AttackWithOwnerGoal(this));
    }

    @Override
    public boolean isInAir() {
        return !this.onGround;
    }

    class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(CrowEntity crow) {
            super(crow, 1.0, false);
        }

        @Override
        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return super.getSquaredMaxAttackDistance(entity);
        }

    }

    @Override
    public void tickMovement() {
        if (this.world.isClient) {
            for (int i = 0; i < 1; ++i) {
                this.world.addParticle(ModParticles.ENSHROUDED_PARTICLE, this.getParticleX(0.5), this.getRandomBodyY() - 0.25, this.getParticleZ(0.5), 0.0, 0.0, 0.0);
            }
        }
        super.tickMovement();
        this.flapWings();
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    private void flapWings() {
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation += (float)(this.onGround || this.hasVehicle() ? -1 : 4) * 0.3f;
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0f, 1.0f);
        if (!this.onGround && this.flapSpeed < 1.0f) {
            this.flapSpeed = 1.0f;
        }
        this.flapSpeed *= 0.9f;
        Vec3d vec3d = this.getVelocity();
        if (!this.onGround && vec3d.y < 0.0) {
            this.setVelocity(vec3d.multiply(1.0, 0.6, 1.0));
        }
        this.flapProgress += this.flapSpeed * 2.0f;
    }


    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
    }

    @Override
    protected boolean hasWings() {
        return this.speed > this.field_28640;
    }



    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.getVelocity().getX() !=0 || this.getVelocity().getZ()!=0 && this.isInAir()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crow.walk", true)); //Not needed
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crow.idle", true)); //Not needed
        return PlayState.CONTINUE;
    }



    private PlayState attackPredicate(AnimationEvent event) {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            //this.world.playSound(this.getX(), this.getEyeY(), this.getZ(), ModSounds.CROW_ATTACK, this.getSoundCategory(), 1.0f, 1.0f, true);
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crow.attack", false));
            this.handSwinging = false;
        }

        return PlayState.CONTINUE;
    }

    private PlayState flyPredicate (AnimationEvent event) {
        if(!this.isInAir() && this.onGround) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crow.fly", true));
            return PlayState.CONTINUE;
        }

        return PlayState.CONTINUE;

    }

    private PlayState sittingController(AnimationEvent event) {
        Vec3d vec3d = this.getVelocity();
        if(this.isSitting()) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crow.sitting", false));
            return PlayState.CONTINUE;
        } else if (this.getVelocity().getX() !=0 || this.getVelocity().getZ()!=0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crow.walk", true)); //Not needed
            return PlayState.CONTINUE;
        } else if (this.airStrafingSpeed !=1) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.crow.fly", true));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        //animationData.addAnimationController(new AnimationController(this, "controller",
        //        0, this::predicate));
        animationData.addAnimationController(new AnimationController(this, "AttackController",
                0, this::attackPredicate));
        //animationData.addAnimationController(new AnimationController(this, "flyController",
        //        0, this::flyPredicate));
        animationData.addAnimationController(new AnimationController(this, "sittingController",
                0, this::sittingController));
    }


    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    protected void addFlapEffects() {
        this.playSound(SoundEvents.ENTITY_PARROT_FLY, 0.15f, 1.0f);
        this.field_28640 = this.speed + this.maxWingDeviation / 2.0f;
    }

    @Override
    protected SoundEvent getAmbientSound() {

        String string = Formatting.strip(getName().getString());
        if (string != null && "Vanta".equals(string)) {
            return ModSounds.CROW_HOLA;
        }
        else {
            return ModSounds.CROW_AMBIENT;
        }

    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.CROW_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.CROW_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15f, 1.0f);
    }

    private static final TrackedData<Boolean> SITTING =
            DataTracker.registerData(CrowEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        Item item = itemstack.getItem();

        Item itemForTaming = Items.PUMPKIN_SEEDS;

        if (item == itemForTaming && !isTamed()) {
            if (this.world.isClient()) {
                return ActionResult.CONSUME;
            } else {
                if (!player.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }

                if (!this.world.isClient()) {
                    super.setOwner(player);
                    this.navigation.recalculatePath();
                    this.setTarget(null);
                    this.world.sendEntityStatus(this, (byte)7);
                    setSit(true);
                }

                return ActionResult.SUCCESS;
            }
        }

        if(isTamed() && !this.world.isClient() && hand == Hand.MAIN_HAND) {
            setSit(!isSitting());
            return ActionResult.SUCCESS;
        }

        if (itemstack.getItem() == itemForTaming) {
            return ActionResult.PASS;
        }

        return super.interactMob(player, hand);
    }

    public void setSit(boolean sitting) {
        this.dataTracker.set(SITTING, sitting);
        super.setSitting(sitting);
    }

    public boolean isSitting() {
        return this.dataTracker.get(SITTING);
    }

    @Override
    public void setTamed(boolean tamed) {
        super.setTamed(tamed);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("isSitting", this.dataTracker.get(SITTING));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(SITTING, nbt.getBoolean("isSitting"));
    }

    @Override
    public AbstractTeam getScoreboardTeam() {
        return super.getScoreboardTeam();
    }

    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }



}
