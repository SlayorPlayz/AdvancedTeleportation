package me.slayor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class playerwarps implements CommandExecutor {

    Main plugin;

    public playerwarps(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pwarps")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Did not specify enough!");
                sender.sendMessage(ChatColor.RED + "/pwarps create <Name>");
                sender.sendMessage(ChatColor.RED + "/pwarps setlocation <Name>");
                sender.sendMessage(ChatColor.RED + "/pwarps tp <Player> <Name>");
                sender.sendMessage(ChatColor.RED + "/pwarps delete <Name>");
                sender.sendMessage(ChatColor.RED + "/pwarps admin");
            } else if (args[0].equalsIgnoreCase("create")) {
                if (sender.hasPermission("pwarps.create")) {
                    if (args.length == 1) {
                        sender.sendMessage(ChatColor.RED + "/pwarps create <Name>");
                    } else if (args.length == 2) {
                        Player player = (Player) sender;
                        File file = new File(this.plugin.getDataFolder(), "pwarps/" + player.getDisplayName() + "/" + args[1] + ".yml");
                        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                        if (!file.exists()) {
                            sender.sendMessage(ChatColor.GREEN + "Player Warp created!");
                            sender.sendMessage(ChatColor.GREEN + "Do /pwarps setlocation <Name> to set location of a player warp.");
                            try {
                                config.save(file);
                            } catch (IOException e) {
                                sender.sendMessage(ChatColor.DARK_RED + "Error!");
                                e.printStackTrace();
                            }
                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Player Warp already exists");
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                }
            } else if (args[0].equalsIgnoreCase("setlocation")) {
                if (sender.hasPermission("pwarps.setlocation")) {
                    if (args.length == 1) {
                        sender.sendMessage(ChatColor.RED + "/pwarps setlocation <Name>");
                    } else if (args.length == 2) {
                        Player player = (Player) sender;
                        File file = new File(this.plugin.getDataFolder(), "pwarps/" + player.getDisplayName() + "/" + args[1] + ".yml");
                        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                        if (file.exists()) {
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
                                sender.sendMessage(ChatColor.GREEN + "Player warp location has been set!");
                            } catch (IOException e) {
                                e.printStackTrace();
                                sender.sendMessage(ChatColor.DARK_RED + "Setlocation has failed!");
                            }
                        } else {
                            sender.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RED + "Player Warp not found!");
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                }
            } else if (args[0].equalsIgnoreCase("tp")) {
                if (sender instanceof Player) {
                    if (args.length == 1) {
                        sender.sendMessage(ChatColor.RED + "/pwarps tp <Player> <Name>");
                    }else if (args.length == 2){
                        sender.sendMessage("/pwarps tp <Player <Name>");
                    }else if (args.length == 3) {
                        if (sender.hasPermission("pwarps.tp")) {
                            Player player2 = (Player) sender;
                            player2.getDisplayName();
                            File file = new File(this.plugin.getDataFolder(), "pwarps/" + args[1] + "/" + args[2] + ".yml");
                            if (!file.exists()) {
                                sender.sendMessage(ChatColor.DARK_RED + "Player Warp does not exist!");
                            } else {
                                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                                String world = config.getString("world");
                                double x = config.getDouble("x");
                                double y = config.getDouble("y");
                                double z = config.getDouble("z");
                                float pitch = (float) config.getDouble("pitch");
                                float yaw = (float) config.getDouble("yaw");
                                player2.teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
                                player2.sendMessage(ChatColor.GREEN + "Successfully teleported to Player Warp!");
                            }
                        }else {
                            sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                        }
                    }
                }
                Bukkit.getConsoleSender().sendMessage("You need to be a player to execute this command!");
            } else if (args[0].equalsIgnoreCase("delete")) {
                if (sender.hasPermission("pwarps.delete")) {
                    if (args.length == 1) {
                        sender.sendMessage(ChatColor.RED + "/pwarps delete <Name>");
                    }else if (args.length == 2) {
                        if (sender.hasPermission("pwarps.delete")) {
                            Player player = (Player) sender;
                            File file = new File(this.plugin.getDataFolder(), "pwarps/" + player.getDisplayName() + "/" + args[1] + ".yml");
                            if (file.exists()) {
                                file.delete();
                                sender.sendMessage(ChatColor.GREEN + "Successfully deleted warp!");
                            } else {
                                sender.sendMessage(ChatColor.DARK_RED + "Player warp does not exist!");
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
                }
            }

        }else if (args[0].equalsIgnoreCase("admin")){
            if (sender.hasPermission(this.plugin.getConfig().getString("Admin_Permission"))){
                sender.sendMessage("Player Warps Admin Commands");
                sender.sendMessage("/pwarps forcedelete <Player> <Name>");
                sender.sendMessage("More to be added later!");
            }
        }else if (args[0].equalsIgnoreCase("forcedelete")) {
            if (sender.hasPermission(this.plugin.getConfig().getString("Admin_Permission"))) {
                if (args.length == 1) {
                    sender.sendMessage(ChatColor.RED + "/pwarps forcedelete <Player> <Name>");
                }
                if (args.length == 2) {
                    sender.sendMessage(ChatColor.RED + "/pwarps forcedelete <Player> <Name>");
                } else if (args.length == 3) {
                    File file = new File(this.plugin.getDataFolder(), "pwarps/" + args[1] + "/" + args[2] + ".yml");
                    if (file.exists()) {
                        file.delete();
                        sender.sendMessage(ChatColor.GREEN + "Successfully deleted player warp!");
                    } else {
                        sender.sendMessage(ChatColor.DARK_RED + "Player warp does not exist!");
                    }

                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
            }
        }

        return true;
    }
}
