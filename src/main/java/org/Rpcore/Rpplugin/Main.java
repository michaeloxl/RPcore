// Main.java
package org.Rpcore.Rpplugin;
import org.bukkit.Bukkit;
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
    // Load the characters.yml file
        YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(file);
        getLogger().info("characters.yml has been loaded");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Commands();
        //Give the server time to load the commands and events
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        Events();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        getLogger().info("Rpcore has been enabled");


    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public File getFile() {
        return this.file;
    }
public void Commands(){
        // Register commands
        Bukkit.getPluginCommand("ooc").setExecutor(new OOC(this));
    Bukkit.getPluginCommand("looc").setExecutor(new LOOC(this));
    Bukkit.getPluginCommand("rpname").setExecutor(new RPName(this));
    Bukkit.getPluginCommand("setdesc").setExecutor(new setdesc(this));
    Bukkit.getPluginCommand("setage").setExecutor(new setage(this));
    Bukkit.getPluginCommand("viewdesc").setExecutor(new viewdesc(this));
    Bukkit.getPluginCommand("setrole").setExecutor(new setrole(this));
    getLogger().info("Commands have been loaded");
}
public void Events(){
    // Register events
        Bukkit.getPluginManager().registerEvents(new OOC(this), this);
    Bukkit.getPluginManager().registerEvents(new Chatformat(this), this);
    Bukkit.getPluginManager().registerEvents(new displayname(this),this);
    Bukkit.getPluginManager().registerEvents(new clickviewdesc(this), this);
    getLogger().info("Events have been loaded");

}

}