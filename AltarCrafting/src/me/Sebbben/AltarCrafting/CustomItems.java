package me.Sebbben.AltarCrafting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomItems {

    private static ItemStack cornerSelectTool;
    private static ItemStack finishSelectItem;

    public static ItemStack getCornerSelectTool() {
        if (cornerSelectTool != null) return cornerSelectTool;

        cornerSelectTool = new ItemStack(Material.WOODEN_SHOVEL,1);
        ItemMeta corner1Meta = cornerSelectTool.getItemMeta();
        corner1Meta.setDisplayName(ChatColor.GOLD + "Corner Select Tool");
        corner1Meta.setUnbreakable(true);
        cornerSelectTool.setItemMeta(corner1Meta);

        return cornerSelectTool;
    }
    
    public static ItemStack getFinishSelectItem() {
        if (finishSelectItem != null) return finishSelectItem;

        finishSelectItem = new ItemStack(Material.EMERALD,1);
        ItemMeta finishSelectMeta = finishSelectItem.getItemMeta();
        finishSelectMeta.setDisplayName(ChatColor.GREEN + "Finish select");
        finishSelectMeta.setUnbreakable(true);
        finishSelectItem.setItemMeta(finishSelectMeta);

        return finishSelectItem;
    }
    

}
