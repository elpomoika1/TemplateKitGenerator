package me.elpomoika.templateKitGenerator.service;

import me.elpomoika.templateKitGenerator.config.subconfig.ArmorElementData;
import me.elpomoika.templateKitGenerator.util.ArabicNumberUtil;
import me.elpomoika.templateKitGenerator.util.FormatUtil;
import me.elpomoika.templateKitGenerator.util.LoreBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class ItemService {
    public ItemStack buildItem(ItemStack item, ArmorElementData elementData, String color, String rawDisplayname, int level, int enchantLevel) {
        ItemMeta meta = item.getItemMeta();

        Map<String, String> placeholder = Map.of(
                "{color}", color,
                "{displayname}", rawDisplayname,
                "{level}", String.valueOf(level),
                "{roman_enchant}", ArabicNumberUtil.arabicToRoman(enchantLevel)
        );

        List<Component> lore = LoreBuilder.build(elementData.getLore(), null, placeholder);
        Component displayname = FormatUtil.parseAndFormatMessage(null, elementData.getDisplayname(), placeholder);

        meta.displayName(displayname);
        meta.lore(lore);
        meta.addItemFlags(ItemFlag.values());
        applyEnchantments(meta, elementData.getEnchants(), enchantLevel);

        item.setItemMeta(meta);

        return item;
    }

    private void applyEnchantments(ItemMeta meta, List<String> enchants, int enchantLevel) {
        for (String rawEnchantment : enchants) {
            Enchantment enchantment = null;

            NamespacedKey key = NamespacedKey.fromString(rawEnchantment);
            if (key != null) {
                enchantment = Enchantment.getByKey(key);
            }

            if (enchantment == null) {
                enchantment = Enchantment.getByName(rawEnchantment.toUpperCase());
            }

            if (enchantment == null) {
                System.out.println("Unknown enchant: " + rawEnchantment);
                continue;
            }

            meta.addEnchant(enchantment, enchantLevel, true);
        }
    }
}
