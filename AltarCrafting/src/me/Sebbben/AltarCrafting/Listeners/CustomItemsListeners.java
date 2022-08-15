package me.Sebbben.AltarCrafting.Listeners;

import me.Sebbben.AltarCrafting.AltarFiles.AltarHandler;
import me.Sebbben.AltarCrafting.Files.CustomItems;
import me.Sebbben.AltarCrafting.RecipeFiles.RecipeHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CustomItemsListeners implements Listener {
    public void onSelectCorner(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();

        if (mainHand.isSimilar(CustomItems.getCancelItem())) {
            e.getPlayer().sendMessage("Canceled!");
            if (AltarHandler.isCreatingAltar()) {
                AltarHandler.cancelAltar();
            } else if (RecipeHandler.isCreatingRecipe()) {
                RecipeHandler.cancelRecipe();
            }
            Inventory inv = e.getPlayer().getInventory();
            for (int i = 0; i < 9; i++) {
                inv.setItem(i, null);
            }
        }
    }
}
