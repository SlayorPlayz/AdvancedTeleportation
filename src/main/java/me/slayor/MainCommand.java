package me.slayor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (command.getName().equalsIgnoreCase("advancedteleportation")){
            if (strings.length == 0){
                commandSender.sendMessage("Running " + ChatColor.RED + "AdvancedTeleportation " + AdvancedTeleportation.ver);
                commandSender.sendMessage("/at permissions");
                commandSender.sendMessage("/at commands");
                commandSender.sendMessage("/at helpdiscord");
            }else if (strings.length == 1){
                if (strings[0].equalsIgnoreCase("permissions")){
                    if (commandSender.hasPermission("at.main.permissions")){
                        commandSender.sendMessage(ChatColor.RED + "AdvancedTeleportation Permissions");
                        commandSender.sendMessage("at.main.permissions - view this page");
                        commandSender.sendMessage("at.main.commands - view the commands page");
                        commandSender.sendMessage("at.main.helpdiscord - see the help discord");
                        commandSender.sendMessage("at.homes.multiple - have multiple homes");
                        commandSender.sendMessage("at.homes.single - have 1 home");
                        commandSender.sendMessage("at.setspawn - set the spawn");
                        commandSender.sendMessage("at.spawn - go to spawn");
                    }else {
                        commandSender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                    }
                }else if (strings[0].equalsIgnoreCase("helpdiscord")){
                    if (commandSender.hasPermission("at.main.helpdiscord")){
                        commandSender.sendMessage(ChatColor.BLUE + "Help Discord: https://discord.gg/ge55SFRXGn");
                    }else {
                        commandSender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                    }
                }else if (strings[0].equalsIgnoreCase("commands")){
                    if (commandSender.hasPermission("at.main.commands")){
                        commandSender.sendMessage(ChatColor.RED + "AdvancedTeleportation Commands");
                        commandSender.sendMessage("/at permissions");
                        commandSender.sendMessage("/at commands");
                        commandSender.sendMessage("/at helpdiscord");
                        commandSender.sendMessage("/home");
                        commandSender.sendMessage("/sethome");
                        commandSender.sendMessage("/listhomes");
                        commandSender.sendMessage("/spawn");
                        commandSender.sendMessage("/setspawn");
                    }else {
                        commandSender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                    }
                }
            }else {
                commandSender.sendMessage(ChatColor.DARK_RED + "Too many arguments");
            }
        }

        return true;
    }
}
