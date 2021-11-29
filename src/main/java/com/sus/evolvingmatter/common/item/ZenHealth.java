package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.util.IDMG;
import com.sus.evolvingmatter.util.ModDamageSource;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ZenHealth extends Item implements IDMG {

    float BASE_HP = 100F;
    float MULTIPLIER = 1F;
    float NATURAL_REGENERATE=0.05F; // 1 ZHP Per second
    public static final String ZEN_TAG=new ResourceLocation(EvolvingMatter.MOD_ID,"zen_hp").toString();
    float MAX_HP = BASE_HP*MULTIPLIER;


    public ZenHealth(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        CompoundTag itemTag = this.getOrCreateZenTag(itemStack);
        CompoundTag zenHPTag = itemStack.getOrCreateTagElement(ZEN_TAG);
        float addingHP = zenHPTag.getFloat("hp_amount")+NATURAL_REGENERATE;
        if (addingHP>MAX_HP)
            addingHP=MAX_HP;
        zenHPTag.putFloat("hp_amount",addingHP);
        itemTag.put(ZEN_TAG,zenHPTag);

    }

    public CompoundTag getOrCreateZenTag(ItemStack itemStack){
        if (itemStack.hasTag()){
            return itemStack.getTag();
        }
        CompoundTag itemTag = itemStack.getOrCreateTag();
        CompoundTag zenHPTag = itemStack.getOrCreateTagElement(ZEN_TAG);
        zenHPTag.putFloat("hp_amount",BASE_HP*MULTIPLIER);
        itemTag.put(ZEN_TAG,zenHPTag);
        return itemTag;

    }
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, level, list, tooltipFlag);
        appendTooltipExtra(itemStack, level, list, tooltipFlag);
    }
    public void appendTooltipExtra(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag flags) {
        CompoundTag itemTag = itemStack.getOrCreateTag();
        CompoundTag zenHPTag = itemStack.getTagElement(ZEN_TAG);
        float amount = zenHPTag != null ? zenHPTag.getFloat("hp_amount") : MAX_HP;

        components.add(new TranslatableComponent("tooltip.zen_health.hp", String.valueOf((int) amount), MAX_HP).withStyle(ChatFormatting.YELLOW));
        components.add(new TextComponent(""));
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
                entity.hurt(DamageSource.DRAGON_BREATH,sub);
            }
            zenHPTag.putFloat("hp_amount",sub);
            itemTag.put(ZEN_TAG,zenHPTag);

            return true;
        }
        return false;
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {

        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        if(stack.hasTag()) {
            CompoundTag zenHPTag = stack.getTagElement(ZEN_TAG);
            float currentHP = zenHPTag.getFloat("hp_amount");
            if (currentHP<0)
                currentHP=0;
            if (currentHP==MAX_HP)
                currentHP=currentHP-1;
            return 1- ((double) currentHP / (double) MAX_HP);
        }
        return 1;
    }


    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return Mth.hsvToRgb(Math.max(0.0F, (float) (1.0F - getDurabilityForDisplay(stack))) / 3.0F, 1.0F, 1.0F);
    }
}
