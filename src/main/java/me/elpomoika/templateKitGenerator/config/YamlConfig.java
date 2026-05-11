package me.elpomoika.templateKitGenerator.config;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import me.elpomoika.templateKitGenerator.config.subconfig.ArmorElementData;

import java.util.ArrayList;
import java.util.List;

@Getter
public class YamlConfig extends OkaeriConfig {
    private List<ArmorElementData> template = new ArrayList<>(

    );
}
