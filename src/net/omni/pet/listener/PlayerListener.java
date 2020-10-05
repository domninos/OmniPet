package net.omni.pet.listener;

import net.omni.pet.OmniPetPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class PlayerListener implements Listener {
    private final OmniPetPlugin plugin;

    public PlayerListener(OmniPetPlugin plugin) {
        this.plugin = plugin;
    }

    // TODO feeding and check if player is sneaking -> disguise.setSneaking

    public void register() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
