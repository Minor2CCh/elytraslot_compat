package com.Minor2CCh.elytraslot_compat.neoforge;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import com.Minor2CCh.elytraslot_compat.neoforge.mixin.ElytraSlotProviderAccessorNeoForge;
import com.Minor2CCh.elytraslot_compat.neoforge.platform.NeoForgePlatform;
import net.neoforged.fml.common.Mod;

@Mod(ElytraslotCompat.MOD_ID)
public final class ElytraslotCompatNeoForge {
    public ElytraslotCompatNeoForge() {
        // Run our common setup.
        ElytraslotCompat.PLATFORM = new NeoForgePlatform();
        ElytraslotCompat.init();
        ElytraSlotProviderAccessorNeoForge.getProvider().add(new ExtraCompatibilityProviderNeoForge());
    }
}
