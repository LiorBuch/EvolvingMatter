package com.sus.evolvingmatter.util;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IDMG {
    boolean isDamageBlockableByMod(LivingEntity entity, DamageSource damageSource, float amount, ItemStack itemStack);


}
