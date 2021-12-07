package com.sus.evolvingmatter.core.network;

import com.sus.evolvingmatter.util.IDMG;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ClientAccess {
    public static boolean updateForGui(ItemStack itemStack) {
        final ItemStack itemStackOffHand = Minecraft.getInstance().player.getItemBySlot(EquipmentSlot.OFFHAND);
        if (itemStack.getItem() instanceof IDMG) {
            return true;
        }
    return false;
    }
}
