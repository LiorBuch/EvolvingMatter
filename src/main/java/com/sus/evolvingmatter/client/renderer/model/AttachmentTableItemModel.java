package com.sus.evolvingmatter.client.renderer.model;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.item.StaffOfPoison;
import com.sus.evolvingmatter.common.item.blockitem.AttachmentTableItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AttachmentTableItemModel extends AnimatedGeoModel<AttachmentTableItem> {


    @Override
    public ResourceLocation getModelLocation(AttachmentTableItem object) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/attachment_table_item.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AttachmentTableItem object) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/blocks/attachment_table.png");

    }

    @Override
    public ResourceLocation getAnimationFileLocation(AttachmentTableItem animatable) {
        return null;
    }
}
