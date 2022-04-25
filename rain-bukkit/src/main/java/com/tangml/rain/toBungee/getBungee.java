package com.tangml.rain.toBungee;

import com.tangml.rain.order.asadmin;
import me.skymc.taboolib.bungeesuite.events.BukkitCommandEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class getBungee implements Listener {
    @EventHandler
    public void onCommand(BukkitCommandEvent e) throws InterruptedException {
        String ss = e.getString(0, "null");
        if (ss.startsWith("deleteIS@")) {
            //删除命令
            ss = ss.replace("deleteIS@","");
            UUID uuid = UUID.fromString(ss);
            //发送删除空岛命令
            asadmin.getInstance().sendCommand("asadmin delete {player}" , uuid);
            //向Bungee 发送执行完成消息
            sendBungee.getInstance().sendbungee("accept@" + uuid);
        }else if(ss.startsWith("leave@")){
            //删除命令
            ss = ss.replace("leave@","");
            UUID uuid = UUID.fromString(ss);
            //发送删除空岛命令
            asadmin.getInstance().sendCommand("asadmin team kick {player}" , uuid);
            //向Bungee 发送执行完成消息
            sendBungee.getInstance().sendbungee("accept@" + uuid);
        }else if(ss.startsWith("teamadd@")){
            //延迟操作
            Thread.sleep(8000);
            ss = ss.replace("teamadd@","");
            System.out.println(ss);
            UUID owner = UUID.fromString(ss.substring(0,ss.indexOf("@")).replaceAll("@",""));
            ss= ss.replace(ss.substring(0,ss.indexOf("@")),"").replaceAll("@","");
            System.out.println(ss);
            UUID uuid = UUID.fromString(ss);
            asadmin.getInstance().sendCommand("asadmin team add {player} {owner}" , uuid,owner);
        }
    }
}
