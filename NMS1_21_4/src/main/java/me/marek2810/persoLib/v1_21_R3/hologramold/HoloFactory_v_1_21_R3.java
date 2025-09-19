package me.marek2810.persoLib.v1_21_R3.hologramold;

import me.marek2810.persoLib.hologramOld.BlockHologramOld;
import me.marek2810.persoLib.hologramOld.HologramFactory;
import me.marek2810.persoLib.hologramOld.ItemHologramOld;
import me.marek2810.persoLib.hologramOld.TextHologramOld;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

public class HoloFactory_v_1_21_R3 implements HologramFactory {

    @Override
    public String supportedVersion() {
        return "1.21.4";
    }

    @Override
    public TextHologramOld spawnTextDisplay(String id, Location loc, String text) {
        TextHologramOld textHologram = new TextHologram_Old_v_1_21_R3(id, loc);
        textHologram.setText(text);
        return textHologram;
    }

    @Override
    public ItemHologramOld spawnItemDisplay(String id, Location loc, ItemStack item) {
        return null;
    }

    @Override
    public BlockHologramOld spawnBlockDisplay(String id, Location loc, BlockData data) {
        return null;
    }
}
