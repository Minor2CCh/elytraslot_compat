package com.Minor2CCh.elytraslot_compat;

import com.Minor2CCh.elytraslot_compat.config.ECConfigLoader;
import com.Minor2CCh.elytraslot_compat.platform.Platform;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;

public final class ElytraslotCompat {
    public static final String MOD_ID = "elytraslot_compat";
    public static Platform PLATFORM;
    public static HashMap<String, ResourceLocation> ELYTRA_TEXTURE_MAP = new HashMap<>();
    public static void init() {

        ECConfigLoader.load();
        ECConfigLoader.getConfig().compatList.forEach((item, texture) -> {

            ELYTRA_TEXTURE_MAP.put(item, ResourceLocation.parse(texture));
            /*
            System.out.println(item);
            System.out.println(texture);
            System.out.println(ElytraslotCompat.ELYTRA_TEXTURE_MAP.get(item));
            */
        });
    }
    public static ResourceLocation of(String id){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }
}
