package me.marek2810.persoLib.nms_v1_21_R3;

import me.marek2810.persoLib.NMSAdapter;
import me.marek2810.persoLib.event.listener.PacketListener;
import me.marek2810.persoLib.nms_v1_21_R3.hologram.HologramManager_v1_21_R3;
import org.bukkit.plugin.java.JavaPlugin;

public class NMSAdapter_v1_21_R3 extends NMSAdapter {

    public NMSAdapter_v1_21_R3(JavaPlugin plugin) {
        super(plugin, new HologramManager_v1_21_R3());
    }

    protected PacketListener createPacketListener() {
        return new PacketListener_v1_21_R3(getPlugin(), this.getHologramManager());
    }

}
