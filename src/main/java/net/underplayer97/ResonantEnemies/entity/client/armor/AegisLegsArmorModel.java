package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.item.custom.AegisLegsArmorItem;
import net.underplayer97.ResonantEnemies.item.custom.BlackHatArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AegisLegsArmorModel extends AnimatedGeoModel<AegisLegsArmorItem> {
    @Override
    public Identifier getModelLocation(AegisLegsArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/armor/aegis_legs.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AegisLegsArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/models/armor/aegis_legs.png");
    }

    @Override
    public Identifier getAnimationFileLocation(AegisLegsArmorItem animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/armor.animation.json");
    }
}
