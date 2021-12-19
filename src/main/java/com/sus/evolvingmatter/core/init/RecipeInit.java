package com.sus.evolvingmatter.core.init;

import com.google.gson.JsonObject;
import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.item.IEvolvingItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.crafting.RecipeSerializer.register;

public class RecipeInit implements Recipe<Container> {
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_MOD = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EvolvingMatter.MOD_ID);
    public static final RecipeType EVOLVING = RecipeType.register(new ResourceLocation(EvolvingMatter.MOD_ID,"evolving").toString());
    public static final RegistryObject<RecipeSerializer<?>> EVOLUTION_RECIPE = SERIALIZER_MOD.register("evolution", RecipeInit.Serializer::new);



    //mechanics
    final Ingredient base;
    final Ingredient addition1;
    final Ingredient addition2;
    final Ingredient catalyst;
    final ItemStack result;
    final ResourceLocation id;

    public RecipeInit(ResourceLocation idLocation,Ingredient baseItem,Ingredient normalMaterial1,Ingredient normalMaterial2,Ingredient catalyst,ItemStack result){
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

    public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<RecipeInit> {
        public RecipeInit fromJson(ResourceLocation id, JsonObject p_44563_) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44563_, "base"));
            Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44563_, "addition1"));
            Ingredient ingredient2 = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44563_, "addition2"));
            Ingredient catalyst = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_44563_, "catalyst"));
            ItemStack resultItem = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_44563_, "result"));
            return new RecipeInit(id,ingredient, ingredient1, ingredient2,catalyst , resultItem);
        }

        public RecipeInit fromNetwork(ResourceLocation id, FriendlyByteBuf p_44566_) {
            Ingredient baseItem = Ingredient.fromNetwork(p_44566_);
            Ingredient ingredient1 = Ingredient.fromNetwork(p_44566_);
            Ingredient ingredient2 = Ingredient.fromNetwork(p_44566_);
            Ingredient catalyst = Ingredient.fromNetwork(p_44566_);
            ItemStack itemstack = p_44566_.readItem();
            return new RecipeInit(id,baseItem, ingredient1, ingredient2,catalyst, itemstack);
        }

        public void toNetwork(FriendlyByteBuf p_44553_, RecipeInit p_44554_) {
            p_44554_.base.toNetwork(p_44553_);
            p_44554_.addition1.toNetwork(p_44553_);
            p_44554_.addition2.toNetwork(p_44553_);
            p_44554_.catalyst.toNetwork(p_44553_);
            p_44553_.writeItem(p_44554_.result);
        }
    }

}
