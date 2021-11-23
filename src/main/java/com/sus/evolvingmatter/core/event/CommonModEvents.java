package com.sus.evolvingmatter.core.event;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.renderer.EvolutionStandRenderer;
import com.sus.evolvingmatter.client.renderer.SoulGhostRenderer;
import com.sus.evolvingmatter.common.entity.SoulGhost;
import com.sus.evolvingmatter.core.init.BlockEntityInit;
import com.sus.evolvingmatter.core.init.EntityInit;
import com.sus.evolvingmatter.core.world.OreGeneration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.example.GeckoLibMod;
import software.bernie.example.client.renderer.entity.ExampleGeoRenderer;
import software.bernie.example.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = EvolvingMatter.MOD_ID,bus= Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(EntityInit.SOUL_GHOST.get(), SoulGhost.createAttributes().build());
    }
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(OreGeneration::registerOres);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        if (!FMLEnvironment.production && !GeckoLibMod.DISABLE_IN_DEV) {
            event.registerEntityRenderer(EntityInit.SOUL_GHOST.get(), SoulGhostRenderer::new);
            event.registerBlockEntityRenderer(BlockEntityInit.EVOLUTION_STAND_BLOCK_ENTITY.get(), EvolutionStandRenderer::new);
            /*
            event.registerBlockEntityRenderer(TileRegistry.BOTARIUM_TILE.get(), BotariumTileRenderer::new);
            event.registerBlockEntityRenderer(TileRegistry.FERTILIZER.get(), FertilizerTileRenderer::new);

            event.registerEntityRenderer(EntityType.CREEPER, ReplacedCreeperRenderer::new);

             */
        }
    }











/*
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {
        if (!FMLEnvironment.production && !GeckoLibMod.DISABLE_IN_DEV) {
            GeoArmorRenderer.registerArmorRenderer(PotatoArmorItem.class, new PotatoArmorRenderer());
        }
    }

    @SubscribeEvent
    public static void registerRenderers(final FMLClientSetupEvent event) {
        if (!FMLEnvironment.production && !GeckoLibMod.DISABLE_IN_DEV) {
            ItemBlockRenderTypes.setRenderLayer(BlockRegistry.BOTARIUM_BLOCK.get(), RenderType.cutout());
        }
    }
*/
}
