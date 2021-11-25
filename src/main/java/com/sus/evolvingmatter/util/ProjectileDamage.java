package com.sus.evolvingmatter.util;

import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

public class ProjectileDamage extends EntityDamageSource {

    public ProjectileDamage(String p_19394_, Entity p_19395_) {
        super(p_19394_, p_19395_);
    }

    public static DamageSource poisonDamage(PoisonProjectile poisonProjectile, @Nullable Entity entity) {
        return (entity == null)
                ?
                (new IndirectEntityDamageSource("onPoison", poisonProjectile, poisonProjectile).setProjectile())
                :
                (new IndirectEntityDamageSource("poisonProjectile", poisonProjectile, entity).setProjectile());
    }

    public static DamageSource stupidity(@Nullable Entity entity) {
        return new EntityDamageSource("stupidity", entity);
    }
}
