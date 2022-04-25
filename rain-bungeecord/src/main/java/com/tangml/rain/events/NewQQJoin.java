package com.tangml.rain.events;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.bungee.event.MiraiGroupBotJoinGroupEvent;
import me.dreamvoid.miraimc.bungee.event.MiraiGroupMemberJoinEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class NewQQJoin implements Listener {
    @EventHandler
    public void newQQJoin(MiraiGroupMemberJoinEvent e){
        //新线程
        new Thread(() -> {
            e.getGroup().sendMessage("@"+MiraiBot.getBot(e.getBotID()).getGroup(e.getGroupID()).getMember(e.getNewMemberID()).getNick()+" "
                    +"欢迎加入随风逐雨科技空岛QQ群\n"
                    +"注册地址：http://skins.tangml.fun \n"
                    +"客户端在群文件里，也可以在：https://www.123pan.com/s/cUTDVv-ZCU3A 提取码:sfzy \n"
                    +"登录教程：https://www.tangml.fun/archives/11/ \n"
                    +"有什么问题可以在群里发送：帮助\n"
                    +"或者直接@群主哦"
            );
            return;
        }).start();
    }
}
