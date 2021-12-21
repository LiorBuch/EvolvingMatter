package com.sus.evolvingmatter.client.renderer.model;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.item.StaffOfArrows;
import com.sus.evolvingmatter.common.item.StaffOfPoison;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StaffOfArrowsModel extends AnimatedGeoModel<StaffOfArrows> {
    @Override
    public ResourceLocation getModelLocation(StaffOfArrows object) {
        StaffOfArrows.Stage stage = object.getStageOfWeapon();
        if (stage == StaffOfArrows.Stage.EVOLUTION)
            return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/staff_of_arrows_e.geo.json");
        if (stage == StaffOfArrows.Stage.BREAKTHROW)
            return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/staff_of_arrows_b.geo.json");
        return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/staff_of_arrows.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(StaffOfArrows object) {
        StaffOfArrows.Stage stage = object.getStageOfWeapon();
        if (stage == StaffOfArrows.Stage.EVOLUTION)
            return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/items/staff_of_arrows_e.png");
        if (stage == StaffOfArrows.Stage.BREAKTHROW)
            return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/items/staff_of_arrows_b.png");
        return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/items/staff_of_arrows.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(StaffOfArrows animatable) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "animations/staff_of_arrows.animation.json");
    }
}
