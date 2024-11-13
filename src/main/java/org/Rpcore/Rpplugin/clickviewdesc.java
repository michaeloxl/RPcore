package org.Rpcore.Rpplugin;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class clickviewdesc implements Listener {
    private final Main main;
    public clickviewdesc(Main main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (event.getRightClicked() instanceof Player && event.getHand() == EquipmentSlot.HAND) {
            Player target = (Player) event.getRightClicked();
            YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(main.getFile());
            String desc = modifyfile.getString(target.getUniqueId() + ".desc");
            if (desc != null) {
                player.sendMessage("Name: " + modifyfile.getString(target.getUniqueId()+".rpname") + "\nAge: " + modifyfile.getString(target.getUniqueId()+ ".age") + "\nDescription: " + desc) ;
            }


    }
    }




}
