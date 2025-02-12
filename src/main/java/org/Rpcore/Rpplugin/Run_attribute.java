package org.Rpcore.Rpplugin;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Run_attribute implements Listener {
    private final Main main;

    public Run_attribute(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        double distance = event.getFrom().distance(Objects.requireNonNull(event.getTo()));
        if (distance > 0) {
            File charactersFile = main.getCharactersFile();
            YamlConfiguration modifyfile = YamlConfiguration.loadConfiguration(charactersFile);
            String runKey = player.getUniqueId() + ".Run";
            String levelKey = player.getUniqueId() + ".Level";
            int runValue = modifyfile.getString(runKey) == null ? 0 : Integer.parseInt(modifyfile.getString(runKey));
            int levelValue = modifyfile.getString(levelKey) == null ? 0 : Integer.parseInt(modifyfile.getString(levelKey));

            runValue += 1; // Increment by 1 for each step
            modifyfile.set(runKey, String.valueOf(runValue));

            int newLevel = runValue / 1000; // Example: 1 level per 1000 steps
            if (newLevel > levelValue) {
                levelValue = newLevel;
                modifyfile.set(levelKey, String.valueOf(levelValue));
                player.sendMessage("Congratulations! You've reached level " + levelValue);
                if (levelValue == 50) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1));
                } else if (levelValue == 100) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 2));
                }
            }

            try {
                modifyfile.save(charactersFile);
            } catch (IOException e) {
                main.getLogger().warning("Could not save characters.yml");
            }
        }
    }
}