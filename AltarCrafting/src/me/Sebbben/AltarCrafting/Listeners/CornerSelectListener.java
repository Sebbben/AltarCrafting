package me.Sebbben.AltarCrafting.Listeners;

import me.Sebbben.AltarCrafting.AltarFiles.AltarHandler;
import me.Sebbben.AltarCrafting.Files.CustomItems;
import me.Sebbben.AltarCrafting.RecipeFiles.RecipeHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CornerSelectListener implements Listener {

    @EventHandler
    public void onSelectCorner(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();
        // ----- RIGHT CLICK BLOCK -------
        if  (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (mainHand.isSimilar(CustomItems.getCornerSelectTool())) {
                AltarHandler.setCorner(e.getClickedBlock().getLocation(), e.getPlayer());
            }
        }

        // ----- THE REST -------
        if (mainHand.isSimilar(CustomItems.getFinishItem())) {
            if (AltarHandler.isCreatingAltar()) {
                if (AltarHandler.hasTwoCorners()){
                    e.getPlayer().sendMessage("Finished new Altar!");
                    AltarHandler.finishAltar();
                }
            } else if (RecipeHandler.isCreatingRecipe()) {
                if (RecipeHandler.recipeIsComplete()) {
                    e.getPlayer().sendMessage("Recipe finished and added!");
                    RecipeHandler.finishRecipe();
                }
            }


            Inventory inv = e.getPlayer().getInventory();

            for (int i=0;i<9;i++) {
                inv.setItem(i, null);
            }

        }

    }
}
