package org.Rpcore.Rpplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class setdesc implements CommandExecutor {

    private final Main main;

    public setdesc(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender Sender, Command command, String s, String[] args) {
        YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getCharactersFile());

        if (args.length == 0) {
            Sender.sendMessage("You must provide a description.");
        } else {
            Player player = (Player) Sender;
            String desc = String.join(" ", args);
            modifyfile.set(player.getUniqueId() + ".desc", desc);
            try {
                modifyfile.save(main.getCharactersFile());
            } catch (Exception e) {
                main.getLogger().warning("Could not save player desc to characters.yml file");
            }
        }




        return false;
    }
}
