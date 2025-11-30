package me.marek2810.persoLib.hologram;

import me.marek2810.persoLib.hologram.line.BlockLine;
import me.marek2810.persoLib.hologram.line.HologramLine;
import me.marek2810.persoLib.hologram.line.ItemLine;
import me.marek2810.persoLib.hologram.line.TextLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Hologram {

    //ID for hologram manager
    String getName();

    TextLine setTextLine();

    ItemLine setItemLine();

    BlockLine setBlockLine();

    //TODO more lines
    HologramLine getHologramLine();

    Location getLocation();

    void teleport(Location location);

    void showTo(Player player);

    void hideFrom(Player player);

}
