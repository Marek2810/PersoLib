package me.marek2810.persoLib.interaction;

import me.marek2810.persoLib.entity.EntitySetting;
import me.marek2810.persoLib.entity.PersoEntity;
import me.marek2810.persoLib.hologram.Hologram;
import me.marek2810.persoLib.hologram.setting.HologramSetting;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public abstract class PersoInteraction implements PersoEntity {

    private static final Map<EntitySetting<?>, Object> DEFAULT_SETTINGS = Map.ofEntries(
            Map.entry(HologramSetting.Interaction.WIDTH, 1F),
            Map.entry(HologramSetting.Interaction.HEIGHT, 1F),
            Map.entry(HologramSetting.Interaction.RESPONSIVE, false)
    );

    @NotNull
    protected final Hologram hologram;
    private final Map<EntitySetting<?>, Object> settings;

    @Nullable
    private InteractionAction action;

    public PersoInteraction(@NotNull Hologram hologram, @Nullable InteractionAction action) {
        this.hologram = hologram;
        this.settings = new HashMap<>();
        this.settings.putAll(DEFAULT_SETTINGS);
        this.action = action;
    }

    @NotNull
    public Hologram getHologram() {
        return this.hologram;
    }

    @Override
    public Location getLocation() {
        return hologram.getLocation();
    }

    @Nullable
    public InteractionAction getAction() {
        return action;
    }

    public void setAction(@Nullable InteractionAction action) {
        this.action = action;
    }

    public void setWidth(float width) {
        this.setSetting(HologramSetting.Interaction.WIDTH, width);
    }

    public float getWith(){
        return this.getSetting(HologramSetting.Interaction.WIDTH);
    }

    public void setHeigh(float heigh) {
        this.setSetting(HologramSetting.Interaction.HEIGHT, heigh);
    }

    public float getHeigh(){
        return this.getSetting(HologramSetting.Interaction.HEIGHT);
    }

    public void setResponsive(boolean responsive) {
        this.setSetting(HologramSetting.Interaction.RESPONSIVE, responsive);
    }

    public boolean isResponsive(){
        return this.getSetting(HologramSetting.Interaction.RESPONSIVE);
    }

    @SuppressWarnings("unchecked")
    public <T> T getSetting(EntitySetting<T> setting) {
        Object value = settings.get(setting);
        if (value == null) return null;
        return (T) value;
    }

    protected  <T> void setSetting(EntitySetting<T> setting, T value) {
        settings.put(setting, value);
    }

    protected Map<EntitySetting<?>, Object> getSettings() {
        return Map.copyOf(this.settings);
    }

    public abstract int getEntityId();

}
