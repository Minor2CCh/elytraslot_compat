package com.Minor2CCh.elytraslot_compat;

import com.illusivesoulworks.elytraslot.client.ElytraRenderResult;
import com.illusivesoulworks.elytraslot.common.IElytraProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ExtraCompatibilityProvider implements IElytraProvider {
    @Override
    public boolean matches(ItemStack stack) {
        return ElytraslotCompat.PLATFORM.checkTrueElytra(stack);
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
        return !ElytraslotCompat.ELYTRA_TEXTURE_MAP.getOrDefault(ElytraslotCompat.PLATFORM.getId(stack.getItem()).toString(), ResourceLocation.parse("")).equals(ResourceLocation.parse(""));
    }
}
