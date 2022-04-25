package com.tangml.rain.config;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class keepFood {
    //懒汉式
    private static keepFood instance;
    private keepFood(){}
    public static keepFood getInstance(){
        if(instance == null){
            instance = new keepFood();
        }
        return instance;
    }
    //定义饥饿值
    private static final HashMap<Player, Integer> food = new HashMap<>();
    //获取饥饿值
    public int getFood(Player player){
        if(!food.containsKey(player)){
            return 233;
        }
        return food.get(player);
    }
    //设置饥饿值
    public void setFood(Player player, int food){
        keepFood.food.put(player, food);
    }

}
