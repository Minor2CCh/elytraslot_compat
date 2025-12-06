package com.Minor2CCh.elytraslot_compat.fabric;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import com.Minor2CCh.elytraslot_compat.config.ECConfigLoader;
import com.Minor2CCh.elytraslot_compat.fabric.platform.FabricPlatform;
import com.Minor2CCh.elytraslot_compat.registry.ECItemTags;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.event.TrinketEquipCallback;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.*;

public final class ElytraslotCompatFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        ElytraslotCompat.PLATFORM = new FabricPlatform();
        ElytraslotCompat.init();
        if(ECConfigLoader.getConfig().enableArmorValue){
            //Equip,Unequipの2つのイベントで制御せず、必ずEquipのみで判別すること(TrinketsはUnequip→Equipの順だが、Accessories Layer適用中はEquip→Unequipの順で処理する)
            TrinketEquipCallback.EVENT.register((stack, slot, player) -> {
                ItemAttributeModifiers modifiers = stack.getComponents().get(DataComponents.ATTRIBUTE_MODIFIERS);
                // 無関係なスロットは処理の軽量化のためスキップ
                if(!(slot.inventory().getSlotType().getName().equals("cape") || slot.inventory().getSlotType().getName().equals("back"))
                 || !slot.inventory().getSlotType().getGroup().equals("chest")){
                    return;
                }
                /*
                if(!player.level().isClientSide()){
                    System.out.println("Equip:"+stack);
                    System.out.println("Equip:"+slot.inventory().getSlotType().getName());
                    System.out.println("Equip:"+slot.inventory().getSlotType().getGroup());
                    System.out.println("Equip:"+slot.index());
                }
                */
                AttributeInstance instanceArmor = player.getAttribute(Attributes.ARMOR);
                AttributeInstance instanceToughness = player.getAttribute(Attributes.ARMOR_TOUGHNESS);
                AttributeInstance instanceKnockbackResistance = player.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
                // アーマー値を外す
                if(instanceArmor != null && instanceArmor.hasModifier(getModifierId(Attributes.ARMOR, AttributeModifier.Operation.ADD_VALUE, slot))){
                    instanceArmor.removeModifier(getModifierId(Attributes.ARMOR, AttributeModifier.Operation.ADD_VALUE, slot));
                }
                if(instanceToughness != null && instanceToughness.hasModifier(getModifierId(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, slot))){
                    instanceToughness.removeModifier(getModifierId(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, slot));
                }
                if(instanceKnockbackResistance != null && instanceKnockbackResistance.hasModifier(getModifierId(Attributes.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_VALUE, slot))){
                    instanceKnockbackResistance.removeModifier(getModifierId(Attributes.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_VALUE, slot));
                }

                List<AttributeModifier.Operation> operationList = new ArrayList<>(Arrays.asList(AttributeModifier.Operation.ADD_VALUE, AttributeModifier.Operation.ADD_MULTIPLIED_BASE, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
                operationList.forEach((operation) -> {
                    if(instanceArmor != null && instanceArmor.hasModifier(getModifierId(Attributes.ARMOR, operation, slot))){
                        instanceArmor.removeModifier(getModifierId(Attributes.ARMOR, operation, slot));
                    }
                });
                operationList.forEach((operation) -> {
                    if(instanceToughness != null && instanceToughness.hasModifier(getModifierId(Attributes.ARMOR_TOUGHNESS, operation, slot))){
                        instanceToughness.removeModifier(getModifierId(Attributes.ARMOR_TOUGHNESS, operation, slot));
                    }
                });
                operationList.forEach((operation) -> {
                    if(instanceKnockbackResistance != null && instanceKnockbackResistance.hasModifier(getModifierId(Attributes.KNOCKBACK_RESISTANCE, operation, slot))){
                        instanceKnockbackResistance.removeModifier(getModifierId(Attributes.KNOCKBACK_RESISTANCE, operation, slot));
                    }
                });

                // アーマー値を付与する
                if(stack.is(ECItemTags.ELYTRA_SLOT_ALLOW) && stack.getItem() instanceof ArmorItem armorItem){
                    if(instanceArmor != null && !instanceArmor.hasModifier(getModifierId(Attributes.ARMOR, AttributeModifier.Operation.ADD_VALUE, slot))){
                        instanceArmor
                                .addTransientModifier(new AttributeModifier(
                                        getModifierId(Attributes.ARMOR, AttributeModifier.Operation.ADD_VALUE, slot),
                                        armorItem.getDefense(),
                                        AttributeModifier.Operation.ADD_VALUE
                                ));
                    }
                    if(instanceToughness != null && !instanceToughness.hasModifier(getModifierId(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, slot))){
                        instanceToughness
                                .addTransientModifier(new AttributeModifier(
                                        getModifierId(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADD_VALUE, slot),
                                        armorItem.getToughness(),
                                        AttributeModifier.Operation.ADD_VALUE
                                ));
                    }
                    if(instanceKnockbackResistance != null && !instanceKnockbackResistance.hasModifier(getModifierId(Attributes.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_VALUE, slot))){
                        instanceKnockbackResistance
                                .addTransientModifier(new AttributeModifier(
                                        getModifierId(Attributes.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_VALUE, slot),
                                        armorItem.getMaterial().value().knockbackResistance(),
                                        AttributeModifier.Operation.ADD_VALUE
                                ));
                    }
                } else if (modifiers != null && stack.is(ECItemTags.ELYTRA_SLOT_ALLOW)) {
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
                    armor.forEach((operation, value) -> {
                        if(instanceArmor != null && !instanceArmor.hasModifier(getModifierId(Attributes.ARMOR, operation, slot))){
                            instanceArmor
                                    .addTransientModifier(new AttributeModifier(
                                            getModifierId(Attributes.ARMOR, operation, slot),
                                            value,
                                            operation
                                    ));
                        }
                    });
                    toughness.forEach((operation, value) -> {
                        if(instanceToughness != null && !instanceToughness.hasModifier(getModifierId(Attributes.ARMOR_TOUGHNESS, operation, slot))){
                            instanceToughness
                                    .addTransientModifier(new AttributeModifier(
                                            getModifierId(Attributes.ARMOR_TOUGHNESS, operation, slot),
                                            value,
                                            operation
                                    ));
                        }
                    });
                    knockbackResistance.forEach((operation, value) -> {
                        if(instanceKnockbackResistance != null && !instanceKnockbackResistance.hasModifier(getModifierId(Attributes.KNOCKBACK_RESISTANCE, operation, slot))){
                            instanceKnockbackResistance
                                    .addTransientModifier(new AttributeModifier(
                                            getModifierId(Attributes.KNOCKBACK_RESISTANCE, operation, slot),
                                            value,
                                            operation
                                    ));
                        }
                    });
                }
            });
        }
    }
    private static Map<AttributeModifier.Operation, Double> createHashMap() {
        Map<AttributeModifier.Operation, Double> map = new HashMap<>();
        map.put(AttributeModifier.Operation.ADD_VALUE, 0.0);
        map.put(AttributeModifier.Operation.ADD_MULTIPLIED_BASE, 0.0);
        map.put(AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL, 0.0);
        return map;
    }
    private static ResourceLocation getModifierId(Holder<Attribute> attributes, AttributeModifier.Operation operation, SlotReference slot) {
        // System.out.println("elytraslot_"+getAttributeString(attributes)+"_"+operation.toString().toLowerCase()+"_"+slot.getId());
        return ElytraslotCompat.of("elytraslot_"+getAttributeString(attributes)+"_"+operation.toString().toLowerCase()+"_"+ getSlotIdentifier(slot));
    }
    private static String getSlotIdentifier(SlotReference slot) {
        return slot.inventory().getSlotType().getGroup()+"/"+slot.inventory().getSlotType().getName()+"/"+slot.index();
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
    private static void calcModifier(Map<AttributeModifier.Operation, Double> map, AttributeModifier modifier) {
        map.replace(modifier.operation(), map.getOrDefault(modifier.operation(), 0.0)+modifier.amount());
    }
}
