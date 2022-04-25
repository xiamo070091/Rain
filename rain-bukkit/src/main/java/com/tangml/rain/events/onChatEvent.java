package com.tangml.rain.events;

import com.tangml.rain.toBungee.sendBungee;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String message;
        message = "★["+event.getPlayer().getName() + "] " + event.getMessage();
        sendBungee.getInstance().sendbungee(message);
    }
}
