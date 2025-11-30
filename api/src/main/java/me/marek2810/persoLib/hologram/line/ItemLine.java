package me.marek2810.persoLib.hologram.line;

import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

public interface ItemLine extends HologramLine<ItemStack> {

//    Content methods should be used
//    ItemStack getItem();
//    void setItem(ItemStack item);

    ItemDisplay.ItemDisplayTransform getTransform();

    void setTransform(ItemDisplay.ItemDisplayTransform transform);

}
