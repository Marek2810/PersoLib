package me.marek2810.persoLib.event.event;

import me.marek2810.persoLib.hologram.Hologram;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.NotNull;

public class HologramInteractEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Hologram hologram;
    private final Player player;
    private final EquipmentSlot hand;

    private boolean canceled;

    public HologramInteractEvent(Hologram hologram, Player player, EquipmentSlot hand) {
        this.hologram = hologram;
        this.player = player;
        this.hand = hand;
    }

    @Override
    public boolean isCancelled() {
        return this.canceled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.canceled = b;
    }

    public Hologram getHologram() {
        return hologram;
    }

    public Player getPlayer() {
        return player;
    }

    public EquipmentSlot getHand() {
        return hand;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

}
