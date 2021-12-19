package com.sus.evolvingmatter.core.crafting;

import com.sus.evolvingmatter.EvolvingMatter;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeInit {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EvolvingMatter.MOD_ID);
    public static final RegistryObject<RecipeSerializer<?>> EVOLUTION_RECIPE = RECIPES.register("evolution", EvolvingSerializer::new);

    public static final RecipeType<EvolvingRecipe> EVOLVING = RecipeType.register(new ResourceLocation(EvolvingMatter.MOD_ID,"evolving").toString());
}
