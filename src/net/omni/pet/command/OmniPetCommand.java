package net.omni.pet.command;

import net.omni.pet.OmniPetPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

public class OmniPetCommand implements CommandExecutor {
    private final OmniPetPlugin plugin;
    private final String help;

    public OmniPetCommand(OmniPetPlugin plugin) {
        this.plugin = plugin;

        String[] msg = {

        };

        this.help = String.join("\n", msg);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!plugin.getDisguiser().isHooked()) {
            plugin.sendMessage(sender, "&cCould not execute. Please check if LibsDisguises was hooked.");
            return true;
        }

        if (!(sender instanceof Player)) {
            plugin.sendMessage(sender, "&cOnly players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            plugin.sendMessage(player, help);
            return true;
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                plugin.sendMessage(player, "&cPlayer not found.");
                return true;
            }

            plugin.getDisguiser().disguise(target);
            plugin.sendMessage(player, "&a" + target.getName() + " is now your pet!");
        }

        return true;
    }

    public void register() {
        PluginCommand pluginCommand = plugin.getCommand("omnipet");

        if (pluginCommand != null)
            pluginCommand.setExecutor(this);
    }
}
