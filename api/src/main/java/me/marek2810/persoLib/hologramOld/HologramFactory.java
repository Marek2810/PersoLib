package me.marek2810.persoLib.hologramOld;

import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

public interface HologramFactory {

    String supportedVersion();

    TextHologramOld spawnTextDisplay(String id, Location loc, String text);
    ItemHologramOld spawnItemDisplay(String id, Location loc, ItemStack item);
    BlockHologramOld spawnBlockDisplay(String id, Location loc, BlockData data);

}


