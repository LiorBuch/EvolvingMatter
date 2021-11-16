package com.sus.evolvingmatter.client.renderer;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.renderer.model.SoulGhostModel;
import com.sus.evolvingmatter.common.entity.SoulGhost;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SoulGhostRenderer<Type extends SoulGhost> extends MobRenderer<Type, SoulGhostModel<Type>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(EvolvingMatter.MOD_ID,"textures/entities/soul_ghost.png");

    public SoulGhostRenderer(EntityRendererProvider.Context context) {
        super(context,new SoulGhostModel<>(context.bakeLayer(SoulGhostModel.LAYER_LOCATION)),0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(Type p_114482_) {
        return TEXTURE;
    }
}
