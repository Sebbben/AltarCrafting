package me.Sebbben.AltarCrafting.Listeners;

import me.Sebbben.AltarCrafting.AltarActions.CreateAltar;
import me.Sebbben.AltarCrafting.Files.CustomItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class CornerSelectListener implements Listener {
    private CreateAltar createAltar;
    public CornerSelectListener(CreateAltar createAltar) {
        this.createAltar = createAltar;
    }

    @EventHandler
    public void onSelectCorner(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();
        // ----- RIGHT CLICK BLOCK -------
        if  (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (mainHand.isSimilar(CustomItems.getCornerSelectTool())) {
                createAltar.setCorner(e.getClickedBlock().getLocation(), e.getPlayer());
            }
        }

    }
}
