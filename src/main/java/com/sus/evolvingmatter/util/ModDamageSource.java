package com.sus.evolvingmatter.util;

import com.sus.evolvingmatter.EvolvingMatter;
import net.minecraft.world.damagesource.DamageSource;

public class ModDamageSource extends DamageSource {
    public ModDamageSource(String msgId) {
        super(EvolvingMatter.MOD_ID+"."+msgId);
    }

    public static final DamageSource ZEN_DAMAGE = new ModDamageSource("zenDamage");

}
