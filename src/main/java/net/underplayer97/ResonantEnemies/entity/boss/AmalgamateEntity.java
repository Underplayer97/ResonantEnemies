package net.underplayer97.ResonantEnemies.entity.boss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.Text;
import net.minecraft.world.World;

public class AmalgamateEntity extends AbstractBossEntity {
    public AmalgamateEntity(EntityType<? extends AbstractBossEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public BossBar.Color getBossColor() {
        return null;
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
}
