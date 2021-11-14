package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.core.event.HeartMech;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HeartOfMobs extends Item {
    public HeartOfMobs(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand handIn) {
        //Reward Mechanism\\
        Entity entityToSpawn = HeartMech.setFriendlyMob(level);
        entityToSpawn.setPos(player.getX(),player.getY(),player.getZ());
        level.addFreshEntity(entityToSpawn);
        //FireWork Mechanism\\
        return super.use(level, player, handIn);
    }
}
