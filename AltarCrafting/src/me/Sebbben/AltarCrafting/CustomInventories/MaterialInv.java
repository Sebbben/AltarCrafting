package me.Sebbben.AltarCrafting.CustomInventories;

import me.Sebbben.AltarCrafting.AltarActions.AddRecipe;
import me.Sebbben.AltarCrafting.Files.AltarRecipe;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaterialInv extends CustomInventory{
    private final AddRecipe addRecipe;

    private ItemStack[] items;

    public MaterialInv(AddRecipe addRecipe) {
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
    public Inventory getInventory(List<AltarRecipe> recipes) {
        return null;
    }

    @Override
    public void onClose(InventoryCloseEvent e) {
        List<ItemStack> i = new ArrayList<>(Arrays.stream(e.getInventory().getContents()).toList());
        while (i.remove(null)){}
        addRecipe.setMaterials(i);
        e.getPlayer().sendMessage("Crafting Materials Set!");
        items = e.getInventory().getContents().clone();
    }

    @Override
    protected int getSize() {
        return 27;
    }

    @Override
    protected String getName() {
        return "Material Input";
    }
}
