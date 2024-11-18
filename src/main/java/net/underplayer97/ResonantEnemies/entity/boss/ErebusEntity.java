package net.underplayer97.ResonantEnemies.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.entity.boss.erebus.ErebusPart;
import net.underplayer97.ResonantEnemies.entity.util.EntityUtil;
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

    private ErebusPart headPart;
    private ErebusPart bodyPart;
    private ErebusPart topRightHand;
    private ErebusPart topLeftHand;
    private ErebusPart bottomRightHand;
    private ErebusPart bottomLeftHand;


    public ErebusEntity(EntityType<? extends ErebusEntity> entityType, World world) {
        super(entityType, world);
        //this.parts = new ErebusPart[]{this.head, this.body, this.topHandL, this.topHandR, this.bottomHandL, this.bottomHandR};
        this.setHealth(this.getMaxHealth());
    }

    public void updateScale(float scale) {
        if (this.headPart == null || this.headPart.isRemoved()) {
            this.headPart = new ErebusPart(this, 1.55F, 0, 0.6F, 0.5F, 0.35F, 1.5F);
            this.getWorld().spawnEntity(this.headPart);
        }
        this.headPart.updateScale(scale);

        if (this.bodyPart == null || this.bodyPart.isRemoved()) {
            this.bodyPart = new ErebusPart(this, 1.55F, 0, 0.6F, 0.5F, 0.35F, 1.5F);
            this.getWorld().spawnEntity(this.bodyPart);
        }
        this.bodyPart.updateScale(scale);

        if (this.topRightHand == null || this.topRightHand.isRemoved()) {
            this.topRightHand = new ErebusPart(this, 1.55F, 0, 0.6F, 0.5F, 0.35F, 1.5F);
            this.getWorld().spawnEntity(this.topRightHand);
        }
        this.topRightHand.updateScale(scale);

        if (this.topLeftHand == null || this.topLeftHand.isRemoved()) {
            this.topLeftHand = new ErebusPart(this, 1.55F, 0, 0.6F, 0.5F, 0.35F, 1.5F);
            this.getWorld().spawnEntity(this.topLeftHand);
        }
        this.topLeftHand.updateScale(scale);

        if (this.bottomRightHand == null || this.bottomRightHand.isRemoved()) {
            this.bottomRightHand = new ErebusPart(this, 1.55F, 0, 0.6F, 0.5F, 0.35F, 1.5F);
            this.getWorld().spawnEntity(this.bottomRightHand);
        }
        this.bottomRightHand.updateScale(scale);

        if (this.bottomLeftHand == null || this.bottomLeftHand.isRemoved()) {
            this.bottomLeftHand = new ErebusPart(this, 1.55F, 0, 0.6F, 0.5F, 0.35F, 1.5F);
            this.getWorld().spawnEntity(this.bottomLeftHand);
        }
        this.bottomLeftHand.updateScale(scale);

    }

    public void removeParts() {
        if (this.headPart != null) {
            this.headPart.remove(RemovalReason.DISCARDED);
            this.headPart = null;
        }
        if (this.bodyPart != null) {
            this.bodyPart.remove(RemovalReason.DISCARDED);
            this.bodyPart = null;
        }
        if (this.topRightHand != null) {
            this.topRightHand.remove(RemovalReason.DISCARDED);
            this.topRightHand = null;
        }
        if (this.topLeftHand != null) {
            this.topLeftHand.remove(RemovalReason.DISCARDED);
            this.topLeftHand = null;
        }
        if (this.bottomRightHand != null) {
            this.bottomRightHand.remove(RemovalReason.DISCARDED);
            this.bottomRightHand = null;
        }
        if (this.bottomLeftHand != null) {
            this.bottomLeftHand.remove(RemovalReason.DISCARDED);
            this.bottomLeftHand = null;
        }
    }

    public void updateParts() {
        if (this.isRemoved()) return;
        this.headPart.copyPositionAndRotation(this);
        this.bodyPart.copyPositionAndRotation(this);
        this.topRightHand.copyPositionAndRotation(this);
        this.topLeftHand.copyPositionAndRotation(this);
        this.bottomRightHand.copyPositionAndRotation(this);
        this.bottomLeftHand.copyPositionAndRotation(this);

        EntityUtil.updatePart(this.headPart, this);
        EntityUtil.updatePart(this.bodyPart, this);
        EntityUtil.updatePart(this.topRightHand, this);
        EntityUtil.updatePart(this.topLeftHand, this);
        EntityUtil.updatePart(this.bottomRightHand, this);
        EntityUtil.updatePart(this.bottomLeftHand, this);
    }

    @Override
    public void remove(RemovalReason reason) {
        this.removeParts();
        super.remove(reason);
    }

    @Override
    public void tick() {
        super.tick();
        this.updateParts();
    }

    public boolean isPart(Entity entityHit) {
        return this.headPart != null && this.headPart.isPartOf(entityHit) ||
         this.bodyPart != null && this.bodyPart.isPartOf(entityHit) ||
         this.topRightHand != null && this.topRightHand.isPartOf(entityHit) ||
         this.topLeftHand != null && this.topLeftHand.isPartOf(entityHit) ||
         this.bottomRightHand != null && this.bottomRightHand.isPartOf(entityHit) ||
         this.bottomLeftHand != null && this.bottomLeftHand.isPartOf(entityHit);
    }

    @Override
    public void calculateDimensions() {
        super.calculateDimensions();

    }


    //public void tickMovement() {
    //    float g;
    //    if (this.isDead()) {
    //        float f = (this.random.nextFloat() - 0.5F) * 8.0F;
    //        g = (this.random.nextFloat() - 0.5F) * 4.0F;
    //        float h = (this.random.nextFloat() - 0.5F) * 8.0F;
    //        this.world.addParticle(ParticleTypes.EXPLOSION, this.getX() + (double)f, this.getY() + 2.0 + (double)g, this.getZ() + (double)h, 0.0, 0.0, 0.0);
    //    }
    //}


    @Override
    protected void updatePostDeath() {
        ++this.ticksSinceDeath;
        if (this.ticksSinceDeath >= 180 && this.ticksSinceDeath <= 200) {
            float f = (this.random.nextFloat() - 0.5F) * 8.0F;
            float g = (this.random.nextFloat() - 0.5F) * 4.0F;
            float h = (this.random.nextFloat() - 0.5F) * 8.0F;
            this.world.addParticle(ParticleTypes.EXPLOSION_EMITTER, this.getX() + (double)f, this.getY() + 2.0 + (double)g, this.getZ() + (double)h, 0.0, 0.0, 0.0);
        }

        boolean bl = this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT);
        int i = 500;
        if (this.world instanceof ServerWorld) {
            if (this.ticksSinceDeath > 150 && this.ticksSinceDeath % 5 == 0 && bl) {
                ExperienceOrbEntity.spawn((ServerWorld)this.world, this.getPos(), MathHelper.floor((float)i * 0.08F));
            }

            if (this.ticksSinceDeath == 1 && !this.isSilent()) {
                this.world.syncGlobalEvent(1028, this.getBlockPos(), 0);
            }
        }

        this.move(MovementType.SELF, new Vec3d(0.0, 0.10000000149011612, 0.0));
        this.setYaw(this.getYaw() + 20.0F);
        this.bodyYaw = this.getYaw();
        if (this.ticksSinceDeath == 200 && this.world instanceof ServerWorld) {
            if (bl) {
                ExperienceOrbEntity.spawn((ServerWorld)this.world, this.getPos(), MathHelper.floor((float)i * 0.2F));
            }

            this.remove(RemovalReason.KILLED);
        }
    }

    protected boolean parentDamage(DamageSource source, float amount) {
        return super.damage(source, amount);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0f)
                .add(EntityAttributes.GENERIC_ARMOR, 15.0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0f);
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
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.erebus.walk", true));
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
}
