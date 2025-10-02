package me.marek2810.persoLib.v1_21_R3.hologram;

import me.marek2810.persoLib.hologram.Hologram;
import me.marek2810.persoLib.hologram.HologramManager;
import me.marek2810.persoLib.hologram.line.BlockHologramLine;
import me.marek2810.persoLib.hologram.line.ItemHologramLine;
import me.marek2810.persoLib.hologram.line.TextHologramLine;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class HologramManager_v1_21_R3 extends HologramManager {

    public Hologram createTextHologram(String id, Location location) {
        Hologram_v1_21_R3 hologram = new Hologram_v1_21_R3(id, location, new TextHologramLine());
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    public Hologram createTextHologram(String id, Location location, String text) {
        Hologram_v1_21_R3 hologram = new Hologram_v1_21_R3(id, location, new TextHologramLine(text));
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    public Hologram createItemHologram(String id, Location location) {
        Hologram_v1_21_R3 hologram = new Hologram_v1_21_R3(id, location, new ItemHologramLine());
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    public Hologram createItemHologram(String id, Location location, ItemStack item) {
        Hologram_v1_21_R3 hologram = new Hologram_v1_21_R3(id, location, new ItemHologramLine(item));
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    public Hologram createBlockHologram(String id, Location location) {
        Hologram_v1_21_R3 hologram = new Hologram_v1_21_R3(id, location, new BlockHologramLine());
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    public Hologram createBlockHologram(String id, Location location, Material block) {
        Hologram_v1_21_R3 hologram = new Hologram_v1_21_R3(id, location, BlockHologramLine.of(block));
        this.hologramMap.put(id, hologram);
        return hologram;
    }

}
