package org.Rpcore.Rpplugin.commands;

import org.Rpcore.Rpplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LOOC implements CommandExecutor {

    private final Main main;

    public LOOC(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;

        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "You must provide a message.");
            return true;
        }

        String message = String.join(" ", args);
        int radius = main.getConfig().getInt("looc-radius", 10);

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (target.getWorld().equals(player.getWorld()) && target.getLocation().distance(player.getLocation()) <= radius) {
                target.sendMessage(ChatColor.AQUA + "[LOOC] " + ChatColor.RESET + player.getName() + ": " + message);
            }
        }

        return false;
    }
}