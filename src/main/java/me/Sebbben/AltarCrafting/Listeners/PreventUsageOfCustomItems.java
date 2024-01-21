package me.Sebbben.AltarCrafting.Listeners;

import me.Sebbben.AltarCrafting.Files.CustomItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PreventUsageOfCustomItems implements Listener {
    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent e) {
        for (ItemStack item : CustomItems.getAllItems()) {
            if (item.isSimilar(e.getItemInHand())){
                e.setCancelled(true);
                return;
            }

        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        for (ItemStack item : CustomItems.getAllItems()) {
            if (item.isSimilar(e.getPlayer().getItemInUse())){
                e.setCancelled(true);
                return;
            }

        }
    }
}
