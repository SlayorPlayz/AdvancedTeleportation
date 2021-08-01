package me.slayor;

import com.avaje.ebean.config.dbplatform.PostgresPlatform;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class Spawn implements CommandExecutor {

    AdvancedTeleportation plugin;

    public Spawn(AdvancedTeleportation plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spawn")){
            if (sender.hasPermission("at.spawn")){
                Player player = (Player) sender;
                long delay = AdvancedTeleportation.getInstance().getConfig().getInt("delays.spawn");
                if (AdvancedTeleportation.getInstance().getConfig().getDouble("delays.spawn") > 0){
                    BukkitScheduler scheduler = AdvancedTeleportation.getInstance().getServer().getScheduler();
                    scheduler.scheduleSyncDelayedTask(this.plugin, new Runnable() {
                        @Override
                        public void run() {
                            API.tpToSpawn(player);
                            player.sendMessage(ChatColor.GREEN + "Teleporting...");
                        }
                    }, delay);
                }else {
                    player.teleport(API.getSpawn());
                }
            }else {
                sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
            }
        }

        if (command.getName().equalsIgnoreCase("setspawn")){
            if (sender.hasPermission("at.setspawn")){
                Player player = (Player) sender;
                try {
                    API.setSpawn(player);
                    player.sendMessage(ChatColor.GREEN + "Set the spawn!");
                }catch (NullPointerException npe){
                    player.sendMessage(ChatColor.DARK_RED + "Error trying to set the spawn!");
                    Bukkit.getLogger().severe(ChatColor.DARK_RED + "Error while settings the spawn!");
                }
            }else {
                sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
            }
        }
        return true;
    }
}
