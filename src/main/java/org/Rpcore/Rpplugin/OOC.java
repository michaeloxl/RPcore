// OOC.java
package org.Rpcore.Rpplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

public class OOC implements CommandExecutor, Listener {
    private final List<UUID> Enabled = new ArrayList<>();
    private final Logger logger = Bukkit.getLogger();

    public OOC(Main main) {
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Enabled.add(player.getUniqueId());
        logger.info("Player " + player.getName() + " added to Enabled list.");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player1 = (Player) commandSender;
            UUID senderUUID = player1.getUniqueId();
            String Message = args.length >= 1 ? String.join(" ", args) : "";

            if (args.length >= 1 && Enabled.contains(senderUUID)) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (Enabled.contains(player.getUniqueId())) {
                        player.sendMessage(ChatColor.AQUA + "[OOC] " + ChatColor.RESET + player1.getName() + ": " + Message);
                    }
                }
            } else if (args.length >= 1 && !Enabled.contains(senderUUID)) {
                player1.sendMessage(ChatColor.RED + "You can't send OOC messages with OOC disabled.");
                return true; // Cancel the command
            } else if (args.length != 1 && Enabled.contains(senderUUID)) {
                Enabled.remove(senderUUID);
                player1.sendMessage(ChatColor.RED + "You have disabled OOC");
            } else if (args.length != 1 && !Enabled.contains(senderUUID)) {
                Enabled.add(senderUUID);
                player1.sendMessage(ChatColor.GREEN + "You have enabled OOC");
            }
        }
        return true;
    }
    @EventHandler
    public void OnPlayerLeave(PlayerQuitEvent event2) {
        //To remove the player from the list when they leave
        Player player2 = event2.getPlayer();
        Enabled.remove(player2.getUniqueId());
        logger.info("Player " + player2.getName() + " removed from Enabled list because they left the server.");
    }



}