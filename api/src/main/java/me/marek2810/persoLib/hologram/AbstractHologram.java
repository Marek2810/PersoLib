package me.marek2810.persoLib.hologram;

import me.marek2810.persoLib.hologram.line.HologramLine;
import me.marek2810.persoLib.interaction.InteractionAction;
import me.marek2810.persoLib.interaction.PersoInteraction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractHologram implements Hologram {

    protected final String id;

    protected Location location;

    protected final HologramLine hologramLine;

    @Nullable
    protected PersoInteraction persoInteraction;

    public AbstractHologram(String id, Location location, HologramLine hologramLine) {
        this.id = id;
        this.location = location;
        this.hologramLine = hologramLine;
        create();
    }

    protected void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return this.location;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    abstract public void addInteraction(InteractionAction action);

    @Override
    public void removeInteraction() {
        PersoInteraction interaction = this.getInteraction();
        if (interaction == null)
            return;

        Bukkit.getOnlinePlayers().forEach(interaction::hideFrom);
        this.persoInteraction = null;
    }

    @Override
    @Nullable
    public PersoInteraction getInteraction() {
        return persoInteraction;
    }

}
