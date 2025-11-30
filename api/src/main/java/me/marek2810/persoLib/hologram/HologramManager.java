package me.marek2810.persoLib.hologram;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class HologramManager {

    protected final Map<String, Hologram> hologramMap;
    private final HologramFactory hologramFactory;

    public HologramManager(HologramFactory hologramFactory) {
        this.hologramMap = new HashMap<>();
        this.hologramFactory = hologramFactory;
    }

    public Hologram createHologram(String name, Location location) {
        if(hologramMap.containsKey(name)) {
            throw new IllegalArgumentException("Hologram with this name already exist.");
        }
        Hologram hologram = hologramFactory.createHologram(name, location);
        hologramMap.put(name, hologram);
        return hologram;
    }

    public Hologram get(String name) {
        return hologramMap.get(name);
    }

    public void remove(String name) {
        Hologram hologram = this.hologramMap.remove(name);
        if (hologram == null)
            return;
        Bukkit.getOnlinePlayers().forEach(hologram::hideFrom);
    }

    public void initPlayer(Player player) {
        //TODO optimization
        hologramMap.values().forEach(hologram -> hologram.showTo(player));
    }

//    public Optional<Hologram> get(int entityId) {
//        return hologramMap.values().stream()
//                .filter(holo -> holo.getEntityId() == entityId)
//                .findFirst();
//    }
//
//    public Optional<Hologram> getInteracted(int entityId) {
//        return hologramMap.values().stream()
//                .filter(hologram ->
//                        hologram.getHologramLine().getInteraction() != null
//                        && hologram.getHologramLine().getInteraction().getEntityId() == entityId
//                )
//                .findFirst();
//    }

}
