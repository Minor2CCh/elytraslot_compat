package com.Minor2CCh.elytraslot_compat.fabric;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import com.Minor2CCh.elytraslot_compat.fabric.mixin.ElytraSlotProviderAccessorFabric;
import com.Minor2CCh.elytraslot_compat.fabric.platform.FabricPlatform;
import net.fabricmc.api.ModInitializer;

public final class ElytraslotCompatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        ElytraslotCompat.PLATFORM = new FabricPlatform();
        ElytraslotCompat.init();
        ElytraSlotProviderAccessorFabric.getProvider().add(new ExtraCompatibilityProviderFabric());
    }
}
