package net.underplayer97.ResonantEnemies.entity.util;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.underplayer97.ResonantEnemies.entity.boss.EntityPart;

import java.util.Collection;

public interface WorldMultipartHelper {
    default Collection<EntityPart> getPMEParts() {
        return getPMEPartMap().values();
    }

    Int2ObjectMap<EntityPart> getPMEPartMap();
}
