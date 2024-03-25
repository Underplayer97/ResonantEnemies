package net.underplayer97.ResonantEnemies.entity.client.armor;

import net.underplayer97.ResonantEnemies.item.custom.AegisLegsArmorItem;
import net.underplayer97.ResonantEnemies.item.custom.BlackHatArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class AegisLegsArmorRenderer extends GeoArmorRenderer<AegisLegsArmorItem> {

    public AegisLegsArmorRenderer() {
        super(new AegisLegsArmorModel());

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

