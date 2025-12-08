package com.Minor2CCh.elytraslot_compat.forge.platform;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import com.Minor2CCh.elytraslot_compat.platform.Platform;
import com.google.auto.service.AutoService;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

@AutoService(Platform.class)
public class ForgePlatform implements Platform {
    @Override
    public ModLoader getModLoader() {
        return ModLoader.FORGE;
    }

    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
    @Override
    public TagKey<Item> registerTagItem(String modId, String id) {
        return TagKey.create(Registries.ITEM, resourceLocation(modId, id));
    }
    @Override
    @SuppressWarnings("deprecation")
    public ResourceLocation getId(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    @Override
    public boolean checkTrueElytra(ItemStack stack) {
        return ElytraslotCompat.ELYTRA_TEXTURE_MAP.containsKey(getId(stack.getItem()).toString());
    }
}
