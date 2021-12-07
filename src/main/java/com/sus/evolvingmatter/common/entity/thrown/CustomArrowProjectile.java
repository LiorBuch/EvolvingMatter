package com.sus.evolvingmatter.common.entity.thrown;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;

import com.sus.evolvingmatter.common.item.StaffOfArrows;
import com.sus.evolvingmatter.common.item.StaffOfPoison;
import com.sus.evolvingmatter.core.init.EffectInit;
import com.sus.evolvingmatter.util.ModDamageSource;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class CustomArrowProjectile extends AbstractArrow {
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(Arrow.class, EntityDataSerializers.INT);
    private Potion potion = Potions.EMPTY;
    private final Set<MobEffectInstance> effects = Sets.newHashSet();
    private boolean fixedColor;
    public boolean isCritAbility=false;
    public boolean isEvoCrit=false;


    public CustomArrowProjectile(Level p_36861_, double p_36862_, double p_36863_, double p_36864_) {
        super(EntityType.ARROW, p_36862_, p_36863_, p_36864_, p_36861_);
    }

    public void setCritAbility(boolean critAbility){
        this.isCritAbility =critAbility;
    }

    public void  setEvoArrow(boolean evoCrit){
        this.isEvoCrit=evoCrit;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if(!level.isClientSide && result.getEntity() instanceof LivingEntity) {
                Entity entity = result.getEntity();
                Entity owner = getOwner();
                if (isCritAbility){
                    entity.hurt(ModDamageSource.ZEN_DAMAGE,55F);
                }else {
                    if (isEvoCrit){
                        entity.hurt(ModDamageSource.ZEN_DAMAGE,25F);
                    }
                    entity.hurt(ModDamageSource.ZEN_DAMAGE,15F);
                }
        }
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_EFFECT_COLOR, -1);
    }

    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.makeParticle(1);
                }
            } else {
                this.makeParticle(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && !this.effects.isEmpty() && this.inGroundTime >= 600) {
            this.level.broadcastEntityEvent(this, (byte)0);
            this.potion = Potions.EMPTY;
            this.effects.clear();
            this.entityData.set(ID_EFFECT_COLOR, -1);
        }

    }

    private void makeParticle(int p_36877_) {
        int i = this.getColor();
        if (i != -1 && p_36877_ > 0) {
            double d0 = (double)(i >> 16 & 255) / 255.0D;
            double d1 = (double)(i >> 8 & 255) / 255.0D;
            double d2 = (double)(i >> 0 & 255) / 255.0D;

            for(int j = 0; j < p_36877_; ++j) {
                this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
            }

        }
    }

    public int getColor() {
        return this.entityData.get(ID_EFFECT_COLOR);
    }

    protected ItemStack getPickupItem() {
        if (this.effects.isEmpty() && this.potion == Potions.EMPTY) {
            return new ItemStack(Items.ARROW);
        } else {
            ItemStack itemstack = new ItemStack(Items.TIPPED_ARROW);
            PotionUtils.setPotion(itemstack, this.potion);
            PotionUtils.setCustomEffects(itemstack, this.effects);
            if (this.fixedColor) {
                itemstack.getOrCreateTag().putInt("CustomPotionColor", this.getColor());
            }

            return itemstack;
        }
    }

    public void handleEntityEvent(byte p_36869_) {
        if (p_36869_ == 0) {
            int i = this.getColor();
            if (i != -1) {
                double d0 = (double)(i >> 16 & 255) / 255.0D;
                double d1 = (double)(i >> 8 & 255) / 255.0D;
                double d2 = (double)(i >> 0 & 255) / 255.0D;

                for(int j = 0; j < 20; ++j) {
                    this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
                }
            }
        } else {
            super.handleEntityEvent(p_36869_);
        }

    }
}