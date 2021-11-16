package com.sus.evolvingmatter.common.entity;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.core.init.EntityInit;
import com.sus.evolvingmatter.core.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class SoulGhost extends Animal implements FlyingAnimal {

    public SoulGhost(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new FlyingMoveControl(this, 20, true);

    }
    public static final ResourceLocation LOOT_TABLE = new ResourceLocation(EvolvingMatter.MOD_ID,
            "entities/soul_ghost");

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0,new FloatGoal(this));
        this.goalSelector.addGoal(0,new FlyingMobWonderGoal());
        this.goalSelector.addGoal(1,new PanicGoal(this,0.5D));
        this.goalSelector.addGoal(2,new TemptGoal(this,1D, Ingredient.of(Items.GLOW_BERRIES),false));
        this.goalSelector.addGoal(3,new AvoidEntityGoal<>(this, Player.class,8.0F, 2.2D, 0.5D));
    }

    public static AttributeSupplier.Builder createAttributes(){
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0D).add(Attributes.FLYING_SPEED,0.6D).add(Attributes.MOVEMENT_SPEED,0.2D);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return EntityInit.SOUL_GHOST.get().create(serverLevel);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundInit.SOUL_GHOST_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return SoundInit.SOUL_GHOST_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.SOUL_GHOST_DEATH.get();
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return LOOT_TABLE;
    }

    @Override
    public boolean isFlying() {
        return true;
    }

    @Override
    protected PathNavigation createNavigation(Level p_27815_) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_27815_) {
            public boolean isStableDestination(BlockPos p_27947_) {
                return !this.level.getBlockState(p_27947_.below()).isAir();
            }
        };
        flyingpathnavigation.setCanOpenDoors(true);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    public boolean causeFallDamage(float p_148750_, float p_148751_, DamageSource p_148752_) {
        return false;
    }

    protected void checkFallDamage(double p_27754_, boolean p_27755_, BlockState p_27756_, BlockPos p_27757_) {
    }



    class FlyingMobWonderGoal extends Goal {
        private static final int WANDER_THRESHOLD = 22;

        FlyingMobWonderGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return SoulGhost.this.navigation.isDone() && SoulGhost.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return SoulGhost.this.navigation.isInProgress();
        }

        public void start() {
            Vec3 vec3 = this.findPos();
            if (vec3 != null) {
                SoulGhost.this.navigation.moveTo(SoulGhost.this.navigation.createPath(new BlockPos(vec3), 1), 1.0D);
            }

        }

        @Nullable
        private Vec3 findPos() {
            Vec3 vec3;
                vec3 = SoulGhost.this.getViewVector(0.0F);

            int i = 8;
            Vec3 vec32 = HoverRandomPos.getPos(SoulGhost.this, 8, 7, vec3.x, vec3.z, ((float)Math.PI / 2F), 3, 1);
            return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(SoulGhost.this, 8, 4, -2, vec3.x, vec3.z, (double)((float)Math.PI / 2F));
        }
    }

}
