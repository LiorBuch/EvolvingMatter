package com.sus.evolvingmatter.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sus.evolvingmatter.client.renderer.model.AttachmentTableModel;
import com.sus.evolvingmatter.client.renderer.model.EvolutionStandModel;
import com.sus.evolvingmatter.common.block.entity.AttachmentTableBlockEntity;
import com.sus.evolvingmatter.common.block.entity.EvolutionStandBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class AttachmentTableRenderer extends GeoBlockRenderer<AttachmentTableBlockEntity> {

    public AttachmentTableRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new AttachmentTableModel());
    }

    @Override
    public RenderType getRenderType(AttachmentTableBlockEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
