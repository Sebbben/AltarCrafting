package me.Sebbben.AltarCrafting.listeners;

import me.Sebbben.AltarCrafting.Altar;
import me.Sebbben.AltarCrafting.customItems.AltarSelectionTools;
import me.Sebbben.AltarCrafting.utils.particleUtils.ParticleSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

public class AltarSelectionListener implements Listener {
    private final HashMap<UUID, Altar> activePlayers = new HashMap<>();
    public void addActivePlayer(Player player, Altar altar) {
        this.activePlayers.put(player.getUniqueId(), altar);
    }
    public Altar removeActivePlayer(Player player) {
        return this.activePlayers.remove(player.getUniqueId());
    }
    public boolean isActivePlayer(Player player) {
        return this.activePlayers.containsKey(player.getUniqueId());
    }
    @EventHandler
    public void onSelectCorner(PlayerInteractEvent e) {
        if (!this.activePlayers.containsKey(e.getPlayer().getUniqueId())) return;
        if (e.getHand() == EquipmentSlot.OFF_HAND) return;

        ItemStack mainHand = e.getPlayer().getInventory().getItemInMainHand();
        // ----- RIGHT CLICK BLOCK -------
        if  (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (mainHand.isSimilar(AltarSelectionTools.getCornerSelectTool())) {
                if (e.getClickedBlock() == null) return;
                e.getPlayer().sendMessage("Selected a corner");
                boolean finished = this.activePlayers.get(e.getPlayer().getUniqueId()).addCorner(e.getClickedBlock().getLocation());
                if (finished) {
                    e.getPlayer().sendMessage("finished altar selection");
                    Altar altar = this.removeActivePlayer(e.getPlayer());
                    e.getPlayer().sendMessage(altar.getBounds().toString());
                    altar.updateBlocks();
                    ParticleSpawner ps = new ParticleSpawner(e.getPlayer().getWorld());
                    ps.spawnParticleBox(altar.getBounds());
                }
                else {
                    ParticleSpawner ps = new ParticleSpawner(e.getPlayer().getWorld());
                    ps.spawnParticleBox(e.getClickedBlock().getLocation(), e.getClickedBlock().getLocation());
                }
            }
        }

    }
}
