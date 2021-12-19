package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.common.entity.thrown.IcicleProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class StaffOfIce extends Item implements IAnimatable, IEvolvingItem {
    public AnimationFactory factory = new AnimationFactory(this);
    private final StaffOfIce.Stage stageOfWeapon;

    public enum Stage{
        NORMAL,
        BREAKTHROW,
        EVOLUTION
    }
    private int sec = 20;
    public StaffOfIce(Properties p_41383_, StaffOfIce.Stage stage) {
        super(p_41383_);
        this.stageOfWeapon=stage;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, (int) (sec*2.5));
        if(!world.isClientSide) {
            IcicleProjectile icicleProjectile = new IcicleProjectile(player, player.getX(), player.getY() + 1, player.getZ(), 0.0D, -0.1D, 0.0D, world);
            icicleProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.0F);
            world.addFreshEntity(icicleProjectile);
        }
        return  super.use(world, player, hand);
    }

    @Override
    public ItemStack getEvolution() {
        return null;
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
