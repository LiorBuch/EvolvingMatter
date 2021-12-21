package com.sus.evolvingmatter.client.renderer.model;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.item.StaffOfPoison;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StaffOfPoisonModel extends AnimatedGeoModel<StaffOfPoison> {
    @Override
    public ResourceLocation getModelLocation(StaffOfPoison object) {
        StaffOfPoison.Stage stage = object.getStageOfWeapon();
        if (stage == StaffOfPoison.Stage.EVOLUTION)
            return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/staff_of_poison_e.geo.json");
        return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/staff_of_poison_b.geo.json");

    }

    @Override
    public ResourceLocation getTextureLocation(StaffOfPoison object) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/items/staff_of_poison.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(StaffOfPoison animatable) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "animations/staff_of_poison_e.animation.json");
    }
}
