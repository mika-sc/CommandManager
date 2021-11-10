package com.github.avery.event;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import com.command.*;

import java.io.File;

public class PlayerQuit implements Listener {
    @EventHandler
    public void PlayerQuit(PlayerQuitEvent event){
        File set = new File("\\DelayCommand\\config.yml");
        YamlConfiguration setyml = YamlConfiguration.loadConfiguration(set);
        String p=event.getPlayer().getName();
        if("enable".equalsIgnoreCase(setyml.getString("enablejoinmessage")))event.setQuitMessage("[§4-§f]§e "+p);
    }
}
