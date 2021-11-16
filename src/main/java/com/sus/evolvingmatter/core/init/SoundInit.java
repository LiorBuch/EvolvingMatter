package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public final class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EvolvingMatter.MOD_ID);

    public static final RegistryObject<SoundEvent> SOUL_GHOST_AMBIENT = SOUNDS.register("soul_ghost_ambient",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.soul_stone_ghost.ambient")));

    public static final RegistryObject<SoundEvent> SOUL_GHOST_HURT = SOUNDS.register("soul_ghost_hurt",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.soul_stone_ghost.hurt")));

    public static final RegistryObject<SoundEvent> SOUL_GHOST_DEATH = SOUNDS.register("soul_ghost_death",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.soul_stone_ghost.death")));



    public SoundInit(){
    }
}
