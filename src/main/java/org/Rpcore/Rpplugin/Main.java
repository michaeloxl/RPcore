// Main.java
package org.Rpcore.Rpplugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    private File file;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getDataFolder().mkdirs();
        file = new File(getDataFolder(), "characters.yml");
        if (!file.exists()) {
            getLogger().info("characters.yml not found, creating new file...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                getLogger().warning("Could not create characters.yml file");
                return;
            }
        }

        YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(file);
        getLogger().info("characters.yml has been loaded");

        OOC oocInstance = new OOC();
        LOOC loocInstance = new LOOC(this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getPluginCommand("ooc").setExecutor(oocInstance);
        Bukkit.getPluginCommand("looc").setExecutor(loocInstance);
        Bukkit.getPluginManager().registerEvents(oocInstance, this);
        Bukkit.getPluginManager().registerEvents(new Chatformat(this), this);
        Bukkit.getPluginCommand("rpname").setExecutor(new RPName(this));
        Bukkit.getPluginManager().registerEvents(new displayname(this),this);
        Bukkit.getPluginCommand("setdesc").setExecutor(new setdesc(this));
        Bukkit.getPluginManager().registerEvents(new clickviewdesc(this), this);
        Bukkit.getPluginCommand("setage").setExecutor(new setage(this));
        Bukkit.getPluginCommand("viewdesc").setExecutor(new viewdesc(this));
        Bukkit.getPluginCommand("setrole").setExecutor(new setrole(this));
        getLogger().info("plugin has been enabled");




    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public File getFile() {
        return this.file;
    }
}