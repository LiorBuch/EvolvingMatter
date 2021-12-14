package com.sus.evolvingmatter.core.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FoodInit {
    public static final FoodProperties FADE_APPLE_PROPERTIES = (new FoodProperties.Builder()).nutrition(4).saturationMod(1.2F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1.0F)
            //.effect(new MobEffectInstance(EffectInit.ZEN_REGENERATION_EFFECT.get(), 400, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0F).alwaysEat().build();
}
