package de.skyslycer.basichome.commands;

import de.skyslycer.basichome.BasicHome;
import de.skyslycer.basichome.serialization.HomeData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    private final BasicHome plugin;

    public SetHomeCommand(BasicHome plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (plugin.getPlayerData() == null) {
            sender.sendMessage(ChatColor.RED + "The plugin couldn't load, please try again later.");
            return false;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player!");
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Please select a name for the new home!");
            return false;
        }

        var homeName = args[0];

        if (plugin.getPlayerData().get(player.getUniqueId()) == null) {
            plugin.getPlayerData().put(player.getUniqueId(), new HomeData());
        }

        var homeData = plugin.getPlayerData().get(player.getUniqueId());
        if (homeData.get(homeName) != null) {
            sender.sendMessage(ChatColor.RED + "This home already exists! " + ChatColor.GRAY + "Please delete it before creating a new one.");
            return false;
        }

        homeData.put(homeName, player.getLocation());
        player.sendMessage(ChatColor.GREEN + "You successfully create a home named: " + ChatColor.GOLD + homeName);
        return true;
    }

}
