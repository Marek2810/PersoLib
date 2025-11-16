package me.marek2810.persoLib;

import me.marek2810.persoLib.event.listener.PacketListener;
import me.marek2810.persoLib.hologram.HologramManager;
import me.marek2810.persoLib.nms_v1_21_R3.NMSAdapter_v1_21_R3;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PersoLib {

    private static PersoLib INSTANCE;

    private final HologramManager hologramManager;
    private final PacketListener packetListener;

    private PersoLib(HologramManager hologramManager, PacketListener packetListener) {
        INSTANCE = this;
        this.hologramManager = hologramManager;
        this.packetListener = packetListener;
    }

    public static PersoLib init(JavaPlugin plugin) {
        if (INSTANCE != null)
            return INSTANCE;

        String mcVersion = getCurrentMinecraftVersion();

        NMSAdapter nmsAdapter;

        //NMS 1_21_R3
        if (mcVersion.equalsIgnoreCase("1.21.4")) {
            nmsAdapter = new NMSAdapter_v1_21_R3(plugin);
        }
        else {
            throw new IllegalStateException("This server version is not supported!");
        }

        HologramManager hologramManager = nmsAdapter.getHologramManager();
        PacketListener packetListener = nmsAdapter.getPacketListener();

        INSTANCE = new PersoLib(hologramManager, packetListener);

        Bukkit.getPluginManager().registerEvents(packetListener, plugin);
        return INSTANCE;
    }

    public HologramManager getHologramManager() {
        return hologramManager;
    }

    public static PersoLib getInstance() {
        if (INSTANCE == null)
            throw new IllegalStateException("PersoLib is not initialized! Call PersoLib.init(JavaPlugin) in onEnable().");
        return INSTANCE;
    }

    private static String getCurrentMinecraftVersion() {
        String bukkitVersion = Bukkit.getServer().getBukkitVersion();
        return bukkitVersion.split("-", 2)[0];
    }

}
