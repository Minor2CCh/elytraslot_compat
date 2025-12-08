# About<br>
This mod allows users to add mods that support the [Elytra Slot](https://modrinth.com/mod/elytra-slot). By its nature, simply installing this mod does nothing; you must customize it via the configuration.<br>

# Use<br>
When you launch the game with this MOD installed, a file named “elytraslot_compat.json” will be added to the “config” directory. Opening that file will reveal the following contents:<br>
```
{
  "compatList": {
    "netherite_ext:netherite_elytra": "netherite_ext:textures/entity/netherite_elytra.png",
    "netherite_plated_elytra:netherite_elytra": "netherite_plated_elytra:textures/item/netherite_elytra_model.png",
    "reimaginingpotatoes:poisonous_polytra": "reimaginingpotatoes:textures/entity/poisonous_polytra.png"
  },
  "enableArmorValue": true,
  "enableAnotherSlot": true
  }
}
```
By entering the Elytra item ID you wish to map on the left side and the target Elytra's texture ID on the right side (leave blank if no texture exists), you can map the target Elytra to the Elytra Slot.<br>

By default, it supports the Netherite Elytra from the [Netherite Extension](https://modrinth.com/mod/netheriteextension) and the Poisonous Polytra from the [Poisonous Potato Update](https://modrinth.com/mod/reimagining-potatoes).

# Other<br>
## Back Slot<br>
Only in Fabric will allow you to equip elytra in the back slot. (After 1.1.0) <br>
If enableAnotherSlot is false, you cannot use one of the slots. (After 1.2.0) <br>
## Cape Slot<br>
Only in NeoForge can you simultaneously install [Accessories](https://modrinth.com/mod/accessories),  and the [Accessories Compatibility Layer](https://modrinth.com/mod/accessories-compat-layer) to equip Elytra compatible with the Elytra Slot in the Accessories's Cape slot. <br>
If enableAnotherSlot is false, you cannot use one of the slots. (After 1.2.0) <br>
In Minecraft version 1.20.1, it can be added as-is.<br>
## Custom Elytra Function<br>
Even if the elytra has custom functionality, it will still function as a regular elytra. Therefore, please note that even if the elytra has custom features, you will not be able to benefit from them.<br>
## Elytra Armor Values<br>
Elytra with armor values may not display their equipped texture, regardless of whether a texture is specified.<br>
Starting with version 1.1.0, setting “enableArmorValue” to true (Default: true) now applies the durability of elytra equipped in the Cape or Back slots.<br>
In Minecraft version 1.20.1, armor values are not applied regardless of settings.
