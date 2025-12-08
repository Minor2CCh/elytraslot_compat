package com.Minor2CCh.elytraslot_compat;

import com.Minor2CCh.elytraslot_compat.platform.Platform;
import com.illusivesoulworks.elytraslot.client.ElytraRenderResult;
import com.illusivesoulworks.elytraslot.common.IElytraProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class ExtraCompatibilityProvider implements IElytraProvider {
    @Override
    public boolean matches(ItemStack stack) {
        return Platform.INSTANCE.checkTrueElytra(stack);
    }
    @Override
    public ElytraRenderResult getRender(ItemStack stack) {
        if(!hasCapeTexture(stack)){
            return null;
        }
        return new ElytraRenderResult(COLOR,
                ElytraslotCompat.ELYTRA_TEXTURE_MAP.get(Platform.INSTANCE.getId(stack.getItem()).toString()),
                stack.isEnchanted(), stack, hasCapeTexture(stack));
    }

    @Override
    public boolean hasCapeTexture(ItemStack stack) {
        return !ElytraslotCompat.ELYTRA_TEXTURE_MAP.getOrDefault(Platform.INSTANCE.getId(stack.getItem()).toString(), new ResourceLocation("")).equals(new ResourceLocation(""));
    }
}
