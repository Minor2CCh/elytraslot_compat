package com.Minor2CCh.elytraslot_compat.platform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.nio.file.Path;

@SuppressWarnings("unused")
public interface Platform {
    enum ModLoader{
        NEOFORGE,
        FABRIC
    }
    ModLoader getModLoader();
    Path getConfigPath();
    boolean isModLoaded(String id);
    TagKey<Item> registerTagItem(String modId, String id);
    ResourceLocation getId(Item item);
    boolean checkTrueElytra(ItemStack stack);
}
