package me.slayor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AdvancedTeleportation extends JavaPlugin {

    private static AdvancedTeleportation instance;
    public static String ver = "v2.0-rc3";

    @Override
    public void onEnable() {
        this.getCommand("sethome").setExecutor(new Home());
        this.getCommand("listhomes").setExecutor(new Home());
        this.getCommand("advancedteleportation").setExecutor(new MainCommand());
        this.getCommand("home").setExecutor(new Home());
        this.getCommand("setspawn").setExecutor(new Spawn(this));
        this.getCommand("spawn").setExecutor(new Spawn(this));


        saveDefaultFile("config.yml");
        saveDefaultFile("data.yml");

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "---------------------------------");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "|     AdvancedTeleportation2    |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "| Made and Maintained by Slayor |");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "---------------------------------");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static AdvancedTeleportation getInstance(){
        return instance;
    }

    public void saveDefaultFile(String string){
        File file = new File(getDataFolder(), string);
        if (!file.exists()) {
            saveResource(string, false);
        }
    }
}
