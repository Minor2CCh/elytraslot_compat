package com.Minor2CCh.elytraslot_compat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ExtraCompatibilityProviderBridge {
    public static boolean matches(ItemStack stack) {
        return ElytraslotCompat.PLATFORM.checkTrueElytra(stack);
    }
    public static boolean hasCapeTexture(ItemStack stack) {
        return !ElytraslotCompat.ELYTRA_TEXTURE_MAP.getOrDefault(ElytraslotCompat.PLATFORM.getId(stack.getItem()).toString(), ResourceLocation.parse("")).equals(ResourceLocation.parse(""));
    }
}
