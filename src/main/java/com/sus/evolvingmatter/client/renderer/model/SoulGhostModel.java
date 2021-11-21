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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class SoulGhostModel<T extends SoulGhost> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(EvolvingMatter.MOD_ID, "soul_ghost"), "main");
	private final ModelPart body;
	private final ModelPart arm1;
	private final ModelPart arm2;
	private final ModelPart arm3;
	private final ModelPart arm4;
	private final ModelPart arm5;
	private final ModelPart arm6;
	private final ModelPart arm7;
	private final ModelPart arm8;

	public SoulGhostModel(ModelPart root) {
		this.body = root.getChild("body");
		this.arm1 = root.getChild("arm1");
		this.arm2 = root.getChild("arm2");
		this.arm3 = root.getChild("arm3");
		this.arm4 = root.getChild("arm4");
		this.arm5 = root.getChild("arm5");
		this.arm6 = root.getChild("arm6");
		this.arm7 = root.getChild("arm7");
		this.arm8 = root.getChild("arm8");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -11.0F, -3.0F, 7.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition arm1 = partdefinition.addOrReplaceChild("arm1", CubeListBuilder.create().texOffs(5, 20).addBox(-1.0F, -7.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition arm2 = partdefinition.addOrReplaceChild("arm2", CubeListBuilder.create().texOffs(0, 20).addBox(-4.0F, -7.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition arm3 = partdefinition.addOrReplaceChild("arm3", CubeListBuilder.create().texOffs(19, 19).addBox(-1.0F, -7.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition arm4 = partdefinition.addOrReplaceChild("arm4", CubeListBuilder.create().texOffs(15, 15).addBox(-4.0F, -7.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition arm5 = partdefinition.addOrReplaceChild("arm5", CubeListBuilder.create().texOffs(10, 15).addBox(2.0F, -7.0F, 3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition arm6 = partdefinition.addOrReplaceChild("arm6", CubeListBuilder.create().texOffs(5, 15).addBox(-4.0F, -7.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition arm7 = partdefinition.addOrReplaceChild("arm7", CubeListBuilder.create().texOffs(0, 15).addBox(2.0F, -7.0F, -3.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition arm8 = partdefinition.addOrReplaceChild("arm8", CubeListBuilder.create().texOffs(0, 0).addBox(2.0F, -7.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		ModelPart[] armsArray = new ModelPart[]{arm1,arm2,arm3,arm4,arm5,arm6,arm7,arm8};
		for(int i = 0; i < armsArray.length; ++i) {
			armsArray[i].xRot = 0.2F * Mth.sin(ageInTicks * 0.3F + (float)i) + 0.4F;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, buffer, packedLight, packedOverlay);
		arm1.render(poseStack, buffer, packedLight, packedOverlay);
		arm2.render(poseStack, buffer, packedLight, packedOverlay);
		arm3.render(poseStack, buffer, packedLight, packedOverlay);
		arm4.render(poseStack, buffer, packedLight, packedOverlay);
		arm5.render(poseStack, buffer, packedLight, packedOverlay);
		arm6.render(poseStack, buffer, packedLight, packedOverlay);
		arm7.render(poseStack, buffer, packedLight, packedOverlay);
		arm8.render(poseStack, buffer, packedLight, packedOverlay);
	}
}