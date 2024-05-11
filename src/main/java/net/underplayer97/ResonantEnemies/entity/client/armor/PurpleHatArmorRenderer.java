package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.underplayer97.ResonantEnemies.item.custom.PurpleHatArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class PurpleHatArmorRenderer extends GeoArmorRenderer<PurpleHatArmorItem> {

    public PurpleHatArmorRenderer() {
        super(new PurpleHatArmorModel());

        this.headBone = "armorHead";


    }

}

