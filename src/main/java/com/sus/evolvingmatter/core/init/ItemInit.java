package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.item.ZenHealth;
import com.sus.evolvingmatter.common.item.*;
import com.sus.evolvingmatter.common.item.blockitem.AttachmentTableItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ItemInit {
    //DeferredRegister
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EvolvingMatter.MOD_ID);
    //Special Items
    public static final RegistryObject<SoulStone> SOUL_STONE = ITEMS.register("soul_stone",()-> new SoulStone(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<HeartOfLuck> HEART_OF_LUCK = ITEMS.register("heart_of_luck",()-> new HeartOfLuck(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<HeartOfMobs> HEART_OF_MOBS = ITEMS.register("heart_of_mobs",()-> new HeartOfMobs(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));

    public static final RegistryObject<StaffOfTheBlackSmith> STAFF_OF_THE_BLACK_SMITH = ITEMS.register("staff_of_the_black_smith",()-> new StaffOfTheBlackSmith(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<StaffOfArrows> STAFF_OF_ARROWS = ITEMS.register("staff_of_arrows",()-> new StaffOfArrows(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab), StaffOfArrows.Stage.NORMAL));
    public static final RegistryObject<StaffOfArrows> STAFF_OF_ARROWSB = ITEMS.register("staff_of_arrows_b",()-> new StaffOfArrows(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab), StaffOfArrows.Stage.BREAKTHROW));
    public static final RegistryObject<StaffOfArrows> STAFF_OF_ARROWSE = ITEMS.register("staff_of_arrows_e",()-> new StaffOfArrows(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab), StaffOfArrows.Stage.EVOLUTION));

    public static final RegistryObject<StaffOfPoison> STAFF_OF_POISON = ITEMS.register("staff_of_poison",()-> new StaffOfPoison(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab), StaffOfPoison.Stage.NORMAL));
    public static final RegistryObject<StaffOfPoison> STAFF_OF_POISONB = ITEMS.register("staff_of_poison_b",()-> new StaffOfPoison(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab), StaffOfPoison.Stage.BREAKTHROW));
    public static final RegistryObject<StaffOfPoison> STAFF_OF_POISONE = ITEMS.register("staff_of_poison_e",()-> new StaffOfPoison(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab), StaffOfPoison.Stage.EVOLUTION));

    public static final RegistryObject<StaffOfIce> STAFF_OF_ICE = ITEMS.register("staff_of_ice",()-> new StaffOfIce(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab), StaffOfIce.Stage.NORMAL));

    public static final RegistryObject<ForgeSpawnEggItem> SOUL_GHOST_SPAWN_EGG = ITEMS.register("soul_ghost_spawn_egg",()-> new ForgeSpawnEggItem(EntityInit.SOUL_GHOST,0xc6cfc6,0x34BD27,new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab).stacksTo(16)));
    public static final RegistryObject<Item> SOUL_STONE_SHARD = ITEMS.register("soul_stone_shard",()->new Item(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<Item> ANCIENT_ORB = ITEMS.register("ancient_orb",()->new AncinetOrb(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<FadeApple> FADE_APPLE = ITEMS.register("fade_apple", ()->new FadeApple(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab).food(FoodInit.FADE_APPLE_PROPERTIES)));

    public static final RegistryObject<Item> SOUL_STONE_SMALL_BUNDLE = ITEMS.register("soul_stone_small_bundle",()-> new Bundle(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab),ItemInit.SOUL_STONE.get(), Bundle.BundleSize.SMALL));
    public static final RegistryObject<Item> SOUL_STONE_MEDIUM_BUNDLE = ITEMS.register("soul_stone_medium_bundle",()-> new Bundle(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab),ItemInit.SOUL_STONE.get(), Bundle.BundleSize.MEDIUM));
    public static final RegistryObject<Item> SOUL_STONE_LARGE_BUNDLE = ITEMS.register("soul_stone_large_bundle",()-> new Bundle(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab),ItemInit.SOUL_STONE.get(), Bundle.BundleSize.LARGE));

    public static final RegistryObject<Item> FADE_APPLE_SMALL_BUNDLE = ITEMS.register("fade_apple_small_bundle",()-> new Bundle(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab),ItemInit.FADE_APPLE.get(), Bundle.BundleSize.SMALL));
    public static final RegistryObject<Item> FADE_APPLE_MEDIUM_BUNDLE = ITEMS.register("fade_apple_medium_bundle",()-> new Bundle(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab),ItemInit.FADE_APPLE.get(), Bundle.BundleSize.MEDIUM));
    public static final RegistryObject<Item> FADE_APPLE_LARGE_BUNDLE = ITEMS.register("fade_apple_large_bundle",()-> new Bundle(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab),ItemInit.FADE_APPLE.get(), Bundle.BundleSize.LARGE));

    public static final RegistryObject<Item> ZEN_HEALTH_MKI = ITEMS.register("zen_health_mki",()-> new ZenHealth(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab), ZenHealth.HeartLevel.MKI));
    public static final RegistryObject<Item> ZEN_HEALTH_MKII = ITEMS.register("zen_health_mkii",()-> new ZenHealth(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab), ZenHealth.HeartLevel.MKII));
    public static final RegistryObject<Item> ZEN_HEALTH_MKIII = ITEMS.register("zen_health_mkiii",()-> new ZenHealth(new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab), ZenHealth.HeartLevel.MKIII));

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
    public static final RegistryObject<BlockItem> FADE_APPLE_LEAVES_ITEM = ITEMS.register("fade_apple_leaves",
            () -> new BlockItem(BlockInit.FADE_APPLE_LEAVES.get(), new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<BlockItem> CLOUD_ITEM = ITEMS.register("cloud_block1",
            () -> new BlockItem(BlockInit.CLOUD_BLOCK.get(), new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<BlockItem> EVOLUTION_STAND_BLOCK = ITEMS.register("evolution_stand_block",
            () -> new BlockItem(BlockInit.EVOLUTION_STAND.get(), new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
    public static final RegistryObject<BlockItem> ATTACHMENT_TABLE_BLOCK = ITEMS.register("attachment_table",
            () -> new AttachmentTableItem(BlockInit.ATTACHEMENT_TABLE.get(), new Item.Properties().tab(EvolvingMatter.EvolvingMatterTab)));
}
