package com.sus.evolvingmatter.core.event;


import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.client.KeyMaps;
import com.sus.evolvingmatter.common.dimension.BiomeProvider;
import com.sus.evolvingmatter.common.dimension.ChunkGeneratorMod;
import com.sus.evolvingmatter.common.item.StaffOfArrows;
import com.sus.evolvingmatter.util.IDMG;
import com.sus.evolvingmatter.util.ModDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EvolvingMatter.MOD_ID,bus= Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeModEvents {

    @SubscribeEvent
    public static boolean onEntityDamageEvent(LivingAttackEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            boolean intercepted = false;

            for (ItemStack itemStack : player.getInventory().items) {
                Item item = itemStack.getItem();
                if (item instanceof IDMG) {
                    if (((IDMG) item).isDamageBlockableByMod(player, event.getSource(), event.getAmount(), itemStack)) {

                        intercepted = true;
                        break;
                    }
                }
            }

            event.setCanceled(intercepted);

            return !intercepted;
        }

        if (entity instanceof IDMG) {

        } else if (event.getSource() == ModDamageSource.ZEN_DAMAGE) {
            event.setCanceled(true);
        }

        return true;
    }
    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event){

    }

}
