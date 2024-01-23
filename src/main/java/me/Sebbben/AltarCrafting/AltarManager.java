package me.Sebbben.AltarCrafting;

import me.Sebbben.AltarCrafting.Listeners.AltarSelectionListener;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class AltarManager {
    private final HashMap<String, Altar> altars = new HashMap<>();
    private final AltarSelectionListener selectionListener = new AltarSelectionListener();
    private final Main plugin;

    public AltarManager() {
        this.plugin = Main.getInstance();
    }

    public void createAltar(String name) {
        altars.put(name, new Altar(name));
    }
    public void setAltarStructure(String name, Location corner1, Location corner2) {}
    public void addAltarFeature(String name, AltarFeature feature) {}
    public void removeAltarFeature(String name, AltarFeature feature) {}
    public void renameAltar(String oldName, String newName) {}
    public void removeAltar(String name) {}
    public void saveAltars() {}
    public void loadAltars() {}

    public void startSelectionProcess(Player player, boolean useTools) {
        if (useTools) {

        }
    }
}
