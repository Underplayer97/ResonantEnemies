package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.underplayer97.ResonantEnemies.item.custom.BlackHatArmorItem;
import net.underplayer97.ResonantEnemies.item.custom.PurpleHatArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class BlackHatArmorRenderer extends GeoArmorRenderer<BlackHatArmorItem> {

    public BlackHatArmorRenderer() {
        super(new BlackHatArmorModel());

        this.headBone = "armorHead";


    }

}

