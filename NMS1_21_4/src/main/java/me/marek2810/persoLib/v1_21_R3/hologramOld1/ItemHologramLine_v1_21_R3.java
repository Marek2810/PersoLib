package me.marek2810.persoLib.v1_21_R3.hologramOld1;

import me.marek2810.persoLib.hologramOld1.ItemHologramLine;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.Location;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

public class ItemHologramLine_v1_21_R3 extends AbstractHologramLine_v1_21_R3 implements ItemHologramLine {

    public ItemHologramLine_v1_21_R3(Location location) {
        super(ItemHologramLine.DEFAULT_SETTINGS, location);
    }

    @Override
    public void setItem(ItemStack item) {
        this.setSetting(HologramSetting.Item.ITEM_STACK, item);
    }

    @Override
    public ItemStack getItem() {
        return this.getSetting(HologramSetting.Item.ITEM_STACK);
    }

    @Override
    public void setTransform(ItemDisplay.ItemDisplayTransform transform) {
        this.setSetting(HologramSetting.Item.DISPLAY_TRANSFORM, transform);
    }

    @Override
    public ItemDisplay.ItemDisplayTransform getTransform() {
        return this.getSetting(HologramSetting.Item.DISPLAY_TRANSFORM);
    }
}

