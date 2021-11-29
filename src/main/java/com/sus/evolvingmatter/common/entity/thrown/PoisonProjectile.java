package com.sus.evolvingmatter.common.entity.thrown;

import com.mojang.math.Vector3f;
import com.sus.evolvingmatter.common.item.ZenHealth;
import com.sus.evolvingmatter.core.init.EntityInit;
import com.sus.evolvingmatter.core.init.ItemInit;
import com.sus.evolvingmatter.util.ModDamageSource;
import com.sus.evolvingmatter.util.ProjectileDamage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class PoisonProjectile extends AbstractPoisonProjectile implements IAnimatable {


    private AnimationFactory factory = new AnimationFactory(this);

    public PoisonProjectile(EntityType<? extends PoisonProjectile> entityType, Level world) {
        super(entityType, world);
    }

    public PoisonProjectile(LivingEntity entity, double x, double y, double z, double accelX, double accelY, double accelZ, Level world) {
        super(EntityInit.POISONPROJECTILE.get(), x, y, z, accelX, accelY, accelZ, world);
    }

    public PoisonProjectile(LivingEntity entity, double x, double y, double z, Level world) {
        super(EntityInit.POISONPROJECTILE.get(), entity, x, y, z, world);
    }

    public PoisonProjectile(Level world, LivingEntity shooter, double x, double y, double z) {
        super(EntityInit.POISONPROJECTILE.get(), shooter, x, y, z, world);
    }

    @Override
    public ItemStack getItem() {
        ItemStack stack = this.getItemRaw();
        return stack.isEmpty() ? new ItemStack(ItemInit.STAFF_OF_POISON.get()) : stack;
    }

    @Override
    public void setItem(ItemStack stack) {
        super.setItem(ItemInit.STAFF_OF_POISON.get().getDefaultInstance());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        ItemStack stack = this.getItemRaw();
        if(!stack.isEmpty())
            nbt.put("Item", stack.save(new CompoundTag()));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        ItemStack stack = ItemStack.of(nbt.getCompound("Item"));
        this.setItem(stack);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
    }

    //Animations Starts
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.poison_projectile.spin", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }
    //Animation Ends


    @Override
    public void tick() {
        super.tick();
        if (this.level.isClientSide) {
            this.level.addParticle(new DustParticleOptions(new Vector3f(Vec3.fromRGB24(0x31D90B)),1.0F) , this.getX(), this.getY() - 0.3D, this.getZ(), this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);
        }
    }

    @Override
    public boolean shouldBlockExplode(Explosion p_19987_, BlockGetter p_19988_, BlockPos p_19989_, BlockState p_19990_, float p_19991_) {
        return false;
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected boolean canHitEntity(Entity entity) {
        return true;
    }



    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if(!level.isClientSide && result.getEntity() instanceof LivingEntity) {
            Entity entity = result.getEntity();
            Entity owner = getOwner();
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.POISON,80,1));
            entity.hurt(ModDamageSource.ZEN_DAMAGE,50F);
        }
    }
}
