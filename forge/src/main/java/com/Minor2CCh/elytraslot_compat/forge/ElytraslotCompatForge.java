package com.Minor2CCh.elytraslot_compat.forge;

import com.Minor2CCh.elytraslot_compat.ElytraslotCompat;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ElytraslotCompat.MOD_ID)
@Mod(ElytraslotCompat.MOD_ID)
public final class ElytraslotCompatForge {
    public ElytraslotCompatForge() {
        // Run our common setup.
        ElytraslotCompat.init();
    }
    /*
    @SubscribeEvent
    public static void onCurioAttributeModifiers(CurioAttributeModifierEvent event) {
        if(!ECConfigLoader.getConfig().enableArmorValue || true){
            return;
        }
        //System.out.println(event.getSlotContext().index());
        //System.out.println(event.getSlotContext().identifier());
        //System.out.println(event.getId());
        ItemStack stack = event.getItemStack();
        if (stack.is(ECItemTags.ELYTRA_SLOT_ALLOW)) {
            Multimap<Attribute, AttributeModifier> modifiers = stack.getAttributeModifiers(EquipmentSlot.CHEST);
            Map<AttributeModifier.Operation, Double> armor = createHashMap();
            Map<AttributeModifier.Operation, Double> toughness = createHashMap();
            Map<AttributeModifier.Operation, Double> knockbackResistance = createHashMap();
            modifiers.forEach((attribute, modifier) -> {
                if (attribute.equals(Attributes.ARMOR)) {
                    calcModifier(armor, modifier);
                } else if (attribute.equals(Attributes.ARMOR_TOUGHNESS)) {
                    calcModifier(toughness, modifier);
                } else if (attribute.equals(Attributes.KNOCKBACK_RESISTANCE)) {
                    calcModifier(knockbackResistance, modifier);
                }

            });
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
    @SubscribeEvent
    public static void onCurioUnequipAttributeModifiers(CurioUnequipEvent event){
        if(!ECConfigLoader.getConfig().enableArmorValue || event.getEntity().level().isClientSide){
            return;
        }
        LivingEntity entity = event.getEntity();
        SlotContext slot = event.getSlotContext();
        AttributeInstance instanceArmor = entity.getAttribute(Attributes.ARMOR);
        AttributeInstance instanceToughness = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
        AttributeInstance instanceKnockbackResistance = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
        for(AttributeModifier.Operation operation : AttributeModifier.Operation.values()){
            if(instanceArmor != null && instanceArmor.getModifier(getModifierUUID(Attributes.ARMOR, operation, slot)) != null){
                instanceArmor.removeModifier(getModifierUUID(Attributes.ARMOR, operation, slot));
            }
            if(instanceToughness != null && instanceToughness.getModifier(getModifierUUID(Attributes.ARMOR_TOUGHNESS, operation, slot)) != null){
                instanceToughness.removeModifier(getModifierUUID(Attributes.ARMOR_TOUGHNESS, operation, slot));
            }
            if(instanceKnockbackResistance != null && instanceKnockbackResistance.getModifier(getModifierUUID(Attributes.KNOCKBACK_RESISTANCE, operation, slot)) != null){
                instanceKnockbackResistance.removeModifier(getModifierUUID(Attributes.KNOCKBACK_RESISTANCE, operation, slot));
            }

        }

    }
    @SubscribeEvent
    public static void onCurioEquipAttributeModifiers(CurioEquipEvent event){
        if(!ECConfigLoader.getConfig().enableArmorValue || event.getEntity().level().isClientSide){
            return;
        }
        LivingEntity entity = event.getEntity();
        SlotContext slot = event.getSlotContext();
        ItemStack stack = event.getStack();
        AttributeInstance instanceArmor = entity.getAttribute(Attributes.ARMOR);
        AttributeInstance instanceToughness = entity.getAttribute(Attributes.ARMOR_TOUGHNESS);
        AttributeInstance instanceKnockbackResistance = entity.getAttribute(Attributes.KNOCKBACK_RESISTANCE);
        Multimap<Attribute, AttributeModifier> modifiers = stack.getAttributeModifiers(EquipmentSlot.CHEST);

        if(stack.is(ECItemTags.ELYTRA_SLOT_ALLOW) && stack.getItem() instanceof ArmorItem armorItem){
            if(instanceArmor != null && instanceArmor.getModifier(getModifierUUID(Attributes.ARMOR, AttributeModifier.Operation.ADDITION, slot)) == null){
                instanceArmor
                        .addTransientModifier(new AttributeModifier(
                                getModifierId(Attributes.ARMOR, AttributeModifier.Operation.ADDITION, slot),
                                armorItem.getDefense(),
                                AttributeModifier.Operation.ADDITION
                        ));
            }
            if(instanceToughness != null && instanceToughness.getModifier(getModifierUUID(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADDITION, slot)) == null){
                instanceToughness
                        .addTransientModifier(new AttributeModifier(
                                getModifierId(Attributes.ARMOR_TOUGHNESS, AttributeModifier.Operation.ADDITION, slot),
                                armorItem.getToughness(),
                                AttributeModifier.Operation.ADDITION
                        ));
            }
            if(instanceKnockbackResistance != null && instanceKnockbackResistance.getModifier(getModifierUUID(Attributes.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADDITION, slot)) == null){
                instanceKnockbackResistance
                        .addTransientModifier(new AttributeModifier(
                                getModifierId(Attributes.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADDITION, slot),
                                armorItem.getMaterial().getKnockbackResistance(),
                                AttributeModifier.Operation.ADDITION
                        ));
            }
        } else if (stack.is(ECItemTags.ELYTRA_SLOT_ALLOW)) {
            modifiers.forEach((attribute, modifier) -> {
                if(attribute.equals(Attributes.ARMOR)){
                    if(instanceArmor != null && instanceArmor.getModifier(getModifierUUID(Attributes.ARMOR, modifier.getOperation(), slot)) == null){
                        instanceArmor
                                .addTransientModifier(new AttributeModifier(
                                        getModifierId(Attributes.ARMOR, modifier.getOperation(), slot),
                                        modifier.getAmount(),
                                        modifier.getOperation()
                                ));
                    }
                }

                if(attribute.equals(Attributes.ARMOR_TOUGHNESS)){
                    if(instanceToughness != null && instanceToughness.getModifier(getModifierUUID(Attributes.ARMOR_TOUGHNESS, modifier.getOperation(), slot)) == null){
                        instanceToughness
                                .addTransientModifier(new AttributeModifier(
                                        getModifierId(Attributes.ARMOR_TOUGHNESS, modifier.getOperation(), slot),
                                        modifier.getAmount(),
                                        modifier.getOperation()
                                ));
                    }
                }
                if(attribute.equals(Attributes.KNOCKBACK_RESISTANCE)){
                    if(instanceKnockbackResistance != null && instanceKnockbackResistance.getModifier(getModifierUUID(Attributes.KNOCKBACK_RESISTANCE, modifier.getOperation(), slot)) == null){
                        instanceKnockbackResistance
                                .addTransientModifier(new AttributeModifier(
                                        getModifierId(Attributes.KNOCKBACK_RESISTANCE, modifier.getOperation(), slot),
                                        modifier.getAmount(),
                                        modifier.getOperation()
                                ));
                    }
                }

            });
        }

    }
    private static Map<AttributeModifier.Operation, Double> createHashMap() {
        Map<AttributeModifier.Operation, Double> map = new HashMap<>();
        map.put(AttributeModifier.Operation.ADDITION, 0.0);
        map.put(AttributeModifier.Operation.MULTIPLY_BASE, 0.0);
        map.put(AttributeModifier.Operation.MULTIPLY_TOTAL, 0.0);
        return map;
    }
    private static String getModifierId(Attribute attributes, AttributeModifier.Operation operation, SlotContext slot) {
        // System.out.println("elytraslot_"+getAttributeString(attributes)+"_"+operation.toString().toLowerCase()+"_"+slot.getId());
        return UUID.nameUUIDFromBytes((ElytraslotCompat.MOD_ID+getAttributeString(attributes)+operation.toString().toLowerCase()+getSlotIdentifier(slot)).getBytes()).toString();
    }
    private static UUID getModifierUUID(Attribute attributes, AttributeModifier.Operation operation, SlotContext slot) {
        // System.out.println("elytraslot_"+getAttributeString(attributes)+"_"+operation.toString().toLowerCase()+"_"+slot.getId());
        return UUID.nameUUIDFromBytes((ElytraslotCompat.MOD_ID+getAttributeString(attributes)+operation.toString().toLowerCase()+getSlotIdentifier(slot)).getBytes());

    }
    private static String getAttributeString(Attribute attributes) {
        String id = attributes.getDescriptionId();
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
        map.replace(modifier.getOperation(), map.getOrDefault(modifier.getOperation(), 0.0)+modifier.getAmount());
    }*/
}
