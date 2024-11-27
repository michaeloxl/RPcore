package org.Rpcore.Rpplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.permissions.Permission;

public class viewdesc implements CommandExecutor {
    private final Main main;

    public viewdesc(Main main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender Sender, Command command, String s, String[] args) {
        Player player = (Player) Sender;
        if (args.length== 1 && Sender instanceof Player) {
            YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getCharactersFile());
            String desc = modifyfile.getString(player.getUniqueId() + ".desc");
            if (desc != null) {
                player.sendMessage("Name: " + modifyfile.getString(player.getUniqueId() + ".rpname") + "\nAge: " + modifyfile.getString(player.getUniqueId() + ".age") + "\nDescription: " + desc);


            }
        } else if (args.length == 2 && Sender.hasPermission("rp.viewdesc.admin")) {
            Player target = main.getServer().getPlayer(args[1]);
            if (target != null) {
                YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getCharactersFile());
                String desc = modifyfile.getString(target.getUniqueId() + ".desc");
                if (desc != null) {
                    player.sendMessage("Name: " + modifyfile.getString(target.getUniqueId() + ".rpname") + "\nAge: " + modifyfile.getString(target.getUniqueId() + ".age") + "\nDescription: " + desc);
                }
            }

        }
        return false;
    }
}