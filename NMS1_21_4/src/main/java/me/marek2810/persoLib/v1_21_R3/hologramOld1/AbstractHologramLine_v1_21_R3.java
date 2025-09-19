package me.marek2810.persoLib.v1_21_R3.hologramOld1;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologramOld1.HologramLine;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import net.minecraft.world.entity.Display;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHologramLine_v1_21_R3 implements HologramLine {

    private final Map<EntitySetting<?>, Object> settings;
    @Nullable
    protected Display entity;
    protected Location location;

    public AbstractHologramLine_v1_21_R3(Map<EntitySetting<?>, Object> defaultSettings, Location location) {
        this.location = location;
        this.settings = new HashMap<>();
        this.settings.putAll(HologramLine.DEFAULT_SETTINGS);
        this.settings.putAll(defaultSettings);
    }

    @Override
    public void create() {

    }

    @Override
    public void update(Player player) {

    }

    @Override
    public void showTo(Player player) {

    }

    @Override
    public void hideFrom(Player player) {
        
    }

    @Override
    public void teleport(Location location) {
        this.setLocation(location);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getSetting(EntitySetting<?> setting) {
        return (T) this.settings.get(setting);
    }

    protected <T> void setSetting(EntitySetting<?> setting, T value) {
        this.settings.put(setting, value);
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    protected void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Vector3f getTranslation() {
        return this.getSetting(HologramSetting.Global.TRANSLATION);
    }

    @Override
    public void setTranslation(Vector3f translation) {
        this.setSetting(HologramSetting.Global.TRANSLATION, translation);
    }

    @Override
    public Vector3f getScale() {
        return this.getSetting(HologramSetting.Global.SCALE);
    }

    @Override
    public void setScale(Vector3f scale) {
        this.setSetting(HologramSetting.Global.SCALE, scale);
    }

    @Override
    public org.bukkit.entity.Display.Billboard getBillboard() {
        return this.getSetting(HologramSetting.Global.BILLBOARD);
    }

    @Override
    public void setBillboard(org.bukkit.entity.Display.Billboard billboard) {
        this.setSetting(HologramSetting.Global.BILLBOARD, billboard);
    }

    @Override
    public org.bukkit.entity.Display.Brightness getBrightness() {
        return this.getSetting(HologramSetting.Global.BRIGHTNESS);
    }

    @Override
    public void setBrightness(org.bukkit.entity.Display.Brightness brightness) {
        this.setSetting(HologramSetting.Global.BRIGHTNESS, brightness);
    }

    @Override
    public float getViewRange() {
        return this.getSetting(HologramSetting.Global.VIEW_RANGE);
    }

    @Override
    public void setViewRange(float viewRange) {
        this.setSetting(HologramSetting.Global.VIEW_RANGE, viewRange);
    }

    @Override
    public float getShadowRadius() {
        return this.getSetting(HologramSetting.Global.SHADOW_RADIUS);
    }

    @Override
    public void setShadowRadius(float shadowRadius) {
        this.setSetting(HologramSetting.Global.SHADOW_RADIUS, shadowRadius);
    }

    @Override
    public float getShadowStrange() {
        return this.getSetting(HologramSetting.Global.SHADOW_STRANGE);
    }

    @Override
    public void setShadowStrange(float shadowStrange) {
        this.setSetting(HologramSetting.Global.SHADOW_STRANGE, shadowStrange);
    }

}
