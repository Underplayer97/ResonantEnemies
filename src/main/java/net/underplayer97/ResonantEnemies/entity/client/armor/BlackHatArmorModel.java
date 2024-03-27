package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.item.custom.BlackHatArmorItem;
import net.underplayer97.ResonantEnemies.item.custom.PurpleHatArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BlackHatArmorModel extends AnimatedGeoModel<BlackHatArmorItem> {
    @Override
    public Identifier getModelLocation(BlackHatArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/armor/tophat.geo.json");
    }

    @Override
    public Identifier getTextureLocation(BlackHatArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/models/armor/tophat/tophat_black.png");
    }

    @Override
    public Identifier getAnimationFileLocation(BlackHatArmorItem animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/armor.animation.json");
    }
}
