package me.marek2810.persoLib.event.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public interface PacketListener extends Listener {

    String PACKET_INJECTOR = "perso_packet_listener";

    void injectPacketListener(Player player);

    void uninjectPacketListener(Player player);

    @EventHandler
    default void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        injectPacketListener(player);
    }

    @EventHandler
    default void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        uninjectPacketListener(player);
    }

}
