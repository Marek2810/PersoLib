package me.marek2810.persoLib.entity;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface PersoEntity {

    Location getLocation();

    void create();

    void teleport(Location target);

    void showTo(Player player);

    void hideFrom(Player player);

    void update(Player player);

}
