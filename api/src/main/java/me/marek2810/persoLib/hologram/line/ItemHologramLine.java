package me.marek2810.persoLib.hologram.line;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.HologramType;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ItemHologramLine extends HologramLine {

    private static final Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Item.ITEM_STACK, new ItemStack(Material.GRASS_BLOCK)),
            Map.entry(HologramSetting.Item.DISPLAY_TRANSFORM, ItemDisplay.ItemDisplayTransform.NONE)
    );

    public ItemHologramLine() {
        super(HologramType.ITEM, DEFAULT_SETTINGS);
    }

    public ItemHologramLine(ItemStack item) {
        super(HologramType.ITEM, DEFAULT_SETTINGS);
        setItem(item);
    }

    public ItemHologramLine(ItemStack item, ItemDisplay.ItemDisplayTransform transform) {
        super(HologramType.ITEM, DEFAULT_SETTINGS);
        setItem(item);
        setTransform(transform);
    }

    public void setItem(ItemStack item) {
        this.setSetting(HologramSetting.Item.ITEM_STACK, item);
    }

    public ItemStack getItem() {
        return this.getSetting(HologramSetting.Item.ITEM_STACK);
    }

    public void setTransform(ItemDisplay.ItemDisplayTransform transform) {
        this.setSetting(HologramSetting.Item.DISPLAY_TRANSFORM, transform);
    }

    public ItemDisplay.ItemDisplayTransform getTransform() {
        return this.getSetting(HologramSetting.Item.DISPLAY_TRANSFORM);
    }

}

