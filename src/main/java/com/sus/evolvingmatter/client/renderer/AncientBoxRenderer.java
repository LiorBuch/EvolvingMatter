package com.sus.evolvingmatter.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sus.evolvingmatter.client.renderer.model.AncientBoxModel;
import com.sus.evolvingmatter.client.renderer.model.EvolutionStandModel;
import com.sus.evolvingmatter.common.block.AncientBox;
import com.sus.evolvingmatter.common.block.entity.AncientBoxBlockEntity;
import com.sus.evolvingmatter.common.block.entity.EvolutionStandBlockEntity;
import com.sus.evolvingmatter.common.item.blockitem.AttachmentTableItem;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class AncientBoxRenderer extends GeoBlockRenderer<AncientBoxBlockEntity> {
    public AncientBoxRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super(rendererDispatcherIn, new AncientBoxModel());
    }

    @Override
    public RenderType getRenderType(AncientBoxBlockEntity animatable, float partialTicks, PoseStack stack,
                                    MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
