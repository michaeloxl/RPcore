package org.Rpcore.Rpplugin;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;

public class onjoin implements Listener {
    private final Main main;

    public onjoin(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        File charactersFile = main.getCharactersFile();
        YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(charactersFile);
        String playerUUID = event.getPlayer().getUniqueId().toString();
        Player player = event.getPlayer();
        String runKey = playerUUID + ".Run";
        String levelKey = playerUUID + ".Level";
        String roleKey = playerUUID + ".role";
        String nameKey = playerUUID + ".rpame";
        String descKey = playerUUID + ".desc";
        String ageKey = playerUUID + ".age";

        if (modifyfile.getString(runKey) == null) {
            main.getLogger().info("Creating default paths for player " + player.getName());
            modifyfile.set(runKey, "0");
        }
        if (modifyfile.getString(levelKey) == null) {
            main.getLogger().info("Creating default paths for player " + player.getName());
            modifyfile.set(levelKey, "0");
        }
        if (modifyfile.getString(roleKey) == null) {
            main.getLogger().info("Creating default paths for player " + player.getName());
            modifyfile.set(roleKey, "Default");
        }
        if (modifyfile.getString(nameKey) == null) {
            main.getLogger().info("Creating default paths for player " + player.getName());
            modifyfile.set(nameKey, player.getName());
        }
        if (modifyfile.getString(descKey) == null) {
            main.getLogger().info("Creating default paths for player " + player.getName());
            modifyfile.set(descKey, "W.I.P");
        }
        if (modifyfile.getString(ageKey) == null) {
            main.getLogger().info("Creating default paths for player " + player.getName());
            modifyfile.set(ageKey, "0");
        }


        try {
                modifyfile.save(charactersFile);
                main.getLogger().info("Created default paths for player " + playerUUID);
            } catch (IOException e) {
                main.getLogger().warning("Could not save characters.yml for player " + playerUUID);
            }
        }
    }
