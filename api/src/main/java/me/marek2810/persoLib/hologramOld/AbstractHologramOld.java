package me.marek2810.persoLib.hologramOld;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHologramOld implements HologramOld {

    protected final String id;

    protected Location location;

    protected final Map<EntitySetting<?>, Object> settings;

    public AbstractHologramOld(String id, Location location) {
        this.id = id;
        this.location = location;
        this.settings = new HashMap<>();
        this.settings.putAll(HologramSetting.DEFAULT_SETTINGS);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getSetting(EntitySetting<T> setting) {
        return (T) this.settings.get(setting);
    }

    protected <T> void setSetting(EntitySetting<T> setting, T value) {
        this.settings.put(setting, value);
    }

    @Override
    public Location getLocation() {
        return location;
    }

    protected void setLocation(Location location) {
        this.location = location;
    }
}
