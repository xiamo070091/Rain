package com.tangml.rain.events;

import org.apache.commons.lang.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class onPlayChat implements Listener {
    @EventHandler
    public void onplayChat(PlayerCommandPreprocessEvent e) {
        if (StringUtils.substringBefore(e.getMessage(), " ").contains(":")) {
            e.setCancelled(true);
        }
    }
}
