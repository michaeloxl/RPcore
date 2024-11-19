
package org.Rpcore.Rpplugin;

import org.bukkit.Bukkit;
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
        YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getFile());
        String rpname = modifyfile.getString(event.getPlayer().getUniqueId() + ".rpname");
        int radius = main.getConfig().getInt("ic-radius", 7);
        String role = modifyfile.getString(event.getPlayer().getUniqueId() + ".role");
        String Chatrole = main.getConfig().getString("roles" + "." + role);
        event.setCancelled(true); // Cancel the normal chat


        for (Player target : Bukkit.getOnlinePlayers()) {
            if (rpname != null && target.getWorld().equals(event.getPlayer().getWorld()) && target.getLocation().distance(event.getPlayer().getLocation()) <= radius) {
                target.sendMessage(Chatrole + rpname + " says " + message);


            }
        }
    }
}