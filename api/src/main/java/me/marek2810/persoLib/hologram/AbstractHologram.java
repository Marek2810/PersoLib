package me.marek2810.persoLib.hologram;

import me.marek2810.persoLib.hologram.line.HologramLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class AbstractHologram implements Hologram {

    protected final String id;

    protected Location location;

    //TODO list of lines
    protected HologramLine hologramLine;

    public AbstractHologram(String id, Location location) {
        this.id = id;
        this.location = location;
    }

    @Override
    public void teleport(Location location) {
        //TODO teleport all lines
        setLocation(location);
        this.hologramLine.teleport(location);
    }

    @Override
    public void showTo(Player player) {
        //TODO show all lines
        this.hologramLine.showTo(player);
    }

    @Override
    public void hideFrom(Player player) {
        //TODO hide all lines
        this.hologramLine.hideFrom(player);
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
