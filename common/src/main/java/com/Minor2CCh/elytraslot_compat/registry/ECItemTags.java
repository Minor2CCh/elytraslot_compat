package com.Minor2CCh.elytraslot_compat.registry;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ECItemTags {
    public static final TagKey<Item> ELYTRA_SLOT_ALLOW = register("elytraslot","elytra");
    public static final TagKey<Item> TRINKETS_SLOT_BACK_ALLOW = register("trinkets","chest/back");
    public static final TagKey<Item> ACCESSORIES_ALLOW_CAPE = register("accessories","cape");
    private static TagKey<Item> register(String modId, String id){
        return ElytraslotCompat.PLATFORM.registerTagItem(modId, id);
    }
}
