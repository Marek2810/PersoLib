package me.marek2810.persoLib.hologram;

import java.util.HashMap;
import java.util.Map;

public abstract class HologramManager {

    protected final Map<String, Hologram> hologramMap;

    public HologramManager() {
        this.hologramMap = new HashMap<>();
    }

    public Hologram get(String id) {
        return hologramMap.get(id);
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

    public void remove(String id) {
        this.hologramMap.remove(id);
    }

}
