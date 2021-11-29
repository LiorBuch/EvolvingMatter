package com.sus.evolvingmatter.common.item;

import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.entity.thrown.PoisonProjectile;
import com.sus.evolvingmatter.util.IDMG;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class StaffOfArrows extends Item {
    public enum Stage{
        NORMAL,
        BREAKTHROW,
        EVOLUTION
    }
    private int sec = 20;
    public StaffOfArrows(Properties p_41383_,Stage stage) {
        super(p_41383_);
        this.stageOfWeapon=stage;
    }
    private Stage stageOfWeapon;

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        player.getCooldowns().addCooldown(this, sec*2);
        if(!world.isClientSide) {
            if (stageOfWeapon==Stage.NORMAL){
                Arrow arrow = new Arrow(world,player.getX(), player.getY()+1.5, player.getZ());
                arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 0.0F);
                world.addFreshEntity(arrow);
            }
            if (stageOfWeapon==Stage.BREAKTHROW){
                for (int i=0;i<4;i++) {
                    Arrow arrow = new Arrow(world,player.getX(), player.getY()+1.5, player.getZ());
                    Vec3 vec31 = player.getUpVector(1.0F);
                    Quaternion quaternion = new Quaternion(new Vector3f(vec31), -15+(i*10), true);
                    Vec3 vec3 = player.getViewVector(1.0F);
                    Vector3f vector3f = new Vector3f(vec3);
                    vector3f.transform(quaternion);
                    arrow.shoot((double)vector3f.x(), (double)vector3f.y(), (double)vector3f.z(), 2.5F, 1F);
                    world.addFreshEntity(arrow);
                }
            }
        }
        return super.use(world, player, hand);
    }
}
