package com.tangml.rain.u;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class sendServer {
    //懒汉式
    private static sendServer instance = null;
    private sendServer(){}
    public static sendServer getInstance(){
        if(instance == null){
            instance = new sendServer();
        }
        return instance;
    }

    public void sendPlayer(ProxiedPlayer p, String server) {
    }
}
