package me.Sebbben.AltarCrafting;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItems {

    private static ItemStack corner1Select;
    private static ItemStack corner2Select;

    public static ItemStack getCorner1Select() {
        if (corner1Select != null) return corner1Select;

        corner1Select = new ItemStack(Material.WOODEN_SHOVEL,1);
        ItemMeta corner1Meta = corner1Select.getItemMeta();
        corner1Meta.setDisplayName("Select Corner 1");
        corner1Meta.setUnbreakable(true);
        corner1Select.setItemMeta(corner1Meta);

        return corner1Select;
    }

    public static ItemStack getCorner2Select() {
        if (corner2Select != null) return corner2Select;

        corner2Select = new ItemStack(Material.WOODEN_SHOVEL,1);
        ItemMeta corner2Meta = corner2Select.getItemMeta();
        corner2Meta.setDisplayName("Select Corner 2");
        corner2Meta.setUnbreakable(true);
        corner2Select.setItemMeta(corner2Meta);

        return corner2Select;
    }


}
