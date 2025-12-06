package com.Minor2CCh.elytraslot_compat.config;

import java.util.HashMap;

public class ECConfig {
    public HashMap<String, String> compatList = defaultCompatList();
    public Boolean enableArmorValue = true;
    public Boolean enableAnotherSlot = true;
    public void fillDefaults() {
        if(compatList == null){
            compatList = defaultCompatList();
        }
        if(enableArmorValue == null){
            enableArmorValue = true;
        }
        if(enableAnotherSlot == null){
            enableAnotherSlot = true;
        }
    }
    private HashMap<String, String> defaultCompatList(){
        HashMap<String, String> defaultCompat = new HashMap<>();
        defaultCompat.put("netherite_ext:netherite_elytra", "netherite_ext:textures/entity/netherite_elytra.png");
        defaultCompat.put("netherite_plated_elytra:netherite_elytra", "netherite_plated_elytra:textures/item/netherite_elytra_model.png");
        defaultCompat.put("reimaginingpotatoes:poisonous_polytra", "reimaginingpotatoes:textures/entity/poisonous_polytra.png");
        return defaultCompat;
    }
}
