package net.underplayer97.ResonantEnemies.entity.boss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.world.World;
import net.underplayer97.ResonantEnemies.entity.boss.erebus.ErebusPart;

public class ErebusEntity extends HostileEntity{

    private final ServerBossBar bossBar;
    private final ErebusPart[] parts;
    public final ErebusPart head = new ErebusPart(this, "head", 1.0f, 1.0f);
    private final ErebusPart body = new ErebusPart(this, "torso", 1.0f, 1.0f);
    private final ErebusPart hand1 = new ErebusPart(this, "hand1", 1.0f, 1.0f);
    private final ErebusPart hand2 = new ErebusPart(this, "hand2", 1.0f, 1.0f);
    private final ErebusPart hand3 = new ErebusPart(this, "hand3", 1.0f, 1.0f);
    private final ErebusPart hand4 = new ErebusPart(this, "hand4", 1.0f, 1.0f);


    protected ErebusEntity(EntityType<? extends ErebusEntity> entityType, World world) {
        super(entityType, world);
        this.parts = new ErebusPart[]{this.head, this.body, this.hand1, this.hand2, this.hand3, this.hand4};
        this.bossBar = (ServerBossBar) (new ServerBossBar(this.getDisplayName(), BossBar.Color.PURPLE, BossBar.Style.PROGRESS)).setDarkenSky(true);
        this.setHealth(this.getMaxHealth());

    }
}
