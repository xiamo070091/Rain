package com.tangml.rain.hook;

import me.dreamvoid.miraimc.api.MiraiBot;
import me.dreamvoid.miraimc.api.bot.MiraiGroup;

public class Mirai {
    private static Mirai instance;
    private Mirai() {}
    public static Mirai getInstance() {
        if (instance == null) {
            instance = new Mirai();
        }
        return instance;
    }
    //定义QQ以及QQ群
    private long qq = 0;
    private long group = 0;
    MiraiBot bot;
    MiraiGroup miraiGroup;
    public void setQG(long qq, long group) {
        this.qq = qq;
        this.group = group;
    }
    //尝试初始化bot和MiraiGroup
    public void init() {
        //新线程 Thread
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10000);
                    bot = MiraiBot.getBot(qq);
                    miraiGroup = bot.getGroup(group);
                    System.out.println("机器人初始化成功"+bot.getID()+" "+miraiGroup.getName());
                    Thread.sleep(5000);
                    sendMessage("艾兜已经初始化成功啦，有问题可以输入：帮助");
                    return;
                } catch (Exception e) {
                    System.out.println("初始化失败,正在重试");
                }
            }
        }).start();
    }
    public void sendMessage(String message) {
        //新线程 Thread
        new Thread(() -> {
            miraiGroup.sendMessage(message);
            return;
        }).start();
    }
}
