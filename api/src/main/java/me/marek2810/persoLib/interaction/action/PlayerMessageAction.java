package me.marek2810.persoLib.interaction.action;

import me.marek2810.persoLib.interaction.InteractionAction;
import me.marek2810.persoLib.util.TextUtil;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PlayerMessageAction implements InteractionAction {

    private final String text;

    private final Map<String, String> placeholders;
    private final Map<String, Function<Player, String>> playerPlaceholders;

    public PlayerMessageAction(String text) {
        this.text = text;
        this.placeholders = new HashMap<>();
        this.playerPlaceholders = new HashMap<>();
    }

    @Override
    public void execute(Player player) {
        player.sendMessage(TextUtil.replaceFromMapRegex(player, this.text, this.placeholders, this.playerPlaceholders));
    }

}
 