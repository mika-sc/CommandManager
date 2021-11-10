package com.github.avery.event;

import cc.baka9.catseedlogin.event.CatSeedPlayerRegisterEvent;
import com.command.CommandRunnable;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;

public class CatReg implements Listener {
    @EventHandler
    public void CatReg(CatSeedPlayerRegisterEvent event){
        File set = new File("\\DelayCommand\\config.yml");
        YamlConfiguration setyml = YamlConfiguration.loadConfiguration(set);
        String p=event.getPlayer().getName();
        //if("enable".equalsIgnoreCase(setyml.getString("enablejoinmessage")))event.setJoinMessage("[§a+§f]§e "+p);
        CommandRunnable runnable = new CommandRunnable();
        runnable.getname(p);
        runnable.run();
    }
}
