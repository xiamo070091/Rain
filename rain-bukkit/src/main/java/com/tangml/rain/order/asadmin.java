package com.tangml.rain.order;

import com.tangml.rain.RainBukkit;
import org.bukkit.Bukkit;

import java.util.UUID;

public class asadmin {
    //懒汉式
    private static asadmin instance = null;
    private asadmin(){}
    public static asadmin getInstance(){
        if(instance == null){
            instance = new asadmin();
        }
        return instance;
    }


    //使用bukkit控制台发送命令
    public void sendCommand(String command, UUID uuid){
        RainBukkit.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", RainBukkit.getInstance().getServer().getPlayer(uuid).getName()));
    }
    public void sendCommand(String command, UUID uuid,UUID owner){
        RainBukkit.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", RainBukkit.getInstance().getServer().getPlayer(uuid).getName()).replace("{owner}", RainBukkit.getInstance().getServer().getPlayer(owner).getName()));
        System.out.println(command.replace("{player}", RainBukkit.getInstance().getServer().getPlayer(uuid).getName()).replace("{owner}", RainBukkit.getInstance().getServer().getPlayer(owner).getName()));
    }
}
