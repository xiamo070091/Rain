package com.tangml.rain.order;

import com.tangml.rain.RainBukkit;
import org.bukkit.Bukkit;

import java.util.UUID;

public class all {
    //懒汉式
    private static all instance = null;
    private all(){}
    public static all getInstance(){
        if(instance == null){
            instance = new all();
        }
        return instance;
    }
    //控制台执行
    public void sendCommand(String command){
        RainBukkit.getInstance().getServer().dispatchCommand(Bukkit.getConsoleSender(),command);
    }
}
