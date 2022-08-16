package me.Sebbben.AltarCrafting.CustomInventories;

import me.Sebbben.AltarCrafting.AltarActions.AddRecipe;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class ResultInv extends CustomInventory{
    private final AddRecipe addRecipe;

    public ResultInv(AddRecipe addRecipe) {
        this.addRecipe = addRecipe;
    }

    @Override
    public void onClose(InventoryCloseEvent e) {
        addRecipe.setResult(e.getInventory().getContents());

        e.getPlayer().sendMessage("You closed me! (result)");

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
