package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.underplayer97.ResonantEnemies.item.custom.AegisLegsArmorItem;
import net.underplayer97.ResonantEnemies.item.custom.NimhsyEarsArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class NimhsyEarsArmorRenderer extends GeoArmorRenderer<NimhsyEarsArmorItem> {

    public NimhsyEarsArmorRenderer() {
        super(new NimhsyEarsArmorModel());

        this.headBone = "armorHead";


    }

}

