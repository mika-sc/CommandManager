package com.config;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
    private File configFile;

    private YamlConfiguration configYaml;

    private String configName;

    public Config(File configFile, YamlConfiguration configYaml, String configName) {
        setConfigFile(configFile);
        setConfigYaml(configYaml);
        this.configName = configName;
    }


    public YamlConfiguration getConfigYaml() {
        return this.configYaml;
    }

    public void setConfigYaml(YamlConfiguration configYaml) {
        this.configYaml = configYaml;
    }

    public File getConfigFile() {
        return this.configFile;
    }

    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

    public String getName() {
        return this.configName;
    }
}

