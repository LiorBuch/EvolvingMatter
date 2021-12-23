package com.sus.evolvingmatter.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.common.item.IEvolvingItem;
import com.sus.evolvingmatter.common.item.StaffOfArrows;
import com.sus.evolvingmatter.util.IDMG;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class AbilityCDGui extends Gui {
    private Minecraft minecraft;
    private Font fontRenderer;
    private ItemRenderer itemRenderer;
    private boolean visible;
    private ResourceLocation ABILITY_ICONS = new ResourceLocation(EvolvingMatter.MOD_ID, "textures/gui/ability_icons.png");

    public static float normalCD;
    public static float ultimateCD;
    public static float normalCDTop=160;
    public static float ultimateCDTop=800;

    public AbilityCDGui() {
        super(Minecraft.getInstance());
        minecraft = Minecraft.getInstance();
        fontRenderer = minecraft.font;
        itemRenderer = minecraft.getItemRenderer();
        visible=true;
    }

    @SubscribeEvent
    public void renderAbility(final RenderGameOverlayEvent.PostLayer event) {
        if (event.getOverlay() == ForgeIngameGui.HOTBAR_ELEMENT) {
            LocalPlayer localPlayer = minecraft.player;
            int posX = event.getWindow().getGuiScaledWidth()/2;
            int posY = event.getWindow().getGuiScaledHeight()/2;
            if (localPlayer.getItemBySlot(EquipmentSlot.MAINHAND).getItem() instanceof IEvolvingItem.IArrowsGUI){
                RenderSystem.setShaderTexture(0,ABILITY_ICONS);
                minecraft.gui.blit(event.getMatrixStack(), posX+10, posY+10, 1, 1, 35, 35);
                minecraft.gui.blit(event.getMatrixStack(), posX+10, posY+50, 1, 37, 35, 35);
                int i=Math.round(((normalCD)/((normalCDTop/31))));
                int j=Math.round(((ultimateCD)/((ultimateCDTop/31))));
                minecraft.gui.blit(event.getMatrixStack(), posX + 12, posY + 12, 36, 3-i, 31, 31);
                minecraft.gui.blit(event.getMatrixStack(), posX + 12, posY + 52, 68, 3-j, 31, 31);
            }
        }
    }
}
