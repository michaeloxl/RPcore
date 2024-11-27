package org.Rpcore.Rpplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chatformat implements Listener {
    private final Main main;

    public Chatformat(Main main) {
        this.main = main;
    }

    @EventHandler
    public void AsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getCharactersFile());
        YamlConfiguration roles = YamlConfiguration.loadConfiguration(main.getRoles());
        String rpname = modifyfile.getString(event.getPlayer().getUniqueId() + ".rpname");
        int radius = main.getConfig().getInt("ic-radius", 7);
        String role = modifyfile.getString(event.getPlayer().getUniqueId() + ".role");
        String Chatrole = roles.getString(role);
        String colorCode = roles.getString(role, "&f"); // Default to white if no color is found
        String translatedColorCode = ChatColor.translateAlternateColorCodes('&', colorCode);
        event.setCancelled(true); // Cancel the normal chat

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (rpname != null && target.getWorld().equals(event.getPlayer().getWorld()) && target.getLocation().distance(event.getPlayer().getLocation()) <= radius) {
                target.sendMessage(translatedColorCode + " " + ChatColor.RESET + rpname + " says " + message);
            } else if (rpname == null && target.getWorld().equals(event.getPlayer().getWorld()) && target.getLocation().distance(event.getPlayer().getLocation()) <= radius) {
                target.sendMessage(event.getPlayer().getName() + " says " + message);
            }
        }
    }
}