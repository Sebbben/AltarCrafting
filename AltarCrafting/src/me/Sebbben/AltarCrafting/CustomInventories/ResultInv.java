package me.Sebbben.AltarCrafting.CustomInventories;

import me.Sebbben.AltarCrafting.AltarActions.AddRecipe;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ResultInv extends CustomInventory{
    private final AddRecipe addRecipe;
    private ItemStack[] items;


    public ResultInv(AddRecipe addRecipe) {
        this.addRecipe = addRecipe;
    }

    @Override
    public Inventory getInventory() {
        if (items == null) {
            return super.getInventory();
        } else {
            Inventory inv = super.getInventory();
            inv.setContents(items);
            return inv;
        }
    }

    @Override
    public void onClose(InventoryCloseEvent e) {
        addRecipe.setResult(e.getInventory().getContents());
        items = e.getInventory().getContents().clone();
        e.getPlayer().sendMessage("Result Items Set!");

    }

    @Override
    protected int getSize() {
        return 27;
    }

    @Override
    protected String getName() {
        return "Result Input";
    }
}
