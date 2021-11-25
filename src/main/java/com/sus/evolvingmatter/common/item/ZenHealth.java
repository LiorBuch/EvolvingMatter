package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.util.IDMG;
import com.sus.evolvingmatter.util.ModDamageSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ZenHealth extends Item implements IDMG {

    float BASE_HP = 100F;
    float MULTIPLIER = 1F;
    public static final String ZEN_TAG=new ResourceLocation(EvolvingMatter.MOD_ID,"zen_hp").toString();


    public ZenHealth(Properties p_41383_) {
        super(p_41383_);
    }

    public CompoundTag getOrCreateZenTag(ItemStack itemStack){
        if (itemStack.hasTag()){
            return itemStack.getTag();
        }
        CompoundTag itemTag = itemStack.getOrCreateTag();
        CompoundTag zenHPTag = itemStack.getOrCreateTagElement(ZEN_TAG);
        zenHPTag.putFloat("hp_amount",BASE_HP*MULTIPLIER);
        return itemTag;

    }

    @Override
    public boolean isDamageBlockableByMod(LivingEntity entity, DamageSource damageSource, float amount, ItemStack itemStack) {

        CompoundTag itemTag = this.getOrCreateZenTag(itemStack);
        CompoundTag zenHPTag = itemStack.getOrCreateTagElement(ZEN_TAG); //if not found creates
        float zenHP = zenHPTag.getFloat("hp_amount");

        if (damageSource == ModDamageSource.ZEN_DAMAGE) {
            float sub = zenHP- amount;
            zenHPTag.putFloat("hp_amount", sub);
            if (sub<= 0) {
                entity.hurt(DamageSource.DRAGON_BREATH,100F);
            }
            zenHPTag.putFloat("hp_amount",sub);
            itemTag.put(ZEN_TAG,zenHPTag);

            return true;
        }
        return false;
    }



}
