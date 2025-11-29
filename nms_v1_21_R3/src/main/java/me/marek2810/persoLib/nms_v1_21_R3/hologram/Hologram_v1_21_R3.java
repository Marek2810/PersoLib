package me.marek2810.persoLib.nms_v1_21_R3.hologram;

import me.marek2810.persoLib.hologram.AbstractHologram;
import me.marek2810.persoLib.hologram.line.BlockLine;
import me.marek2810.persoLib.hologram.line.ItemLine;
import me.marek2810.persoLib.hologram.line.TextLine;
import me.marek2810.persoLib.nms_v1_21_R3.hologram.line.BlockLine_V1_23_R3;
import me.marek2810.persoLib.nms_v1_21_R3.hologram.line.ItemLine_V1_23_R3;
import me.marek2810.persoLib.nms_v1_21_R3.hologram.line.TextLine_V1_23_R3;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

public class Hologram_v1_21_R3 extends AbstractHologram {

    Hologram_v1_21_R3(String id, Location location) {
        super(id, location);
    }

    @Override
    public TextLine setTextLine(String text) {
        TextLine_V1_23_R3 line = new TextLine_V1_23_R3(location);
        this.hologramLine = line;
        return line;
    }

    @Override
    public ItemLine setItemLine(ItemStack item) {
        ItemLine_V1_23_R3 line = new ItemLine_V1_23_R3(location);
        this.hologramLine = line;
        return line;
    }

    @Override
    public BlockLine setBlockLine(BlockData blockData) {
        BlockLine_V1_23_R3 line = new BlockLine_V1_23_R3(location);
        this.hologramLine = line;
        return line;
    }


}
