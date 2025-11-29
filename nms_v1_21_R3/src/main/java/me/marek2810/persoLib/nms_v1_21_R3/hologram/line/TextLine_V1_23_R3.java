package me.marek2810.persoLib.nms_v1_21_R3.hologram.line;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.HologramLineType;
import me.marek2810.persoLib.hologram.line.TextLine;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;

import java.util.Map;

public class TextLine_V1_23_R3 extends HologramLine_V1_23_R3<String> implements TextLine {

    private static final Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Text.TEXT, "default text"),
            Map.entry(HologramSetting.Text.LINE_WIDTH, 200),
            Map.entry(HologramSetting.Text.BACKGROUND_COLOR, 1073741824),
            Map.entry(HologramSetting.Text.TEXT_OPACITY, (byte) -1),
            Map.entry(HologramSetting.Text.FORMAT, (byte) 0)
    );

    private boolean hasShadow;
    private boolean seeThrough;
    private boolean useDefaultColor;
    private TextDisplay.TextAlignment textAlignment;

    public TextLine_V1_23_R3(Location location) {
        super(HologramLineType.TEXT, location, DEFAULT_SETTINGS);
        this.display = new Display.TextDisplay(EntityType.TEXT_DISPLAY, getLevel());
        this.display.setPos(getLocation().getX(), getLocation().getY(), getLocation().getZ());
    }

    @Override
    public int getLineWidth() {
        return this.getSetting(HologramSetting.Text.LINE_WIDTH);
    }

    @Override
    public void setLineWidth(int width) {
        this.setSetting(HologramSetting.Text.LINE_WIDTH, width);
    }

    @Override
    public int getBackgroundColor() {
        return this.getSetting(HologramSetting.Text.BACKGROUND_COLOR);
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.setSetting(HologramSetting.Text.BACKGROUND_COLOR, backgroundColor);
    }

    @Override
    public byte getOpacity() {
        return this.getSetting(HologramSetting.Text.TEXT_OPACITY);
    }

    @Override
    public void setOpacity(byte opacity) {
        this.setSetting(HologramSetting.Text.TEXT_OPACITY, opacity);
    }

    @Override
    public void setShadow(boolean shadow) {
        this.hasShadow = shadow;
        updateFormat();
    }

    @Override
    public boolean hasShadow() {
        return this.hasShadow;
    }

    @Override
    public boolean isSeeThrough() {
        return this.seeThrough;
    }

    @Override
    public void setSeeThrough(boolean seeThrough) {
        this.seeThrough = seeThrough;
        updateFormat();
    }

    @Override
    public void useBackgroundColor(boolean use) {
        this.useDefaultColor = use;
        updateFormat();
    }

    @Override
    public boolean useBackgroundColor() {
        return this.useDefaultColor;
    }

    @Override
    public TextDisplay.TextAlignment getAlignment() {
        return this.textAlignment;
    }

    @Override
    public void setAlignment(TextDisplay.TextAlignment alignment) {
        this.textAlignment = alignment;
        updateFormat();
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
