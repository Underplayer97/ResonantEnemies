package net.underplayer97.ResonantEnemies.entity.boss;

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
    public int latestSegment = -1;
    public int ticksSinceDeath;
    public final double[][] segmentCircularBuffer = new double[64][3];
    //private final ErebusPart[] parts;
    //public final ErebusPart head = new ErebusPart(this, "head", 1.0f, 1.0f);
    //private final ErebusPart body = new ErebusPart(this, "torso", 1.0f, 1.0f);
    //private final ErebusPart topHandL = new ErebusPart(this, "topHandL", 1.0f, 1.0f);
    //private final ErebusPart topHandR = new ErebusPart(this, "topHandR", 1.0f, 1.0f);
    //private final ErebusPart bottomHandL = new ErebusPart(this, "bottomHandL", 1.0f, 1.0f);
    //private final ErebusPart bottomHandR = new ErebusPart(this, "bottomHandR", 1.0f, 1.0f);


    public ErebusEntity(EntityType<? extends ErebusEntity> entityType, World world) {
        super(entityType, world);
        //this.parts = new ErebusPart[]{this.head, this.body, this.topHandL, this.topHandR, this.bottomHandL, this.bottomHandR};
        this.setHealth(this.getMaxHealth());

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

    public double[] getSegmentProperties(int segmentNumber, float tickDelta) {
        if (this.isDead()) {
            tickDelta = 0.0F;
        }

        tickDelta = 1.0F - tickDelta;
        int i = this.latestSegment - segmentNumber & 63;
        int j = this.latestSegment - segmentNumber - 1 & 63;
        double[] ds = new double[3];
        double d = this.segmentCircularBuffer[i][0];
        double e = MathHelper.wrapDegrees(this.segmentCircularBuffer[j][0] - d);
        ds[0] = d + e * (double)tickDelta;
        d = this.segmentCircularBuffer[i][1];
        e = this.segmentCircularBuffer[j][1] - d;
        ds[1] = d + e * (double)tickDelta;
        ds[2] = MathHelper.lerp((double)tickDelta, this.segmentCircularBuffer[i][2], this.segmentCircularBuffer[j][2]);
        return ds;
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
