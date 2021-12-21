package com.sus.evolvingmatter.client.renderer;

import com.sus.evolvingmatter.client.renderer.model.StaffOfArrowsModel;
import com.sus.evolvingmatter.client.renderer.model.StaffOfPoisonModel;
import com.sus.evolvingmatter.common.item.StaffOfArrows;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class StaffOfArrowsRenderer extends GeoItemRenderer<StaffOfArrows> {

    public StaffOfArrowsRenderer() {
        super(new StaffOfArrowsModel());
    }

}
