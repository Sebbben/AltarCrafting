package me.Sebbben.AltarCrafting.Listeners;

import me.Sebbben.AltarCrafting.AltarActions.AddRecipe;
import me.Sebbben.AltarCrafting.CustomInventories.CustomInventory;
import me.Sebbben.AltarCrafting.CustomInventories.MaterialInv;
import me.Sebbben.AltarCrafting.CustomInventories.ResultInv;
import me.Sebbben.AltarCrafting.Files.CustomItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class RecipeConfigurationListener implements Listener {

    private final AddRecipe addRecipe;
    private final CustomInventory materialInv;
    private final CustomInventory resultInv;

    public RecipeConfigurationListener(AddRecipe addRecipe) {
        this.addRecipe = addRecipe;

        materialInv = new MaterialInv(addRecipe);
        resultInv = new ResultInv(addRecipe);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        if (e.getInventory().getHolder() instanceof CustomInventory) {
            ((CustomInventory) e.getInventory().getHolder()).onClose(e);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            e.setCancelled(true);
            if (mainHand.isSimilar(CustomItems.getMaterialsItem())) {
                e.getPlayer().openInventory(materialInv.getInventory());
            } else if (mainHand.isSimilar(CustomItems.getResultItem())) {
                e.getPlayer().openInventory(resultInv.getInventory());
            }
        }
    }
}
