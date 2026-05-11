package me.elpomoika.templateKitGenerator.util;

import lombok.experimental.UtilityClass;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.OfflinePlayer;

import javax.annotation.Nullable;
import java.util.Map;

@UtilityClass
public class FormatUtil {
    private static final MiniMessage mm = MiniMessage.miniMessage();

    public Component parseAndFormatMessage(OfflinePlayer player, String message, @Nullable Map<String, String> placeholders) {
        String result = apply(message, placeholders);
        String withPlaceholderApi = PlaceholderAPI.setPlaceholders(player, result);

        return mm.deserialize(withPlaceholderApi);
    }

    public String apply(String message, @Nullable Map<String, String> placeholders) {
        if (message == null || placeholders == null || placeholders.isEmpty()) {
            return message;
        }

        String result = message;
        for (var entry : placeholders.entrySet()) {
            result = result.replace(
                    entry.getKey(),
                    entry.getValue() != null ? entry.getValue() : ""
            );
        }
        return result;
    }
}
