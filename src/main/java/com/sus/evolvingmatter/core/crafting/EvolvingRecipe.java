package com.sus.evolvingmatter.core.crafting;

import com.google.gson.JsonObject;
import com.sus.evolvingmatter.EvolvingMatter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class EvolvingRecipe implements Recipe<Container> {

    //mechanics
    final Ingredient base;
    final Ingredient addition1;
    final Ingredient addition2;
    final Ingredient catalyst;
    final ItemStack result;
    final ResourceLocation id;

    public EvolvingRecipe(ResourceLocation idLocation,Ingredient baseItem,Ingredient normalMaterial1,Ingredient normalMaterial2,Ingredient catalyst,ItemStack result){
        this.base = baseItem;
        this.result = result;
        this.addition1=normalMaterial1;
        this.addition2=normalMaterial2;
        this.catalyst=catalyst;
        this.id=idLocation;

    }

    @Override
    public boolean matches(Container container, Level level) {
        return this.base.test(container.getItem(0))&&
                (this.addition1.test(container.getItem(1))||this.addition1.test(container.getItem(2))||this.addition1.test(container.getItem(3))||this.addition1.test(container.getItem(4)))&&
                (this.addition2.test(container.getItem(1))||this.addition2.test(container.getItem(2))||this.addition2.test(container.getItem(3))||this.addition2.test(container.getItem(4)));

    }

    @Override
    public ItemStack assemble(Container container) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return result;
    }

    @Override
    public ResourceLocation getId() { //Json name?
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeInit.EVOLUTION_RECIPE.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeInit.EVOLVING;
    }


}
