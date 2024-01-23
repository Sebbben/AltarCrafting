package me.Sebbben.AltarCrafting.CustomItems;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.format.TextColor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AltarSelectionTools {
    private static ItemStack cornerSelectTool;
    private static ItemStack finishItem;
    private static ItemStack cancelItem;
    private static ItemStack materialsItem;
    private static ItemStack resultItem;

    public static ItemStack getCornerSelectTool() {
        if (cornerSelectTool != null) return cornerSelectTool;

        cornerSelectTool = new ItemStack(Material.WOODEN_SHOVEL,1);
        ItemMeta corner1Meta = cornerSelectTool.getItemMeta();
        corner1Meta.displayName(Component.text( "Corner Select Tool", TextColor.color(0,255,255)));
        corner1Meta.setUnbreakable(true);
        cornerSelectTool.setItemMeta(corner1Meta);

        return cornerSelectTool;
    }

    public static ItemStack getFinishItem() {
        if (finishItem != null) return finishItem;

        finishItem = new ItemStack(Material.EMERALD,1);
        ItemMeta finishItemMeta = finishItem.getItemMeta();
        finishItemMeta.displayName(Component.text("Finish",TextColor.color(0,255,0)));
        finishItemMeta.setUnbreakable(true);
        finishItem.setItemMeta(finishItemMeta);

        return finishItem;
    }

    public static ItemStack getCancelItem() {
        if (cancelItem != null) return cancelItem;

        cancelItem = new ItemStack(Material.BARRIER,1);
        ItemMeta cancelItemMeta = cancelItem.getItemMeta();
        cancelItemMeta.displayName(Component.text("Cancel", TextColor.color(255,0,0)));
        cancelItemMeta.setUnbreakable(true);
        cancelItem.setItemMeta(cancelItemMeta);

        return cancelItem;
    }

}
