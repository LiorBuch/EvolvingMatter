package com.sus.evolvingmatter.common.block.entity;

import com.sus.evolvingmatter.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class EvolutionStandBlockEntity extends BlockEntity implements IAnimatable {
    public int ticks = 0;
    private final AnimationFactory factory = new AnimationFactory(this);

    public EvolutionStandBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.EVOLUTION_STAND_BLOCK_ENTITY.get(), pos, state);
    }
    public void tick() {
        this.ticks++;
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        return super.save(compoundTag);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return save(new CompoundTag());
    }


    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        var tag = new CompoundTag();
        tag.putInt("beans",this.ticks);
        return new ClientboundBlockEntityDataPacket(this.worldPosition,-1,save(tag));
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }

    private <E extends BlockEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().transitionLengthTicks = 0;
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.evolution_stand.book", true));
        return PlayState.CONTINUE;
    }
    private <E extends BlockEntity & IAnimatable> PlayState predicate2(AnimationEvent<E> event) {
        event.getController().transitionLengthTicks = 0;
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.evolution_stand.particles", true));
        return PlayState.CONTINUE;
    }
//animations
    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController(this, "controller2", 0, this::predicate2));

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
