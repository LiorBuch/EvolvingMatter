package com.sus.evolvingmatter.core.event;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.renderer.*;
import com.sus.evolvingmatter.common.entity.Fiend;
import com.sus.evolvingmatter.common.entity.SoulGhost;
import com.sus.evolvingmatter.core.init.*;
import com.sus.evolvingmatter.core.world.OreGeneration;
import com.sus.evolvingmatter.util.IDMG;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import software.bernie.example.GeckoLibMod;

@Mod.EventBusSubscriber(modid = EvolvingMatter.MOD_ID,bus= Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
        event.put(EntityInit.SOUL_GHOST.get(), SoulGhost.createAttributes().build());
        event.put(EntityInit.FIEND.get(), Fiend.createAttributes().build());
    }
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(OreGeneration::registerOres);
        event.enqueueWork(PacketHandler::init);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        if (!FMLEnvironment.production && !GeckoLibMod.DISABLE_IN_DEV) {
            event.registerEntityRenderer(EntityInit.SOUL_GHOST.get(), SoulGhostRenderer::new);
            event.registerEntityRenderer(EntityInit.FIEND.get(), FiendRenderer::new);
            event.registerEntityRenderer(EntityInit.POISONPROJECTILE.get(), PoisonProjectileRenderer::new);
            event.registerEntityRenderer(EntityInit.ICICLE_PROJECTILE.get(), IcicleProjectileRenderer::new);
            event.registerBlockEntityRenderer(BlockEntityInit.EVOLUTION_STAND_BLOCK_ENTITY.get(), EvolutionStandRenderer::new);
            event.registerBlockEntityRenderer(BlockEntityInit.ATTACHMENT_TABLE_BLOCK_ENTITY.get(),AttachmentTableRenderer::new);
            event.registerBlockEntityRenderer(BlockEntityInit.ANCIENT_BOX_BLOCK_ENTITY.get(),AncientBoxRenderer::new);

            /*
            event.registerBlockEntityRenderer(TileRegistry.BOTARIUM_TILE.get(), BotariumTileRenderer::new);
            event.registerBlockEntityRenderer(TileRegistry.FERTILIZER.get(), FertilizerTileRenderer::new);

            event.registerEntityRenderer(EntityType.CREEPER, ReplacedCreeperRenderer::new);

             */
        }
    }
}
