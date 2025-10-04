package me.marek2810.persoLib;

import me.marek2810.persoLib.event.listener.PacketListener;
import me.marek2810.persoLib.hologram.HologramManager;
import me.marek2810.persoLib.nms_v1_21_R3.PacketListener_v1_21_R3;
import me.marek2810.persoLib.nms_v1_21_R3.hologram.HologramManager_v1_21_R3;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PersoLib {

    private static PersoLib INSTANCE;
    private static JavaPlugin PLUGIN;

    private final HologramManager hologramManager;
    private final PacketListener packetListener;

    private PersoLib(JavaPlugin plugin, HologramManager hologramManager, PacketListener packetListener) {
        INSTANCE = this;
        PLUGIN = plugin;
        this.hologramManager = hologramManager;
        this.packetListener = packetListener;
    }

    public static PersoLib init(JavaPlugin plugin) {
        if (INSTANCE != null)
            return INSTANCE;

        String mcVersion = getCurrentMinecraftVersion();

        HologramManager hologramManager;
        PacketListener packetListener;

        //NMS 1_21_R3
        if (mcVersion.equalsIgnoreCase("1.21.4")) {
            hologramManager = new HologramManager_v1_21_R3();
            packetListener  = new PacketListener_v1_21_R3(plugin, hologramManager);
        }
        else {
            throw new IllegalStateException("This server version is not supported!");
        }
        INSTANCE = new PersoLib(plugin, hologramManager, packetListener);
        Bukkit.getPluginManager().registerEvents(packetListener, plugin);
        return INSTANCE;
    }

    public HologramManager getHologramManager() {
        return hologramManager;
    }

    public PacketListener getPacketListener() {
        return packetListener;
    }

    public static PersoLib getInstance() {
        if (INSTANCE == null)
            throw new IllegalStateException("PersoLib is not initialized! Call PersoLib.init(plugin) in onEnable().");
        return INSTANCE;
    }

    public static JavaPlugin getPlugin() {
        if (PLUGIN == null)
            throw new IllegalStateException("PersoLib plugin instance is null! Did you call init?");
        return PLUGIN;
    }

    private static String getCurrentMinecraftVersion() {
        String bukkitVersion = Bukkit.getServer().getBukkitVersion();
        return bukkitVersion.split("-", 2)[0];
    }

}
