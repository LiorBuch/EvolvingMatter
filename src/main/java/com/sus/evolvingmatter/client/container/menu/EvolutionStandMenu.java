package com.sus.evolvingmatter.client.container.menu;

import com.sus.evolvingmatter.common.item.IEvolvingItem;
import com.sus.evolvingmatter.core.init.BlockInit;
import com.sus.evolvingmatter.core.init.ContainerInit;
import com.sus.evolvingmatter.core.crafting.EvolvingRecipe;
import com.sus.evolvingmatter.core.crafting.RecipeInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;


public class EvolutionStandMenu extends AbstractContainerMenu {
    private BlockEntity blockEntity;
    private Player playerEntity;
    private IItemHandler playerInventory;

    private EvolvingRecipe evolvingRecipe;
    protected final RecipeType<?> recipeType = RecipeInit.EVOLVING;

    private final ResultContainer resultContainer = new ResultContainer();
    private final CraftingContainer craftingContainer = new CraftingContainer(this,3,3);

    public EvolutionStandMenu(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(ContainerInit.EVOLUTION_STAND_MENU.get(), windowId);

        blockEntity = world.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0,28,13));
                for (int i =0;i<4;i++){ // basic needs
                    this.addSlot(new SlotItemHandler(h,i+1,10+(i*18),43));
                }
                this.addSlot(new SlotItemHandler(h,5,105,36)); // transformation stone slot, if needed
                this.addSlot(new SlotItemHandler(h,6,147,13)); // output slot

            });
        }

        layoutPlayerInventorySlots(10, 70);
    }



    @Override
    public void removed(Player player) {
        super.removed(player);
        clearContainer(player, craftingContainer);
    }


    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, BlockInit.EVOLUTION_STAND.get());
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index<7) {
                if (!this.moveItemStackTo(stack, 7, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else { // slot define
                if (stack.getItem() instanceof IEvolvingItem) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) { //(Slotindex(false),Slotindex(true) ,true/false )
                        return ItemStack.EMPTY;
                    }
                }
                else if (stack.getItem() instanceof IEvolvingItem.ICatalyst) {
                    if (!this.moveItemStackTo(stack, 5, 6, false)) { //(Slot range 5<=X<6,true/false )
                        return ItemStack.EMPTY;
                    }
                }
                else if (stack.getItem() instanceof IEvolvingItem.IMaterial) {
                    if (!this.moveItemStackTo(stack, 1, 5, false)) { //(Slot range ,true/false )
                        return ItemStack.EMPTY;
                    }
                }
                else if (index < 28) {
                    if (!this.moveItemStackTo(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.moveItemStackTo(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

}
