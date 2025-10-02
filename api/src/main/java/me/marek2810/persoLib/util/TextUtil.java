package me.marek2810.persoLib.util;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    public static String replaceFromMapRegex(String text, Map<String, String> replacements) {
        if (text == null) return null;
        if ((replacements == null || replacements.isEmpty())) {
            return text;
        }

        Pattern pattern = Pattern.compile("\\{[^}]+}");
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String match = matcher.group();
            String replacement = replacements.getOrDefault(match, match);

            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    public static String replaceFromMapRegex(Player player, String text, Map<String, String> replacements, Map<String, Function<Player, String>> playerReplacements) {
        if (text == null) return null;
        if ((replacements == null || replacements.isEmpty()) && (playerReplacements == null || playerReplacements.isEmpty())) {
            return text;
        }

        Pattern pattern = Pattern.compile("\\{[^}]+}");
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String match = matcher.group();
            String replacement = match;

            if (playerReplacements != null && playerReplacements.containsKey(match)) {
                replacement = Optional.ofNullable(playerReplacements.get(match).apply(player)).orElse(match);
            } else if (replacements != null && replacements.containsKey(match)) {
                replacement = replacements.getOrDefault(match, match);
            }

            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

}
