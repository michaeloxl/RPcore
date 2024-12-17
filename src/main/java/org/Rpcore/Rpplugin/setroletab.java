package org.Rpcore.Rpplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Calendar;
import java.util.List;

public class setroletab implements TabCompleter {
    private final Main main;

    public setroletab(Main main) {
        this.main = main;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            String onlinelist = Bukkit.getOnlinePlayers().toString();
            return List.of(onlinelist);
        } else if (args.length == 2) {
            YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getRoles());
            String roles = modifyfile.getString("roles");
            return List.of(roles);


        }
        return List.of();
    }
}