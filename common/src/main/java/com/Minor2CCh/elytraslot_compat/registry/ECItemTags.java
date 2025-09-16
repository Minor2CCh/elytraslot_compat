package com.Minor2CCh.elytraslot_compat.registry;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ECItemTags {
    public static final TagKey<Item> ELYTRA_SLOT_ALLOW = register("elytraslot","elytra");
    public static final TagKey<Item> CURIOS_SLOT_ALLOW = register("curios","back");
    public static final TagKey<Item> TRINKETS_SLOT_ALLOW = register("trinkets","chest/cape");
    public static final TagKey<Item> ACCESSORIES_COMPAT_ALL_CURIOS = register("accessories_compat_layer","all_curios_items");
    public static final TagKey<Item> ACCESSORIES_COMPAT_ALL_TRINKETS = register("accessories_compat_layer","all_trinkets_items");
    public static final TagKey<Item> CURIOS_COMPAT_ALL_CURIOS = register("cclayer","all_curios_items");
    public static final TagKey<Item> TRINKETS_COMPAT_ALL_TRINKETS = register("tclayer","all_trinkets_items");
    public static final TagKey<Item> ACCESSORIES_ALLOW_BACK = register("accessories","back");
    public static final TagKey<Item> ACCESSORIES_ALLOW_CAPE = register("accessories","cape");

    private static TagKey<Item> register(String modId, String id){
        return ElytraslotCompat.PLATFORM.registerTagItem(modId, id);
    }
}
