
package org.Rpcore.Rpplugin.Tabcomplete;

import org.Rpcore.Rpplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class setroletab implements TabCompleter {
    private final Main main;

    public setroletab(Main main) {
        this.main = main;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 1) {
            return Bukkit.getOnlinePlayers().stream()
                    .map(player -> player.getName())
                    .collect(Collectors.toList());
        } else if (args.length == 2) {
            YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getRoles());
            ConfigurationSection rolesSection = modifyfile.getConfigurationSection("roles");
            if (rolesSection != null) {
                return new ArrayList<>(rolesSection.getKeys(false));
            }
        }
        return List.of();
    }
}