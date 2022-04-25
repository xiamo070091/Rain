package com.tangml.rain.events;

import com.tangml.rain.config.read;
import com.tangml.rain.config.status;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.UUID;

public class PlayerSwitch implements Listener {
    @EventHandler
    public void onPlayerSwitch(ServerSwitchEvent event) throws InterruptedException {
        ProxiedPlayer e = event.getPlayer();
        UUID uuid = e.getUniqueId();
        //检查目标是否需要返回空岛
        if (status.getInstance().isIS(uuid)) {
            if (e.getServer().getInfo().getName().equals(read.getInstance().getServerID(uuid))) {
                Thread.sleep(4000);
                e.chat("/is");
                status.getInstance().setIS(uuid,false);
            }
            else{
                e.sendMessage(read.getInstance().getMessage("prefix")+read.getInstance().getMessage("is_tp_error"));
            }
        }

    }
}
