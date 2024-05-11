package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.item.custom.AegisLegsArmorItem;
import net.underplayer97.ResonantEnemies.item.custom.WoundedAegisLegsArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WoundedAegisLegsArmorModel extends AnimatedGeoModel<WoundedAegisLegsArmorItem> {
    @Override
    public Identifier getModelLocation(WoundedAegisLegsArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/armor/wounded_aegis_legs.geo.json");
    }

    @Override
    public Identifier getTextureLocation(WoundedAegisLegsArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/models/armor/aegis_legs.png");
    }

    @Override
    public Identifier getAnimationFileLocation(WoundedAegisLegsArmorItem animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/armor.animation.json");
    }
}
