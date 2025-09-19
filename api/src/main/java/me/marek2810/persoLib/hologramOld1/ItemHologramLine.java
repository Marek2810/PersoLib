package me.marek2810.persoLib.hologramOld1;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public interface ItemHologramLine extends HologramLine {

    Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Item.ITEM_STACK, new ItemStack(Material.GRASS_BLOCK)),
            Map.entry(HologramSetting.Item.DISPLAY_TRANSFORM, ItemDisplay.ItemDisplayTransform.NONE)
    );

    void setItem(ItemStack item);

    ItemStack getItem();

    void setTransform(ItemDisplay.ItemDisplayTransform transform);

    ItemDisplay.ItemDisplayTransform getTransform();

}
