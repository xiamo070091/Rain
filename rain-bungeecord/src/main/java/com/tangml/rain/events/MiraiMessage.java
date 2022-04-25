package com.tangml.rain.events;

import com.tangml.rain.RainBungeeCord;
import com.tangml.rain.config.read;
import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.bungee.event.MiraiGroupMessageEvent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MiraiMessage implements Listener {
    @EventHandler
    public void miraiGroupMessageEvent(MiraiGroupMessageEvent e){
        if(e.getGroupID()==893898350){
            me.dreamvoid.miraimc.api.bot.MiraiGroup miraiGroup = e.getGroup();
            //新
            new Thread(() -> {
                if(e.getMessage().equalsIgnoreCase("在线人数")){
                    miraiGroup.sendMessage("在线人数："+ RainBungeeCord.getInstance().getProxy().getOnlineCount()+"人\n"
                            +"在线玩家："+RainBungeeCord.getInstance().getProxy().getPlayers().toString());
                }else if(e.getMessage().equalsIgnoreCase("帮助")){
                    miraiGroup.sendMessage("==帮助指南==\n"
                            +"在线人数  查看在线人数以及谁在线\n"
                            +"服务器地址  查看服务器地址\n"
                            +"服务器状态  查看服务器状态\n"
                            +"皮肤站     查看皮肤站\n"
                            +"下载       查看下载地址\n"
                            +"您直接发送的消息将会被转发到服务器\n"
                            +"更多帮助教程请查看：http://www.tangml.fun");
                }else if(e.getMessage().equalsIgnoreCase("服务器地址")){
                    miraiGroup.sendMessage("服务器地址：\n"
                            +"默认地址（直连）：tangml.fun\n"
                            +"源地址（直连）：mc.tangml.fun:64112\n"
                            +"樱花代理（枣庄）：saz.tangml.fun:54112\n"
                            +"樱花代理（成都电信）：sac.tangml.fun:64112\n"
                            +"香港代理（host）：frp.tangml.fun:64112\n"
                            +"更多线路等待开放！"
                    );
                }else if(e.getMessage().equalsIgnoreCase("服务器状态")){
                    miraiGroup.sendMessage("暂未实现，你看到这个消息了就代表服务器还开着\n"
                            +"主要原因是1.7.10PAPI的变量有问题，获取到TPS是0.0"
                    );
                }else if(e.getMessage().equalsIgnoreCase("皮肤站")){
                    miraiGroup.sendMessage("皮肤站：https://skins.tangml.fun");
                }else if(e.getMessage().equalsIgnoreCase(" ")){

                }else if(e.getMessage().equalsIgnoreCase("下载")){
                    miraiGroup.sendMessage("由于群文件有可能导致文件损坏\n"
                            +"123Pan：https://www.123pan.com/s/cUTDVv-ZCU3A提取码:sfzy\n"
                    );
                }else {
                    //向bungee 玩家发送消息
                    for (ProxiedPlayer player : RainBungeeCord.getInstance().getProxy().getPlayers()) {
                        //获取QQ群前缀
                        String prefix = read.getInstance().getMessage("QGprefix");
                        //获取QQ群内群名片
                        String QQName =  MiraiBot.getBot(e.getBotID()).getGroup(e.getGroupID()).getMember(e.getSenderID()).getNick();

                        player.sendMessage(prefix+read.getInstance().getMessage("QGropuD").replaceAll("%player%",QQName)+e.getMessage());
                    }
                }
                return;
            }).start();

        }
    }
}
