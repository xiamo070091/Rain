package com.tangml.rain;

import com.tangml.rain.commands.is;
import com.tangml.rain.config.read;
import com.tangml.rain.config.write;
import com.tangml.rain.events.*;
import com.tangml.rain.hook.Mirai;
import com.tangml.rain.toBukkit.getBukkit;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.IOException;

public final class RainBungeeCord extends Plugin implements Listener {
    private static RainBungeeCord instance;
    public static RainBungeeCord getInstance(){
        return instance;
    }
    read RD;

    @Override
    public void onEnable() {
        //接口
        RD = read.getInstance();
        instance = this;
        //注册事件
        getProxy().getPluginManager().registerListener(this, this);
        getProxy().getPluginManager().registerListener(this, new getBukkit());
        getProxy().getPluginManager().registerListener(this, new PlayerJoin());
        getProxy().getPluginManager().registerListener(this, new PlayerLeave());
        getProxy().getPluginManager().registerListener(this, new PlayerSwitch());
        getProxy().getPluginManager().registerListener(this, new MiraiMessage());
        getProxy().getPluginManager().registerListener(this, new NewQQJoin());
        //注册指令
        getProxy().getPluginManager().registerCommand(this, new is());
//        getProxy().getPluginManager().registerCommand(this, new tangml());
        //初始化一些参数
        try {
            write.getInstance().cshwww();
            read.getInstance().cshwww();
            write.getInstance().rnull();
            read.getInstance().readConfig();
            read.getInstance().readLanguage();
            Mirai.getInstance().setQG(Integer.parseInt(RD.getConfig("qq")), Integer.parseInt(RD.getConfig("group")));
            Mirai.getInstance().init();
        } catch (IOException e) {

            e.printStackTrace();
        }



        //String强制转换为int
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
