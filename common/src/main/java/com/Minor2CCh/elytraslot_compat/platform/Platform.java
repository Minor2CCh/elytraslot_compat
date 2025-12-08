package com.Minor2CCh.elytraslot_compat.platform;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.nio.file.Path;
import java.util.Iterator;
import java.util.ServiceLoader;

@SuppressWarnings("unused")
public interface Platform {
    Platform INSTANCE = Util.make(() -> {
        final ServiceLoader<Platform> loader = ServiceLoader.load(Platform.class);
        final Iterator<Platform> iterator = loader.iterator();
        if (!iterator.hasNext()) {
            throw new RuntimeException("Platform instance not found!");
        } else {
            final Platform platform = iterator.next();
            if (iterator.hasNext()) {
                throw new RuntimeException("More than one platform instance was found!");
            }
            return platform;
        }
    });
    enum ModLoader{
        FORGE,
        FABRIC
    }
    ModLoader getModLoader();
    Path getConfigPath();
    boolean isModLoaded(String id);
    TagKey<Item> registerTagItem(String modId, String id);
    ResourceLocation getId(Item item);
    boolean checkTrueElytra(ItemStack stack);
    default ResourceLocation resourceLocation(String namespace, String path){
        return new ResourceLocation(namespace, path);
    }   // 新し目のForgeだと1.21以降のメソッドを推奨されるので
}
