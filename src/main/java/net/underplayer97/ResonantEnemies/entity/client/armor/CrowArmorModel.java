package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.minecraft.util.Identifier;
import net.underplayer97.ResonantEnemies.ResonantMain;
import net.underplayer97.ResonantEnemies.item.custom.BlackHatArmorItem;
import net.underplayer97.ResonantEnemies.item.custom.CrowArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CrowArmorModel extends AnimatedGeoModel<CrowArmorItem> {
    @Override
    public Identifier getModelLocation(CrowArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "geo/armor/crow_armor.geo.json");
    }

    @Override
    public Identifier getTextureLocation(CrowArmorItem object) {
        return new Identifier(ResonantMain.MOD_ID, "textures/entity/crow/crow.png");
    }

    @Override
    public Identifier getAnimationFileLocation(CrowArmorItem animatable) {
        return new Identifier(ResonantMain.MOD_ID, "animations/armor.animation.json");
    }
}
