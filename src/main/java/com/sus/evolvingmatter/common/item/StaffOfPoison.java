package com.sus.evolvingmatter.common.item;

import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.KeyMaps;
import com.sus.evolvingmatter.client.gui.AbilityCDGui;
import com.sus.evolvingmatter.client.renderer.StaffOfPoisonRenderer;
import com.sus.evolvingmatter.common.entity.thrown.CustomArrowProjectile;
import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.IItemRenderProperties;
import oshi.jna.platform.mac.SystemB;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;
import java.util.function.Consumer;

public class StaffOfPoison extends Item implements IAnimatable, IEvolvingItem {
    public AnimationFactory factory = new AnimationFactory(this);
    private final Stage stageOfWeapon;
    public static boolean didIHit = false;
    public static Vec3 hitLocation = new Vec3(0, 0, 0);
    private int round = 0;

    @Override
    public ItemStack getEvolution() {
        return null;
    }

    public Stage getStageOfWeapon() {
        return this.stageOfWeapon;
    }

    public enum Stage {
        NORMAL,
        BREAKTHROW,
        EVOLUTION
    }

    private int sec = 20;

    public StaffOfPoison(Properties p_41383_, Stage stage) {
        super(p_41383_);
        this.stageOfWeapon = stage;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, sec * 3);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LLAMA_SPIT, SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClientSide) {
            if (stageOfWeapon == Stage.NORMAL) {
                PoisonProjectile poisonProjectile = new PoisonProjectile(player, player.getX(), player.getY() + 1, player.getZ(), 0.0D, -0.8D, 0.0D, world, this.stageOfWeapon, PoisonProjectile.AbilityType.NONABILITY);
                poisonProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.0F);
                world.addFreshEntity(poisonProjectile);
            }
            if (stageOfWeapon == Stage.BREAKTHROW) {
                PoisonProjectile poisonProjectile = new PoisonProjectile(player, player.getX(), player.getY() + 1, player.getZ(), 0.0D, -0.8D, 0.0D, world, this.stageOfWeapon, PoisonProjectile.AbilityType.NONABILITY);
                poisonProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 0.0F);
                world.addFreshEntity(poisonProjectile);
            }
            if (stageOfWeapon == Stage.EVOLUTION) {
                PoisonProjectile poisonProjectile = new PoisonProjectile(player, player.getX(), player.getY() + 1, player.getZ(), 0.0D, -0.8D, 0.0D, world, this.stageOfWeapon, PoisonProjectile.AbilityType.NONABILITY);
                poisonProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 0.0F);
                world.addFreshEntity(poisonProjectile);
            }
        }
        return super.use(world, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int p_41407_, boolean p_41408_) {
        CompoundTag itemTag = this.getOrCreateCooldownTag(itemStack);
        CompoundTag abilityCooldownTag = itemStack.getTagElement("ability_cooldown");
        float normalAbilityCD = abilityCooldownTag.getFloat("normal_amount");
        float ultimateAbilityCD = abilityCooldownTag.getFloat("ultimate_amount");
        int tickFromLunch = abilityCooldownTag.getInt("lunch_ticks");

        super.inventoryTick(itemStack, level, entity, p_41407_, p_41408_);
        if (entity instanceof Player player) {
            if (player.getMainHandItem() == itemStack) {
                if (KeyMaps.keyAbility.isDown() && (stageOfWeapon == StaffOfPoison.Stage.BREAKTHROW || stageOfWeapon == StaffOfPoison.Stage.EVOLUTION)) {
                    if (normalAbilityCD == 160) {
                        level.playSound(player, player, SoundEvents.DISPENSER_LAUNCH, SoundSource.AMBIENT, 1F, 0.3F);
                        if (!level.isClientSide) {
                            PoisonProjectile poisonProjectile = new PoisonProjectile(player, player.getX(), player.getY() + 1, player.getZ(), 0.0D, -0.4D, 0.0D, level, this.stageOfWeapon, PoisonProjectile.AbilityType.NORMAL);
                            poisonProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3F, 0.0F);
                            level.addFreshEntity(poisonProjectile);
                            normalAbilityCD = 0;
                        }
                    }
                }
                if (KeyMaps.keyUltimate.isDown() && stageOfWeapon == StaffOfPoison.Stage.EVOLUTION) {
                    if (ultimateAbilityCD == 800) {
                        if (!level.isClientSide) {
                            level.playSound(player, player, SoundEvents.GENERIC_EXPLODE, SoundSource.AMBIENT, 1F, 1F);
                            PoisonProjectile poisonProjectileMain = new PoisonProjectile(player, player.getX(), player.getY() + 1, player.getZ(), 0.0D, 0D, 0.0D, level, this.stageOfWeapon, PoisonProjectile.AbilityType.ULTIMATE);
                            poisonProjectileMain.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2F, 0.0F);
                            level.addFreshEntity(poisonProjectileMain);
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
        AbilityCDGui.normalCD = normalAbilityCD;
        AbilityCDGui.ultimateCD = ultimateAbilityCD;


        if (this.didIHit) {
            if (entity instanceof Player player) {
                float hitX = (float) this.hitLocation.x;
                float hitY = (float) this.hitLocation.y;
                float hitZ = (float) this.hitLocation.z;

                if (round < 8) {

                    Random r = new Random();
                    float randomX = (float) r.nextInt(20);
                    float randomZ = (float) r.nextInt(20);
                    if (!level.isClientSide) {
                        if (tickFromLunch>9) {
                            PoisonProjectile poisonProjectile = new PoisonProjectile(player,hitX + randomX, hitY + 20, hitZ + randomZ, 0.0D, -0.8D, 0.0D, level, this.stageOfWeapon, PoisonProjectile.AbilityType.POWER);
                            level.addFreshEntity(poisonProjectile);
                            tickFromLunch=0;
                            round++;
                        }
                    }
                }

            }
            if (round==8){
                this.didIHit=false;
                round=0;
                tickFromLunch=11;
            }
            tickFromLunch++;
        }
        abilityCooldownTag.putInt("lunch_ticks", tickFromLunch);
        itemTag.put("ability_cooldown", abilityCooldownTag);
    }

    private void lunchPoisonBarrage(Vec3 vec3, Player player, Level level) {
        float hitX = (float) vec3.x;
        float hitY = (float) vec3.y;
        float hitZ = (float) vec3.z;

        for (int i = 0; i < 5; i++) {
            Random r = new Random();
            float randomX = (float) r.nextInt(10);
            float randomZ = (float) r.nextInt(10);
            if (!level.isClientSide) {
                PoisonProjectile poisonProjectile = new PoisonProjectile(player, player.getX(), player.getY() + 1, player.getZ(), 0.0D, -0.8D, 0.0D, level, this.stageOfWeapon, PoisonProjectile.AbilityType.NONABILITY);
                poisonProjectile.shootFromRotation(player, hitX + randomX, (float) hitY, (float) hitZ + randomZ, 2.0F, 0.0F);
            }
        }
    }

    public CompoundTag getOrCreateCooldownTag(ItemStack itemStack) {
        if (itemStack.hasTag()) {
            return itemStack.getTag();
        }
        CompoundTag itemTag = itemStack.getOrCreateTag();
        CompoundTag cooldownTag = itemStack.getOrCreateTagElement("ability_cooldown");
        cooldownTag.putFloat("normal_amount", 160);
        cooldownTag.putFloat("ultimate_amount", 800);
        cooldownTag.putInt("lunch_ticks",11);
        itemTag.put("ability_cooldown", cooldownTag);
        return itemTag;

    }


    private <P extends Item & IAnimatable> PlayState predicateE(AnimationEvent<P> event) {
        if (this.stageOfWeapon == Stage.EVOLUTION) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.staff_of_poison_e.sticks", true));
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.staff_of_poison_e.cube", true));
        }
        return PlayState.CONTINUE;
    }

    private <P extends Item & IAnimatable> PlayState predicateB(AnimationEvent<P> event) {

        if (this.stageOfWeapon == Stage.BREAKTHROW) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.staff_of_poison_e.sticks", true));
            return PlayState.CONTINUE;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controllerb", 20, this::predicateB));
        data.addAnimationController(new AnimationController(this, "controllere", 20, this::predicateE));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IItemRenderProperties() {
            private final BlockEntityWithoutLevelRenderer renderer = new StaffOfPoisonRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return renderer;
            }
        });
    }

}
