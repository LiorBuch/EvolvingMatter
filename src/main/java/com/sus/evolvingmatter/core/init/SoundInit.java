package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EvolvingMatter.MOD_ID);
    // SoulGhost
    public static final RegistryObject<SoundEvent> SOUL_GHOST_AMBIENT = SOUNDS.register("soul_ghost_ambient",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.soul_stone_ghost.ambient")));

    public static final RegistryObject<SoundEvent> SOUL_GHOST_HURT = SOUNDS.register("soul_ghost_hurt",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.soul_stone_ghost.hurt")));

    public static final RegistryObject<SoundEvent> SOUL_GHOST_DEATH = SOUNDS.register("soul_ghost_death",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.soul_stone_ghost.death")));

    // Fiend
    public static final RegistryObject<SoundEvent> FIEND_AMBIENT = SOUNDS.register("fiend_ambient",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.fiend.ambient")));

    public static final RegistryObject<SoundEvent> FIEND_HURT = SOUNDS.register("fiend_hurt",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.fiend.hurt")));

    public static final RegistryObject<SoundEvent> FIEND_DEATH = SOUNDS.register("fiend_death",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.fiend.death")));
    //Spells
    public static final RegistryObject<SoundEvent> ARROW_SHOT = SOUNDS.register("shot_arrow",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"item.staff_of_arrows.sho")));

    public static final RegistryObject<SoundEvent> ICE_SHOT = SOUNDS.register("spell_ice",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"item.staff_of_ice.shot")));



    public SoundInit(){
    }
}
