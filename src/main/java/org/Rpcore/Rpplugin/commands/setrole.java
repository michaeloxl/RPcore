package org.Rpcore.Rpplugin.commands;

import org.Rpcore.Rpplugin.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;


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
                YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getCharactersFile());;
                modifyfile.set(target.getUniqueId() + ".role", args[1]);
                try {
                    modifyfile.save(main.getCharactersFile());
                } catch (IOException e) {
                    main.getLogger().warning("Could not save the file");
                }
                target.sendMessage("Your role has been set to " + args[1]);
                return true;

            }
        }






        return false;
    }
}
