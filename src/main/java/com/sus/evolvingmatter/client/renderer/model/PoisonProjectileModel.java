package com.sus.evolvingmatter.client.renderer.model;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class PoisonProjectileModel extends AnimatedGeoModel<PoisonProjectile> {

    @Override
    public ResourceLocation getModelLocation(PoisonProjectile object)
    {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/poison_projectile.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PoisonProjectile object)
    {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/entities/poison_projectile.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PoisonProjectile object)
    {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "animations/poison_projectile.animation.json");
    }
}

