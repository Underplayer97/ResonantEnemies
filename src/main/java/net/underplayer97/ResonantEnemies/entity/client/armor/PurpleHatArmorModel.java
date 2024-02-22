package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.item.custom.PurpleHatArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PurpleHatArmorModel extends AnimatedGeoModel<PurpleHatArmorItem> {
    @Override
    public Identifier getModelLocation(PurpleHatArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/tophat.geo.json");
    }

    @Override
    public Identifier getTextureLocation(PurpleHatArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/models/armor/tophat/tophat_purple.png");
    }

    @Override
    public Identifier getAnimationFileLocation(PurpleHatArmorItem animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/armor.animation.json");
    }
}
