package me.marek2810.persoLib.nms_v1_21_R3.hologram.line;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.HologramLineType;
import me.marek2810.persoLib.hologram.line.BlockLine;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;

import java.util.Map;

public class BlockLine_v1_23_R3 extends HologramLine_v1_23_R3<BlockData> implements BlockLine {

    private static final Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.BLock.BLOCK_STATE, Material.GRASS_BLOCK.createBlockData().createBlockState())
    );

    public BlockLine_v1_23_R3(Location location) {
        super(HologramLineType.BLOCK, location, DEFAULT_SETTINGS);
        this.display = new Display.BlockDisplay(EntityType.BLOCK_DISPLAY, getLevel());
        this.display.setPos(getLocation().getX(), getLocation().getY(), getLocation().getZ());
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
