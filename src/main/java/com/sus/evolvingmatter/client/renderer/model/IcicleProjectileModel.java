package com.sus.evolvingmatter.client.renderer.model;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.entity.thrown.IcicleProjectile;
import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class IcicleProjectileModel extends AnimatedGeoModel<IcicleProjectile> {

    @Override
    public ResourceLocation getModelLocation(IcicleProjectile object)
    {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/icicle_projectile.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(IcicleProjectile object)
    {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/entities/icicle_projectile.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(IcicleProjectile object)
    {
        return new ResourceLocation(EvolvingMatter.MOD_ID, "animations/icicle_projectile.animation.json");
    }
}
