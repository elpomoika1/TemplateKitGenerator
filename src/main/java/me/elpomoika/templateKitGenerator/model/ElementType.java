package me.elpomoika.templateKitGenerator.model;

public enum ElementType {
    HELMET,
    CHESTPLATE,
    LEGGINGS,
    BOOTS,
    WEAPON,
    LEFT_HAND;

    public static ElementType fromString(String s) {
        return ElementType.valueOf(s.toUpperCase());
    }
}
