package me.marek2810.persoLib.hologram;

import me.marek2810.persoLib.hologram.line.HologramLine;
import org.bukkit.Location;

public abstract class AbstractHologram implements Hologram {

    protected final String id;

    protected Location location;

    protected final HologramLine hologramLine;

    public AbstractHologram(String id, Location location, HologramLine hologramLine) {
        this.id = id;
        this.location = location;
        this.hologramLine = hologramLine;
        create();
    }

    protected void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public HologramLine getHologramLine() {
        return hologramLine;
    }

}
