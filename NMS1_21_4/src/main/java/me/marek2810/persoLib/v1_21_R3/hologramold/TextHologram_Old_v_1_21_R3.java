package me.marek2810.persoLib.v1_21_R3.hologramold;


import me.marek2810.persoLib.hologramOld.TextHologramOld;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Location;

public final class TextHologram_Old_v_1_21_R3 extends AbstractHologram_Old_v1_21_R3 implements TextHologramOld {

    public TextHologram_Old_v_1_21_R3(String id, Location location) {
        super(id, EntityType.TEXT_DISPLAY, location);
        this.settings.putAll(HologramSetting.TEXT_DEFAULT_SETTINGS);
    }

    @Override
    public void setText(String text) {
        this.setSetting(HologramSetting.Text.TEXT, text);
    }

    @Override
    public String getText() {
        return this.getSetting(HologramSetting.Text.TEXT);
    }



}
