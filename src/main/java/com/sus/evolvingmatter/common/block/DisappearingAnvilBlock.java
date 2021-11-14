package com.sus.evolvingmatter.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class DisappearingAnvilBlock extends AnvilBlock {
    public DisappearingAnvilBlock(Properties p_48777_) {
        super(p_48777_);
    }

    @Override
    public void onLand(Level level, BlockPos blockPos, BlockState blockState, BlockState p_48796_, FallingBlockEntity p_48797_) {
        super.onLand(level, blockPos, blockState, p_48796_, p_48797_);
        level.setBlock(blockPos, Blocks.AIR.defaultBlockState(),0);
    }
}
