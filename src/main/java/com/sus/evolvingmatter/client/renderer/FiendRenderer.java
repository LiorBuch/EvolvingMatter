package com.sus.evolvingmatter.client.renderer;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.renderer.model.FiendModel;
import com.sus.evolvingmatter.client.renderer.model.SoulGhostModel;
import com.sus.evolvingmatter.common.entity.Fiend;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class FiendRenderer extends GeoEntityRenderer<Fiend> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(EvolvingMatter.MOD_ID,"textures/entities/fiend.png");

    public FiendRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new FiendModel());
        //this.shadowSize = 0.7F; //change 0.7 to the desired shadow size.
    }

}
