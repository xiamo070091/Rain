package com.tangml.rain.commands;

import com.tangml.rain.toBungee.sendBungee;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class iss implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //判断是不是 玩家
        if(sender instanceof org.bukkit.entity.Player){
            sendBungee.getInstance().sendbungee("⑥"+((Player)sender).getUniqueId());
        }
        return true; //true代表命令执行没问题, 返回false的话Bukkit会给命令输入方一个错误提示语
    }
}