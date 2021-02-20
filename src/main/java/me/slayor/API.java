package me.slayor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class API {

    Main plugin;

    public API(Main plugin) {
        this.plugin = plugin;
    }

    public Location TeleportToHome(Player player) {
        File file = new File(this.plugin.getDataFolder(), "homes/" + player.getDisplayName() + "/" + "home.yml");
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
        File file = new File(this.plugin.getDataFolder(), "spawndata/" + "spawn.yml");
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

}
