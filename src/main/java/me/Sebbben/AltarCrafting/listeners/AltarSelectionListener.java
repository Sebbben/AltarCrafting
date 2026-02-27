package me.Sebbben.AltarCrafting.listeners;

import me.Sebbben.AltarCrafting.Altar;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.customItems.AltarSelectionTools;
import me.Sebbben.AltarCrafting.managers.AltarBlueprintsManager;
import me.Sebbben.AltarCrafting.managers.AltarCreationManager;
import me.Sebbben.AltarCrafting.utils.particleUtils.ParticleSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class AltarSelectionListener implements Listener {
    private final AltarBlueprintsManager blueprintsManager;
    public AltarSelectionListener(AltarBlueprintsManager blueprintsManager) {
        super();
        this.blueprintsManager = blueprintsManager;
    }
    @EventHandler
    public void onSelectCorner(PlayerInteractEvent e) {
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();
        // ----- RIGHT CLICK BLOCK -------
        if  (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK && mainHand.isSimilar(AltarSelectionTools.getCornerSelectTool())) {
                selectCorner(e);
            } else if (mainHand.isSimilar(AltarSelectionTools.getFinishItem())) {
                this.blueprintsManager.finishAltar(e.getPlayer());
            } else if (mainHand.isSimilar(AltarSelectionTools.getCancelItem())) {
                this.blueprintsManager.cancelCreation(e.getPlayer());
            }
        }

    }

    private void selectCorner(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;

        AltarCreationManager manager = this.blueprintsManager.getAltarCreationManager(e.getPlayer());

        if (manager == null) {
            e.getPlayer().sendMessage("You are not currently in an altar creation process");
            return;
        }

        manager.addCorner(e.getClickedBlock().getLocation());
        manager.spawnBoundsParticles(e.getPlayer().getWorld());
    }
}
