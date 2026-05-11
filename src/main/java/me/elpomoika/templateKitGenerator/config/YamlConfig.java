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
    private Map<String, ArmorElementData> template = new HashMap<>() {{
        put("helmet",
                new ArmorElementData(
                        ElementType.HELMET,
                        "{color}Шлем {displayname} {level} Уровня",
                        List.of("&#{color}Защита {roman_enchant}"),
                        Map.of("PROTECTION_ENVIRONMENTAL", 10)
                )
        );
    }};
}
