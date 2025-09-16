package com.Minor2CCh.elytraslot_compat.fabric;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import com.Minor2CCh.elytraslot_compat.ExtraCompatibilityProviderBridge;
import com.illusivesoulworks.elytraslot.client.ElytraRenderResult;
import com.illusivesoulworks.elytraslot.common.IElytraProvider;
import net.minecraft.world.item.ItemStack;

public class ExtraCompatibilityProviderFabric implements IElytraProvider {
    @Override
    public boolean matches(ItemStack stack) {
        return ExtraCompatibilityProviderBridge.matches(stack);
    }
    @Override
    public ElytraRenderResult getRender(ItemStack stack) {
        if(!hasCapeTexture(stack)){
            return null;
        }
        return new ElytraRenderResult(COLOR,
                ElytraslotCompat.ELYTRA_TEXTURE_MAP.get(ElytraslotCompat.PLATFORM.getId(stack.getItem()).toString()),
                stack.isEnchanted(), stack, hasCapeTexture(stack));
    }

    @Override
    public boolean hasCapeTexture(ItemStack stack) {
        return ExtraCompatibilityProviderBridge.hasCapeTexture(stack);
    }
}
