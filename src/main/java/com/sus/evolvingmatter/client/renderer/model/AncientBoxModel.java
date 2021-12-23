package com.sus.evolvingmatter.client.renderer.model;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.block.AncientBox;
import com.sus.evolvingmatter.common.item.blockitem.AttachmentTableItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AncientBoxModel extends AnimatedGeoModel {

    @Override
    public ResourceLocation getModelLocation(Object object) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/ancient_box.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object object) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/blocks/ancient_box.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Object animatable) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "animations/ancient_box.animation.json");
    }
}
