package com.Minor2CCh.elytraslot_compat.neoforge;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import com.Minor2CCh.elytraslot_compat.config.ECConfigLoader;
import com.Minor2CCh.elytraslot_compat.neoforge.mixin.ElytraSlotProviderAccessorNeoForge;
import com.Minor2CCh.elytraslot_compat.neoforge.platform.NeoForgePlatform;
import com.Minor2CCh.elytraslot_compat.registry.ECItemTags;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.event.CurioAttributeModifierEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = ElytraslotCompat.MOD_ID)
@Mod(ElytraslotCompat.MOD_ID)
public final class ElytraslotCompatNeoForge {
    public ElytraslotCompatNeoForge() {
        // Run our common setup.
        ElytraslotCompat.PLATFORM = new NeoForgePlatform();
        ElytraslotCompat.init();
        ElytraSlotProviderAccessorNeoForge.getProvider().add(new ExtraCompatibilityProviderNeoForge());
    }
    @SubscribeEvent
    public static void onCurioAttributeModifiers(CurioAttributeModifierEvent event) {
        if(!ECConfigLoader.getConfig().enableArmorValue){
            return;
        }
        //System.out.println(event.getSlotContext().index());
        //System.out.println(event.getSlotContext().identifier());
        //System.out.println(event.getId());
        ItemStack stack = event.getItemStack();
        if (stack.is(ECItemTags.ELYTRA_SLOT_ALLOW)) {
            ItemAttributeModifiers modifiers = stack.getAttributeModifiers();
            List<ItemAttributeModifiers.Entry> entries = modifiers.modifiers();
            Map<AttributeModifier.Operation, Double> armor = createHashMap();
            Map<AttributeModifier.Operation, Double> toughness = createHashMap();
            Map<AttributeModifier.Operation, Double> knockbackResistance = createHashMap();
            for (ItemAttributeModifiers.Entry entry : entries) {
                if (entry.attribute().equals(Attributes.ARMOR)) {
                    AttributeModifier modifier = entry.modifier();
                    calcModifier(armor, modifier);
                } else if (entry.attribute().equals(Attributes.ARMOR_TOUGHNESS)) {
                    AttributeModifier modifier = entry.modifier();
                    calcModifier(toughness, modifier);
                } else if (entry.attribute().equals(Attributes.KNOCKBACK_RESISTANCE)) {
                    AttributeModifier modifier = entry.modifier();
                    calcModifier(knockbackResistance, modifier);
                }
            }
            armor.forEach((operation, value) -> event.addModifier(
                    Attributes.ARMOR,
                    new AttributeModifier(
                            getModifierId(Attributes.ARMOR, operation, event.getSlotContext()),
                            value,
                            operation
                    )
            ));
            toughness.forEach((operation, value) -> event.addModifier(
                    Attributes.ARMOR_TOUGHNESS,
                    new AttributeModifier(
                            getModifierId(Attributes.ARMOR_TOUGHNESS, operation, event.getSlotContext()),
                            value,
                            operation
                    )
            ));
            knockbackResistance.forEach((operation, value) -> event.addModifier(
                    Attributes.KNOCKBACK_RESISTANCE,
                    new AttributeModifier(
                            getModifierId(Attributes.KNOCKBACK_RESISTANCE, operation, event.getSlotContext()),
                            value,
                            operation
                    )
            ));

        }
    }
    private static Map<AttributeModifier.Operation, Double> createHashMap() {
        Map<AttributeModifier.Operation, Double> map = new HashMap<>();
        map.put(AttributeModifier.Operation.ADD_VALUE, 0.0);
        map.put(AttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.0);
        map.put(AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, 0.0);
        return map;
    }
    private static ResourceLocation getModifierId(Holder<Attribute> attributes, AttributeModifier.Operation operation, SlotContext slot) {
        // System.out.println("elytraslot_"+getAttributeString(attributes)+"_"+operation.toString().toLowerCase()+"_"+slot.getId());
        return ElytraslotCompat.of("elytraslot_"+getAttributeString(attributes)+"_"+operation.toString().toLowerCase()+"_"+ getSlotIdentifier(slot));
    }
    private static String getAttributeString(Holder<Attribute> attributes) {
        String id = attributes.getRegisteredName();
        int index = id.indexOf(':');

        if (index != -1 && index + 1 < id.length()) {
            return id.substring(index + 1);
        } else {
            return "";
        }
    }

    private static String getSlotIdentifier(SlotContext slot) {
        return getCompatIdentifier(slot.identifier())+"/"+slot.index();
    }
    //Accessoriesとの互換性維持のため
    private static String getCompatIdentifier(String id) {
        int index = id.indexOf(':');

        if (index != -1 && index + 1 < id.length()) {
            return id.substring(index + 1);
        } else {
            return id;
        }
    }
    private static void calcModifier(Map<AttributeModifier.Operation, Double> map, AttributeModifier modifier) {
        map.replace(modifier.operation(), map.getOrDefault(modifier.operation(), 0.0)+modifier.amount());
    }
}
