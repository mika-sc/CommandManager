package com.github.avery.event;

/*import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.VersionRange;*/
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.command.*;

import java.io.File;
/*import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.bukkit.platform.BukkitViaAPI;*/

public class PlayerJoin implements Listener{
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event){
        File set = new File("\\DelayCommand\\config.yml");
        YamlConfiguration setyml = YamlConfiguration.loadConfiguration(set);
        String p=event.getPlayer().getName();
        if("enable".equalsIgnoreCase(setyml.getString("enablejoinmessage")))event.setJoinMessage("[§a+§f]§e "+p);

        CommandRunnable runnable = new CommandRunnable();
        runnable.getname(p);
        runnable.run();
        //int PlayerVer=tmp.getPlayerVersion();


    }
}
