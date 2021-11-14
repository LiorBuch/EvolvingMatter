package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.core.event.HeartMech;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
        //FireWork Mechanism\\
        CompoundTag itemTag = new CompoundTag();
        CompoundTag fireworkTag = new CompoundTag();
        ListTag explosionListTag = new ListTag();
        CompoundTag explosionTag = new CompoundTag();

        explosionTag.putIntArray("Colors",new int[]{0x1f09e3});
        explosionTag.putIntArray("FadeColors",new int[]{0xffffff});
        explosionTag.putByte("Flicker", (byte) 1);
        explosionTag.putByte("Trail", (byte) 1);
        explosionTag.putByte("Type", (byte) 2);

        explosionListTag.add(explosionTag);

        fireworkTag.put("Explosions",explosionListTag);
        fireworkTag.putByte("Flight", (byte) 0);

        itemTag.put("Fireworks",fireworkTag);

        ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET, 1);
        firework.setTag(itemTag);
        FireworkRocketEntity fireworkEntity = new FireworkRocketEntity(level, player.getX(), player.getY(), player.getZ(),firework);
        level.addFreshEntity(fireworkEntity);

        //level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.AMETHYST_CLUSTER_HIT, SoundSource.NEUTRAL, 100F, 50F);
        return super.use(level, player, handIn);
    }
}
