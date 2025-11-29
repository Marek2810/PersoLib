package me.marek2810.persoLib.hologram;

import me.marek2810.persoLib.hologram.line.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface Hologram {

    //ID for hologram manager
    String getName();

    TextLine setTextLine(String text);

    ItemLine setItemLine(org.bukkit.inventory.ItemStack item);

    BlockLine setBlockLine(org.bukkit.block.data.BlockData blockData);

    //TODO more lines
    HologramLine getHologramLine();

    Location getLocation();

    void teleport(Location location);

    void showTo(Player player);

    void hideFrom(Player player);

}
