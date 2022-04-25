package com.tangml.rain.events;

import com.tangml.rain.config.keepFood;
import com.tangml.rain.toBungee.sendBungee;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeath implements Listener {
    @EventHandler
    public void onplayerDeath(PlayerDeathEvent event ) {
        if (event.getEntity() != null){
            keepFood.getInstance().setFood(event.getEntity(),event.getEntity().getFoodLevel());
            sendBungee.getInstance().sendbungee("gb@"+event.getDeathMessage());
            event.setDeathMessage(null);
        }
    }
}
