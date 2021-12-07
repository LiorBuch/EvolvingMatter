package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class StaffOfPoison extends Item{
    public enum Stage{
        NORMAL,
        BREAKTHROW,
        EVOLUTION
    }
    private int sec = 20;
    public StaffOfPoison(Properties p_41383_,Stage stage) {
        super(p_41383_);
        this.stageOfWeapon =stage;
    }
    private final Stage stageOfWeapon;
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, sec*3);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if(!world.isClientSide) {
            if (stageOfWeapon==Stage.NORMAL) {
                PoisonProjectile poisonProjectile = new PoisonProjectile(player, player.getX(), player.getY() + 1, player.getZ(), 0.0D, -0.8D, 0.0D, world,this.stageOfWeapon);
                poisonProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.0F);
                world.addFreshEntity(poisonProjectile);
            }
            if (stageOfWeapon==Stage.BREAKTHROW){
                PoisonProjectile poisonProjectile = new PoisonProjectile(player, player.getX(), player.getY() + 1, player.getZ(), 0.0D, -0.8D, 0.0D, world,this.stageOfWeapon);
                poisonProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 0.0F);
                world.addFreshEntity(poisonProjectile);
            }
        }
        return super.use(world, player, hand);
    }
}
