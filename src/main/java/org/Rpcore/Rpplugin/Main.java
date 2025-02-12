package org.Rpcore.Rpplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
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
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        Commands();
        Events();
        Tab();
        getLogger().info("Rpcore has been enabled");
        YamlConfiguration charactersConfig = YamlConfiguration.loadConfiguration(getCharactersFile());
        YamlConfiguration rolesConfig = YamlConfiguration.loadConfiguration(getRoles());
        rolesConfig.options().copyDefaults(true);
        // Check if the configuration section exists
        if (rolesConfig.getConfigurationSection("roles") == null) {
            getLogger().info("The 'roles' section is missing in roles.yml, creating default section...");
            rolesConfig.createSection("roles");
            rolesConfig.set("roles.Example1", "&7[test]");
            rolesConfig.set("roles.Example2", "&7[test]");
            rolesConfig.set("roles.Example3", "&7[test]");
            rolesConfig.set("roles.Default", "&7[Grade 7]");
        }

        // Set default values from roles.yml
        getConfig().addDefaults(rolesConfig.getConfigurationSection("roles").getValues(true));
        saveConfig(); // Save the main config with the new defaults

        try {
            rolesConfig.save(getRoles());
            getLogger().info("roles.yml has been saved successfully");
        } catch (IOException e) {
            getLogger().warning("Could not save roles.yml");

        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Rpcore has been disabled");

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
        //Bukkit.getPluginCommand("attributes").setExecutor(new attributes(this));
        getLogger().info("Commands have been loaded");
    }

    public void Events() {
        // Register events
        Bukkit.getPluginManager().registerEvents(new OOC(this), this);
        Bukkit.getPluginManager().registerEvents(new Chatformat(this), this);
        Bukkit.getPluginManager().registerEvents(new displayname(this), this);
        Bukkit.getPluginManager().registerEvents(new clickviewdesc(this), this);
        Bukkit.getPluginManager().registerEvents(new Run_attribute(this), this);
        Bukkit.getPluginManager().registerEvents(new onjoin(this), this);
        getLogger().info("Events have been loaded");

    }

    public void Tab() {
        // Register tab completion
        Bukkit.getPluginCommand("rpname").setTabCompleter(new RPNametab(this));
        Bukkit.getPluginCommand("setdesc").setTabCompleter(new setdesctab(this));
        Bukkit.getPluginCommand("setage").setTabCompleter(new setagetab(this));
        Bukkit.getPluginCommand("setrole").setTabCompleter(new setroletab(this));
        getLogger().info("Tab completion has been loaded");
    }


}