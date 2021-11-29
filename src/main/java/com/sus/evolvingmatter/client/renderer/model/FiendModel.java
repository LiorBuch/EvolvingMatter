package com.sus.evolvingmatter.client.renderer.model;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.entity.Fiend;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class FiendModel extends AnimatedGeoModel<Fiend> {

    @Override
    public ResourceLocation getModelLocation(Fiend object) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/fiend.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Fiend object) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/entities/fiend.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Fiend animatable) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "animations/fiend.animation.json");
    }
}
