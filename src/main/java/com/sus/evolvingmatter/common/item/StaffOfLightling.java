package com.sus.evolvingmatter.common.item;

import net.minecraft.world.item.Item;

public class StaffOfLightling extends Item {
    public enum Stage{
        NORMAL,
        BREAKTHROW,
        EVOLUTION
    }
    public StaffOfLightling(Properties p_41383_,Stage stage) {
        super(p_41383_);
        this.stageOfWeapon=stage;
    }
    private final Stage stageOfWeapon;
}
