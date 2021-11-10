package com.command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.YamlConfiguration;
import com.github.avery.event.*;

public class CommandRunnable extends BukkitRunnable {
    private List<String> needRemoveCommands = new ArrayList<>();
    String tempName;
    public void getname(String nam){
        this.tempName=nam;
        System.out.println("Name:"+this.tempName);
    }
    private List<String> commands = CommandManager.INSTANCE.getCommands();
    private List<String> delcommands = CommandManager.INSTANCE.getDelcommands();
    public void run(){
        //if (!commands.isEmpty())
            for (String command : commands) {
                String[] trueCommand = command.split(",");
                String pla = trueCommand[0];
                String commandStr = trueCommand[1];
                //System.out.println(this.namep+" "+pla+" Command:"+command);
                if (this.tempName.equals(pla)) {
                    //System.out.println(namep+" "+pla);
                    this.needRemoveCommands.add(command);
                    /*File con = new File(".\\DelayCommand\\data.yml");
                    YamlConfiguration conYaml = YamlConfiguration.loadConfiguration(con);
                    conYaml.set(this.tempName,"");*/
                    //Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "npc create tmp");
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),commandStr);
                }
            }
        for (String remove : this.needRemoveCommands)
            commands.remove(remove);
        this.needRemoveCommands.clear();
    }
    public void remove() {
        //if (!delcommands.isEmpty())
            //System.out.println("REMOVE TEST_1.2_Unstable");
            for (String command : delcommands) {
                String[] trueCommand = command.split(",");
                String pla = trueCommand[0];
                String commandStr = trueCommand[1];
                //System.out.println(this.namep+" "+pla+" Command:"+command);
                if (this.tempName.equals(pla)) {
                    //System.out.println(namep+" "+pla);
                    this.needRemoveCommands.add(command);
                    //File con = new File(".\\DelayCommand\\data.yml");
                    //YamlConfiguration conYaml = YamlConfiguration.loadConfiguration(con);
                    //conYaml.set(this.tempName,"");
                    //Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "npc create tmp");
                    //Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),commandStr);
                }
            }
        for (String remove : this.needRemoveCommands)
            commands.remove(remove);
        this.needRemoveCommands.clear();
    }
}
