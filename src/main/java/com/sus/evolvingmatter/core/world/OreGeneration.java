package com.sus.evolvingmatter.core.world;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.core.init.BlockInit;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class OreGeneration {

    public static final List<ConfiguredFeature<?,?>> OVERWORLD_ORES = new ArrayList<>();
    public static final List<ConfiguredFeature<?,?>> END_ORES = new ArrayList<>();
    public static final List<ConfiguredFeature<?,?>> NETHER_ORES = new ArrayList<>();

    public static final RuleTest END_STONE_CONFIG = new BlockMatchTest(Blocks.END_STONE);
    public static final RuleTest STONE_CONFIG = new BlockMatchTest(Blocks.STONE);

    public static void registerOres() {
        ConfiguredFeature<?, ?> soulStoneOre = Feature.ORE.configured(new OreConfiguration(List.of(OreConfiguration
                .target(STONE_CONFIG, BlockInit.SOUL_STONE_ORE.get().defaultBlockState()),
                OreConfiguration.target(OreConfiguration.Predicates.DEEPSLATE_ORE_REPLACEABLES,BlockInit.SOUL_STONE_ORE_DEEPSLATE.get().defaultBlockState())), 4))
                .rangeUniform(VerticalAnchor.bottom(),VerticalAnchor.top()).squared().count(100);
        OVERWORLD_ORES.add(register("soul_stone_ore",soulStoneOre));

    }

    private static <Config extends FeatureConfiguration> ConfiguredFeature<Config,?> register(String name,ConfiguredFeature<Config,?> configuredFeature){
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,new ResourceLocation(EvolvingMatter.MOD_ID,name),configuredFeature);
    }

    @Mod.EventBusSubscriber(modid = EvolvingMatter.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBusSubscriber{
        @SubscribeEvent
        public static void biomeLoading(BiomeLoadingEvent event){
            List<Supplier<ConfiguredFeature<?,?>>> features = event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);
            /*if (event.getCategory()== Biome.BiomeCategory.ICY){    for biome specific
                OreGeneration.END_ORES.forEach(ore -> features.add(()-> ore));
            }
             */

            switch (event.getCategory()){
                case NETHER -> OreGeneration.NETHER_ORES.forEach(ore -> features.add(()-> ore));
                case THEEND -> OreGeneration.END_ORES.forEach(ore -> features.add(()-> ore));
                default -> OreGeneration.OVERWORLD_ORES.forEach(ore -> features.add(()-> ore));
            }
        }
    }
}
