package com.Minor2CCh.elytraslot_compat.config;

import java.util.LinkedHashMap;

public class ECConfig {
    public LinkedHashMap<String, String> compatList = defaultCompatList();
    public Boolean enableArmorValue = false;
    public Boolean enableAnotherSlot = true;
    public void fillDefaults() {
        if(compatList == null){
            compatList = defaultCompatList();
        }
        if(enableArmorValue == null){
            enableArmorValue = false;
        }
        if(enableAnotherSlot == null){
            enableAnotherSlot = true;
        }
    }
    private LinkedHashMap<String, String> defaultCompatList(){
        LinkedHashMap<String, String> defaultCompat = new LinkedHashMap<>();
        defaultCompat.put("netherite_ext:netherite_elytra", "netherite_ext:textures/entity/netherite_elytra.png");
        defaultCompat.put("netherite_plated_elytra:netherite_elytra", "netherite_plated_elytra:textures/item/netherite_elytra_model.png");
        defaultCompat.put("reimaginingpotatoes:poisonous_polytra", "reimaginingpotatoes:textures/entity/poisonous_polytra.png");
        defaultCompat.put("tconstruct:slime_chestplate", "tconstruct:textures/tinker_armor/slime/wings_tconstruct_enderslime.png");
        return defaultCompat;
    }
}
