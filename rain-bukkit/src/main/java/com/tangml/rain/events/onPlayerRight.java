package com.tangml.rain.events;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class onPlayerRight implements Listener {
    @EventHandler
    public void onplayerRight(PlayerInteractEvent event) {
        if(ASkyBlockAPI.getInstance().getOwner(event.getPlayer().getLocation()).toString().equals("ff4ed47e-58ec-388e-b992-7e72d233b344")
                && !event.isBlockInHand()
                &&event.getClickedBlock().getType().toString().equals("CROPS")
                ||event.getClickedBlock().getType().toString().equals("CARROT")
                ||event.getClickedBlock().getType().toString().equals("POTATO")){
            //允许操作
            event.getPlayer();
        }

    }
}
