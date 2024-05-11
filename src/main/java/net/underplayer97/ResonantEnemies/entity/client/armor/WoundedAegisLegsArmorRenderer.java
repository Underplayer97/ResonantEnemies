package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.underplayer97.ResonantEnemies.item.custom.AegisLegsArmorItem;
import net.underplayer97.ResonantEnemies.item.custom.WoundedAegisLegsArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class WoundedAegisLegsArmorRenderer extends GeoArmorRenderer<WoundedAegisLegsArmorItem> {

    public WoundedAegisLegsArmorRenderer() {
        super(new WoundedAegisLegsArmorModel());

        this.bodyBone = "armorBody";


    }

}

