package com.sus.evolvingmatter.client.renderer.model;

import com.sus.evolvingmatter.EvolvingMatter;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class EvolutionStandModel extends AnimatedGeoModel {


    @Override
    public ResourceLocation getModelLocation(Object object) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/evolution_stand.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object object) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/blocks/evolution_stand.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Object animatable) {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "animations/evolution_stand.animation.json");
    }
}
