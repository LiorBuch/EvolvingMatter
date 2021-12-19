package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.container.menu.EvolutionStandMenu;
import com.sus.evolvingmatter.common.block.AttachementTable;
import com.sus.evolvingmatter.common.block.DisappearingAnvilBlock;
import com.sus.evolvingmatter.common.block.EvolotionStand;
import com.sus.evolvingmatter.common.block.entity.AttachmentTableBlockEntity;
import com.sus.evolvingmatter.common.block.entity.EvolutionStandBlockEntity;
import com.sus.evolvingmatter.common.effects.ZenPoisonEffect;
import com.sus.evolvingmatter.common.effects.ZenRegenerationEffect;
import com.sus.evolvingmatter.common.entity.Fiend;
import com.sus.evolvingmatter.common.entity.SoulGhost;
import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import com.sus.evolvingmatter.common.item.*;
import com.sus.evolvingmatter.common.item.blockitem.AttachmentTableItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registrations {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, EvolvingMatter.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, EvolvingMatter.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, EvolvingMatter.MOD_ID);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, EvolvingMatter.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EvolvingMatter.MOD_ID);
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, EvolvingMatter.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, EvolvingMatter.MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EvolvingMatter.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, EvolvingMatter.MOD_ID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(bus);
        ITEMS.register(bus);
        BLOCK_ENTITIES.register(bus);
        EFFECTS.register(bus);
        CONTAINERS.register(bus);
        SOUNDS.register(bus);
        PARTICLE_TYPE.register(bus);
        ENTITIES.register(bus);
        RECIPES.register(bus);
    }


    //*************************************Items*************************************
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

    //Tools
    //Items
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
    //*************************************Blocks*************************************
    public static final RegistryObject<DisappearingAnvilBlock> DISAPPEARING_ANVIL_BLOCK = BLOCKS.register("disappearing_anvil_block",()-> new DisappearingAnvilBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 1200.0F).sound(SoundType.ANVIL)));
    public static final RegistryObject<Block> SOUL_STONE_ORE = BLOCKS.register("soul_stone_ore",()-> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> SOUL_STONE_ORE_DEEPSLATE = BLOCKS.register("soul_stone_ore_deepslate",()-> new OreBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> FADE_APPLE_LEAVES = BLOCKS.register("fade_apple_leaves",()-> new Block(BlockBehaviour.Properties.of(Material.GLASS).requiresCorrectToolForDrops().sound(SoundType.GRASS).noOcclusion().isSuffocating((p1,p2,p3)-> false).isViewBlocking((p1,p2,p3)-> false)));
    public static final RegistryObject<Block> CLOUD_BLOCK = BLOCKS.register("cloud_block1",()-> new Block(BlockBehaviour.Properties.of(Material.GLASS).sound(SoundType.WOOL).noOcclusion().isSuffocating((p1,p2,p3)-> false).isViewBlocking((p1,p2,p3)-> false)));
    //*************************************BlockEntities*************************************
    public static final RegistryObject<EvolotionStand> EVOLUTION_STAND = BLOCKS.register("evolution_stand",()-> new EvolotionStand(BlockBehaviour.Properties.of(Material.HEAVY_METAL).noOcclusion().requiresCorrectToolForDrops()));
    public static final RegistryObject<AttachementTable> ATTACHEMENT_TABLE = BLOCKS.register("attachment_table",()-> new AttachementTable(BlockBehaviour.Properties.of(Material.HEAVY_METAL).noOcclusion().requiresCorrectToolForDrops()));
    //*************************************BlockEntityType*************************************
    public static final RegistryObject<BlockEntityType<EvolutionStandBlockEntity>> EVOLUTION_STAND_BLOCK_ENTITY = BLOCK_ENTITIES.register("evolution_stand",
            ()-> BlockEntityType.Builder.of(EvolutionStandBlockEntity::new, BlockInit.EVOLUTION_STAND.get()).build(null));

    public static final RegistryObject<BlockEntityType<AttachmentTableBlockEntity>> ATTACHMENT_TABLE_BLOCK_ENTITY  = BLOCK_ENTITIES.register("attachment_table",
            ()-> BlockEntityType.Builder.of(AttachmentTableBlockEntity::new, BlockInit.ATTACHEMENT_TABLE.get()).build(null));
    //*************************************Containers*************************************
    public static final RegistryObject<MenuType<EvolutionStandMenu>> EVOLUTION_STAND_MENU = CONTAINERS.register("evolution_stand_menu", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();
        return new EvolutionStandMenu(windowId, world, pos, inv, inv.player);
    }));
    //*************************************Sounds*************************************
    // SoulGhost
    public static final RegistryObject<SoundEvent> SOUL_GHOST_AMBIENT = SOUNDS.register("soul_ghost_ambient",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.soul_stone_ghost.ambient")));

    public static final RegistryObject<SoundEvent> SOUL_GHOST_HURT = SOUNDS.register("soul_ghost_hurt",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.soul_stone_ghost.hurt")));

    public static final RegistryObject<SoundEvent> SOUL_GHOST_DEATH = SOUNDS.register("soul_ghost_death",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.soul_stone_ghost.death")));

    // Fiend
    public static final RegistryObject<SoundEvent> FIEND_AMBIENT = SOUNDS.register("fiend_ambient",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.fiend.ambient")));

    public static final RegistryObject<SoundEvent> FIEND_HURT = SOUNDS.register("fiend_hurt",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.fiend.hurt")));

    public static final RegistryObject<SoundEvent> FIEND_DEATH = SOUNDS.register("fiend_death",
            ()-> new SoundEvent(new ResourceLocation(EvolvingMatter.MOD_ID,"entity.fiend.death")));
    //*************************************Dimensions*************************************
    public static final ResourceKey<DimensionType> CAVE_DIM_TYPE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(EvolvingMatter.MOD_ID, "cavedim"));
    public static final ResourceKey<Level> CAVE_DIM = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(EvolvingMatter.MOD_ID, "cavedim"));
    //*************************************Effects*************************************
    public static final RegistryObject<MobEffect> ZEN_POISON_EFFECT = EFFECTS.register("zen_poison_effect", () -> new ZenPoisonEffect(MobEffectCategory.HARMFUL,0x0d8c2d));
    public static final RegistryObject<MobEffect> ZEN_REGENERATION_EFFECT = EFFECTS.register("zen_regeneration_effect", () -> new ZenRegenerationEffect(MobEffectCategory.BENEFICIAL,0xf54242));
    //*************************************Entities*************************************
    public static final RegistryObject<EntityType<SoulGhost>> SOUL_GHOST = ENTITIES.register("soul_ghost", ()-> EntityType.Builder.of(SoulGhost::new, MobCategory.CREATURE)
            .sized(0.8F,0.6F).build(new ResourceLocation(EvolvingMatter.MOD_ID,"soul_ghost").toString()));

    public static final RegistryObject<EntityType<Fiend>> FIEND = ENTITIES.register("fiend", ()-> EntityType.Builder.of(Fiend::new, MobCategory.MONSTER)
            .sized(0.8F,0.6F).build(new ResourceLocation(EvolvingMatter.MOD_ID,"fiend").toString()));
    //Projectile
    public static final RegistryObject<EntityType<PoisonProjectile>> POISONPROJECTILE = ENTITIES.register("poison_projectile",
            () -> EntityType.Builder.<PoisonProjectile>of(PoisonProjectile::new, MobCategory.MISC).sized(0.5f, 0.5f).build(String.valueOf((new ResourceLocation(EvolvingMatter.MOD_ID, "poison_projectile_entity")))));;
    //*************************************Food Properties*************************************
    public static final FoodProperties FADE_APPLE_PROPERTIES = (new FoodProperties.Builder()).nutrition(4).saturationMod(1.2F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1.0F)
            //.effect(new MobEffectInstance(EffectInit.ZEN_REGENERATION_EFFECT.get(), 400, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0F).alwaysEat().build();
    //*************************************Food Properties*************************************

}
