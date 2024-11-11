package org.OOC.oOC;

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
        YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getFile());
        String rpname = modifyfile.getString(player.getUniqueId() + ".rpname");

        if (rpname != null) {
            player.setDisplayName(rpname);
            player.setPlayerListName(rpname);
        } else {
            player.setDisplayName(player.getName());
        }
    }
}
