package com.sus.evolvingmatter.client.events;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.KeyMaps;
import com.sus.evolvingmatter.core.init.RecipeInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod.EventBusSubscriber(modid = EvolvingMatter.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class ClientModEvent {

    private  ClientModEvent(){}

    @SubscribeEvent
    public static void clientSetup(EntityRenderersEvent.RegisterLayerDefinitions event){
        KeyMaps.init();
    }
}
