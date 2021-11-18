package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.item.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
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
    public static final RegistryObject<ForgeSpawnEggItem> SOUL_GHOST_SPAWN_EGG = ITEMS.register("soul_ghost_spawn_egg",()-> new ForgeSpawnEggItem(EntityInit.SOUL_GHOST,0xc6cfc6,0x34BD27,new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab).stacksTo(16)));
    public static final RegistryObject<Item> SOUL_STONE_SHARD = ITEMS.register("soul_stone_shard",()->new Item(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<FadeApple> FADE_APPLE = ITEMS.register("fade_apple", ()->new FadeApple(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab).food(FoodInit.FADE_APPLE_PROPERTIES)));
    public static final RegistryObject<Item> SOUL_STONE_SMALL_BUNDLE = ITEMS.register("soul_stone_small_bundle",()-> new Bundle(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab),ItemInit.SOUL_STONE.get(), Bundle.BundleSize.SMALL));
    public static final RegistryObject<Item> FADE_APPLE_SMALL_BUNDLE = ITEMS.register("fade_apple_small_bundle",()-> new Bundle(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab),ItemInit.FADE_APPLE.get(), Bundle.BundleSize.SMALL));

   // public static final RegistryObject<DaggerOfNoob> DAGGER_OF_NOOB =ITEMS.register("dagger_of_noob",()-> new DaggerOfNoob(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

                                //Tools
                                //Items
   // public static final RegistryObject<Item> SOUL_STONE_BUNDLE = ITEMS.register("soul_stone_Bundle", () -> new Item(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));

    //Blocks
    public static final RegistryObject<BlockItem> SOUL_STONE_ORE_ITEM = ITEMS.register("soul_stone_ore",
            () -> new BlockItem(BlockInit.SOUL_STONE_ORE.get(), new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<BlockItem> SOUL_STONE_ORE_DEEPSLATE_ITEM = ITEMS.register("soul_stone_ore_deepslate",
            () -> new BlockItem(BlockInit.SOUL_STONE_ORE_DEEPSLATE.get(), new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<BlockItem> DISAPPEARING_ANVIL = ITEMS.register("disappearing_anvil_block",
            () -> new BlockItem(BlockInit.DISAPPEARING_ANVIL_BLOCK.get(), new Item.Properties()));
}
