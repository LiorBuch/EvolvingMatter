package com.sus.evolvingmatter.client.renderer;

import com.sus.evolvingmatter.client.renderer.model.StaffOfPoisonModel;
import com.sus.evolvingmatter.common.item.StaffOfPoison;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class StaffOfPoisonRenderer extends GeoItemRenderer<StaffOfPoison> {

    public StaffOfPoisonRenderer() {
        super(new StaffOfPoisonModel());
    }

}
