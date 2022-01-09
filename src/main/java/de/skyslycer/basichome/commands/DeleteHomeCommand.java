package de.skyslycer.basichome.commands;

import de.skyslycer.basichome.BasicHome;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteHomeCommand implements CommandExecutor {

    private final BasicHome plugin;

    public DeleteHomeCommand(BasicHome plugin) {
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
            sender.sendMessage(ChatColor.RED + "Please select a name for the home to delete!");
            return false;
        }

        var homeName = args[0];
        var homeData = plugin.getPlayerData().get(player.getUniqueId());

        if (homeData == null || homeData.get(homeName) == null) {
            sender.sendMessage(ChatColor.RED + "This home doesn't exist!");
            return false;
        }

        homeData.remove(homeName);
        player.sendMessage(ChatColor.GREEN + "You successfully deleted the home named: " + ChatColor.GOLD + homeName);

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, plugin::save, 0L);
        return true;
    }

}
