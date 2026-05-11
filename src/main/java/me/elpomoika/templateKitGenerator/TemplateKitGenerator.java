package me.elpomoika.templateKitGenerator;

import co.aikar.commands.PaperCommandManager;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.serdes.okaeri.SerdesOkaeriBukkit;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import me.elpomoika.templateKitGenerator.command.GeneratorCommand;
import me.elpomoika.templateKitGenerator.config.YamlConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class TemplateKitGenerator extends JavaPlugin {
    private YamlConfig yamlConfig;

    private PaperCommandManager commandManager;

    @Override
    public void onEnable() {
        initConfigs();

        this.commandManager = new PaperCommandManager(this);
        commandManager.registerCommand(new GeneratorCommand(yamlConfig));
    }

    private void initConfigs() {
        this.yamlConfig = ConfigManager.create(YamlConfig.class, it -> {
            it.configure(opt -> {
                opt.configurer(new YamlBukkitConfigurer(), new SerdesOkaeriBukkit());
                opt.bindFile(new File(this.getDataFolder(), "config.yml"));
                opt.removeOrphans(true);
                opt.resolvePlaceholders();
            });
            it.saveDefaults();
            it.load(true);
        });
    }
}
