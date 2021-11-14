package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.core.init.BlockInit;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class StaffOfTheBlackSmith extends Item {
    public StaffOfTheBlackSmith(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity livingEntity, InteractionHand hand) {
        livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 40), player);

        BlockState blockState = BlockInit.DISAPPEARING_ANVIL_BLOCK.get().defaultBlockState();
        FallingBlockEntity fallingBlock = new FallingBlockEntity(livingEntity.level, livingEntity.getX(), livingEntity.getY() + 10, livingEntity.getZ(), blockState);
        fallingBlock.time = 1;
        fallingBlock.setHurtsEntities(3.0f, 40);
        livingEntity.level.addFreshEntity(fallingBlock);

        return super.interactLivingEntity(itemStack, player, livingEntity, hand);
    }

}
