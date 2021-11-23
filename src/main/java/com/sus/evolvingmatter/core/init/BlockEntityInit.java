package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.block.EvolotionStand;
import com.sus.evolvingmatter.common.block.entity.EvolutionStandBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class BlockEntityInit {
    private BlockEntityInit(){}

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, EvolvingMatter.MOD_ID);

    public static final RegistryObject<BlockEntityType<EvolutionStandBlockEntity>> EVOLUTION_STAND_BLOCK_ENTITY = BLOCK_ENTITIES.register("evolution_stand",
            ()-> BlockEntityType.Builder.of(EvolutionStandBlockEntity::new, BlockInit.EVOLUTION_STAND.get()).build(null));


}
