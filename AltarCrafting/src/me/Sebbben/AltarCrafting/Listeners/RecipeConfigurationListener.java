package me.Sebbben.AltarCrafting.Listeners;

import me.Sebbben.AltarCrafting.Files.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RecipeConfigurationListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            e.setCancelled(true);
            if (mainHand.isSimilar(CustomItems.getMaterialsItem())) {

                Inventory materialsInv = Bukkit.createInventory(e.getPlayer(), 27, "Crafting Materials");

                e.getPlayer().openInventory(materialsInv);
            } else if (mainHand.isSimilar(CustomItems.getResultItem())) {

                Inventory resultInv = Bukkit.createInventory(e.getPlayer(), 27, "Crafting Result");

                e.getPlayer().openInventory(resultInv);
            }
        }
    }
}
