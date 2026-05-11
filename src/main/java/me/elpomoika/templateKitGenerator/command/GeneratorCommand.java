package me.elpomoika.templateKitGenerator.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Subcommand;
import lombok.RequiredArgsConstructor;
import me.elpomoika.templateKitGenerator.config.YamlConfig;
import me.elpomoika.templateKitGenerator.config.subconfig.ArmorElementData;
import me.elpomoika.templateKitGenerator.util.ArabicNumberUtil;
import me.elpomoika.templateKitGenerator.util.FormatUtil;
import me.elpomoika.templateKitGenerator.util.LoreBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

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

    // /templatekit apply helmet <red> Грома 7 12
    @Subcommand("apply")
    public void onApply(Player sender, String rawElementType, String color, String rawDisplayname, int level, int enchantLevel) {
        ItemStack appliedItem = sender.getInventory().getItemInMainHand().clone();
        ItemMeta meta = appliedItem.getItemMeta();

        Map<String, String> placeholder = Map.of(
                "{color}", color,
                "{displayname}", rawDisplayname,
                "{level}", String.valueOf(level),
                "{roman_enchant}", ArabicNumberUtil.arabicToRoman(enchantLevel)
        );

        ArmorElementData elementData = yamlConfig.getElementData(rawElementType);

        List<Component> lore = LoreBuilder.build(elementData.getLore(), null, placeholder);
        Component displayname = FormatUtil.parseAndFormatMessage(null, rawDisplayname, placeholder);

        meta.displayName(displayname);
        meta.lore(lore);
        for (String rawEnchantment : elementData.getEnchants()) {
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.fromString(rawEnchantment));

            if (enchantment == null) continue;

            appliedItem.addUnsafeEnchantment(enchantment, enchantLevel);
        }

        appliedItem.setItemMeta(meta);

        sender.getInventory().addItem(appliedItem);
    }
}
