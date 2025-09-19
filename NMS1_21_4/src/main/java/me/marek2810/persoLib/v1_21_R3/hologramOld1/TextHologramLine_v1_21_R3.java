package me.marek2810.persoLib.v1_21_R3.hologramOld1;

import me.marek2810.persoLib.hologramOld1.TextHologramLine;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;

public class TextHologramLine_v1_21_R3 extends AbstractHologramLine_v1_21_R3 implements TextHologramLine {

    private boolean hasShadow;
    private boolean seeThrough;
    private boolean useDefaultColor;
    private TextDisplay.TextAlignment textAlignment;

    public TextHologramLine_v1_21_R3(Location location) {
        super(TextHologramLine.DEFAULT_SETTINGS, location);
    }

    @Override
    public void setText(String text) {
        this.setSetting(HologramSetting.Text.TEXT, text);
    }

    @Override
    public String getText() {
        return this.getSetting(HologramSetting.Text.TEXT);
    }

    @Override
    public void setLineWidth(int width) {
        this.setSetting(HologramSetting.Text.LINE_WIDTH, width);
    }

    @Override
    public int getLineWidth() {
        return 0;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.setSetting(HologramSetting.Text.BACKGROUND_COLOR, backgroundColor);
    }

    @Override
    public int getBackgroundColor() {
        return 0;
    }

    @Override
    public void setOpacity(byte opacity) {
        this.setSetting(HologramSetting.Text.TEXT_OPACITY, opacity);
    }

    @Override
    public byte getOpacity() {
        return 0;
    }

    @Override
    public void setShadow(boolean shadow) {
        this.hasShadow = shadow;
    }

    @Override
    public boolean getShadow() {
        return this.hasShadow;
    }

    @Override
    public void setSeeThrough(boolean seeThrough) {
        this.seeThrough = seeThrough;
    }

    @Override
    public boolean getSeeThrough() {
        return this.seeThrough;
    }

    @Override
    public void useBackgroundColor(boolean use) {
        this.useDefaultColor = use;
    }

    @Override
    public boolean useBackgroundColor() {
        return this.useDefaultColor;
    }

    @Override
    public void setAlignment(TextDisplay.TextAlignment alignment) {
        this.textAlignment = alignment;
    }

    @Override
    public TextDisplay.TextAlignment getAlignment() {
        return this.textAlignment;
    }

}

