package net.omni.pet;

import net.omni.pet.command.OmniPetCommand;
import net.omni.pet.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class OmniPetPlugin extends JavaPlugin {
    private DisguiseHook disguiser;

    @Override
    public void onEnable() {
        this.disguiser = new DisguiseHook(this);

        disguiser.hook();

        new OmniPetCommand(this).register();
        new PlayerListener(this).register();

        sendConsole("&aSuccessfully enabled OmniPet v" + getDescription().getVersion());
    }

    @Override
    public void onDisable() {

        sendConsole("&aSuccessfully disabled OmniPet");
    }

    public void sendConsole(String message) {
        sendMessage(Bukkit.getConsoleSender(), message);
    }

    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(translate("[PREFIX] " + message));
    }

    public String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public DisguiseHook getDisguiser() {
        return disguiser;
    }
}
