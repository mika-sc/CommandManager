package com.github.avery.event;

import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class VioMessage implements Listener {
    @EventHandler

    //Deserted module : VioMessage

    public void VoiMessage(AsyncPlayerChatEvent event){
        if(event.getMessage().contains("2b")||event.getMessage().contains("sb")||event.getMessage().contains("2B")||event.getMessage().contains("SB")||event.getMessage().contains("Sb")||event.getMessage().contains("sB")){
            String msg=event.getMessage();
            event.setMessage("***");
            Player pl= event.getPlayer();
            /*pl.sendMessage("§6=================================================");
            pl.sendMessage("§cYou said DIRTY WORDs.You will be permanently banned from these servers if you attempt to say DIRTY WORDs.");
            pl.sendMessage("§6=================================================");*/
            System.out.println(pl+" said §cdirty words§f.Dirty message:"+msg);
            //System.out.println("§6Kick§f[§6K§f]/§4Ban§f[§4B§f]/§aAbort§f[§aA§f]?");

        }
    }
}
