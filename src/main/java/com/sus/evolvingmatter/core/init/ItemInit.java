package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.item.HeartOfLuck;
import com.sus.evolvingmatter.common.item.HeartOfMobs;
import com.sus.evolvingmatter.common.item.SoulStone;
import com.sus.evolvingmatter.common.item.StaffOfTheBlackSmith;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemInit {
    //DeferredRegister
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EvolvingMatter.MOD_ID);
    //Special Items

    public static final RegistryObject<SoulStone> SOUL_STONE = ITEMS.register("soul_stone",()-> new SoulStone(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<HeartOfLuck> HEART_OF_LUCK = ITEMS.register("heart_of_luck",()-> new HeartOfLuck(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<HeartOfMobs> HEART_OF_MOBS = ITEMS.register("heart_of_mobs",()-> new HeartOfMobs(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<StaffOfTheBlackSmith> STAFF_OF_THE_BLACK_SMITH = ITEMS.register("staff_of_the_black_smith",()-> new StaffOfTheBlackSmith(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));

   // public static final RegistryObject<DaggerOfNoob> DAGGER_OF_NOOB =ITEMS.register("dagger_of_noob",()-> new DaggerOfNoob(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

                                //Tools
                                //Items
   // public static final RegistryObject<Item> SOUL_STONE_BUNDLE = ITEMS.register("soul_stone_Bundle", () -> new Item(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));

    //Blocks
}
