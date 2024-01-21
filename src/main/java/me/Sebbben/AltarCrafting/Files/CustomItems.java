package me.Sebbben.AltarCrafting.Files;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomItems {

    private static ItemStack cornerSelectTool;
    private static ItemStack finishItem;
    private static ItemStack cancelItem;
    private static ItemStack materialsItem;
    private static ItemStack resultItem;

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
    
    public static ItemStack getFinishItem() {
        if (finishItem != null) return finishItem;

        finishItem = new ItemStack(Material.EMERALD,1);
        ItemMeta finishItemMeta = finishItem.getItemMeta();
        finishItemMeta.setDisplayName(ChatColor.GREEN + "Finish");
        finishItemMeta.setUnbreakable(true);
        finishItem.setItemMeta(finishItemMeta);

        customItems.add(finishItem);
        return finishItem;
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

    public static ItemStack getMaterialsItem() {
        if (materialsItem != null) return materialsItem;

        materialsItem = new ItemStack(Material.HOPPER,1);
        ItemMeta materialsItemMeta = materialsItem.getItemMeta();
        materialsItemMeta.setDisplayName(ChatColor.GOLD + "Materials");
        materialsItemMeta.setUnbreakable(true);
        materialsItem.setItemMeta(materialsItemMeta);

        customItems.add(materialsItem);
        return materialsItem;
    }

    public static ItemStack getResultItem() {
        if (resultItem != null) return resultItem;

        resultItem = new ItemStack(Material.DROPPER,1);
        ItemMeta resultItemMeta = resultItem.getItemMeta();
        resultItemMeta.setDisplayName(ChatColor.GOLD + "Result");
        resultItemMeta.setUnbreakable(true);
        resultItem.setItemMeta(resultItemMeta);

        customItems.add(resultItem);
        return resultItem;
    }

}
