package me.slayor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        getCommand("pwarps").setExecutor(new playerwarps(this));
        getCommand("sethome").setExecutor(new playerhomes(this));
        getCommand("home").setExecutor(new playerhomes(this));
        getCommand("setspawn").setExecutor(new serverspawn(this));
        RunStartupMessage();
        getServer().getPluginManager().registerEvents(this, (Plugin)this);
        this.saveDefaultConfig();
    }

    public void SetupConfig(){

    }

    public void RunLicense(){
        if (!getConfig().getString("license").equals("Sy8jKa7dQwXjL")){
            Bukkit.getPluginManager().disablePlugins();
            Bukkit.getPluginManager().getPlugin("s");
        }else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Advanced Teleportation License Key is Verified!");
        }
    }

    public void RunStartupMessage(){
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "---------------------------------");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "| Advanced Teleportation v1.0.3 |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "| Made and Maintained by Slayor |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "---------------------------------");
    }

    @Override
    public void onDisable() {
        RunStartupMessage();
    }

    public Location TeleportToHome(Player player) {
        File file = new File(getDataFolder(), "homes/" + player.getDisplayName() + "/" + "home.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String world = config.getString("world");
        double x = config.getDouble("x");
        double y = config.getDouble("y");
        double z = config.getDouble("z");
        float pitch = (float) config.getDouble("pitch");
        float yaw = (float) config.getDouble("yaw");
        player.teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
        return null;
    }

    public Location TeleportToSpawn(Player player) {
        File file = new File(getDataFolder(), "spawndata/" + "spawn.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String world = config.getString("world");
        double x = config.getDouble("x");
        double y = config.getDouble("y");
        double z = config.getDouble("z");
        float pitch = (float) config.getDouble("pitch");
        float yaw = (float) config.getDouble("yaw");
        player.teleport(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("spawn")) {
            if (sender.hasPermission("at.spawn")) {
                Player player = (Player) sender;

                player.sendMessage(ChatColor.GOLD + "Teleporting to spawn in " + ChatColor.RED + getConfig().getString("Spawn_Delay") + ChatColor.GOLD + " seconds.");

                BukkitScheduler scheduler = getServer().getScheduler();
                scheduler.runTaskLater(this, new Runnable() {
                    @Override
                    public void run() {
                        player.sendMessage(ChatColor.GOLD + "Teleporting...");
                        TeleportToSpawn(player);
                    }
                }, getConfig().getInt("Spawn_Delay") * 20);

                //Delay logic (times 20 is 50 x 20 = 1000 MS = 1 second)

            }else {
                sender.sendMessage(ChatColor.DARK_RED + "You do not have permission!");
            }
        }


        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (getConfig().getString("Spawn_On_Join").equals("true")) {
            Player p = e.getPlayer();
            TeleportToSpawn(p);
        }
        if (getConfig().getString("Home_On_Join").equals("true")) {
            Player p = e.getPlayer();
            TeleportToHome(p);
        }
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        if (getConfig().getString("Spawn_On_Death").equals("true")) {
            Player p = e.getPlayer();
            File file = new File(getDataFolder(), "spawndata/" + "spawn.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            String world = config.getString("world");
            double x = config.getDouble("x");
            double y = config.getDouble("y");
            double z = config.getDouble("z");
            float pitch = (float) config.getDouble("pitch");
            float yaw = (float) config.getDouble("yaw");
            e.setRespawnLocation(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
        }
        if (getConfig().getString("Home_On_Death").equals("true")) {
            Player p = e.getPlayer();
            File file = new File(getDataFolder(), "homes/" + p.getDisplayName() + "/" + "home.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            String world = config.getString("world");
            double x = config.getDouble("x");
            double y = config.getDouble("y");
            double z = config.getDouble("z");
            float pitch = (float) config.getDouble("pitch");
            float yaw = (float) config.getDouble("yaw");
            e.setRespawnLocation(new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
        }
    }




}
