package com.sus.evolvingmatter.common.entity;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.core.init.SoundInit;
import com.sus.evolvingmatter.util.ModDamageSource;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fmllegacy.common.registry.IEntityAdditionalSpawnData;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class Fiend extends Monster implements IAnimatable, IZenMob {
    float BASE_HP = 150F;
    float MULTIPLIER = 1F;
    float NATURAL_REGENERATE = 0.05F; // 1 ZHP Per second
    public static final String ZEN_TAG = new ResourceLocation(EvolvingMatter.MOD_ID, "zen_hp").toString();
    float MAX_HP = BASE_HP * MULTIPLIER;
    float CURRANT_HP;

    public Fiend(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new MoveControl(this);
    }

    private AnimationFactory factory = new AnimationFactory(this);
    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(EvolvingMatter.MOD_ID, "entities/fiend");

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
        this.goalSelector.addGoal(2, new FiendAttackGoal(this, 1, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));


    }

    //Damage Mech
    class FiendAttackGoal extends MeleeAttackGoal {
        FiendAttackGoal(PathfinderMob p_27960_, double p_27961_, boolean p_27962_) {
            super(p_27960_, p_27961_, p_27962_);
        }
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 30D).add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, (double) 0.23F).add(Attributes.ATTACK_DAMAGE, 1D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }


    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) { //give me a tag and ill upload the data
        super.addAdditionalSaveData(compoundTag);
            LOGGER.info("ive updated the tag");
            compoundTag.putFloat("zen_health",CURRANT_HP);

    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) { //give me a tag and ill download the data
        super.readAdditionalSaveData(compoundTag);
        if (!compoundTag.contains("zen_health")) {
            compoundTag.putFloat("zen_health", this.MAX_HP);
            CURRANT_HP=MAX_HP;
            LOGGER.info("ive made the tag of fiend");
        }else {
            this.CURRANT_HP=compoundTag.getFloat("zen_health");
            LOGGER.info("ive Read your tags");
        }
    }

    @Override
    public boolean hurt(DamageSource damageSource, float amount) {
        if (damageSource == ModDamageSource.ZEN_DAMAGE) {
            float hp = CURRANT_HP;
            LOGGER.info("i got ---"+hp);
            CURRANT_HP = hp - amount;
            LOGGER.info("sub Value is : "+CURRANT_HP);
            if (CURRANT_HP <= 0) {
                CURRANT_HP=0;
                this.playHurtSound(damageSource);
                this.setHealth(0F);
                this.die(ModDamageSource.ZEN_DAMAGE);
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean doHurtTarget(Entity p_21372_) {
        return super.doHurtTarget(p_21372_);
    }

    //Sounds
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundInit.FIEND_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundInit.FIEND_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.FIEND_DEATH.get();
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return LOOT_TABLE;
    }

    //Animations Starts
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.fiend.walk", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
    //Animation Ends

    @Override
    public boolean checkSpawnRules(LevelAccessor p_21686_, MobSpawnType p_21687_) {
        return super.checkSpawnRules(p_21686_, p_21687_);
    }

    public boolean causeFallDamage(float p_148750_, float p_148751_, DamageSource p_148752_) {
        return false;
    }

    protected void checkFallDamage(double p_27754_, boolean p_27755_, BlockState p_27756_, BlockPos p_27757_) {
    }

}
