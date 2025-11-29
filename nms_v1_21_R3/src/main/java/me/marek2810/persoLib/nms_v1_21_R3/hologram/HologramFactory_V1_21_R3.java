package me.marek2810.persoLib.nms_v1_21_R3.hologram;

import me.marek2810.persoLib.hologram.Hologram;
import me.marek2810.persoLib.hologram.HologramFactory;
import org.bukkit.Location;

public class HologramFactory_V1_21_R3 implements HologramFactory {

    @Override
    public Hologram createHologram(String name, Location location) {
        return new Hologram_v1_21_R3(name, location);
    }
    
}
