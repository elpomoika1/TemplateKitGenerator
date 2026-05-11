package me.elpomoika.templateKitGenerator.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import me.elpomoika.templateKitGenerator.config.YamlConfig;
import org.bukkit.entity.Player;

@CommandAlias("templatekit")
@CommandPermission("templatekit.admin")
@RequiredArgsConstructor
public class GeneratorCommand extends BaseCommand {
    private final YamlConfig yamlConfig;

    @Subcommand("reload")
    public void onReload(Player sender) {
        yamlConfig.load();

        sender.sendMessage("TemplateKitGenerator successfully reload");
    }
}
