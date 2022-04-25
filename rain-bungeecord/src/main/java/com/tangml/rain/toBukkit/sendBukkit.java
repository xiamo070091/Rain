package com.tangml.rain.toBukkit;

import com.tangml.rain.RainBungeeCord;
import me.skymc.taboolib.bungeesuite.TabooLibBungee;
import me.skymc.taboolib.bungeesuite.bungee.TBungeeChannel;
import me.skymc.taboolib.bungeesuite.bungee.TBungeeChannelTask;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class sendBukkit {
    private static final sendBukkit instance = new sendBukkit();
    private sendBukkit(){}
    public static sendBukkit getInstance(){
        return instance;
    }
    public void sendbukkit(ProxiedPlayer p, String msg) {
        TBungeeChannel channel = TabooLibBungee.getInstance().getBungeeChannel();
        TBungeeChannelTask.createTask()
                .channel(channel)
                .target(p)
                .command(msg)
                .result(result -> RainBungeeCord.getInstance().getProxy().getLogger().info("Hello: " + result[0]))
                .run();
    }
}
