package com.tangml.rain.commands;


import com.tangml.rain.config.read;
import com.tangml.rain.config.status;
import com.tangml.rain.toBukkit.sendBukkit;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class tangml extends Command {
    //这个类是处理空岛相关指令的
    public tangml() {
        super("is");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        //判断是不是玩家
        if (!(sender instanceof ProxiedPlayer)) {
            System.out.println("这是一个玩家命令");
        }
    }
}
