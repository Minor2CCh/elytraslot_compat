package com.Minor2CCh.elytraslot_compat;

import com.Minor2CCh.elytraslot_compat.config.ECConfigLoader;
import com.Minor2CCh.elytraslot_compat.mixin.ElytraSlotProviderAccessor;
import com.Minor2CCh.elytraslot_compat.platform.Platform;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public final class ElytraslotCompat {
    public static final String MOD_ID = "elytraslot_compat";
    public static Platform PLATFORM;
    public static final Logger LOGGER = LogManager.getLogger("Elytra Slot Compat");
    public static final HashMap<String, ResourceLocation> ELYTRA_TEXTURE_MAP = new HashMap<>();
    public static void init() {

        ECConfigLoader.load();
        ECConfigLoader.getConfig().compatList.forEach((item, texture) -> {

            ELYTRA_TEXTURE_MAP.put(item, ResourceLocation.parse(texture));
            LOGGER.info("ItemName:\t{}", item);
            LOGGER.info("TextureName:\t{}", texture);
        });
        ElytraSlotProviderAccessor.getProvider().add(new ExtraCompatibilityProvider());
    }
    public static ResourceLocation of(String id){
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }
}
