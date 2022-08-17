package me.Sebbben.AltarCrafting.AltarActions;

import me.Sebbben.AltarCrafting.AltarHandler;
import me.Sebbben.AltarCrafting.Listeners.RecipeConfigurationListener;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.Files.AltarRecipe;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AddRecipe implements AltarAction{
    private final RecipeConfigurationListener recipeConfigurationListener = new RecipeConfigurationListener(this);
    private final AltarHandler altarHandler;
    private final AltarRecipe recipe;
    private final String altarName;
    public AddRecipe(AltarHandler altarHandler, String altarName) {

        this.altarHandler = altarHandler;
        this.altarName = altarName;
        recipe = new AltarRecipe();
        Main.getInstance().getServer().getPluginManager().registerEvents(recipeConfigurationListener, Main.getInstance());

    }

    public void setMaterials(List<ItemStack> materials) {
        recipe.setMaterials(materials);
    }

    public void setResult(List<ItemStack> result) {
        recipe.setResult(result);
    }

    @Override
    public void finish(Player player) {
        altarHandler.addRecipe(altarName, recipe);
        PlayerInteractEvent.getHandlerList().unregister(recipeConfigurationListener);
        InventoryCloseEvent.getHandlerList().unregister(recipeConfigurationListener);

        player.sendMessage("Recipe added!");

        Inventory inv = player.getInventory();

        for (int i=0;i<9;i++) {
            inv.setItem(i, null);
        }

    }

    @Override
    public void cancel(Player player) {
        Inventory inv = player.getInventory();

        for (int i=0;i<9;i++) {
            inv.setItem(i, null);
        }
    }


}
