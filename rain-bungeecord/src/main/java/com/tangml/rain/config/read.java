package com.tangml.rain.config;

import com.tangml.rain.RainBungeeCord;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class read {
    private static final read instance = new read();
    private read(){}
    public static read getInstance(){
        return instance;
    }
    //引入外部参数
    private static RainBungeeCord RBC;
    private static write Write;
    //定义配置文件
    private static File PluginPath;
    private static File ConfigPath;
    private static File Language;
    private static File PlayerData;
    //定义玩家数据类型
    private static  HashMap<UUID,String> Player;
    private static HashMap<UUID,String> Server;
    private static HashMap<UUID,UUID> Owner;
    private static HashMap<UUID,ArrayList<UUID>> Members;
    //定义语言数据类型
    private static HashMap<String,String> language;
    //定义Config类型
    private static HashMap<String,String> Config;
    //初始化数据
    public void cshwww(){
        Player = new HashMap<>();
        Server = new HashMap<>();
        Owner = new HashMap<>();
        Members = new HashMap<>();
        language = new HashMap<>();
        Write = write.getInstance();
        Config = new HashMap<>();
        RBC = RainBungeeCord.getInstance();
        PluginPath = RBC.getDataFolder();
        ConfigPath = new File(PluginPath,"config.yml");
        Language = new File(PluginPath,"language.yml");
        PlayerData = new File(PluginPath,"/PlayerData/");
    }
    //初始化玩家参数
    public void csh(UUID uuid) throws IOException {
        //初始化玩家数据
        Write.csh(uuid);
        //定义玩家配置
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(PlayerData, uuid +".yml"));
        //从配置文件获取参数
        upPlayer(uuid,config);
    }
    //从文件获取玩家信息
    private void upPlayer(UUID uuid,Configuration config){
        Player.put(uuid,String.valueOf(config.get("name")));
        Server.put(uuid,String.valueOf(config.get("server")));
        Owner.put(uuid,UUID.fromString(String.valueOf(config.get("owner"))));
        ArrayList<UUID> members = new ArrayList<>();
        if(!config.get("members").toString().equalsIgnoreCase("00000000-0000-0000-0000-000000000000")){
//            if(getConfig("debug").equalsIgnoreCase("debug")) {
//                System.out.println("members" + config.get("members"));
//                System.out.println("wqeqweqw" + config.getStringList("members"));
//            }
            for (String uuid1 : config.getString("members").replaceAll("[\\[\\]]","").split(",")) {
                members.add(UUID.fromString(uuid1));
                System.out.println("members"+uuid1);
            }
            Members.put(uuid, members);
        }else {
            members.add(UUID.fromString("00000000-0000-0000-0000-000000000000"));
//            if(getConfig("debug").equalsIgnoreCase("debug"))
//                System.out.println("members"+members);
            Members.put(uuid,members);
        }
    }
    //初始化语言文件
    public void readLanguage() throws IOException {
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(Language);
        //遍历config赋值给language
        for (String key : config.getKeys()) {
//            if(getConfig("debug").equalsIgnoreCase("debug"))
//                System.out.println(key+" "+config.get(key));
            language.put(key, String.valueOf(config.get(key)).replaceAll("&","§"));
        }
    }
    //读取Config文佳
    public void readConfig() throws IOException {
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(ConfigPath);
        //遍历config赋值给language
        for (String key : config.getKeys()) {
            Config.put(key,String.valueOf(config.get(key)));
        }
    }

    //==========================================下面是读取玩家信息==============================================//
    //读取玩家岛屿服务器
    public String getServerID(UUID uuid) {
        return Server.get(uuid);
    }
    public ServerInfo getServer(UUID uuid) {
        //根据服务器名获取服务器
        return RBC.getProxy().getServerInfo(Server.get(uuid));
    }
    //修改服务器
    public void setServerID(UUID uuid,String serverID) {
        Server.put(uuid,serverID);
    }
    //读取玩家团队信息
    public ArrayList<UUID> getMembers(UUID uuid) {
        return Members.get(uuid);
    }
    public int getMembersSize(UUID uuid) {
        ArrayList<UUID> suuid = Members.get(uuid);
//        if(getConfig("debug").equalsIgnoreCase("debug")) {
//            System.out.println(suuid);
//        }
        if (suuid == null) return 0;
        if(suuid.get(suuid.size()-1).toString().equalsIgnoreCase("00000000-0000-0000-0000-000000000000")) return 0;
        return Members.get(uuid).size();
    }
    //修改玩家团队信息
    public void setMembers(UUID uuid,UUID members) {
        if(Members.get(uuid).get(0).toString().equalsIgnoreCase("00000000-0000-0000-0000-000000000000")){
            ArrayList<UUID> members1 = new ArrayList<>();
            members1.add(members);
            Members.put(uuid,members1);
            return;
        }
        if(!Members.get(uuid).contains(members)){
            ArrayList<UUID> members1;
            members1 = Members.get(uuid);
            members1.add(members);
            Members.put(uuid,members1);
        }
    }
    public void deletesetMembers(UUID uuid,UUID members) {
            ArrayList<UUID> members1;
            members1 = Members.get(uuid);
            members1.remove(members);
            Members.put(uuid,members1);
    }
    //读取玩家是不是岛主
    public boolean isOwner(UUID uuid) {
        return Owner.get(uuid).equals(uuid);
    }
    //修改玩家岛主信息
    public void setOwner(UUID uuid,UUID uuid1){
        Owner.put(uuid,uuid1);
    }
    //读取玩家岛主
    public UUID Owner(UUID uuid) {
        return Owner.get(uuid);
    }
    //读取玩家
    public ProxiedPlayer getPlayer(UUID uuid){
        return RBC.getProxy().getPlayer(uuid);
    }
    public ProxiedPlayer getPlayer(String name){
        return RBC.getProxy().getPlayer(name);
    }
    //从内存中删除玩家
    public void removePlayer(UUID uuid){
        Player.remove(uuid);
        Server.remove(uuid);
        Owner.remove(uuid);
        Members.remove(uuid);
    }
    //获取玩家集合
    public HashMap<UUID, String> getPlayerList(){
        return Player;
    }
    //==============================================结束=====================================================//
    //===========================================读取语言消息=================================================//
    public String getMessage(String key){
        return language.get(key);
    }
    //==============================================结束=====================================================//
    //===========================================获取config================================================//
    public String getConfig(String key){
        return Config.get(key);
    }


}
