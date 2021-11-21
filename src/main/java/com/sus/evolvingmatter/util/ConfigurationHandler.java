package com.sus.evolvingmatter.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

public class ConfigurationHandler {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final Spawn SPAWN = new Spawn(BUILDER);

    public static class Spawn {
        public final ForgeConfigSpec.IntValue min;
        public final ForgeConfigSpec.IntValue max;
        public final ForgeConfigSpec.IntValue weight;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> include;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> exclude;

        Spawn(ForgeConfigSpec.Builder builder) {
            builder.push("spawn chances");
            builder.comment("Configure penguins spawn weight & min/max group size. Set weight to 0 to disable.");
            min = builder.defineInRange("min", 1, 0, 64);
            max = builder.defineInRange("max", 4, 0, 64);
            weight = builder.defineInRange("weight", 7, 0, 100);
            builder.pop();
            builder.push("spawnable biomes");
            include = builder.defineList("include", Collections.singletonList(FOREST.toString()), o -> o instanceof String && (o.equals("") || BiomeDictionary.Type.getAll().contains(BiomeDictionarySupport.getType(o.toString()))));
            exclude = builder.defineList("exclude", Arrays.asList(END.toString(), MOUNTAIN.toString(), OCEAN.toString(), NETHER.toString()), o -> o instanceof String && (o.equals("") || BiomeDictionary.Type.getAll().contains(BiomeDictionarySupport.getType(o.toString()))));
            builder.pop();
        }
    }

    public static final ForgeConfigSpec spec = BUILDER.build();
}