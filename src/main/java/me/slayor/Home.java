package me.slayor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("home")){
            Player player = (Player) sender;
            if (args.length == 0){
                if (sender.hasPermission("at.homes.multiple")){
                    sender.sendMessage(ChatColor.DARK_RED + "Need to specify a home!");
                }else if (sender.hasPermission("at.homes.single")){
                    try {
                        API.tpSingleHome(player);
                    }catch (NullPointerException npe){
                        sender.sendMessage(ChatColor.DARK_RED + "Error Trying to teleport to home!");
                        sender.sendMessage(ChatColor.GOLD + "Make sure to set your home! (/sethome)");
                    }
                }else {
                    sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                }
            }else if (args.length == 1){
                if (sender.hasPermission("at.homes.multiple")){
                    try {
                        String home = args[0];
                        API.tpMultipleHome(player, home);
                    }catch (NullPointerException npe){
                        sender.sendMessage(ChatColor.DARK_RED + "Error Trying to teleport to home!");
                        sender.sendMessage(ChatColor.GOLD + "Make sure to set your home! (/sethome)");
                    }
                }else if (sender.hasPermission("at.homes.single")){
                    sender.sendMessage(ChatColor.DARK_RED + "Too many arguments!");
                }else {
                    sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                }
            }else {
                sender.sendMessage(ChatColor.DARK_RED + "Too many arguments!");
            }
        }

        if (command.getName().equalsIgnoreCase("sethome")){
            Player player = (Player) sender;
            if (args.length == 0){
                if (sender.hasPermission("at.homes.multiple")){
                    sender.sendMessage(ChatColor.DARK_RED + "Need to specify a home name!");
                }else if (sender.hasPermission("at.homes.single")){
                    try {
                        API.oneSetHome(player);
                        player.sendMessage(ChatColor.GREEN + "Sethome!");
                    }catch (NullPointerException npe){
                        sender.sendMessage(ChatColor.DARK_RED + "Error Trying to teleport set home!");
                    }
                }else {
                    sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                }
            }else if (args.length == 1){
                if (sender.hasPermission("at.homes.multiple")){
                    try {
                        String home = args[0];
                        API.multipleSetHome(player, home);
                        player.sendMessage(ChatColor.GREEN + "Sethome!");
                    }catch (NullPointerException npe){
                        sender.sendMessage(ChatColor.DARK_RED + "Error Trying to teleport to set home!");
                    }
                }else if (sender.hasPermission("at.homes.single")){
                    sender.sendMessage(ChatColor.DARK_RED + "Too many arguments!");
                }else {
                    sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                }
            }else {
                sender.sendMessage(ChatColor.DARK_RED + "Too many arguments!");
            }
        }

        if (command.getName().equalsIgnoreCase("listhomes")){
            Player player = (Player) sender;
            API.sendPlayerHomeList(player);
            player.sendMessage(ChatColor.GOLD + "NOTE: If you only are allowed to make one home then the list will only contain: 'home'");
        }

        return true;
    }
}
