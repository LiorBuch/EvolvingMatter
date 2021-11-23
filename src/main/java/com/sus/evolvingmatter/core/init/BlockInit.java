package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.block.DisappearingAnvilBlock;
import com.sus.evolvingmatter.common.block.EvolotionStand;
import com.sus.evolvingmatter.common.item.SoulStone;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.security.PublicKey;
import java.util.function.ToIntFunction;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EvolvingMatter.MOD_ID);

    public static final RegistryObject<DisappearingAnvilBlock> DISAPPEARING_ANVIL_BLOCK = BLOCKS.register("disappearing_anvil_block",()-> new DisappearingAnvilBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).sound(SoundType.ANVIL)));
    public static final RegistryObject<Block> SOUL_STONE_ORE = BLOCKS.register("soul_stone_ore",()-> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> SOUL_STONE_ORE_DEEPSLATE = BLOCKS.register("soul_stone_ore_deepslate",()-> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> FADE_APPLE_LEAVES = BLOCKS.register("fade_apple_leaves",()-> new Block(BlockBehaviour.Properties.of(Material.GLASS).requiresCorrectToolForDrops().sound(SoundType.GRASS).noOcclusion().isSuffocating((p1,p2,p3)-> false).isViewBlocking((p1,p2,p3)-> false)));
    public static final RegistryObject<Block> CLOUD_BLOCK = BLOCKS.register("cloud_block1",()-> new Block(BlockBehaviour.Properties.of(Material.GLASS).sound(SoundType.WOOL).noOcclusion().isSuffocating((p1,p2,p3)-> false).isViewBlocking((p1,p2,p3)-> false)));
    //Block Entities
    public static final RegistryObject<EvolotionStand> EVOLUTION_STAND = BLOCKS.register("evolution_stand",()-> new EvolotionStand(BlockBehaviour.Properties.of(Material.HEAVY_METAL).noOcclusion().requiresCorrectToolForDrops()));

}
