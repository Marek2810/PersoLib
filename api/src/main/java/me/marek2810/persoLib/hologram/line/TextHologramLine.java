package me.marek2810.persoLib.hologram.line;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.HologramType;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.entity.TextDisplay;

import java.util.Map;

public class TextHologramLine extends HologramLine {

    private static final Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Text.TEXT, ""),
            Map.entry(HologramSetting.Text.LINE_WIDTH, 200),
            Map.entry(HologramSetting.Text.BACKGROUND_COLOR, 1073741824),
            Map.entry(HologramSetting.Text.TEXT_OPACITY, (byte) -1),
            Map.entry(HologramSetting.Text.FORMAT, (byte) 0)
    );

    private boolean hasShadow;
    private boolean seeThrough;
    private boolean useDefaultColor;
    private TextDisplay.TextAlignment textAlignment;

    public TextHologramLine() {
        this("Default text");
    }

    public TextHologramLine(String text) {
        super(HologramType.TEXT, DEFAULT_SETTINGS);
        this.hasShadow = false;
        this.seeThrough = false;
        this.useDefaultColor = false;
        this.textAlignment = TextDisplay.TextAlignment.CENTER;
        setText(text);
    }

    public void setText(String text) {
        this.setSetting(HologramSetting.Text.TEXT, text);
    }

    public String getText() {
        return this.getSetting(HologramSetting.Text.TEXT);
    }

    public void setLineWidth(int width) {
        this.setSetting(HologramSetting.Text.LINE_WIDTH, width);
    }

    public int getLineWidth() {
        return this.getSetting(HologramSetting.Text.LINE_WIDTH);
    }

    public void setBackgroundColor(int backgroundColor) {
        this.setSetting(HologramSetting.Text.BACKGROUND_COLOR, backgroundColor);
    }

    public int getBackgroundColor() {
        return this.getSetting(HologramSetting.Text.BACKGROUND_COLOR);
    }

    public void setOpacity(byte opacity) {
        this.setSetting(HologramSetting.Text.TEXT_OPACITY, opacity);
    }

    public byte getOpacity() {
        return this.getSetting(HologramSetting.Text.TEXT_OPACITY);
    }

    public void setShadow(boolean shadow) {
        this.hasShadow = shadow;
        updateFormat();
    }

    public boolean hasShadow() {
        return this.hasShadow;
    }

    public void setSeeThrough(boolean seeThrough) {
        this.seeThrough = seeThrough;
        updateFormat();
    }

    public boolean isSeeThrough() {
        return this.seeThrough;
    }

    public void useBackgroundColor(boolean use) {
        this.useDefaultColor = use;
        updateFormat();
    }

    public boolean useBackgroundColor() {
        return this.useDefaultColor;
    }

    public void setAlignment(TextDisplay.TextAlignment alignment) {
        this.textAlignment = alignment;
        updateFormat();
    }

    public TextDisplay.TextAlignment getAlignment() {
        return this.textAlignment;
    }

    private void updateFormat() {
        this.setSetting(HologramSetting.Text.FORMAT, getFormat());
    }

    private byte getFormat() {
        byte format = 0;
        if (hasShadow())
            format = (byte) (format | 0x01);
        if (isSeeThrough())
            format = (byte) (format | 0x02);
        if (useBackgroundColor())
            format = (byte) (format | 0x04);
        format = switch (textAlignment) {
            case CENTER -> format;
            case LEFT -> (byte) (format | 0x08);
            case RIGHT -> (byte) (format | 0x10);
        };
        return format;
    }

}
