// Main.java
package org.Rpcore.Rpplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin implements CommandExecutor, Listener {

    public File charactersFile;
    public File Roles; // Add more files as needed

    @Override
    public void onEnable() {
        // Plugin startup logic
        getDataFolder().mkdirs();
        charactersFile = initializeFile("characters.yml");
        Roles = initializeFile("roles.yml");

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Commands();
        Events();
        getLogger().info("Rpcore has been enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private File initializeFile(String fileName) {
        File file = new File(getDataFolder(), fileName);
        if (!file.exists()) {
            getLogger().info(fileName + " not found, creating new file...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                getLogger().warning("Could not create " + fileName + " file");
            }
        } else {
            getLogger().info(fileName + " has been loaded");
        }
        return file;
    }

    public File getCharactersFile() {
        return this.charactersFile;
    }

    public File getRoles() {
        return this.Roles;
    }

    public void Commands() {
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

    public void Events() {
        // Register events
        Bukkit.getPluginManager().registerEvents(new OOC(this), this);
        Bukkit.getPluginManager().registerEvents(new Chatformat(this), this);
        Bukkit.getPluginManager().registerEvents(new displayname(this), this);
        Bukkit.getPluginManager().registerEvents(new clickviewdesc(this), this);
        getLogger().info("Events have been loaded");
    }

        }

