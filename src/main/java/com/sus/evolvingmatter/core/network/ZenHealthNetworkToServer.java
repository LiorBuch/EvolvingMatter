package com.sus.evolvingmatter.core.network;

import com.sus.evolvingmatter.util.IDMG;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ZenHealthNetworkToServer {
    public final CompoundTag zenHPTag;

    //encode
    public ZenHealthNetworkToServer(CompoundTag compoundTag) {
        this.zenHPTag = compoundTag;
    }
    //decode
    public ZenHealthNetworkToServer(FriendlyByteBuf buffer) {
        this(buffer.readNbt());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeNbt(zenHPTag);
    }

    //what to do
    public boolean handle(Supplier<NetworkEvent.Context> ctx){
        final var success=new AtomicBoolean(false);
        ctx.get().enqueueWork(()->{
            final ItemStack itemStack = ctx.get().getSender().getItemBySlot(EquipmentSlot.OFFHAND);
            if (itemStack.getItem() instanceof IDMG){
                success.set(true);
            }
        });
        ctx.get().setPacketHandled(true);
        return success.get();
    }

}
