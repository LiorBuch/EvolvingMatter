package com.sus.evolvingmatter.common.effects;

import com.sus.evolvingmatter.util.ModDamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class ZenRegenerationEffect extends MobEffect {

    public ZenRegenerationEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory,color);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int level) {
        livingEntity.hurt(ModDamageSource.ZEN_DAMAGE,-((level+1)*2));
    }

    @Override
    public boolean isDurationEffectTick(int p_19455_, int p_19456_) { //k is the interval duration
        int k = 50 >> p_19456_;
        if (k > 0) {
            return p_19455_ % k == 0;
        } else {
            return true;
        }
    }

    @Override
    public boolean isInstantenous() {
        return false;
    }
}
