package com.sus.evolvingmatter.common.item;

import com.sus.evolvingmatter.client.renderer.StaffOfPoisonRenderer;
import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.function.Consumer;

public class StaffOfPoison extends Item implements IAnimatable,IEvolvingItem {
    public AnimationFactory factory = new AnimationFactory(this);

    @Override
    public ItemStack getEvolution() {
        return null;
    }

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
            if (stageOfWeapon==Stage.EVOLUTION){
                PoisonProjectile poisonProjectile = new PoisonProjectile(player, player.getX(), player.getY() + 1, player.getZ(), 0.0D, -0.8D, 0.0D, world,this.stageOfWeapon);
                poisonProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 0.0F);
                world.addFreshEntity(poisonProjectile);
            }
        }
        return super.use(world, player, hand);
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event)
    {
        if (this.stageOfWeapon==Stage.EVOLUTION) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.staff_of_poison_e.sticks", true));
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.staff_of_poison_e.cube", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 20, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new StaffOfPoisonRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

}
