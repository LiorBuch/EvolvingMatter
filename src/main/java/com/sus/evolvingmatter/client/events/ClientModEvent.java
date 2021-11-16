package com.sus.evolvingmatter.client.events;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.renderer.SoulGhostRenderer;
import com.sus.evolvingmatter.client.renderer.model.SoulGhostModel;
import com.sus.evolvingmatter.core.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EvolvingMatter.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientModEvent {

    private  ClientModEvent(){}

    @SubscribeEvent
    public static void clientSetup(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(SoulGhostModel.LAYER_LOCATION,SoulGhostModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(EntityInit.SOUL_GHOST.get() , SoulGhostRenderer::new);
    }
}
