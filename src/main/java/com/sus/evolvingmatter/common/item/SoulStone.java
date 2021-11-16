package com.sus.evolvingmatter.common.item;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SoulStone extends Item {
    public SoulStone(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,30,3));
        player.getCooldowns().addCooldown(this,100);

        return super.use(level, player, hand);
    }
}
