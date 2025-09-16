package com.Minor2CCh.elytraslot_compat.neoforge.mixin;

import com.illusivesoulworks.elytraslot.ElytraSlotCommonMod;
import com.illusivesoulworks.elytraslot.common.IElytraProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(ElytraSlotCommonMod.class)
public interface ElytraSlotProviderAccessorNeoForge {
    @Accessor(value = "PROVIDERS", remap = false)
    static List<IElytraProvider> getProvider() {
        throw new AssertionError();
    }
}
