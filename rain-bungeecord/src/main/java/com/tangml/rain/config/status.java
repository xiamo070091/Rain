package com.tangml.rain.config;

import java.util.HashMap;
import java.util.UUID;

public class status {
    //懒汉式
    private static status instance = null;
    private status(){}
    public static status getInstance(){
        if (instance == null) {
            instance = new status();
        }
        return instance;
    }
    //定义玩家的几种状态
    private static final HashMap<UUID, UUID> isInvite = new HashMap<>();
    private static final HashMap<UUID, Boolean> isIS = new HashMap<>();
    //设置
    public void setInvite(UUID uuid,UUID uuid1){
        isInvite.put(uuid,uuid1);
    }
    public void removeInvite(UUID uuid){
        isInvite.remove(uuid);
    }
    public void setIS(UUID uuid,boolean b){
        isIS.put(uuid,b);
    }
    //读取
    public boolean isInvite(UUID uuid){
        return isInvite.get(uuid)==null;
    }
    //查询时候被邀请
    public boolean isInvitep(UUID uuid){
        return isInvite.get(uuid) != null;
    }
    public boolean isIS(UUID uuid){
        if(isIS.get(uuid)==null){
            isIS.put(uuid,false);
        }
        return isIS.get(uuid);
    }
    public UUID getInvite(UUID uuid){
        return isInvite.get(uuid);
    }


}
