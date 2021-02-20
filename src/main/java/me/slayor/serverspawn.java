package me.slayor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class serverspawn implements CommandExecutor {

    Main plugin;

    public serverspawn(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("setspawn")) {
            if (sender.hasPermission("at.setspawn" + this.plugin.getConfig().getString("Admin_Permission"))) {
                Player player = (Player) sender;
                File file = new File(this.plugin.getDataFolder(), "spawndata/" + "spawn" + ".yml");
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                if (!file.exists()) {
                    Location loc = player.getLocation();
                    double x = loc.getBlockX();
                    if (x < 0.0D) {
                        x -= 0.5D;
                    } else {
                        x += 0.5D;
                    }
                    double z = loc.getBlockZ();
                    if (z < 0.0D) {
                        z -= 0.5D;
                    } else {
                        z += 0.5D;
                    }
                    double yaw = loc.getYaw();
                    if (yaw < -135.0D || yaw > 135.0D) {
                        yaw = -180.0D;
                    } else if (yaw < -45.0D) {
                        yaw = -90.0D;
                    } else if (yaw < 45.0D) {
                        yaw = 0.0D;
                    } else {
                        yaw = 90.0D;
                    }
                    config.set("world", player.getLocation().getWorld().getName());
                    config.set("x", Double.valueOf(x));
                    config.set("y", Integer.valueOf(player.getLocation().getBlockY()));
                    config.set("z", Double.valueOf(z));
                    config.set("pitch", Integer.valueOf(0));
                    config.set("yaw", Double.valueOf(yaw));
                    try {
                        config.save(file);
                        sender.sendMessage(ChatColor.GREEN + "spawn location has been set!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        sender.sendMessage(ChatColor.DARK_RED + "Setspawn has failed!");
                    }
                } else if (file.exists()){
                    Location loc = player.getLocation();
                    double x = loc.getBlockX();
                    if (x < 0.0D) {
                        x -= 0.5D;
                    } else {
                        x += 0.5D;
                    }
                    double z = loc.getBlockZ();
                    if (z < 0.0D) {
                        z -= 0.5D;
                    } else {
                        z += 0.5D;
                    }
                    double yaw = loc.getYaw();
                    if (yaw < -135.0D || yaw > 135.0D) {
                        yaw = -180.0D;
                    } else if (yaw < -45.0D) {
                        yaw = -90.0D;
                    } else if (yaw < 45.0D) {
                        yaw = 0.0D;
                    } else {
                        yaw = 90.0D;
                    }
                    config.set("world", player.getLocation().getWorld().getName());
                    config.set("x", Double.valueOf(x));
                    config.set("y", Integer.valueOf(player.getLocation().getBlockY()));
                    config.set("z", Double.valueOf(z));
                    config.set("pitch", Integer.valueOf(0));
                    config.set("yaw", Double.valueOf(yaw));
                    try {
                        config.save(file);
                        sender.sendMessage(ChatColor.GREEN + "spawn location has been set!");
                    } catch (IOException e) {
                        e.printStackTrace();
                        sender.sendMessage(ChatColor.DARK_RED + "Setspawn has failed!");
                    }
                }

            }else {
                sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
            }
        }

        return true;
    }
}
