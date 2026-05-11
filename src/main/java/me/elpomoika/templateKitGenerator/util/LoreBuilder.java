package me.elpomoika.templateKitGenerator.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UtilityClass
public final class LoreBuilder {
    public List<Component> build(List<String> lore, Player viewer, Map<String, String> placeholders) {
        List<Component> result = new ArrayList<>();
        for (String line : lore) {

            String applied = FormatUtil.apply(line, placeholders);

            String[] parts = applied.split("\n");

            for (String part : parts) {
                if (part.isEmpty()) continue;

                Component component = FormatUtil.parseAndFormatMessage(
                        viewer,
                        part,
                        Map.of()
                );

                result.add(component);
            }
        }

        return result;
    }
}
