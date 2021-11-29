package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.effects.ZenPoisonEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EvolvingMatter.MOD_ID);

    public static final RegistryObject<MobEffect> ZEN_POISON_EFFECT = EFFECTS.register("zen_poison_effect", () -> new ZenPoisonEffect(MobEffectCategory.HARMFUL,0x0d8c2d));

}
