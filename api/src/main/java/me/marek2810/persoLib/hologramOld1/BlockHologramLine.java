package me.marek2810.persoLib.hologramOld1;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.Material;
import org.bukkit.block.BlockState;

import java.util.Map;

public interface BlockHologramLine extends HologramLine {

    Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.BLock.BLOCK_STATE, Material.GRASS_BLOCK.createBlockData().createBlockState())
    );

    void setBlockState(BlockState block);

    BlockState getBlockState();

}
