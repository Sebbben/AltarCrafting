package me.Sebbben.AltarCrafting.AltarFeatures;

import me.Sebbben.AltarCrafting.AltarFeature;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickInteractFeature extends AltarFeature implements Listener {
    @Override
    public String getName() {
        return "ClickInteraction";
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;
        event.getPlayer().sendMessage(String.valueOf(event.hasBlock()));
    }
}
