package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.core.event.HeartMech;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HeartOfLuck extends Item{

    public HeartOfLuck(Properties p_41383_) {
        super(p_41383_);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {
                                                            //Reward Mechanism\\
        ItemStack itemStack = new ItemStack(HeartMech.getItemLike());
        itemStack.setCount(HeartMech.getCount());
        player.drop(itemStack,true,false);
        HeartMech.setCount(1);
                                                          //FireWork Mechanism\\
        //FireworkRocketEntity fireworkEntity = new FireworkRocketEntity(level,);

        return super.use(level, player, handIn);
    }
}
