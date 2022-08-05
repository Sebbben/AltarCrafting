package me.Sebbben.AltarCrafting;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItems {

    private static ItemStack cornerSelectTool;

    public static ItemStack getCornerSelectTool() {
        if (cornerSelectTool != null) return cornerSelectTool;

        cornerSelectTool = new ItemStack(Material.WOODEN_SHOVEL,1);
        ItemMeta corner1Meta = cornerSelectTool.getItemMeta();
        corner1Meta.setDisplayName("Corner Select Tool");
        corner1Meta.setUnbreakable(true);
        cornerSelectTool.setItemMeta(corner1Meta);

        return cornerSelectTool;
    }

}
