package com.sus.evolvingmatter.client.renderer;

import com.sus.evolvingmatter.client.renderer.model.AttachmentTableItemModel;
import com.sus.evolvingmatter.client.renderer.model.StaffOfPoisonModel;
import com.sus.evolvingmatter.common.item.blockitem.AttachmentTableItem;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AttachmentTableItemRenderer extends GeoItemRenderer<AttachmentTableItem> {
    public AttachmentTableItemRenderer() {
        super(new AttachmentTableItemModel());
    }
}
