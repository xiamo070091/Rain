package com.tangml.rain.events;

import com.tangml.rain.RainBungeeCord;
import com.tangml.rain.config.read;
import me.dreamvoid.miraimc.api.MiraiBot;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PostLoginEvent event) throws IOException {
        //初始化玩家数据
        read.getInstance().csh(event.getPlayer().getUniqueId());
        //Bungee 玩家
        for (ProxiedPlayer player : RainBungeeCord.getInstance().getProxy().getPlayers()) {
            player.sendMessage(read.getInstance().getMessage("rain_join").replaceAll("%player%", event.getPlayer().getName()));
        }
    }
}
