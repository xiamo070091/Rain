package com.tangml.rain.toBungee;

import com.tangml.rain.RainBukkit;
import me.skymc.taboolib.bungeesuite.TabooLibBukkit;
import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannel;
import me.skymc.taboolib.bungeesuite.bukkit.TBukkitChannelTask;
import org.bukkit.Bukkit;

public class sendBungee {
    private static final sendBungee instance = new sendBungee();
    private sendBungee(){}
    public static sendBungee getInstance(){
        return instance;
    }
    public void sendbungee(String order){
        TBukkitChannel channel = TabooLibBukkit.getInst().getBukkitChannel();
        TBukkitChannelTask.createTask()
                .channel(channel)
                .sender(RainBukkit.getInstance().getServer().getOnlinePlayers().iterator().next())
                .command(order)
                .result(result -> Bukkit.broadcastMessage("Hello: " + result[0]))
                .run();
    }
}
