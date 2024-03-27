package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.underplayer97.ResonantEnemies.item.custom.BlackHatArmorItem;
import net.underplayer97.ResonantEnemies.item.custom.CrowArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class CrowArmorRenderer extends GeoArmorRenderer<CrowArmorItem> {

    public CrowArmorRenderer() {
        super(new CrowArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";


    }

}

