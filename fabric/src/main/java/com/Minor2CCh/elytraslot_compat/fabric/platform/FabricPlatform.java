package com.Minor2CCh.elytraslot_compat.fabric.platform;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import com.Minor2CCh.elytraslot_compat.platform.Platform;
import com.google.auto.service.AutoService;
import com.illusivesoulworks.elytraslot.platform.Services;
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.nio.file.Path;

@AutoService(Platform.class)
public class FabricPlatform implements Platform {
    @Override
    public ModLoader getModLoader() {
        return ModLoader.FABRIC;
    }

    @Override
    public Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @Override
    public boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }


    @Override
    public TagKey<Item> registerTagItem(String modId, String id) {
        return TagKey.create(Registries.ITEM, resourceLocation(modId, id));
    }
    @Override
    public ResourceLocation getId(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    @Override
    public boolean checkTrueElytra(ItemStack stack) {
        return ElytraslotCompat.ELYTRA_TEXTURE_MAP.containsKey(Services.PLATFORM.getId(stack.getItem()).toString()) && stack.getItem() instanceof FabricElytraItem;
    }
}
