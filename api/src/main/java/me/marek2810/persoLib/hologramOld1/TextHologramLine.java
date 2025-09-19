package me.marek2810.persoLib.hologramOld1;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.entity.TextDisplay;

import java.util.Map;

public interface TextHologramLine extends HologramLine {

    Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Text.TEXT, ""),
            Map.entry(HologramSetting.Text.LINE_WIDTH, 200),
            Map.entry(HologramSetting.Text.BACKGROUND_COLOR, 1073741824),
            Map.entry(HologramSetting.Text.TEXT_OPACITY, (byte) -1),
            Map.entry(HologramSetting.Text.FORMAT, TextDisplay.TextAlignment.CENTER)
    );

    void setText(String text);
    String getText();

    void setLineWidth(int width);
    int getLineWidth();

    void setBackgroundColor(int backgroundColor);
    int getBackgroundColor();

    void setOpacity(byte opacity);
    byte getOpacity();

    void setShadow(boolean shadow);
    boolean getShadow();

    void setSeeThrough(boolean seeThrough);
    boolean getSeeThrough();

    void useBackgroundColor(boolean use);
    boolean useBackgroundColor();

    void setAlignment(TextDisplay.TextAlignment alignment);
    TextDisplay.TextAlignment getAlignment();

    default byte getFormat() {
        byte format = 0;
        if (getShadow())
            format = (byte) (format | 0x01);
        if (getSeeThrough())
            format = (byte) (format | 0x02);
        if (useBackgroundColor())
            format = (byte) (format | 0x04);
        format = switch (getAlignment()){
            case CENTER -> format;
            case LEFT -> (byte) (format | 0x08);
            case RIGHT -> (byte) (format | 0x10);
        };
        return format;
    }

}
