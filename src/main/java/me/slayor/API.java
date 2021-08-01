package me.slayor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class API {

    public static YamlConfiguration getData(){
        File file = new File(AdvancedTeleportation.getInstance().getDataFolder(), "data.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        return configuration;
    }

    public static void saveData(){
        File file = new File(AdvancedTeleportation.getInstance().getDataFolder(), "data.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        try {
            configuration.save(file);
        }catch (IOException exception){
            Bukkit.getLogger().severe(ChatColor.DARK_RED + "Failed to sava data.yml! Critical error!");
        }
    }

    public static Location getSpawn() {
        YamlConfiguration config = getData();
        String world = config.getString("spawn.world");
        double x = config.getDouble("spawn.x");
        double y = config.getDouble("spawn.y");
        double z = config.getDouble("spawn.z");
        float pitch = (float) config.getDouble("spawn.pitch");
        float yaw = (float) config.getDouble("spawn.yaw");
        return (new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
    }

    public static void tpToSpawn(Player player){
        Location loc = getSpawn();
        player.teleport(loc);
    }
    public static void setSpawn(Player player){
        YamlConfiguration config = getData();
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
        config.set("spawn.world", player.getLocation().getWorld().getName());
        config.set("spawn.x", Double.valueOf(x));
        config.set("spawn.y", Integer.valueOf(player.getLocation().getBlockY()));
        config.set("spawn.z", Double.valueOf(z));
        config.set("spawn.pitch", Integer.valueOf(0));
        config.set("spawn.yaw", Double.valueOf(yaw));
        saveData();
    }

    public static void oneSetHome(Player player){
        YamlConfiguration config = getData();
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
        config.set("homes." + player.getName() + "." + "home.world", player.getLocation().getWorld().getName());
        config.set("homes." + player.getName() + "." + "home.x", Double.valueOf(x));
        config.set("homes." + player.getName() + "." + "home.y", Integer.valueOf(player.getLocation().getBlockY()));
        config.set("homes." + player.getName() + "." + "home.z", Double.valueOf(z));
        config.set("homes." + player.getName() + "." + "home.pitch", Integer.valueOf(0));
        config.set("homes." + player.getName() + "." + "home.yaw", Double.valueOf(yaw));
        List<String> list =  config.getStringList("homelists." + player.getName());
        if (!list.contains("home")){
            list.add("home");
            config.set("homelists." + player.getName(), list);
        }
        saveData();
    }

    public static void multipleSetHome(Player player, String home){
        YamlConfiguration config = getData();
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
        config.set("homes." + player.getName() + "." + home + ".world", player.getLocation().getWorld().getName());
        config.set("homes." + player.getName() + "." + home + ".x", Double.valueOf(x));
        config.set("homes." + player.getName() + "." + home + ".y", Integer.valueOf(player.getLocation().getBlockY()));
        config.set("homes." + player.getName() + "." + home + ".z", Double.valueOf(z));
        config.set("homes." + player.getName() + "." + home + ".pitch", Integer.valueOf(0));
        config.set("homes." + player.getName() + "." + home + ".yaw", Double.valueOf(yaw));
        List<String> list =  config.getStringList("homelists." + player.getName());
        if (!list.contains(home)){
            list.add(home);
            config.set("homelists." + player.getName(), list);
        }
        saveData();
    }

    public static void tpMultipleHome(Player player, String home){
        YamlConfiguration config = getData();
        String world = config.getString("homes." + player.getName() + "." + home + ".world");
        double x = config.getDouble("homes." + player.getName() + "." + home + ".x");
        double y = config.getDouble("homes." + player.getName() + "." + home + ".y");
        double z = config.getDouble("homes." + player.getName() + "." + home + ".z");
        float pitch = (float) config.getDouble("homes." + player.getName() + "." + home + ".pitch");
        float yaw = (float) config.getDouble("homes." + player.getName() + "." + home + ".yaw");
        Location loc = (new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
        player.teleport(loc);
    }

    public static void tpSingleHome(Player player){
        YamlConfiguration config = getData();
        String world = config.getString("homes." + player.getName() + "." + "home" + ".world");
        double x = config.getDouble("homes." + player.getName() + "." + "home" + ".x");
        double y = config.getDouble("homes." + player.getName() + "." + "home" + ".y");
        double z = config.getDouble("homes." + player.getName() + "." + "home" + ".z");
        float pitch = (float) config.getDouble("homes." + player.getName() + "." + "home" + ".pitch");
        float yaw = (float) config.getDouble("homes." + player.getName() + "." + "home" + ".yaw");
        Location loc = (new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch));
        player.teleport(loc);
    }

    public static void sendPlayerHomeList(Player player){
        YamlConfiguration config = getData();
        List<String> list =  config.getStringList("homelists." + player.getName());
        for (String line : list){
            player.sendMessage(line);
        }
    }

}
