package me.elpomoika.templateKitGenerator.config.subconfig;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArmorElementData extends OkaeriConfig {
    private String material;
    private String displayname;
    private List<String> lore;
    private Map<String, Integer> enchants;
}
