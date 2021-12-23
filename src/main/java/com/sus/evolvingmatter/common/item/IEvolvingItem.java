package com.sus.evolvingmatter.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface IEvolvingItem {
    ItemStack getEvolution();

    interface ICatalyst {
    }
    interface IMaterial{
    }
    interface IGems{}
    interface IArrowsGUI{}

}
