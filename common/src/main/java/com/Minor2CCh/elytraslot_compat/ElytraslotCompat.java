package com.Minor2CCh.elytraslot_compat;

import com.Minor2CCh.elytraslot_compat.config.ECConfigLoader;
import com.illusivesoulworks.elytraslot.ElytraSlotCommonMod;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public final class ElytraslotCompat {
    public static final String MOD_ID = "elytraslot_compat";
    public static final Logger LOGGER = LogManager.getLogger("Elytra Slot Compat");
    public static final HashMap<String, ResourceLocation> ELYTRA_TEXTURE_MAP = new HashMap<>();
    public static void init() {

        ECConfigLoader.load();
        ECConfigLoader.getConfig().compatList.forEach((item, texture) -> {

            ELYTRA_TEXTURE_MAP.put(item, new ResourceLocation(texture));
            LOGGER.info("ItemName:\t{}", item);
            LOGGER.info("TextureName:\t{}", texture);
        });
        ElytraSlotCommonMod.PROVIDERS.add(new ExtraCompatibilityProvider());
    }
    @SuppressWarnings("unused")
    public static ResourceLocation of(String id){
        return new ResourceLocation(MOD_ID, id);
    }
}
