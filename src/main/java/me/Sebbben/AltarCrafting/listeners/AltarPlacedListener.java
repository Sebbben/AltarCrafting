package me.Sebbben.AltarCrafting.listeners;

import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.managers.AltarManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class AltarPlacedListener implements Listener {
    private AltarManager altarManager;

    public AltarPlacedListener() {
        this.altarManager = Main.getInstance().getAltarManager();
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event) {

        boolean success = this.altarManager.tryActivateAltar(event.getBlock().getLocation());

        if (success) {
            event.getPlayer().sendMessage("Altar placed");
        } else {
            event.getPlayer().sendMessage("No altar");
        }
    }
}
