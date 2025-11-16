package me.marek2810.persoLib.hologram;

import me.marek2810.persoLib.entity.PersoEntity;
import me.marek2810.persoLib.hologram.line.HologramLine;

public interface Hologram extends PersoEntity {

    //ID for hologram manager
    String getId();

    int getEntityId();

    HologramLine getHologramLine();

}
