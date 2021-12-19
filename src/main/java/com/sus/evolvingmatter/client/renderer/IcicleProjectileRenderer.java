package com.sus.evolvingmatter.client.renderer;

import com.sus.evolvingmatter.client.renderer.model.IcicleProjectileModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class IcicleProjectileRenderer extends GeoProjectilesRenderer {
    public IcicleProjectileRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new IcicleProjectileModel());
    }
}
