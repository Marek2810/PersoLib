package me.marek2810.persoLib.hologram;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface HologramManager {

    Hologram createTextHologram(String id, Location location);

    Hologram createTextHologram(String id, Location location, String text);

    Hologram createItemHologram(String id, Location location);

    Hologram createItemHologram(String id, Location location, ItemStack item);

    Hologram createBlockHologram(String id, Location location);

    Hologram createBlockHologram(String id, Location location, Material block);

    Hologram get(String id);

    void remove(String id);

}
