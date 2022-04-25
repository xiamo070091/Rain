package com.tangml.rain.events;

import com.tangml.rain.RainBungeeCord;
import com.tangml.rain.config.read;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerLeave implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerDisconnectEvent event) {
        //删除玩家数据
        read.getInstance().removePlayer(event.getPlayer().getUniqueId());
        //向全服广播
        for (ProxiedPlayer player : RainBungeeCord.getInstance().getProxy().getPlayers()) {
            player.sendMessage(read.getInstance().getMessage("rain_leave").replaceAll("%player%", event.getPlayer().getName()));
        }
    }
}
