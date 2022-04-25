package com.tangml.rain.config;

import com.tangml.rain.RainBungeeCord;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class write {
    private static final write instance = new write();
    private write(){}
    public static write getInstance() {
        return instance;
    }

    //获取主配置
    private static RainBungeeCord RBC;
    //定义配置文件
    private static File PluginPath;
    private static File ConfigPath;
    private static File Language;
    private static File PlayerData;
    //引入外部数据
    public void cshwww(){
        RBC = RainBungeeCord.getInstance();
        PluginPath = RBC.getDataFolder();
        ConfigPath = new File(PluginPath,"config.yml");
        Language = new File(PluginPath,"language.yml");
        PlayerData = new File(PluginPath,"/PlayerData/");
    }
    //尝试创建必要的文件
    public void rnull() throws IOException {

        //创建主要目录
        if (!PluginPath.exists()) {
            PluginPath.mkdir();
        }
        //尝试创建 ConfigPath 文件
        if (!ConfigPath.exists()) {
            ConfigPath.createNewFile();
        }
        //尝试创建PlayerData文件夹
        if (!PlayerData.exists()) {
            PlayerData.mkdir();
        }
        //尝试创建language文件
        if (!Language.exists()) {
            Language.createNewFile();
        }
        //初始化config文件必要参数
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(ConfigPath);
        if (!(config.getString("version").equalsIgnoreCase("ver0.2"))) {
            System.out.println("初始化配置文件");
            config.set("version", "ver0.2");
            config.set("language", "zh_CN");
            config.set("qq", "503384825");
            config.set("group", "954128797");
            config.set("debug", "INFO");
            //回写config
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, ConfigPath);
        }
        //读取language文件
        Configuration language = ConfigurationProvider.getProvider(YamlConfiguration.class).load(Language);
        if (!(language.getString("version").equalsIgnoreCase("ver0.4"))) {
            System.out.println("初始化语言文件");
            language.set("version", "ver0.4");
            language.set("prefix", "[跨服空岛]");
            language.set("is_invite_self", "%player%邀请您加入他的跨服空岛，是否同意？\n接收请输入：/is accept 拒绝请输入：/is refuse");
            language.set("is_invite", "您的跨服空岛邀请已发送，请等待对方确认");
            language.set("is_haveMember", "您的空岛还有其他成员，请先将其他玩家踢出空岛或转让给其他玩家岛主");
            language.set("is_from_self", "您只能在您的空岛中使用该命令");
            language.set("is_accept", "%player%已接收您的空岛邀请请求");
            language.set("is_accept_self", "您已接受空岛邀请请求");
            language.set("is_tp_error", "返回空岛时目标数据校验失败，请重新进入服务器后重试");
            language.set("QGprefix", "[QQ]");
            language.set("QGropuD", "[%player%]");
            language.set("rain_join", "&f[&a&l+&f] &e%player% &f进入了游戏");
            language.set("rain_leave", "&f[&a&l+&f] &e%player% &f离开了游戏");
            language.set("is_notInIs","您不在您的空岛服务器中，使用/is返回空岛后才能使用该命令");
            language.set("is_help", "======跨服空岛帮助======\n" +
                    "/is invite <玩家>   将某玩家邀请到您的空岛\n" +
                    "/is accept         接收某玩家的邀请\n" +
                    "/is refuse         拒绝某玩家的邀请\n" +
                    "/is lock           锁定/解锁空岛\n" +
                    "/is sethome        设置返回空岛的传送点\n" +
                    "/is team           查看当前空岛的团队信息\n" +
                    "/is kick <玩家>     将某位玩家踢出空岛\n"+
                    "/is ban <玩家>      禁止某玩家进入空岛 \n"+
                    "/is unban <玩家>     接触对某玩家的禁止\n"+
                    "/is resetname <名字> 设置您的空岛名\n"+
                    "当前系统有腐竹编写，时间有限后期完善！"

            );
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(language, Language);
            //保存 language 文件
        }
    }
    //初始化玩家配置文件
    public void csh(UUID uuid) {
        //尝试创建Player文件
        File Player = new File(PlayerData,uuid.toString()+".yml");
        if (!Player.exists()) {
            try {
                int i;
                if(Math.random()>=0.45) {
                    i=1;
                }else {
                    i=2;
                }
                Player.createNewFile();
                Configuration config;
                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(Player);
                config.set("uuid", uuid.toString());
                config.set("server", "id"+i);
                config.set("owner", uuid.toString());
                config.set("name", RBC.getProxy().getPlayer(uuid).getName());
                config.set("members", "00000000-0000-0000-0000-000000000000");
                //回写config到Player文件
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, Player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //更新玩家配置文件
    public void upPlayer(int i,UUID uuid) throws IOException {
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(PlayerData,uuid.toString()+".yml"));
        config.set("server", "id"+i);
        //回写文件
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(PlayerData, uuid +".yml"));
    }
    //更新玩家岛主信息
    public void upPlayer(UUID owner,UUID uuid) throws IOException {
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(PlayerData,uuid.toString()+".yml"));
        config.set("owner", owner.toString());
        //回写文件
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(PlayerData, uuid +".yml"));
    }
    //更新团队信息
    public void upPlayerM(UUID team,UUID uuid) throws IOException {
        Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(PlayerData,uuid.toString()+".yml"));
        config.set("members", read.getInstance().getMembers(team).toString());
//        if(read.getInstance().getConfig("debug").equalsIgnoreCase("debug"))
//            System.out.println(config);
        //回写文件
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(PlayerData, team +".yml"));
    }



}
