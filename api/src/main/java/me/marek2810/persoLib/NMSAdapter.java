package me.marek2810.persoLib;

import me.marek2810.persoLib.event.listener.PacketListener;
import me.marek2810.persoLib.hologram.HologramManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class NMSAdapter {

    private final JavaPlugin plugin;
    private final HologramManager hologramManager;
    private final PacketListener packetListener;

    public NMSAdapter(JavaPlugin plugin, HologramManager hologramManager) {
        this.plugin = plugin;
        this.hologramManager = hologramManager;
        this.packetListener = createPacketListener();
    }

    protected abstract PacketListener createPacketListener();

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public HologramManager getHologramManager() {
        return hologramManager;
    }

    public PacketListener getPacketListener() {
        return packetListener;
    }

}
