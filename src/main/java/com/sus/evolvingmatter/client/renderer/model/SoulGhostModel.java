// Made with Blockbench 4.0.4
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports
package com.sus.evolvingmatter.client.renderer.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.entity.SoulGhost;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SoulGhostModel<T extends SoulGhost> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EvolvingMatter.MOD_ID, "soul_ghost"), "main");
	private final ModelPart body;
	private final ModelPart arms;

	public SoulGhostModel(ModelPart root) {
		this.body = root.getChild("body");
		this.arms = root.getChild("arms");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -11.0F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition arms = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(5, 20).addBox(-1.0F, -4.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-4.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 19).addBox(-1.0F, -4.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 15).addBox(-4.0F, -4.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(10, 15).addBox(2.0F, -4.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(5, 15).addBox(-4.0F, -4.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(2.0F, -4.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(2.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, buffer, packedLight, packedOverlay);
		arms.render(poseStack, buffer, packedLight, packedOverlay);
	}
}