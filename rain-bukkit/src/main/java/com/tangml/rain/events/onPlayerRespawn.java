package com.tangml.rain.events;

import com.tangml.rain.config.keepFood;
import com.tangml.rain.order.all;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class onPlayerRespawn implements Listener {
    @EventHandler
    public void onplayerRespawn(PlayerRespawnEvent event){
        //新线程
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(keepFood.getInstance().getFood(event.getPlayer()) == 233){
                event.getPlayer().setFoodLevel(20);
                event.getPlayer().setHealth(20);
                all.getInstance().sendCommand("effect "+event.getPlayer().getName()+" 11 30 10");
                return;
            }
            if(keepFood.getInstance().getFood(event.getPlayer()) == 0){
                event.getPlayer().setFoodLevel(6);
            }else event.getPlayer().setFoodLevel(keepFood.getInstance().getFood(event.getPlayer()));
            event.getPlayer().setHealth(10);
            all.getInstance().sendCommand("effect "+event.getPlayer().getName()+" 11 30 10");
            return;
        }).start();
    }
}
