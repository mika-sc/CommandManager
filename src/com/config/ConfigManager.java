package com.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.avery.MyFirstPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
    private HashMap<String, Config> configMap = new HashMap<>();

    public static final ConfigManager INSTANCE = new ConfigManager();

    private static final Plugin PLUGIN = PluginUtil.getPlugin();

    public YamlConfiguration getConfigYaml(String configName) {
        Config config = this.configMap.get(configName);
        if (config == null) {
            config = ConfigUtil.loadConfig(PLUGIN, configName);
            this.configMap.put(configName, config);
        }
        return config.getConfigYaml();
    }
    public Config getConfig(String configName) {
        Config config = this.configMap.get(configName);
        if (config == null) {
            config = ConfigUtil.loadConfig(PLUGIN, configName);
            this.configMap.put(configName, config);
        }
        return config;
    }
    public void reloadConfig(String folder, String configName) {
        if (folder != null) {
            this.configMap.put(configName, null);
            Config config = ConfigUtil.loadConfig(PLUGIN, folder, configName);
            this.configMap.put(configName, config);
        } else {
            this.configMap.put(configName, null);
            Config config = ConfigUtil.loadConfig(PLUGIN, configName);
            this.configMap.put(configName, config);
        }
    }
    public Set<String> getKeysByKey(YamlConfiguration yaml, String fatherKey) {
        Set<String> keys = yaml.getKeys(true);
        Set<String> newKeys = new HashSet<>();
        for (String key : keys) {
            if (key.startsWith(fatherKey))
                newKeys.add(key);
        }
        return newKeys;
    }
    public Set<String> getSonKeys(YamlConfiguration yaml, String fatherKey) {
        int oriNum = (fatherKey.split("\\.")).length;
        Set<String> keys = getKeysByKey(yaml, fatherKey);
        Set<String> newKeys = new HashSet<>();
        for (String key : keys) {
            String[] keyS = key.split("\\.");
            if (keyS.length == oriNum + 1)
                newKeys.add(key);
        }
        return newKeys;
    }
    public void saveConfig(Config config) {
        YamlConfiguration yaml = config.getConfigYaml();
        try {
            yaml.save(config.getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected static class ConfigUtil {
        public static Config loadConfig(Plugin plugin, String configName) {
            File pluginDataFolder = plugin.getDataFolder();
            File configFile = new File(pluginDataFolder, configName);
            if (!pluginDataFolder.exists())
                pluginDataFolder.mkdir();
            if (!configFile.exists())
                plugin.saveResource(configName, true);
            //try {
                Config config = new Config(configFile, YamlConfiguration.loadConfiguration(configFile), configName);
            /*}catch (InvalidConfigurationException e){

            }*/

            return config;
        }
        public static Config loadConfig(Plugin plugin, String folder, String configName) {
            File pluginDataFolder = new File(String.valueOf(plugin.getDataFolder().getPath()) + "\\" + folder);
            File configFile = new File(pluginDataFolder, configName);
            if (!pluginDataFolder.exists())
                pluginDataFolder.mkdir();
            if (!configFile.exists())
                try {
                    configFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            Config config = new Config(configFile, YamlConfiguration.loadConfiguration(configFile), configName);
            return config;
        }
    }
    protected static class PluginUtil {
        public static Plugin getPlugin() {
            return (Plugin) MyFirstPlugin.getPlugin();
        }
    }
}

