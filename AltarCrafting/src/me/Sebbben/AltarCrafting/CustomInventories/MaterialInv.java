package me.Sebbben.AltarCrafting.CustomInventories;

import me.Sebbben.AltarCrafting.AltarActions.AddRecipe;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class MaterialInv extends CustomInventory{
    private final AddRecipe addRecipe;
    public MaterialInv(AddRecipe addRecipe) {
        this.addRecipe = addRecipe;
    }

    @Override
    public void onClose(InventoryCloseEvent e) {
        addRecipe.setMaterials(e.getInventory().getContents());
        e.getPlayer().sendMessage("You closed me! (material)");
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
