package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.item.custom.AegisLegsArmorItem;
import net.underplayer97.ResonantEnemies.item.custom.NimhsyEarsArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NimhsyEarsArmorModel extends AnimatedGeoModel<NimhsyEarsArmorItem> {
    @Override
    public Identifier getModelLocation(NimhsyEarsArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/armor/nimhsy_ears.geo.json");
    }

    @Override
    public Identifier getTextureLocation(NimhsyEarsArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/models/armor/nimhsy_ears.png");
    }

    @Override
    public Identifier getAnimationFileLocation(NimhsyEarsArmorItem animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/armor.animation.json");
    }
}
