package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.block.entity.AncientBoxBlockEntity;
import com.sus.evolvingmatter.common.block.entity.AttachmentTableBlockEntity;
import com.sus.evolvingmatter.common.block.entity.EvolutionStandBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class BlockEntityInit {
    private BlockEntityInit(){}

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, EvolvingMatter.MOD_ID);

    public static final RegistryObject<BlockEntityType<EvolutionStandBlockEntity>> EVOLUTION_STAND_BLOCK_ENTITY = BLOCK_ENTITIES.register("evolution_stand",
            ()-> BlockEntityType.Builder.of(EvolutionStandBlockEntity::new, BlockInit.EVOLUTION_STAND.get()).build(null));

    public static final RegistryObject<BlockEntityType<AttachmentTableBlockEntity>> ATTACHMENT_TABLE_BLOCK_ENTITY  = BLOCK_ENTITIES.register("attachment_table",
            ()-> BlockEntityType.Builder.of(AttachmentTableBlockEntity::new, BlockInit.ATTACHEMENT_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<AncientBoxBlockEntity>> ANCIENT_BOX_BLOCK_ENTITY  = BLOCK_ENTITIES.register("ancient_table",
            ()-> BlockEntityType.Builder.of(AncientBoxBlockEntity::new, BlockInit.ANCIENT_BOX.get()).build(null));


}
