package com.sus.evolvingmatter.core.init;

import com.sus.evolvingmatter.EvolvingMatter;
import com.sus.evolvingmatter.core.network.ZenHealthNetworkToClient;
import com.sus.evolvingmatter.core.network.ZenHealthNetworkToServer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public final class PacketHandler {
    private PacketHandler(){}
    private static final String PROTOCOL_VERSION="1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(EvolvingMatter.MOD_ID,"main"),()-> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,PROTOCOL_VERSION::equals);

    public static void init(){
        int index=0;
        INSTANCE.messageBuilder(ZenHealthNetworkToServer.class,index++, NetworkDirection.PLAY_TO_SERVER).encoder(ZenHealthNetworkToServer::encode).decoder(ZenHealthNetworkToServer::new)
                .consumer(ZenHealthNetworkToServer::handle).add();

        INSTANCE.messageBuilder(ZenHealthNetworkToClient.class,index++, NetworkDirection.PLAY_TO_CLIENT).encoder(ZenHealthNetworkToClient::encode).decoder(ZenHealthNetworkToClient::new)
                .consumer(ZenHealthNetworkToClient::handle).add();
    }
}
