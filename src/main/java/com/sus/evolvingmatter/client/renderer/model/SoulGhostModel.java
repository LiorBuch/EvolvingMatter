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
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SoulGhostModel extends AnimatedGeoModel<SoulGhost> {
	@Override
	public ResourceLocation getModelLocation(SoulGhost object)
	{
		return new ResourceLocation(EvolvingMatter.MOD_ID, "geo/soul_ghost.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(SoulGhost object)
	{
		return new ResourceLocation(EvolvingMatter.MOD_ID, "textures/entities/soul_ghost.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(SoulGhost object)
	{
		return new ResourceLocation(EvolvingMatter.MOD_ID, "animations/soul_ghost.animation.json");
	}
}