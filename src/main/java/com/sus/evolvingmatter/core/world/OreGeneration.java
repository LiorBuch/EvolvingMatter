package com.sus.evolvingmatter.core.world;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.core.init.BlockInit;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.List;

@Mod.EventBusSubscriber(modid = EvolvingMatter.MOD_ID)
public class OreGeneration {
    private static final HashSet<PlacedFeature> FEATURES = new HashSet<>();


    public static final RuleTest END_STONE_CONFIG = new BlockMatchTest(Blocks.END_STONE);
    public static final RuleTest STONE_CONFIG = new BlockMatchTest(Blocks.STONE);
    public static final RuleTest AIR = new BlockMatchTest(Blocks.AIR);

    private static boolean configuredFeaturesRegistered = false;

    //Ore registration
    public static void registerOres() { // called in onCommonSetup(FMLCommonSetupEvent event)
        for (OreConfigForGen ore : OreConfigForGen.values()) {
            BlockState oreDefault = ore.stoneOre.defaultBlockState();
            BlockState oreDeepslateDefault = ore.deepSlateOre.defaultBlockState();
            List<OreConfiguration.TargetBlockState> targetBlockStates = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, oreDefault),
                    OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, oreDeepslateDefault));
            ConfiguredFeature<?, ?> feature = FeatureUtils.register(ore.name(),
                    Feature.ORE.configured(new OreConfiguration(targetBlockStates, ore.sizeOfVein)));
            PlacedFeature placed = PlacementUtils.register(ore.oreName, feature.placed(List.of(
                    CountPlacement.of(ore.vinesInChunk),InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(ore.minY), VerticalAnchor.absolute(ore.maxY)), BiomeFilter.biome())));
            FEATURES.add(placed);
        }
    }

    private static <Config extends FeatureConfiguration> ConfiguredFeature<Config, ?> register(String name, ConfiguredFeature<Config, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(EvolvingMatter.MOD_ID, name), configuredFeature);
    }

    @Mod.EventBusSubscriber(modid = EvolvingMatter.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ForgeBusSubscriber {
        @SubscribeEvent
        public static void biomeLoading(BiomeLoadingEvent event) {
            BiomeGenerationSettingsBuilder gen = event.getGeneration();
            if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND) {
                for (PlacedFeature feature : FEATURES) {
                    gen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature);
                }
            }

            /*if (event.getCategory()== Biome.BiomeCategory.ICY){    for biome specific
                OreGeneration.END_ORES.forEach(ore -> features.add(()-> ore));
            }
             */
        }
    }

    public enum OreConfigForGen {
        soulstone(BlockInit.SOUL_STONE_ORE.get(),BlockInit.SOUL_STONE_ORE_DEEPSLATE.get(),-60,100,10,10,"soulstone_ore");

        private final Block stoneOre;
        private final Block deepSlateOre;
        private final int minY;
        private final int maxY;
        private final int sizeOfVein;
        private final int vinesInChunk;
        private final String oreName;

        OreConfigForGen(Block blockForStone, Block blockForDeepSlate,int minimumY,int maximumY,int sizeOfTheVein,int numberOfVinesInChunk,String oreName) {
            this.stoneOre = blockForStone;
            this.deepSlateOre = blockForDeepSlate;
            this.minY=minimumY;
            this.maxY =maximumY;
            this.sizeOfVein=sizeOfTheVein;
            this.vinesInChunk=numberOfVinesInChunk;
            this.oreName=oreName;
        }
    }
}
