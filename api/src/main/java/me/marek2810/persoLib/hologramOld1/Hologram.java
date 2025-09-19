package me.marek2810.persoLib.hologramOld1;


import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Hologram {

    //ID for hologram manager
    String getId();

    int size();

    void addLine(HologramLine line);

    void setLine(int index, HologramLine line);

    HologramLine getLine(int index);

    Location getLocation();

    void teleport(Location target);

    void showTo(Player player);

    void hideFrom(Player player);

    void removeLine(int index);

}
