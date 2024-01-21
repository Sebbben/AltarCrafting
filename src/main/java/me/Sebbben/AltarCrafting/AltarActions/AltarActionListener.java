package me.Sebbben.AltarCrafting.AltarActions;

import me.Sebbben.AltarCrafting.AltarHandler;
import me.Sebbben.AltarCrafting.Files.CustomItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class AltarActionListener implements Listener {
    private final AltarHandler altarHandler;
    public AltarActionListener(AltarHandler altarHandler) {
        this.altarHandler = altarHandler;
    }


    @EventHandler
    public void onActionActivated(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();

        if (mainHand.isSimilar(CustomItems.getFinishItem())) {
            altarHandler.finishAction(e.getPlayer());
        } else if (mainHand.isSimilar(CustomItems.getCancelItem())) {
            altarHandler.cancelAction(e.getPlayer());
        }
    }
}
