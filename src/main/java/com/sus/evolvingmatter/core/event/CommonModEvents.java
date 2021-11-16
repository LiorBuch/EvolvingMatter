package com.sus.evolvingmatter.core.event;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.entity.SoulGhost;
import com.sus.evolvingmatter.core.init.EntityInit;
import com.sus.evolvingmatter.core.world.OreGeneration;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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

}
