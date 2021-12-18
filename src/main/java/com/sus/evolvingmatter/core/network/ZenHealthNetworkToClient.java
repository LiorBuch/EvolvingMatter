package com.sus.evolvingmatter.core.network;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ZenHealthNetworkToClient {

    public final CompoundTag zenHPTag;

    //encode
    public ZenHealthNetworkToClient(CompoundTag compoundTag) {
        this.zenHPTag = compoundTag;
    }
    //decode
    public ZenHealthNetworkToClient(FriendlyByteBuf buffer) {
        this(buffer.readNbt());
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeNbt(zenHPTag);
    }

    //what to do
    public boolean handle(Supplier<NetworkEvent.Context> ctx){
        final var success=new AtomicBoolean(false);
        ctx.get().enqueueWork(()->{
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()-> ()-> success.set(true));
        });
        ctx.get().setPacketHandled(true);
        return success.get();
    }
}
