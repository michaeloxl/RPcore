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
        String Chatrole = roles.getString("roles." + role);
        String formattedChatrole = ChatColor.translateAlternateColorCodes('&', Chatrole);
        event.setCancelled(true); // Cancel the normal chat

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (rpname != null && target.getWorld().equals(event.getPlayer().getWorld()) && target.getLocation().distance(event.getPlayer().getLocation()) <= radius) {
                target.sendMessage(  formattedChatrole + " " + ChatColor.RESET + rpname + " says " + message);
            } else if (rpname == null && target.getWorld().equals(event.getPlayer().getWorld()) && target.getLocation().distance(event.getPlayer().getLocation()) <= radius) {
                target.sendMessage(  formattedChatrole +" "+ ChatColor.RESET+ event.getPlayer().getName() + " says " + message);
            } else if (Chatrole == null) {
                modifyfile.set(event.getPlayer().getUniqueId() + ".role", "Default");
                target.sendMessage(  formattedChatrole + " " + ChatColor.RESET + rpname + " says " + message);
            }
        }
        }
    }
