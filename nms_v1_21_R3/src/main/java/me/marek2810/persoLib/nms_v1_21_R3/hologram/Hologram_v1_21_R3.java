package me.marek2810.persoLib.nms_v1_21_R3.hologram;

import me.marek2810.persoLib.hologram.AbstractHologram;
import me.marek2810.persoLib.hologram.line.BlockLine;
import me.marek2810.persoLib.hologram.line.ItemLine;
import me.marek2810.persoLib.hologram.line.TextLine;
import me.marek2810.persoLib.nms_v1_21_R3.hologram.line.BlockLine_v1_23_R3;
import me.marek2810.persoLib.nms_v1_21_R3.hologram.line.ItemLine_v1_23_R3;
import me.marek2810.persoLib.nms_v1_21_R3.hologram.line.TextLine_v1_23_R3;
import org.bukkit.Location;

public class Hologram_v1_21_R3 extends AbstractHologram {

    Hologram_v1_21_R3(String name, Location location) {
        super(name, location);
    }

    @Override
    public TextLine setTextLine() {
        TextLine_v1_23_R3 line = new TextLine_v1_23_R3(location);
        setLine(line);
        return line;
    }

    @Override
    public ItemLine setItemLine() {
        ItemLine_v1_23_R3 line = new ItemLine_v1_23_R3(location);
        setLine(line);
        return line;
    }

    @Override
    public BlockLine setBlockLine() {
        BlockLine_v1_23_R3 line = new BlockLine_v1_23_R3(location);
        setLine(line);
        return line;
    }

}
