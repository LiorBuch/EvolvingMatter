package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import com.sus.evolvingmatter.util.IDMG;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StaffOfArrows extends Item {
    private int sec = 20;
    public StaffOfArrows(Properties p_41383_) {
        super(p_41383_);
    }
/*
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, sec*1);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if(!world.isClientSide) {
            Arrow arrow = new Arrow(world, player);
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), (-2f), 1.0f, 1.0f);
            arrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
            world.addFreshEntity(arrow);
        }

        return super.use(world, player, hand);
    }
    */

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, sec*3);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if(!world.isClientSide) {
            PoisonProjectile poisonProjectile = new PoisonProjectile(player, player.getX(), player.getY()+1, player.getZ(), 0.0D, -0.8D, 0.0D, world);
            poisonProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.0F);
            world.addFreshEntity(poisonProjectile);
        }
        return super.use(world, player, hand);
    }
}
