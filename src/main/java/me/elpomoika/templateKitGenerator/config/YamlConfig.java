package me.elpomoika.templateKitGenerator.config;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import me.elpomoika.templateKitGenerator.config.subconfig.ArmorElementData;
import me.elpomoika.templateKitGenerator.model.ElementType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class YamlConfig extends OkaeriConfig {
    private Map<ElementType, ArmorElementData> template = new HashMap<>() {{
        put(ElementType.HELMET,
                new ArmorElementData(
                        "{color}Шлем {displayname} {level} Уровня",
                        List.of("&#{color}Защита {roman_enchant}"),
                        List.of("PROTECTION_ENVIRONMENTAL")
                )
        );
        put(ElementType.CHESTPLATE,
                new ArmorElementData(
                        "{color}Нагрудник {displayname} {level} Уровня",
                        List.of("&#{color}Защита {roman_enchant}"),
                        List.of("PROTECTION_ENVIRONMENTAL")
                )
        );
    }};

    public ArmorElementData getElementData(String rawElementType) {
        return template.get(ElementType.fromString(rawElementType));
    }
}
