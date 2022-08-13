package me.Sebbben.AltarCrafting.Listeners;

import me.Sebbben.AltarCrafting.AltarFiles.AltarHandler;
import me.Sebbben.AltarCrafting.AreaSelect;
import me.Sebbben.AltarCrafting.CustomItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CornerSelectListener implements Listener {

    private AreaSelect altarArea;

    @EventHandler
    public void onSelectCorner(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();
        if  (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (mainHand.isSimilar(CustomItems.getCornerSelectTool())) {
                if (altarArea == null) {
                    altarArea = new AreaSelect(e.getPlayer().getWorld());
                    altarArea.setCorner1(e.getClickedBlock().getLocation());
                    e.getPlayer().sendMessage("Corner 1 selected");
                } else {
                    altarArea.setCorner2(e.getClickedBlock().getLocation());
                    e.getPlayer().sendMessage("Corner 2 selected");
                    AltarHandler.setAltarArea(altarArea, e.getPlayer());
                }
            }
        }
        if (mainHand.isSimilar(CustomItems.getFinishSelectItem())) {
            if (altarArea.hasTwoPositions()) {
                e.getPlayer().sendMessage("Finished new Altar!");
                altarArea.showArea();
                AltarHandler.finishAltar();
                altarArea = null;
                Inventory inv = e.getPlayer().getInventory();

                for (int i=0;i<9;i++) {
                    inv.setItem(i, null);
                }
            }
        }
    }
}
