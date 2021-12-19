package com.sus.evolvingmatter.common.entity.thrown;

import com.mojang.math.Vector3f;
import com.sus.evolvingmatter.common.item.StaffOfPoison;
import com.sus.evolvingmatter.core.init.EffectInit;
import com.sus.evolvingmatter.core.init.EntityInit;
import com.sus.evolvingmatter.util.ModDamageSource;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class IcicleProjectile extends AbstractHurtingProjectile implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public IcicleProjectile(EntityType<? extends IcicleProjectile> entityType, Level world) {
        super(entityType, world);
    }

    public IcicleProjectile(LivingEntity entity , double x, double y, double z, double accelX, double accelY, double accelZ, Level world) {
        super(EntityInit.ICICLE_PROJECTILE.get(), x, y, z, accelX, accelY, accelZ, world);
    }

    public IcicleProjectile(EntityType<? extends IcicleProjectile> entityType, LivingEntity entity, double x, double y, double z, Level world) {
        super(entityType, entity, x, y, z, world);
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return true;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Random r = new Random();
        super.onHitEntity(result);
        if (!level.isClientSide && result.getEntity() instanceof LivingEntity) {
            Entity entity = result.getEntity();
            Entity owner = getOwner();
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 2, 1));
            entity.hurt(ModDamageSource.ZEN_DAMAGE, 60F);
            if (r.nextInt(100)>70){
                Level level = result.getEntity().level;
            }
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        this.discard();
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            this.level.addParticle(new DustParticleOptions(new Vector3f(Vec3.fromRGB24(0x32bbed)), 1.0F), this.getX(), this.getY() - 0.3D, this.getZ(), this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);
        }
    }

    @Override
    public void registerControllers(AnimationData data) {

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
