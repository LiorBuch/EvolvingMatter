package com.sus.evolvingmatter.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.util.IDMG;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ZenGui extends Gui {
    private Minecraft minecraft;
    private Font fontRenderer;
    private ItemRenderer itemRenderer;
    private boolean visible;
    private ResourceLocation GUI = new ResourceLocation(EvolvingMatter.MOD_ID, "textures/gui/zen.png");
    private ResourceLocation ABILITY_ICONS = new ResourceLocation(EvolvingMatter.MOD_ID, "textures/gui/ability_icons.png");

    public static float maxHP;
    public static float currentHP;

    public ZenGui() {
        super(Minecraft.getInstance());
        minecraft = Minecraft.getInstance();
        fontRenderer = minecraft.font;
        itemRenderer = minecraft.getItemRenderer();
        visible=true;
    }

    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.PostLayer event) {
        if (event.getOverlay() == ForgeIngameGui.HOTBAR_ELEMENT) {
            LocalPlayer localPlayer = minecraft.player;
            int posX = event.getWindow().getGuiScaledWidth()/2;
            int posY = event.getWindow().getGuiScaledHeight()/2;
            for (ItemStack itemStack : localPlayer.getInventory().items) {
                Item item = itemStack.getItem();
                if (item instanceof IDMG) {
                    if (itemStack.hasTag()) {
                        RenderSystem.setShaderTexture(0,GUI);
                        // for blit (MatrixStack==????,screen locationX,screen locationY,texture start locationX,texture start locationY,width,height)
                        minecraft.gui.blit(event.getMatrixStack(), posX+100, posY+100, 1, 1, 58, 95);
                        int i =Math.round(89-((currentHP)/(maxHP/89)));
                        minecraft.gui.blit(event.getMatrixStack(), posX +103, posY +103, 59, 4-i, 52, 89);
                        break;
                    }
                }
            }

        }
    }
}
