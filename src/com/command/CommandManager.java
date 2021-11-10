package com.command;

import com.config.Config;
import com.config.ConfigManager;
import java.util.ArrayList;
import java.util.List;
import com.github.avery.MyFirstPlugin;
import org.bukkit.plugin.Plugin;

public class CommandManager {
    public static final CommandManager INSTANCE = new CommandManager();
    private List<String> commands;
    private List<String> delcommands;
    private List<String> timecommands;
    private List<String> deltimecommands;

    public void init(int step) {
        if(step==0)//this.commands = ConfigManager.INSTANCE.getConfigYaml("data.yml").getStringList("Commands");
        this.timecommands = ConfigManager.INSTANCE.getConfigYaml("data.yml").getStringList("commands");
        Config config = ConfigManager.INSTANCE.getConfig("data.yml");
        //config.;
        if(this.commands == null)this.commands = new ArrayList<>();
        this.delcommands = new ArrayList<>();
    }

    public void save() {
        Config config = ConfigManager.INSTANCE.getConfig("data.yml");
        //config.getConfigYaml().set("Commands", this.commands);
        //ConfigManager.INSTANCE.saveConfig(config);
    }

    public List<String> getCommands() {
        return this.commands;
    }

    public List<String> getDelcommands() {
        return this.delcommands;
    }

    public void addDelcommands(String command) {
        this.delcommands.add(command);
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }

    public void addCommand(String command) {
        this.commands.add(command);
    }
}
