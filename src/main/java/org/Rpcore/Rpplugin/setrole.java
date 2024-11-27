package org.Rpcore.Rpplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class setrole implements CommandExecutor{
    private final Main main;

    public setrole(Main main) {
        this.main = main;
    }



    @Override
    public boolean onCommand(CommandSender Sender, Command command, String s, String[] args) {
        if (Sender.hasPermission("rpcore.setrole")) {
            Player target =  main.getServer().getPlayer(args[0]);
            if (args.length == 2) {
                YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getFile());
                modifyfile.set(target.getUniqueId() + ".role", args[1]);
                main.saveConfig();
                target.sendMessage("Your role has been set to " + args[1]);
                return true;

            }
        }






        return false;
    }
}
