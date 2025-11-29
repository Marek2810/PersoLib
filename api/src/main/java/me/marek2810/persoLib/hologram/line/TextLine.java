package me.marek2810.persoLib.hologram.line;

import org.bukkit.entity.TextDisplay;

public interface TextLine extends HologramLine<String> {

    int getLineWidth();

    void setLineWidth(int width);

    int getBackgroundColor();

    void setBackgroundColor(int backgroundColor);

    byte getOpacity();

    void setOpacity(byte opacity);

    void setShadow(boolean shadow);

    boolean hasShadow();

    boolean isSeeThrough();

    void setSeeThrough(boolean seeThrough);

    void useBackgroundColor(boolean use);

    boolean useBackgroundColor();

    TextDisplay.TextAlignment getAlignment();

    void setAlignment(TextDisplay.TextAlignment alignment);

}
