package me.marek2810.persoLib.hologram.line;

import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;

public interface BlockLine extends HologramLine<BlockData> {

    void setBlockState(BlockState block);
    BlockState getBlockState();

}
