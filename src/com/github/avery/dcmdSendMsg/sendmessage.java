package com.github.avery.dcmdSendMsg;

import com.github.avery.MyFirstPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class sendmessage {
    public static sendmessage plugin = new sendmessage();
    public void message(String befMessage, CommandSender sender){
        sender.sendMessage("§eDelayCommand §3>>> §e"+befMessage);
    }
    public static sendmessage getPlugin(){
        return plugin;
    }
}
