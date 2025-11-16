package me.marek2810.persoLib.hologram.line;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.HologramType;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import me.marek2810.persoLib.interaction.InteractionAction;
import me.marek2810.persoLib.interaction.PersoInteraction;
import org.bukkit.entity.Display;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public abstract class HologramLine {

    private static final Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Global.TRANSLATION, new Vector3f(0, 0, 0)),
            Map.entry(HologramSetting.Global.SCALE, new Vector3f(1, 1, 1)),
//            Map.entry(HologramSetting.BILLBOARD, Display.Billboard.FIXED), - MC DEFAULT
            Map.entry(HologramSetting.Global.BILLBOARD, Display.Billboard.CENTER),
            Map.entry(HologramSetting.Global.BRIGHTNESS, -1),
            Map.entry(HologramSetting.Global.VIEW_RANGE, 1f),
            Map.entry(HologramSetting.Global.SHADOW_RADIUS, 0f),
            Map.entry(HologramSetting.Global.SHADOW_STRANGE, 0f),
            Map.entry(HologramSetting.Global.WIDTH, 0f),
            Map.entry(HologramSetting.Global.HEIGHT, 0f),
            Map.entry(HologramSetting.Global.GLOW_COLOR, -1)
    );

    @NotNull
    private final HologramType type;
    private final Map<EntitySetting<?>, Object> settings;

    protected PersoInteraction persoInteraction;

    protected HologramLine(@NotNull HologramType hologramType, Map<EntitySetting<?>, Object> typedDefaultSettings) {
        this.type = hologramType;
        this.settings = new HashMap<>();
        this.settings.putAll(DEFAULT_SETTINGS);
        this.settings.putAll(typedDefaultSettings);
    }

    @NotNull
    public HologramType getType() {
        return type;
    }

//    public void removeInteraction() {
//        PersoInteraction interaction = this.getInteraction();
//        if (interaction == null)
//            return;
//
//        Bukkit.getOnlinePlayers().forEach(interaction::hideFrom);
//        this.persoInteraction = null;
//    }
//

    public PersoInteraction getInteraction() {
        return persoInteraction;
    }

    public void addAction(InteractionAction action) {
        this.persoInteraction.setAction(action);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getSetting(EntitySetting<T> setting) {
        Object value = settings.get(setting);
        if (value == null) return null;
        return (T) value;
    }

    protected <T> void setSetting(EntitySetting<T> setting, T value) {
        settings.put(setting, value);
    }

    public Map<EntitySetting<?>, Object> getSettings() {
        return Map.copyOf(this.settings);
    }

    public Vector3f getTranslation() {
        return this.getSetting(HologramSetting.Global.TRANSLATION);
    }

    public void setTranslation(Vector3f translation) {
        this.setSetting(HologramSetting.Global.TRANSLATION, translation);
    }

    public Vector3f getScale() {
        return this.getSetting(HologramSetting.Global.SCALE);
    }

    public void setScale(Vector3f scale) {
        this.setSetting(HologramSetting.Global.SCALE, scale);
    }

    public org.bukkit.entity.Display.Billboard getBillboard() {
        return this.getSetting(HologramSetting.Global.BILLBOARD);
    }

    public void setBillboard(org.bukkit.entity.Display.Billboard billboard) {
        this.setSetting(HologramSetting.Global.BILLBOARD, billboard);
    }

    public org.bukkit.entity.Display.Brightness getBrightness() {
        return this.getSetting(HologramSetting.Global.BRIGHTNESS);
    }

    public void setBrightness(org.bukkit.entity.Display.Brightness brightness) {
        this.setSetting(HologramSetting.Global.BRIGHTNESS, brightness);
    }

    public float getViewRange() {
        return this.getSetting(HologramSetting.Global.VIEW_RANGE);
    }

    public void setViewRange(float viewRange) {
        this.setSetting(HologramSetting.Global.VIEW_RANGE, viewRange);
    }

    public float getShadowRadius() {
        return this.getSetting(HologramSetting.Global.SHADOW_RADIUS);
    }

    public void setShadowRadius(float shadowRadius) {
        this.setSetting(HologramSetting.Global.SHADOW_RADIUS, shadowRadius);
    }

    public float getShadowStrange() {
        return this.getSetting(HologramSetting.Global.SHADOW_STRANGE);
    }

    public void setShadowStrange(float shadowStrange) {
        this.setSetting(HologramSetting.Global.SHADOW_STRANGE, shadowStrange);
    }

}
