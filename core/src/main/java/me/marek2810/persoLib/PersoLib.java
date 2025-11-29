package me.marek2810.persoLib;

import me.marek2810.persoLib.hologram.HologramFactory;
import me.marek2810.persoLib.hologram.HologramManager;
import me.marek2810.persoLib.nms_v1_21_R3.hologram.HologramFactory_V1_21_R3;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PersoLib {

    private static PersoLib INSTANCE;

    private final HologramManager hologramManager;
//    private final PacketListener packetListener;

//    private PersoLib(HologramManager hologramManager, PacketListener packetListener) {
//        INSTANCE = this;
//        this.hologramManager = hologramManager;
//        this.packetListener = packetListener;
//    }

    private PersoLib(HologramManager hologramManager) {
        INSTANCE = this;
        this.hologramManager = hologramManager;
    }

    public static PersoLib init(JavaPlugin plugin) {
        if (INSTANCE != null)
            return INSTANCE;

        String mcVersion = getCurrentMinecraftVersion();

        HologramFactory hologramFactory;

        //NMS 1_21_R3
        if (mcVersion.equalsIgnoreCase("1.21.4")) {
            //TODO
            hologramFactory = new HologramFactory_V1_21_R3();
        }
        else {
            throw new IllegalStateException("This server version is not supported!");
        }

        HologramManager hologramManager = new HologramManager(hologramFactory);

        //TODO
//        PacketListener packetListener = hologramFactory.getPacketListener();

        INSTANCE = new PersoLib(hologramManager);

//        INSTANCE = new PersoLib(hologramManager, packetListener);
//
//        Bukkit.getPluginManager().registerEvents(packetListener, plugin);
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
