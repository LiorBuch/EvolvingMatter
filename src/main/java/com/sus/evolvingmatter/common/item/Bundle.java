package com.sus.evolvingmatter.common.item;

import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import java.util.Random;

public class Bundle extends Item {

    public static Random r = new Random();

    public enum BundleSize{
        SMALL(1,4),
        MEDIUM(15,20),
        LARGE(40,50);

        private final int min;
        private final int max;

        BundleSize(int min,int max){
            this.max=max;
            this.min=min;
        }
    }

    private ItemLike bundleItem;
    private BundleSize bundleSize;
    public Bundle(Properties properties, ItemLike bundleItem, BundleSize bundleSize) {
        super(properties);

        this.bundleItem=bundleItem;
        this.bundleSize=bundleSize;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = new ItemStack(bundleItem);
        itemStack.setCount(Mth.randomBetweenInclusive(r,bundleSize.min,bundleSize.max));
        player.drop(itemStack, true, false);

        return super.use(level, player, hand);
    }
}
