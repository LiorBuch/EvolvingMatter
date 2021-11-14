package com.sus.evolvingmatter.core.event;

import com.sus.evolvingmatter.core.init.ItemInit;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import java.util.Random;

public class HeartMech {
    public static int count;
    public static ItemLike itemLike;


    public static ItemLike setItemForLoot(){ //setting rarity and index
        Random r = new Random();
        int rarity = r.nextInt(10);
        if (rarity<12){    //common//
            int index = r.nextInt(9)+1;
            switch (index){
                case 1: return Items.OAK_BOAT;
                case 2: return Items.WHEAT_SEEDS;
                case 3: return Items.COOKED_CHICKEN;
                case 4: return Items.COOKED_SALMON;
                case 5: count=3; return Items.OAK_LOG;
                case 6: return Items.COAL;
                case 7: return Items.BOW;
                case 8: return Items.CROSSBOW;
                case 9: count=4; return Items.WHEAT;
            }//common//

        }else if (rarity<18){ //uncommon//
            int index = r.nextInt(7)+1;
            switch (index){
                case 1: return Items.ENDER_PEARL;
                case 2: return Items.IRON_INGOT;
                case 3: return Items.BUCKET;
                case 4: return Items.MELON;
                case 5: return Items.ARMOR_STAND;
                case 6: return Items.AMETHYST_SHARD;
                case 7: count=3; return Items.QUARTZ;
                case 8: count =4; return Items.COAL;

            }//uncommon//

        }else if (rarity<22){ //rare//
            int index = r.nextInt(12)+1;
            switch (index){
                case 1: return Items.GOLD_INGOT;
                case 2: return Items.MUSIC_DISC_WAIT;
                case 3: return Items.COAL_BLOCK;
                case 4: return Items.IRON_AXE;
                case 5: return Items.IRON_PICKAXE;
                case 6: return Items.GLISTERING_MELON_SLICE;
                case 7: return Items.GOLDEN_AXE;
                case 8: return Items.ENDER_EYE;
                case 9: return Items.DRAGON_BREATH;
                case 10: return Items.BLAZE_ROD;
                case 11: return Items.GOLDEN_CARROT;
                case 12: return Items.OBSIDIAN;

            }//rare//
        }else if (rarity<24){ //super rare//
            int index = r.nextInt(7)+1;
            switch (index){
                case 1: return Items.DIAMOND;
                case 2: return Items.GOLDEN_APPLE;
                case 3: return Items.EMERALD;
                case 4: return Items.DIAMOND_AXE;
                case 5: return Items.DIAMOND_CHESTPLATE;
                case 6: return Items.DIAMOND_PICKAXE;
                case 7: return ItemInit.HEART_OF_MOBS.get();
            }//super rare//
        }else { //epic//
            int index = r.nextInt(4)+1;
            switch (index){
                case 1: return Items.TOTEM_OF_UNDYING;
                case 2: return Items.NETHER_STAR;
                case 3: return Items.NETHERITE_SCRAP;
                case 4: return Items.NETHERITE_PICKAXE;

            }//epic//
        }
        return null;
    }

    public static Entity setHostileMob(){
        return null;
    }

    public static Entity setFriendlyMob(Level level){
        Random r = new Random();
        int rarity = r.nextInt(11);
        if (rarity<10){
            int index = r.nextInt(6)+1;
            switch (index){
                case 1: return new Sheep(EntityType.SHEEP,level);
                case 2: return new Chicken(EntityType.CHICKEN,level);
                case 3: return new Cow(EntityType.COW,level);
                case 4: return new Pig(EntityType.PIG,level);
                case 5: return new Rabbit(EntityType.RABBIT,level);
                case 6: return new Wolf(EntityType.WOLF,level);
                case 7: return new Fox(EntityType.FOX,level);
                case 8: return new Parrot(EntityType.PARROT,level);
            }
        }else{
            int index = r.nextInt(2)+1;
            switch (index) {
                case 1: return new MushroomCow(EntityType.MOOSHROOM, level);
                case 2: return new Axolotl(EntityType.AXOLOTL, level);
            }
        }

        return null;
    }

    public static Entity setNaturalMob(){
        return null;
    }

    public static int getCount() {
        return count;
    }

    public static ItemLike getItemLike() {
        itemLike=setItemForLoot();
        return itemLike;
    }

    public static void setCount(int count) {
        HeartMech.count = count;
    }
}
