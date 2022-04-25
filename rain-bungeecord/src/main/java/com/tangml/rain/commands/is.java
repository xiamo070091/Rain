package com.tangml.rain.commands;


import com.tangml.rain.config.read;
import com.tangml.rain.config.status;
import com.tangml.rain.config.write;
import com.tangml.rain.toBukkit.sendBukkit;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.io.IOException;

public class is extends Command {
    public is() {
        super("is");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        //判断是不是玩家
        if(!(sender instanceof ProxiedPlayer)){
            try {
                read.getInstance().readLanguage();
                System.out.println("初始化语言成功过");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //这个类是处理空岛相关指令的
        read RD = read.getInstance();
        ProxiedPlayer p = null;
        if (sender instanceof ProxiedPlayer) {
            p = (ProxiedPlayer) sender;
        }
        assert p != null;
        System.out.println(RD.getServer(p.getUniqueId()));
        //查询玩家空岛并传送
        if (args.length == 0) {
//            System.out.println(RD.getServer(p.getUniqueId()));
            if (p.getServer().getInfo().getName().equalsIgnoreCase(RD.getServerID(p.getUniqueId()))) {
                p.chat("/is");
            }else{
                //传送玩家到其他服务器
                status.getInstance().setIS(p.getUniqueId(),true);
                //传送到其他服务器
                p.connect(RD.getServer(p.getUniqueId()));
            }
        }else if (!p.getServer().getInfo().getName().equalsIgnoreCase(RD.getServerID(p.getUniqueId()))) {
            p.sendMessage(RD.getMessage("prefix")+ RD.getMessage("is_notInIs"));
        }
        else if(args.length == 1){
            switch (args[0]) {
                case "accept":
                    if (!status.getInstance().isInvitep(p.getUniqueId())) {
                        return;
                    }
                    //查询是否是岛主
                    if (RD.isOwner(p.getUniqueId())) {
                        //如果是岛主
                        //查询团队是否有成员
                        if (RD.getMembersSize(p.getUniqueId()) != 0) {
                            p.sendMessage(RD.getMessage("prefix") + RD.getMessage("is_haveMember"));
                            return;
                        }
                        if (!p.getServer().getInfo().getName().equals(RD.getServerID(p.getUniqueId()))) {
                            p.sendMessage(RD.getMessage("prefix") + RD.getMessage("is_from_self"));
                            return;
                        }
                        //向对方空岛查询有几名玩家
                        if (RD.getMembersSize(status.getInstance().getInvite(p.getUniqueId())) <= 4) {
                            //向子服发送消息，删除空岛，事件到此结束，剩下的由子服触发
                            sendBukkit.getInstance().sendbukkit(p, "deleteIS@" + p.getUniqueId());
                        }
                    } else {
                        //如果不是岛主
                        //向对方空岛查询有几名玩家
                        if (RD.getMembersSize(status.getInstance().getInvite(p.getUniqueId())) <= 4) {
                            sendBukkit.getInstance().sendbukkit(p, "leave@" + p.getUniqueId());
                        }
                    }
                    break;
                case "refuse":
                    status.getInstance().removeInvite(p.getUniqueId());
                    break;
                case "reset":
                    p.chat("/is reset");
                    break;
                case "sethome":
                    p.chat("/is sethome");
                    break;
                case "team":
                    p.chat("/is team");
                    break;
                case "leave":
                    //查找岛主
                    //从内存中删除岛主信息
                    RD.deletesetMembers(RD.Owner(p.getUniqueId()), p.getUniqueId());
                    //从文件中删除
                    try {
                        write.getInstance().upPlayerM(RD.Owner(p.getUniqueId()), p.getUniqueId());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.chat("/is leave");
                    p.chat("/is leave");
                    break;
                case "lock":
                    p.chat("/is lock");
                    break;
                case "restart":
                    p.chat("/is restart");
                    break;
                case "help":
                    p.sendMessage(RD.getMessage("is_help"));
                    break;
                case "confirm":
                    p.chat("/is confirm");
                    break;
            }

        }else if(args.length == 2){
            switch (args[0]) {
                case "invite":
                    if (RD.getMembersSize(p.getUniqueId()) <= 4) {
                        ProxiedPlayer pp = RD.getPlayer(args[1]);
                        //判断目标当前是否和自己处在同一个服务器
                        if (pp.getServer().getInfo().getName().equals(RD.getServer(p.getUniqueId()).getName())) {
                            p.chat("/is invite " + args[1]);
                            return;
                        }
                        //向目标玩家发送文字消息
                        pp.sendMessage(RD.getMessage("prefix") + RD.getMessage("is_invite_self").replaceAll("%player%", p.getName()));
                        p.sendMessage(RD.getMessage("prefix") + RD.getMessage("is_invite").replaceAll("%player%", p.getName()));
                        //标记目标跨服空岛接收状态
                        status.getInstance().setInvite(pp.getUniqueId(), p.getUniqueId());
                        return;
                    }
                    break;
                case "kick":
                    p.chat("/is kick " + args[1]);
                    break;
                case "ban":
                    p.chat("/is ban " + args[1]);
                    break;
                case "unban":
                    p.chat("/is unban " + args[1]);
                    break;
                case "resetname":
                    p.chat("/is resetname " + args[1]);
                    break;
            }
        }

    }
}
