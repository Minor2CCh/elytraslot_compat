package com.Minor2CCh.elytraslot_compat.mixin;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import com.Minor2CCh.elytraslot_compat.config.ECConfigLoader;
import com.Minor2CCh.elytraslot_compat.platform.Platform;
import com.Minor2CCh.elytraslot_compat.registry.ECItemTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagLoader;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
@Mixin(TagLoader.class)
public class TagLoaderMixin {
    @Shadow
    @Final
    private String directory;
    @Inject(
            method = "build(Ljava/util/Map;)Ljava/util/Map;",
            at = @At("HEAD")
    )
    private <T> void beforeBuild(Map<ResourceLocation, List<TagLoader.EntryWithSource>> map,
                                 CallbackInfoReturnable<Map<ResourceLocation, Collection<T>>> cir) {

        if (!Registries.tagsDirPath(Registries.ITEM).equals(directory)) return;

        ResourceLocation target = ECItemTags.ELYTRA_SLOT_ALLOW.location();

        List<TagLoader.EntryWithSource> entries = map.get(target);
        if (entries != null) {
            for (ResourceLocation itemId : ElytraslotCompat.ELYTRA_TEXTURE_MAP.keySet().stream().map(ResourceLocation::parse).toList()) {

                Item item = BuiltInRegistries.ITEM.get(itemId);
                ElytraslotCompat.LOGGER.info("id:{}", itemId);
                ElytraslotCompat.LOGGER.info("item:{}", item);
                if (item == Items.AIR) continue; // require=false

                // EntryWithSource の追加（TagLoader が自動で Holder に変換する）
                TagEntry entry = TagEntry.element(itemId); // JSON 相当のアイテム指定
                TagLoader.EntryWithSource ews = new TagLoader.EntryWithSource(entry, "elytraslot_compat:inject_elytra");
                entries.add(ews);
            }
        }
        if(ECConfigLoader.getConfig().enableAnotherSlot){
            if(ElytraslotCompat.PLATFORM.getModLoader() == Platform.ModLoader.FABRIC){
                ResourceLocation targetBack = ECItemTags.TRINKETS_SLOT_BACK_ALLOW.location();
                List<TagLoader.EntryWithSource> entriesBackSlot = map.get(targetBack);
                if(entriesBackSlot != null){
                    TagEntry entry = TagEntry.tag(target);
                    TagLoader.EntryWithSource ews = new TagLoader.EntryWithSource(entry, "elytraslot_compat:inject_backslot");
                    entriesBackSlot.add(ews);
                }
            }else if(ElytraslotCompat.PLATFORM.getModLoader() == Platform.ModLoader.NEOFORGE){
                if(ElytraslotCompat.PLATFORM.isModLoaded("accessories") && ElytraslotCompat.PLATFORM.isModLoaded("accessories_compat_layer")){
                    ResourceLocation targetCape = ECItemTags.ACCESSORIES_ALLOW_CAPE.location();
                    List<TagLoader.EntryWithSource> entriesBackSlot = map.get(targetCape);
                    if(entriesBackSlot != null){
                        TagEntry entry = TagEntry.tag(target);
                        TagLoader.EntryWithSource ews = new TagLoader.EntryWithSource(entry, "elytraslot_compat:inject_capeslot");
                        entriesBackSlot.add(ews);
                    }
                }
            }
        }
    }
}
