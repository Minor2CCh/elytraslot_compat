package com.Minor2CCh.elytraslot_compat.mixin;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import com.Minor2CCh.elytraslot_compat.registry.ECItemTags;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public class TagItemMixin {
    @ModifyReturnValue(method = "is(Lnet/minecraft/tags/TagKey;)Z", at = @At("RETURN"))
    private boolean compatingTag(boolean original, @Local(argsOnly = true) TagKey<Item> tagKey){
        ItemStack stack = ((ItemStack) (Object) this);
        if(original){
            return original;
        }
        if(tagKey.equals(ECItemTags.ELYTRA_SLOT_ALLOW)
             || ElytraslotCompat.PLATFORM.isModLoaded("trinkets") && tagKey.equals(ECItemTags.TRINKETS_SLOT_ALLOW)
             || ElytraslotCompat.PLATFORM.isModLoaded("trinkets") && tagKey.equals(ECItemTags.TRINKETS_SLOT_BACK_ALLOW)
             || ElytraslotCompat.PLATFORM.isModLoaded("curios") && tagKey.equals(ECItemTags.CURIOS_SLOT_ALLOW)
                || ElytraslotCompat.PLATFORM.isModLoaded("accessories_compat_layer") &&
                (ElytraslotCompat.PLATFORM.isModLoaded("trinkets") && tagKey.equals(ECItemTags.ACCESSORIES_COMPAT_ALL_TRINKETS)
                || ElytraslotCompat.PLATFORM.isModLoaded("curios") && tagKey.equals(ECItemTags.ACCESSORIES_COMPAT_ALL_CURIOS))
                || (ElytraslotCompat.PLATFORM.isModLoaded("accessories") &&
                  elytraslot_compat$isCompatAccessories() &&
                (tagKey.equals(ECItemTags.ACCESSORIES_ALLOW_BACK) || tagKey.equals(ECItemTags.ACCESSORIES_ALLOW_CAPE))
                || ElytraslotCompat.PLATFORM.isModLoaded("tclayer") && tagKey.equals(ECItemTags.TRINKETS_COMPAT_ALL_TRINKETS)
                || ElytraslotCompat.PLATFORM.isModLoaded("cclayer") && tagKey.equals(ECItemTags.CURIOS_COMPAT_ALL_CURIOS))




        ){
            if(ElytraslotCompat.PLATFORM.checkTrueElytra(stack)){
                return true;
            }
        }


        return original;
    }
    @Unique
    private static boolean elytraslot_compat$isCompatAccessories(){
        if(!ElytraslotCompat.PLATFORM.isModLoaded("accessories")){
            return false;
        }
        return ElytraslotCompat.PLATFORM.isModLoaded("cclayer") || ElytraslotCompat.PLATFORM.isModLoaded("tclayer") || ElytraslotCompat.PLATFORM.isModLoaded("accessories_compat_layer");
    }

}
