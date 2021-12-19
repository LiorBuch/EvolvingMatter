package com.sus.evolvingmatter.common.block.entity;

import com.sus.evolvingmatter.common.item.IEvolvingItem;
import com.sus.evolvingmatter.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EvolutionStandBlockEntity extends BlockEntity implements IAnimatable{


    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    public int ticks = 0;
    private final AnimationFactory factory = new AnimationFactory(this);

    public EvolutionStandBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityInit.EVOLUTION_STAND_BLOCK_ENTITY.get(), pos, state);
    }


    public void tick() {
        this.ticks++;
    }

    private ItemStackHandler createHandler() {
        final int INPUT_SLOT=0;
        final int OUTPUT_SLOT=6;
        final int CATALYST_SLOT=5;

        return new ItemStackHandler(7) {
            @Override
            public void setSize(int size) {
                super.setSize(7);
            }

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            // item blacklist define
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if (slot==OUTPUT_SLOT){
                    return false;
                }
                if (slot==INPUT_SLOT){
                    return stack.getItem() instanceof IEvolvingItem; // interface for modded tools
                }
                if (slot==CATALYST_SLOT){
                    return stack.getItem() instanceof IEvolvingItem.ICatalyst;
                }
                return stack.getItem() instanceof IEvolvingItem.IMaterial;
            }
        };
    }

    @Override
    public void load(CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        super.load(tag);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());
        return super.save(tag);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return save(new CompoundTag());
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

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }
}
