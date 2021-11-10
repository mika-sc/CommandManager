package com.github.avery.version;

import org.bukkit.Bukkit;

public class getVersion {
    public String getBukkitVer() {
        return Bukkit.getBukkitVersion().split("-")[0];
    }
}
