package me.marek2810.persoLib.hologram;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class HologramManager {

    protected final Map<String, Hologram> hologramMap;

    public HologramManager() {
        this.hologramMap = new HashMap<>();
    }

    public abstract Hologram createTextHologram(String id, Location location);

    public abstract Hologram createTextHologram(String id, Location location, String text);

    public abstract Hologram createItemHologram(String id, Location location);

    public abstract Hologram createItemHologram(String id, Location location, ItemStack item);

    public abstract Hologram createBlockHologram(String id, Location location);

    public abstract Hologram createBlockHologram(String id, Location location, Material block);

    public Hologram get(String id) {
        return hologramMap.get(id);
    }

    public Optional<Hologram> get(int entityId) {
        return hologramMap.values().stream()
                .filter(holo -> holo.getEntityId() == entityId)
                .findFirst();
    }

    public Optional<Hologram> getInteracted(int entityId) {
        return hologramMap.values().stream()
                .filter(hologram ->
                        hologram.getHologramLine().getInteraction() != null
                        && hologram.getHologramLine().getInteraction().getEntityId() == entityId
                )
                .findFirst();
    }

    public void remove(String id) {
        this.hologramMap.remove(id);
    }

}
