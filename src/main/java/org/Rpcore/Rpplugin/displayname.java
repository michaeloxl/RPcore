package org.Rpcore.Rpplugin;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class displayname implements Listener {
    private final Main main;

    public displayname(Main main) {
        this.main = main;
    }
    @EventHandler
    public void Onjoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getCharactersFile());
        YamlConfiguration modify2file = YamlConfiguration.loadConfiguration(main.getRoles());
        String rpname = modifyfile.getString(player.getUniqueId() + ".rpname");
        String role = modifyfile.getString(player.getUniqueId() + ".role");
        String actualrole = modify2file.getString("Roles." + role);
        if (rpname != null) {
            player.setDisplayName(rpname);
            player.setPlayerListName(actualrole + " " + rpname);
        } else {
            player.setDisplayName(player.getName());
        }
    }
}
