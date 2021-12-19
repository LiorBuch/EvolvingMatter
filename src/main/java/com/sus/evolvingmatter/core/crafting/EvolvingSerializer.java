package com.sus.evolvingmatter.core.crafting;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class EvolvingSerializer extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<EvolvingRecipe>{
        @Override
        public EvolvingRecipe fromJson(ResourceLocation id, JsonObject p_44563_) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44563_, "base"));
            Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44563_, "addition1"));
            Ingredient ingredient2 = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44563_, "addition2"));
            Ingredient catalyst = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44563_, "catalyst"));
            ItemStack resultItem = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_44563_, "result"));
            return new EvolvingRecipe(id,ingredient, ingredient1, ingredient2,catalyst , resultItem);
        }
        @Override
        public EvolvingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf p_44566_) {
            Ingredient baseItem = Ingredient.fromNetwork(p_44566_);
            Ingredient ingredient1 = Ingredient.fromNetwork(p_44566_);
            Ingredient ingredient2 = Ingredient.fromNetwork(p_44566_);
            Ingredient catalyst = Ingredient.fromNetwork(p_44566_);
            ItemStack itemstack = p_44566_.readItem();
            return new EvolvingRecipe(id,baseItem, ingredient1, ingredient2,catalyst, itemstack);
        }
        @Override
        public void toNetwork(FriendlyByteBuf p_44553_, EvolvingRecipe p_44554_) {
            p_44554_.base.toNetwork(p_44553_);
            p_44554_.addition1.toNetwork(p_44553_);
            p_44554_.addition2.toNetwork(p_44553_);
            p_44554_.catalyst.toNetwork(p_44553_);
            p_44553_.writeItem(p_44554_.result);
        }

}
