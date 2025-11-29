package me.marek2810.persoLib.hologram;

import org.bukkit.Location;

public interface HologramFactory {

    Hologram createHologram(String name, Location location);

}