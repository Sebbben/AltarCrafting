package me.Sebbben.AltarCrafting;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomItems {

    private static ItemStack cornerSelectTool;
    private static ItemStack finishSelectItem;
    private static ItemStack cancelItem;

    private static List<ItemStack> customItems = new ArrayList<>();
    public static ItemStack[] getAllItems() {
        ItemStack[] items = new ItemStack[customItems.size()];
        for (int i = 0; i < customItems.size(); i++) items[i] = customItems.get(i);
        return items;
    }

    public static ItemStack getCornerSelectTool() {
        if (cornerSelectTool != null) return cornerSelectTool;

        cornerSelectTool = new ItemStack(Material.WOODEN_SHOVEL,1);
        ItemMeta corner1Meta = cornerSelectTool.getItemMeta();
        corner1Meta.setDisplayName(ChatColor.GOLD + "Corner Select Tool");
        corner1Meta.setUnbreakable(true);
        cornerSelectTool.setItemMeta(corner1Meta);

        customItems.add(cornerSelectTool);
        return cornerSelectTool;
    }
    
    public static ItemStack getFinishSelectItem() {
        if (finishSelectItem != null) return finishSelectItem;

        finishSelectItem = new ItemStack(Material.EMERALD,1);
        ItemMeta finishSelectMeta = finishSelectItem.getItemMeta();
        finishSelectMeta.setDisplayName(ChatColor.GREEN + "Finish select");
        finishSelectMeta.setUnbreakable(true);
        finishSelectItem.setItemMeta(finishSelectMeta);

        customItems.add(finishSelectItem);
        return finishSelectItem;
    }

    public static ItemStack getCancelItem() {
        if (cancelItem != null) return cancelItem;

        cancelItem = new ItemStack(Material.BARRIER,1);
        ItemMeta cancelItemMeta = cancelItem.getItemMeta();
        cancelItemMeta.setDisplayName(ChatColor.RED + "Cancel");
        cancelItemMeta.setUnbreakable(true);
        cancelItem.setItemMeta(cancelItemMeta);

        customItems.add(cancelItem);
        return cancelItem;
    }

}
