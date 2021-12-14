package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.gui.ZenGui;
import com.sus.evolvingmatter.core.init.PacketHandler;
import com.sus.evolvingmatter.core.network.ZenHealthNetworkToServer;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class ZenHealth extends Item implements IDMG {

    public enum HeartLevel {
        MKI(0.05F,100F,1F)
        ,MKII(0.1F,300F,1F)
        ,MKIII(0.2F,550F,1F)
        ,MKIV(0.1F,200F,1F)
        ,MKV(0.1F,200F,1F)
        ,MKVI(0.1F,200F,1F)
        ,MKVII(0.1F,200F,1F)
        ,MKVIII(0.1F,200F,1F)
        ,MKIX(0.1F,200F,1F)
        ,MKX(0.1F,200F,1F);

        private final float regen;
        private final float base;
        private final float multi;

         HeartLevel(float regenHP, float baseHP, float multiplier){
            this.regen=regenHP;
            this.base=baseHP;
            this.multi=multiplier;
        }
    }

    private final HeartLevel heartLevel;
    public static final String ZEN_TAG=new ResourceLocation(EvolvingMatter.MOD_ID,"zen_hp").toString();


    public ZenHealth(Properties p_41383_, HeartLevel lvl) {
        super(p_41383_);
        this.heartLevel=lvl;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        CompoundTag itemTag = this.getOrCreateZenTag(itemStack);
        CompoundTag zenHPTag = itemStack.getOrCreateTagElement(ZEN_TAG);
        float addingHP = zenHPTag.getFloat("hp_amount")+ heartLevel.regen;
        if (addingHP>heartLevel.base*heartLevel.multi)
            addingHP=heartLevel.base*heartLevel.multi;
        zenHPTag.putFloat("hp_amount",addingHP);
        itemTag.put(ZEN_TAG,zenHPTag);

        ZenGui.currentHP=addingHP;
        ZenGui.maxHP=heartLevel.base*heartLevel.multi;
    }

    public CompoundTag getOrCreateZenTag(ItemStack itemStack){
        if (itemStack.hasTag()){
            return itemStack.getTag();
        }
        CompoundTag itemTag = itemStack.getOrCreateTag();
        CompoundTag zenHPTag = itemStack.getOrCreateTagElement(ZEN_TAG);
        zenHPTag.putFloat("hp_amount",heartLevel.base*heartLevel.multi);
        zenHPTag.putFloat("max_hp_amount",heartLevel.base*heartLevel.multi);
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
        float amount = zenHPTag != null ? zenHPTag.getFloat("hp_amount") : heartLevel.base*heartLevel.multi;

        components.add(new TranslatableComponent("tooltip.zen_health.hp", String.valueOf((int) amount), heartLevel.base*heartLevel.multi).withStyle(ChatFormatting.YELLOW));
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
            if (currentHP==heartLevel.base*heartLevel.multi)
                currentHP=currentHP-1;
            return 1- ((double) currentHP / (double) heartLevel.base*heartLevel.multi);
        }
        return 1;
    }


    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return Mth.hsvToRgb(Math.max(0.0F, (float) (1.0F - getDurabilityForDisplay(stack))) / 3.0F, 1.0F, 1.0F);
    }
}
