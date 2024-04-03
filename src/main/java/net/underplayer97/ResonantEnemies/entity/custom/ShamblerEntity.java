package net.underplayer97.ResonantEnemies.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.NavigationConditions;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.sound.ModSounds;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Predicate;

public class ShamblerEntity extends HostileEntity implements IAnimatable {

    private static final Predicate<Difficulty> DOOR_BREAK_DIFFICULTY_CHECKER = difficulty -> difficulty == Difficulty.HARD;
    private final BreakDoorGoal breakDoorsGoal = new BreakDoorGoal(this, DOOR_BREAK_DIFFICULTY_CHECKER);
    private boolean canBreakDoors;

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);


    public ShamblerEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 7;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0f)
                .add(EntityAttributes.GENERIC_ARMOR, 5.0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 13.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 2.0f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0f);


    }



    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AttackGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(4, new MoveThroughVillageGoal(this, 1.0, true, 4, this::canBreakDoors));


        this.targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<VillagerEntity>((MobEntity) this, VillagerEntity.class, true));
        this.targetSelector.add(2,new ActiveTargetGoal<PillagerEntity>((MobEntity) this, PillagerEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<IronGolemEntity>((MobEntity) this, IronGolemEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<WanderingTraderEntity>((MobEntity) this, WanderingTraderEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<AnimalEntity>((MobEntity) this, AnimalEntity.class, true));
    }


    @Override
    public void tickMovement() {
        if (this.isAlive()) {
            boolean bl;
            boolean bl2 = bl = this.burnsInDaylight() && this.isAffectedByDaylight();
            if (bl) {
                ItemStack itemStack = this.getEquippedStack(EquipmentSlot.HEAD);
                if (!itemStack.isEmpty()) {
                    if (itemStack.isDamageable()) {
                        itemStack.setDamage(itemStack.getDamage() + this.random.nextInt(2));
                        if (itemStack.getDamage() >= itemStack.getMaxDamage()) {
                            this.sendEquipmentBreakStatus(EquipmentSlot.HEAD);
                            this.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
                        }
                    }
                    bl = false;
                }
                if (bl) {
                    this.setOnFireFor(8);
                }
            }
        }
        super.tickMovement();
    }

    protected boolean burnsInDaylight() {
        return true;
    }

    class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(ShamblerEntity shambler) {
            super(shambler, 1.0, false);
        }

        @Override
        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return super.getSquaredMaxAttackDistance(entity);
        }

    }

    public boolean canBreakDoors() {
        return this.canBreakDoors;
    }

    protected boolean shouldBreakDoors() {
        return true;
    }

    public void setCanBreakDoors(boolean canBreakDoors) {
        if (this.shouldBreakDoors() && NavigationConditions.hasMobNavigation(this)) {
            if (this.canBreakDoors != canBreakDoors) {
                this.canBreakDoors = canBreakDoors;
                ((MobNavigation)this.getNavigation()).setCanPathThroughDoors(canBreakDoors);
                if (canBreakDoors) {
                    this.goalSelector.add(1, this.breakDoorsGoal);
                } else {
                    this.goalSelector.remove(this.breakDoorsGoal);
                }
            }
        } else if (this.canBreakDoors) {
            this.goalSelector.remove(this.breakDoorsGoal);
            this.canBreakDoors = false;
        }
    }

    private boolean damageFromPotion(DamageSource source, PotionEntity potion, float amount) {
        boolean bl;
        ItemStack itemStack = potion.getStack();
        Potion potion2 = PotionUtil.getPotion(itemStack);
        List<StatusEffectInstance> list = PotionUtil.getPotionEffects(itemStack);
        boolean bl2 = bl = potion2 == Potions.HEALING && list.isEmpty();
        if (bl) {
            return super.damage(source, amount);
        }
        return false;
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.getVelocity().getX() !=0 || this.getVelocity().getZ()!=0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shambler.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shambler.idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if(this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            this.world.playSound(this.getX(), this.getEyeY(), this.getZ(), ModSounds.SHAMBLER_ATTACK, this.getSoundCategory(), 1.0f, 1.0f, true);
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.shambler.attack", false));
            this.handSwinging = false;
        }

        return PlayState.CONTINUE;
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



    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.SHAMBLER_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.SHAMBLER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.SHAMBLER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15f, 1.0f);
    }


}
