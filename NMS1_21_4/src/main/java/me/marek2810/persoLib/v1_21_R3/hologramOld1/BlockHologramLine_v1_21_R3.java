package me.marek2810.persoLib.v1_21_R3.hologramOld1;

import me.marek2810.persoLib.hologramOld1.BlockHologramLine;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.Location;
import org.bukkit.block.BlockState;

public class BlockHologramLine_v1_21_R3 extends AbstractHologramLine_v1_21_R3 implements BlockHologramLine {

    public BlockHologramLine_v1_21_R3(Location location) {
        super(BlockHologramLine.DEFAULT_SETTINGS, location);
    }

    @Override
    public void setBlockState(BlockState block) {
        this.setSetting(HologramSetting.BLock.BLOCK_STATE, block);
    }

    @Override
    public BlockState getBlockState() {
        return this.getSetting(HologramSetting.BLock.BLOCK_STATE);
    }

}

