package me.Sebbben.AltarCrafting.CustomInventories;

import me.Sebbben.AltarCrafting.Files.AltarRecipe;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RecipeListInv extends CustomInventory {

    @Override
    public Inventory getInventory(List<AltarRecipe> recipes) {
        Inventory inv = Bukkit.createInventory(this, getSize(), getName());
        for (AltarRecipe recipe : recipes) {
            ItemStack mainResult = recipe.getResult().get(0);
            for (ItemStack item : recipe.getResult()) {
                if (item == null) continue;
                if (mainResult.getAmount() < item.getAmount()) {
                    mainResult = item;
                }
            }
            inv.addItem(mainResult);
        }
        return inv;
    }

    @Override
    public void onClose(InventoryCloseEvent e) {
        return;
    }

    @Override
    protected int getSize() {
        return 27;
    }

    @Override
    protected String getName() {
        return "Recipe List";
    }
}
