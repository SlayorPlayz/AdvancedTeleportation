package me.slayor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    Main plugin;

    public MainCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("advancedteleport")){
            if (args.length == 0){
                if (sender.hasPermission(this.plugin.getConfig().getString("Admin_Permission"))){
                    sender.sendMessage(ChatColor.RED + "Running Advanced Teleport v1.1.0");
                    sender.sendMessage(ChatColor.AQUA + "/at reload");
                    sender.sendMessage(ChatColor.AQUA + "/at discord");
                    sender.sendMessage(ChatColor.RED + "If you have problems join the discord");

                }else {
                    sender.sendMessage(ChatColor.RED + "Running Advanced Teleport v1.1.0");
                    sender.sendMessage(ChatColor.AQUA + "You do not have permisssion for sub-commands");
                }
            }else if (args.length == 1){
                if (args[0].equalsIgnoreCase("reload")){
                    if (sender.hasPermission(this.plugin.getConfig().getString("Admin_Permission"))){
                        this.plugin.reloadConfig();
                        sender.sendMessage(ChatColor.GREEN + "Reloaded the config.yml");
                    }else {
                        sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                    }
                }else if (args[0].equalsIgnoreCase("discord")){
                    if (sender.hasPermission(this.plugin.getConfig().getString("Admin_Permission"))){
                        sender.sendMessage(ChatColor.BLUE + "Discord: https://discord.gg/hdhJK6ZU9S");
                    }else {
                        sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                    }
                }
            }
        }

        return true;
    }
}
