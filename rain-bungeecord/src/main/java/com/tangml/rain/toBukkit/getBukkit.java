package com.tangml.rain.toBukkit;

import com.tangml.rain.RainBungeeCord;
import com.tangml.rain.config.read;
import com.tangml.rain.config.status;
import com.tangml.rain.config.write;
import com.tangml.rain.hook.Mirai;
import me.dreamvoid.miraimc.api.MiraiBot;
import me.skymc.taboolib.bungeesuite.events.BungeeCommandEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.IOException;
import java.util.UUID;

public class getBukkit implements Listener {
    @EventHandler
    public void getbukkit(BungeeCommandEvent e) throws IOException {
        if (e.getString(0, "null").startsWith("★")) {
            //处理聊天信息》》》》QQ群
            Mirai.getInstance().sendMessage(e.getString(0, "null"));
        }else if(e.getString(0, "null").startsWith("⑥")){
            //跨服返回空岛
            ProxiedPlayer p  = read.getInstance().getPlayer(UUID.fromString(e.getString(0, "null").replace("⑥", "")));
            read RD = read.getInstance();
            if (p.getServer().getInfo().getName().equalsIgnoreCase(RD.getServerID(p.getUniqueId()))) {
                p.chat("/is");
            }else{
                //传送玩家到其他服务器
                status.getInstance().setIS(p.getUniqueId(),true);
                //传送到其他服务器
                p.connect(RD.getServer(p.getUniqueId()));
            }
        }else if(e.getString(0, "null").startsWith("gb@")){
            //广播消息
            for (ProxiedPlayer player : RainBungeeCord.getInstance().getProxy().getPlayers()) {
                player.sendMessage(e.getString(0, "null").replace("gb@", ""));
            }



        }else if(e.getString(0, "null").startsWith("accept@")){
//            System.out.println("最原始信息"+e.getString(0, "null"));
            //添加岛屿
            UUID uuid = UUID.fromString(e.getString(0, "null").replace("accept@",""));
//            System.out.println("处理后"+uuid);
            //处理接受岛屿
            ProxiedPlayer player=read.getInstance().getPlayer(uuid);
            ProxiedPlayer owner=read.getInstance().getPlayer(status.getInstance().getInvite(uuid));
            //检测debug是否开启
//            if(read.getInstance().getConfig("debug").equalsIgnoreCase("debug")){
//                System.out.println(" 信息打印");
//                System.out.println("Player"+player.getName());
//                System.out.println("owner"+owner.getName());
//            }


            //将玩家传送到对方所在的服务器
            player.connect(owner.getServer().getInfo());
            //向 p 所在的服务器发送添加岛屿的消息
            sendBukkit.getInstance().sendbukkit(owner,"teamadd@"+owner.getUniqueId().toString()+"@"+player.getUniqueId().toString());
            //向 pp 的内存团队添加信息
            read.getInstance().setServerID(player.getUniqueId(),owner.getServer().getInfo().getName());
            read.getInstance().setMembers(owner.getUniqueId(),player.getUniqueId());
            //向 pp 的团队添加成员
            write.getInstance().upPlayerM(owner.getUniqueId(),player.getUniqueId());
            //向 player的配置文件更新信息
            write.getInstance().upPlayer(owner.getUniqueId(),player.getUniqueId());
            write.getInstance().upPlayer(Integer.valueOf(read.getInstance().getServerID(owner.getUniqueId()).replace("id","")),player.getUniqueId());
            //向两个人发送消息
            player.sendMessage(read.getInstance().getMessage("prefix")+read.getInstance().getMessage("is_accept_self"));
            owner.sendMessage(read.getInstance().getMessage("prefix")+read.getInstance().getMessage("is_accept").replaceAll("%player%",player.getName()));
            //删除邀请信息
            status.getInstance().removeInvite(uuid);
            status.getInstance().removeInvite(owner.getUniqueId());
        }

    }
}
