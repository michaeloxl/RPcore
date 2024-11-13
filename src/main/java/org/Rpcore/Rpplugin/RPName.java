// RPName.java
package org.Rpcore.Rpplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class RPName implements CommandExecutor {
    private final Main main;

    public RPName(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            if (args.length >= 1 && args.length <= 5) {
                Player player = (Player) sender;
                String rpname = String.join(" ", args); // Join args to form the rpname
                YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getFile());
                modifyfile.set(player.getUniqueId() + ".rpname", rpname);
                player.setDisplayName(rpname);
                player.setPlayerListName(rpname);
                try {
                    modifyfile.save(main.getFile());
                } catch (Exception e) {
                    main.getLogger().warning("Could not save the file");
                }
            } else {
                sender.sendMessage("You must provide between 1 and 4 arguments.");
            }
            return true;
        } else {
            sender.sendMessage("You must be a player to use this command.");
            return true;
        }
    }
}