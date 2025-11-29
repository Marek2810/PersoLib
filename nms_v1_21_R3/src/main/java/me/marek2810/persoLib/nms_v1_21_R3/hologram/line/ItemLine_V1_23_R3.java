package me.marek2810.persoLib.nms_v1_21_R3.hologram.line;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.HologramLineType;
import me.marek2810.persoLib.hologram.line.ItemLine;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ItemLine_V1_23_R3 extends HologramLine_V1_23_R3<ItemStack> implements ItemLine {

    private static final Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Item.ITEM_STACK, new ItemStack(Material.GRASS_BLOCK)),
            Map.entry(HologramSetting.Item.DISPLAY_TRANSFORM, ItemDisplay.ItemDisplayTransform.NONE)
    );

    public ItemLine_V1_23_R3(Location location) {
        super(HologramLineType.ITEM, location, DEFAULT_SETTINGS);
        this.display = new Display.ItemDisplay(EntityType.ITEM_DISPLAY, getLevel());
        this.display.setPos(getLocation().getX(), getLocation().getY(), getLocation().getZ());
    }

    @Override
    public ItemStack getItem() {
        return this.getSetting(HologramSetting.Item.ITEM_STACK);
    }

    @Override
    public void setItem(ItemStack item) {
        this.setSetting(HologramSetting.Item.ITEM_STACK, item);
    }

    @Override
    public ItemDisplay.ItemDisplayTransform getTransform() {
        return this.getSetting(HologramSetting.Item.DISPLAY_TRANSFORM);
    }

    @Override
    public void setTransform(ItemDisplay.ItemDisplayTransform transform) {
        this.setSetting(HologramSetting.Item.DISPLAY_TRANSFORM, transform);
    }


}
