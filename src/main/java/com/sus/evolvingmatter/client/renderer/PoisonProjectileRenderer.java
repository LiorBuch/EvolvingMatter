package com.sus.evolvingmatter.client.renderer;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.renderer.model.PoisonProjectileModel;
import com.sus.evolvingmatter.client.renderer.model.SoulGhostModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class PoisonProjectileRenderer extends GeoProjectilesRenderer {

    private static final ResourceLocation TEXTURE = new ResourceLocation(EvolvingMatter.MOD_ID,"textures/entities/poison_projectile.png");

    public PoisonProjectileRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new PoisonProjectileModel());
        //this.shadowSize = 0.7F; //change 0.7 to the desired shadow size.
    }

}
