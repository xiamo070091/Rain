package com.tangml.rain;

import com.tangml.rain.commands.iss;
import com.tangml.rain.events.onChatEvent;
import com.tangml.rain.events.onPlayChat;
import com.tangml.rain.events.onPlayerDeath;
import com.tangml.rain.events.onPlayerRespawn;
import com.tangml.rain.toBungee.getBungee;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class RainBukkit extends JavaPlugin implements Listener {
    private static RainBukkit instance;
    public static RainBukkit getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        //注册事件
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new getBungee(), this);
        getServer().getPluginManager().registerEvents(new onChatEvent(), this);
        getServer().getPluginManager().registerEvents(new onPlayChat(), this);
        getServer().getPluginManager().registerEvents(new onPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new onPlayerRespawn(), this);
        //注册指令
        getServer().getPluginCommand("iss").setExecutor(new iss());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
