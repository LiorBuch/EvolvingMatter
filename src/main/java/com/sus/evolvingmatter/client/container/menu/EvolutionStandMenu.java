package com.sus.evolvingmatter.client.container.menu;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.item.IEvolvingItem;
import com.sus.evolvingmatter.core.init.BlockInit;
import com.sus.evolvingmatter.core.init.ContainerInit;
import com.sus.evolvingmatter.core.crafting.EvolvingRecipe;
import com.sus.evolvingmatter.core.crafting.RecipeInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.List;


public class EvolutionStandMenu extends AbstractContainerMenu {
    private BlockEntity blockEntity;
    private Player playerEntity;
    private IItemHandler playerInventory;
    private final List<EvolvingRecipe> recipes;
    private EvolvingRecipe selectedRecipe;
    private Level level;
    private ResultContainer resultSlot = new ResultContainer();

    final int INPUT_SLOT = 0;
    final int OUTPUT_SLOT = 6;
    final int CATALYST_SLOT = 5;

    protected final Container inputSlots = new SimpleContainer(6) {

        public void setChanged() {
            super.setChanged();
            EvolutionStandMenu.this.slotsChanged(this);
        }
    };

    public EvolutionStandMenu(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player player) {
        super(ContainerInit.EVOLUTION_STAND_MENU.get(), windowId);
        this.level = level;
        this.recipes = level.getRecipeManager().getAllRecipesFor(RecipeInit.EVOLVING);

        blockEntity = level.getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new Slot(inputSlots, 0, 28, 13));
                for (int i = 0; i < 4; i++) { // basic needs
                    this.addSlot(new Slot(inputSlots, i + 1, 10 + (i * 18), 43));
                }
                this.addSlot(new Slot(inputSlots, 5, 105, 36)); // transformation stone slot, if needed
                this.addSlot(new Slot(resultSlot, 6, 147, 13) {

                    public boolean mayPlace(ItemStack p_39818_) {
                        return false;
                    }

                    public boolean mayPickup(Player p_39813_) {
                        return EvolutionStandMenu.this.mayPickup(p_39813_, this.hasItem());
                    }

                    public void onTake(Player p_150604_, ItemStack p_150605_) {
                        EvolutionStandMenu.this.onTake(p_150604_, p_150605_);
                    }
                }); // output slot
            });
        }

        layoutPlayerInventorySlots(10, 70);
    }

    @Override
    public void slotsChanged(Container pInventory) {
        super.slotsChanged(pInventory);
        createResult();
    }

    protected boolean mayPickup(Player p_39798_, boolean p_39799_) {
        return this.selectedRecipe != null && this.selectedRecipe.matches(this.inputSlots, this.level);
    }


    protected void onTake(Player player, ItemStack itemStack) {
        itemStack.onCraftedBy(player.level, player, itemStack.getCount());
        this.resultSlot.awardUsedRecipes(player);
        blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            for (int i = 0; i < 6; i++) {
                var item = this.inputSlots.getItem(i);
                item.shrink(1);
                this.inputSlots.setItem(i, item);
            }
        });
        ContainerLevelAccess.NULL.execute((p_40263_, p_40264_) -> {
            p_40263_.levelEvent(1044, p_40264_, 0);
        });
    }


    protected boolean isValidBlock(BlockState p_39788_) {
        return false;
    }


    public void createResult() {
        blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            List<EvolvingRecipe> list = this.level.getRecipeManager().getRecipesFor(RecipeInit.EVOLVING, this.inputSlots, this.level);
            if (list.isEmpty()) {
                this.resultSlot.setItem(0, ItemStack.EMPTY);
            } else {
                this.selectedRecipe = list.get(0);
                ItemStack itemstack = this.selectedRecipe.assemble(inputSlots);
                this.resultSlot.setRecipeUsed(this.selectedRecipe);
                this.resultSlot.setItem(0, itemstack);
            }
        });

    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        clearContainer(player, inputSlots);
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
            if (index < 7) {
                if (!this.moveItemStackTo(stack, 7, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else { // slot define
                if (stack.getItem() instanceof IEvolvingItem) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) { //(Slotindex(false),Slotindex(true) ,true/false )
                        return ItemStack.EMPTY;
                    }
                } else if (stack.getItem() instanceof IEvolvingItem.ICatalyst) {
                    if (!this.moveItemStackTo(stack, 5, 6, false)) { //(Slot range 5<=X<6,true/false )
                        return ItemStack.EMPTY;
                    }
                } else if (stack.getItem() instanceof IEvolvingItem.IMaterial) {
                    if (!this.moveItemStackTo(stack, 1, 5, false)) { //(Slot range ,true/false )
                        return ItemStack.EMPTY;
                    }
                } else if (index < 28) {
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
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
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
