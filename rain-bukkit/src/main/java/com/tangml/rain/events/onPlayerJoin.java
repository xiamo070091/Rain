package com.tangml.rain.events;

import com.tangml.rain.config.keepFood;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class onPlayerJoin implements Listener {
    @EventHandler
    public void onplayJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        keepFood.getInstance().setFood(event.getPlayer(),event.getPlayer().getFoodLevel());
    }
}
