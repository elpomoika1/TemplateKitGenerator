package me.elpomoika.templateKitGenerator.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import me.elpomoika.templateKitGenerator.config.YamlConfig;
import me.elpomoika.templateKitGenerator.config.subconfig.ArmorElementData;
import me.elpomoika.templateKitGenerator.service.ItemService;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("templatekit")
@CommandPermission("templatekit.admin")
@RequiredArgsConstructor
public class GeneratorCommand extends BaseCommand {
    private final YamlConfig yamlConfig;
    private final ItemService itemService;

    @Subcommand("reload")
    public void onReload(Player sender) {
        yamlConfig.load();

        sender.sendMessage("TemplateKitGenerator successfully reload");
    }

    // /templatekit apply helmet <red> Грома 7 12
    @Subcommand("apply")
    public void onApply(Player sender, String rawElementType, String color, String rawDisplayname, int level, int enchantLevel) {
        ArmorElementData elementData = yamlConfig.getElementData(rawElementType);
        ItemStack appliedItem = sender.getInventory().getItemInMainHand().clone();

        ItemStack result = itemService.buildItem(appliedItem, elementData, color, rawDisplayname, level, enchantLevel);

        sender.getInventory().addItem(result);
    }
}
