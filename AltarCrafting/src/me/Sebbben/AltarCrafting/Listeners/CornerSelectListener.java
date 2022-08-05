package me.Sebbben.AltarCrafting.Listeners;

import me.Sebbben.AltarCrafting.CustomItems;
import me.Sebbben.AltarCrafting.ParticleSpawner;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CornerSelectListener implements Listener {

    Location corner1;
    Location corner2;

    private ParticleSpawner ps;

    @EventHandler
    public void onSelectCorner(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();

        if (ps == null) ps = new ParticleSpawner(e.getPlayer().getWorld(), e.getPlayer());

        if (mainHand.isSimilar(CustomItems.getCorner1Select())) {
            corner1 = e.getClickedBlock().getLocation();

        } else if (mainHand.isSimilar(CustomItems.getCorner2Select())) {
            corner2 = e.getClickedBlock().getLocation();
            if (corner1 != null) {
                e.getPlayer().sendMessage("spawing particles");
                ps.spawnParticleBox(corner1, corner2);
                corner1 = null;
                corner2 = null;
            }
        }





    }
}
