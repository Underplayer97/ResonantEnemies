package net.underplayer97.ResonantEnemies.entity.boss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.entity.util.ModAttributes;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class GrimsleyEntity extends AbstractBossEntity implements IAnimatable {

    boolean isDead = false;
    public int ticksSinceDeath;
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public GrimsleyEntity(EntityType<? extends AbstractBossEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 400.0f)
                .add(EntityAttributes.GENERIC_ARMOR, 15.0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 15.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 50.0f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 4.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0f);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AttackGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));

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
    protected void updatePostDeath() {
        ++this.ticksSinceDeath;
        this.isDead = true;
        if (this.ticksSinceDeath == 200 && this.world instanceof ServerWorld) {

            this.remove(RemovalReason.KILLED);
        }
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (isDead){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grimsley.defeat", false));
            return PlayState.CONTINUE;

        } else if (this.getVelocity().getX() !=0 || this.getVelocity().getZ()!=0) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grimsley.walk", true));
            return PlayState.CONTINUE;
        }

        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grimsley.idle", true));
        return PlayState.CONTINUE;
    }

    private <T extends IAnimatable> PlayState attackPredicate(AnimationEvent<T> event) {
        if (this.handSwinging) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.grimsley.punch", false));
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
