package me.Sebbben.AltarCrafting.customItems;

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
        corner1Meta.setDisplayName("Corner Select Tool");
        corner1Meta.setUnbreakable(true);
        cornerSelectTool.setItemMeta(corner1Meta);

        return cornerSelectTool;
    }

    public static ItemStack getFinishItem() {
        if (finishItem != null) return finishItem;

        finishItem = new ItemStack(Material.EMERALD,1);
        ItemMeta finishItemMeta = finishItem.getItemMeta();
        finishItemMeta.setDisplayName("Finish");
        finishItemMeta.setUnbreakable(true);
        finishItem.setItemMeta(finishItemMeta);

        return finishItem;
    }

    public static ItemStack getCancelItem() {
        if (cancelItem != null) return cancelItem;

        cancelItem = new ItemStack(Material.BARRIER,1);
        ItemMeta cancelItemMeta = cancelItem.getItemMeta();
        cancelItemMeta.setDisplayName("Cancel");
        cancelItemMeta.setUnbreakable(true);
        cancelItem.setItemMeta(cancelItemMeta);

        return cancelItem;
    }

}
