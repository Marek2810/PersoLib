package me.marek2810.persoLib.hologram.line;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.HologramType;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.Material;
import org.bukkit.block.BlockState;

import java.util.Map;

public class BlockHologramLine extends HologramLine {

    private static final Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.BLock.BLOCK_STATE, Material.GRASS_BLOCK.createBlockData().createBlockState())
    );

    public BlockHologramLine() {
        super(HologramType.BLOCK, DEFAULT_SETTINGS);
    }

    protected BlockHologramLine(Material block) {
        super(HologramType.BLOCK, DEFAULT_SETTINGS);
        setBlockState(block.createBlockData().createBlockState());
    }

    public void setBlockState(BlockState block) {
        this.setSetting(HologramSetting.BLock.BLOCK_STATE, block);
    }

    public BlockState getBlockState() {
        return this.getSetting(HologramSetting.BLock.BLOCK_STATE);
    }

    public static BlockHologramLine of(Material material) {
        if (!material.isBlock())
            throw new IllegalArgumentException("Provided material is not a block!");
        return new BlockHologramLine(material);
    }

}

