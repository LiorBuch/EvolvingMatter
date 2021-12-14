package com.sus.evolvingmatter.common.item;

import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.KeyMaps;
import com.sus.evolvingmatter.client.gui.AbilityCDGui;
import com.sus.evolvingmatter.client.gui.ZenGui;
import com.sus.evolvingmatter.common.entity.thrown.CustomArrowProjectile;
import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import com.sus.evolvingmatter.util.IDMG;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class StaffOfArrows extends Item implements IEvolvingItem {

    @Override
    public Item getEvolution() {
        return this;
    }

    public enum Stage {
        NORMAL,
        BREAKTHROW,
        EVOLUTION
    }

    private final int sec = 20;

    public StaffOfArrows(Properties p_41383_, Stage stage) {
        super(p_41383_);
        this.stageOfWeapon = stage;
    }

    private final Stage stageOfWeapon;

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        CompoundTag itemTag = this.getOrCreateCooldownTag(itemStack);
        CompoundTag abilityCooldownTag = itemStack.getTagElement("ability_cooldown");
        float normalAbilityCD = abilityCooldownTag.getFloat("normal_amount");
        float ultimateAbilityCD = abilityCooldownTag.getFloat("ultimate_amount");

        super.inventoryTick(itemStack, level, entity, p_41407_, p_41408_);
        if (entity instanceof Player player) {
            if (player.getMainHandItem() == itemStack) {
                if (KeyMaps.keyAbility.isDown() && (stageOfWeapon == Stage.BREAKTHROW || stageOfWeapon == Stage.EVOLUTION)) {
                    if (normalAbilityCD == 160) {
                        level.playSound(player, player, SoundEvents.DISPENSER_LAUNCH, SoundSource.AMBIENT, 1F, 1F);
                        if (!level.isClientSide) {
                            CustomArrowProjectile arrow = new CustomArrowProjectile(level, player.getX(), player.getY() + 1.5, player.getZ());
                            arrow.setCritAbility(true);
                            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 5.5F, 0.0F);
                            arrow.setSecondsOnFire(10);
                            arrow.isCritArrow();
                            level.addFreshEntity(arrow);

                            normalAbilityCD = 0;
                        }
                    }
                }
                if (KeyMaps.keyUltimate.isDown() && stageOfWeapon == Stage.EVOLUTION) {
                    if (ultimateAbilityCD == 800) {
                        level.playSound(player, player, SoundEvents.GENERIC_EXPLODE, SoundSource.AMBIENT, 1F, 1F);
                        if (!level.isClientSide) {
                            level.playSound(player, player, SoundEvents.GENERIC_EXPLODE, SoundSource.AMBIENT, 1F, 1F);
                            for (int i = 0; i < 72; i++) {
                                CustomArrowProjectile arrow = new CustomArrowProjectile(level, player.getX(), player.getY() + 1.5, player.getZ());
                                Vec3 vec31 = player.getUpVector(1.0F);
                                Quaternion quaternion = new Quaternion(new Vector3f(vec31), -180 + (i * 5), true);
                                Vec3 vec3 = player.getViewVector(1.0F);
                                Vector3f vector3f = new Vector3f(vec3);
                                vector3f.transform(quaternion);
                                arrow.shoot((double) vector3f.x(), (double) vector3f.y(), (double) vector3f.z(), 2.5F, 1F);
                                arrow.isCritArrow();
                                arrow.setSecondsOnFire(10);
                                level.addFreshEntity(arrow);
                            }
                            ultimateAbilityCD = 0;
                        }

                        }
                }
            }
        }
        if (normalAbilityCD < 160)
            normalAbilityCD++;

        if (ultimateAbilityCD < 800)
            ultimateAbilityCD++;

        abilityCooldownTag.putFloat("normal_amount", normalAbilityCD);
        abilityCooldownTag.putFloat("ultimate_amount", ultimateAbilityCD);
        AbilityCDGui.normalCD=normalAbilityCD;
        AbilityCDGui.ultimateCD=ultimateAbilityCD;

        itemTag.put("ability_cooldown",abilityCooldownTag);

    }

    public CompoundTag getOrCreateCooldownTag(ItemStack itemStack) {
        if (itemStack.hasTag()) {
            return itemStack.getTag();
        }
        CompoundTag itemTag = itemStack.getOrCreateTag();
        CompoundTag cooldownTag = itemStack.getOrCreateTagElement("ability_cooldown");
        cooldownTag.putFloat("normal_amount", 160);
        cooldownTag.putFloat("ultimate_amount", 800);
        cooldownTag.putFloat("anti_spam", 60);
        itemTag.put("ability_cooldown", cooldownTag);
        return itemTag;

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, sec * 2);
        if (!world.isClientSide) {
            if (stageOfWeapon == Stage.NORMAL) {
                CustomArrowProjectile arrow = new CustomArrowProjectile(world, player.getX(), player.getY() + 1.5, player.getZ());
                arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.0F);
                world.addFreshEntity(arrow);
            }
            if (stageOfWeapon == Stage.BREAKTHROW) {
                for (int i = 0; i < 3; i++) {
                    CustomArrowProjectile arrow = new CustomArrowProjectile(world, player.getX(), player.getY() + 1.5, player.getZ());

                    Vec3 vec31 = player.getUpVector(1.0F);
                    Quaternion quaternion = new Quaternion(new Vector3f(vec31), -15 + (i * 15), true);
                    Vec3 vec3 = player.getViewVector(1.0F);
                    Vector3f vector3f = new Vector3f(vec3);
                    vector3f.transform(quaternion);
                    arrow.shoot((double) vector3f.x(), (double) vector3f.y(), (double) vector3f.z(), 2.0F, 1F);
                    world.addFreshEntity(arrow);
                }
            }
            if (stageOfWeapon==Stage.EVOLUTION){
                for (int i = 0; i < 5; i++) {

                    CustomArrowProjectile arrow = new CustomArrowProjectile(world, player.getX(), player.getY() + 1.5, player.getZ());
                    arrow.setEvoArrow(true);
                    Vec3 vec31 = player.getUpVector(1.0F);
                    Quaternion quaternion = new Quaternion(new Vector3f(vec31),-30 + (i * 15), true);
                    Vec3 vec3 = player.getViewVector(1.0F);
                    Vector3f vector3f = new Vector3f(vec3);
                    vector3f.transform(quaternion);
                    arrow.shoot((double) vector3f.x(), (double) vector3f.y(), (double) vector3f.z(), 2F, 1F);
                    world.addFreshEntity(arrow);
                }
            }
        }
        return super.use(world, player, hand);
    }


}
