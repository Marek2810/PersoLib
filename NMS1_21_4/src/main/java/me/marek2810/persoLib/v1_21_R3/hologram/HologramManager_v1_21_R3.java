package me.marek2810.persoLib.v1_21_R3.hologram;

import me.marek2810.persoLib.hologram.line.BlockHologramLine;
import me.marek2810.persoLib.hologram.HologramManager;
import me.marek2810.persoLib.hologram.line.ItemHologramLine;
import me.marek2810.persoLib.hologram.line.TextHologramLine;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class HologramManager_v1_21_R3 implements HologramManager {

    private final Map<String, Hologram> hologramMap;

    public HologramManager_v1_21_R3() {
        this.hologramMap = new HashMap<>();
    }

    public Hologram createTextHologram(String id, Location location) {
        Hologram hologram = new Hologram(id, location, new TextHologramLine());
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    public Hologram createTextHologram(String id, Location location, String text) {
        Hologram hologram = new Hologram(id, location, new TextHologramLine(text));
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    public Hologram createItemHologram(String id, Location location) {
        Hologram hologram = new Hologram(id, location, new ItemHologramLine());
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    public Hologram createItemHologram(String id, Location location, ItemStack item) {
        Hologram hologram = new Hologram(id, location, new ItemHologramLine(item));
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    public Hologram createBlockHologram(String id, Location location) {
        Hologram hologram = new Hologram(id, location, new BlockHologramLine());
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    public Hologram createBlockHologram(String id, Location location, Material block) {
        Hologram hologram = new Hologram(id, location, BlockHologramLine.of(block));
        this.hologramMap.put(id, hologram);
        return hologram;
    }

    @Override
    public Hologram get(String id) {
        return hologramMap.get(id);
    }

    @Override
    public void remove(String id) {
        this.hologramMap.remove(id);
    }

}
