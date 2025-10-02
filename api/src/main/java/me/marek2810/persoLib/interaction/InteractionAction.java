package me.marek2810.persoLib.interaction;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface InteractionAction {

    void execute(Player player);

}
