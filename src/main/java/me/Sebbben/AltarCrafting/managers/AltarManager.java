package me.Sebbben.AltarCrafting.managers;

import me.Sebbben.AltarCrafting.Altar;
import me.Sebbben.AltarCrafting.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class AltarManager {
    private final Main plugin;
    private final AltarBlueprintsManager altarBlueprintsManager;

    private HashMap<Location, String> altars;
    public AltarManager() {
        this.plugin = Main.getInstance();
        this.altarBlueprintsManager = this.plugin.getAltarBlueprintsManager();
        this.altars = new HashMap<>();
    }

    public boolean tryActivateAltar(Location location) {
        if (location.getWorld() == null) return false;
        World world = location.getWorld();
        Block block = world.getBlockAt(location);
        List<Altar> altarBlueprints = this.altarBlueprintsManager.getAltarsWithBlock(block.getType());
        for (Altar altarBlueprint : altarBlueprints) {
            this.plugin.getLogger().log(Level.WARNING, altarBlueprint.getName());
            if (altarBlueprint.isComplete(location)) {
                plugin.getLogger().log(Level.WARNING, "found altar: " + altarBlueprint.getName());
                return true;
            }
        }
        return false;
    }
}
