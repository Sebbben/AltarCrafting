package me.Sebbben.AltarCrafting.Listeners;

import me.Sebbben.AltarCrafting.CustomItems;
import me.Sebbben.AltarCrafting.ParticleSpawner;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class CornerSelectListener implements Listener {

    Location corner1;
    Location corner2;

    private ParticleSpawner ps;

    @EventHandler
    public void onSelectCorner(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND || e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();

        if (ps == null) ps = new ParticleSpawner(e.getPlayer().getWorld());

        e.getPlayer().sendMessage(String.valueOf(e.getHand()));

        if (mainHand.isSimilar(CustomItems.getCornerSelectTool())) {
            if (corner1 == null) {
                corner1 = e.getClickedBlock().getLocation();
                ps.spawnParticleBox(corner1, corner1);
                e.getPlayer().sendMessage("Corner 1 selected");
            } else {
                corner2 = e.getClickedBlock().getLocation();
                ps.spawnParticleBox(corner1, corner2);
                e.getPlayer().sendMessage("Corner 2 selected");
                corner1 = null;
                corner2 = null;
            }
        }
    }
}
