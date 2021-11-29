package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.entity.Fiend;
import com.sus.evolvingmatter.common.entity.SoulGhost;
import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class EntityInit {
    private EntityInit(){

    }
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, EvolvingMatter.MOD_ID);

    public static final RegistryObject<EntityType<SoulGhost>> SOUL_GHOST = ENTITIES.register("soul_ghost", ()-> EntityType.Builder.of(SoulGhost::new, MobCategory.CREATURE)
            .sized(0.8F,0.6F).build(new ResourceLocation(EvolvingMatter.MOD_ID,"soul_ghost").toString()));

    public static final RegistryObject<EntityType<Fiend>> FIEND = ENTITIES.register("fiend", ()-> EntityType.Builder.of(Fiend::new, MobCategory.MONSTER)
            .sized(0.8F,0.6F).build(new ResourceLocation(EvolvingMatter.MOD_ID,"fiend").toString()));

    public static final RegistryObject<EntityType<PoisonProjectile>> POISONPROJECTILE = ENTITIES.register("poison_projectile",
            () -> EntityType.Builder.<PoisonProjectile>of(PoisonProjectile::new, MobCategory.MISC).sized(0.5f, 0.5f).build(String.valueOf((new ResourceLocation(EvolvingMatter.MOD_ID, "poison_projectile_entity")))));;

            //Projectile
}
