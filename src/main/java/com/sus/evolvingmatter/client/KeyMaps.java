package com.sus.evolvingmatter.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.sus.evolvingmatter.EvolvingMatter;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.fmlclient.registry.ClientRegistry;

public class KeyMaps {
    private KeyMaps(){
    }
    public static KeyMapping keyAbility;
    public static KeyMapping keyUltimate;

    public static void init(){
        keyAbility = registerKey("ability_key", InputConstants.KEY_Z, keyAbility.CATEGORY_GAMEPLAY);
        keyUltimate = registerKey("ultimate_ability_key",InputConstants.KEY_X,keyUltimate.CATEGORY_GAMEPLAY);
    }

    private static KeyMapping registerKey(String name,int keycode,String catagory){
        final var key = new KeyMapping("key."+ EvolvingMatter.MOD_ID+"."+name,keycode,catagory);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}
