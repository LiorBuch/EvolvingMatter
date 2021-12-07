package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class DimenstionInit {

    public static final ResourceKey<DimensionType> CAVE_DIM_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(EvolvingMatter.MOD_ID, "cavedim"));
    public static final ResourceKey<Level> CAVE_DIM = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(EvolvingMatter.MOD_ID, "cavedim"));
}
