package net.underplayer97.ResonantEnemies.entity.boss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.entity.boss.erebus.ErebusPart;
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

public class ErebusEntity extends AbstractBossEntity implements IAnimatable {

    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    public int ticksSinceDeath;
    private float lastScale;
    //private final ErebusPart headPart = new ErebusPart(this);
    //private final ErebusPart topRightHandPart = new ErebusPart(this);
    //private final ErebusPart topLeftHandPart = new ErebusPart(this);
    //private final ErebusPart bottomRightHandPart = new ErebusPart(this);
    //private final ErebusPart bottomLeftHandPart = new ErebusPart(this);
    //private final ErebusPart[] parts = new ErebusPart[]{headPart,topRightHandPart,topLeftHandPart,bottomRightHandPart,bottomLeftHandPart};

    public ErebusEntity(EntityType<? extends ErebusEntity> entityType, World world) {
        super(entityType, world);
        this.setHealth(this.getMaxHealth());
    }

    //TODO: Add spawn, death, attack animations. Custom beam attacks with animation setup

    //public void tickMovement() {
    //    float g;
    //    if (this.isDead()) {
    //        float f = (this.random.nextFloat() - 0.5F) * 8.0F;
    //        g = (this.random.nextFloat() - 0.5F) * 4.0F;
    //        float h = (this.random.nextFloat() - 0.5F) * 8.0F;
    //        this.world.addParticle(ParticleTypes.EXPLOSION, this.getX() + (double)f, this.getY() + 2.0 + (double)g, this.getZ() + (double)h, 0.0, 0.0, 0.0);
    //    }
    //}

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0f)
                .add(EntityAttributes.GENERIC_ARMOR, 15.0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0f);
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AttackGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>((MobEntity)this, PlayerEntity.class, true));

    }

    @Override
    protected void updatePostDeath() {
        ++this.ticksSinceDeath;
        if (this.ticksSinceDeath >= 180 && this.ticksSinceDeath <= 200) {
            float f = (this.random.nextFloat() - 0.5F) * 8.0F;
            float g = (this.random.nextFloat() - 0.5F) * 4.0F;
            float h = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() + (double)f, this.getY() + 2.0 + (double)g, this.getZ() + (double)h, 0.0, 0.0, 0.0);
        }

        this.move(MovementType.SELF, new Vec3d(0.0, 0.10000000149011612, 0.0));
        this.setYaw(this.getYaw() + 20.0F);
        this.bodyYaw = this.getYaw();
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

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.getVelocity().getX() !=0 || this.getVelocity().getZ()!=0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.erebus.move", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.erebus.idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.handSwinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            this.world.playSound(this.getX(), this.getEyeY(), this.getZ(), ModSounds.SHAMBLER_ATTACK, this.getSoundCategory(), 1.0f, 1.0f, true);
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.erebus.attack", false));
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
