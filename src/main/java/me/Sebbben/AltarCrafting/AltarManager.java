package me.Sebbben.AltarCrafting;

import me.Sebbben.AltarCrafting.customItems.AltarSelectionTools;
import me.Sebbben.AltarCrafting.customSaveFiles.AltarConfigurationHandler;
import me.Sebbben.AltarCrafting.listeners.AltarSelectionListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.BoundingBox;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

public class AltarManager {
    private final HashMap<String, Altar> altars = new HashMap<>();
    private final AltarSelectionListener selectionListener;
    private final Main plugin;

    public AltarManager() {
        this.plugin = Main.getInstance();
        this.selectionListener = new AltarSelectionListener();
        this.plugin.getServer().getPluginManager().registerEvents(this.selectionListener, this.plugin);
        this.loadAltars();
    }

    public void createAltar(String name) {
        altars.put(name, new Altar(name));
    }
    public void setAltarStructure(String name, BoundingBox bounds) {}
    public void addAltarFeature(String name, AltarFeature feature) {}
    public void removeAltarFeature(String name, AltarFeature feature) {}
    public void renameAltar(String oldName, String newName) {
        if (!this.altars.containsKey(newName)) {
            this.altars.put(newName, this.altars.remove(oldName));
            this.altars.get(newName).rename(newName);
        }
    }
    public void removeAltar(String name) {
        this.altars.remove(name);
    }
    public void saveAltars() {
        YamlConfiguration altarConfig = AltarConfigurationHandler.get();
        ConfigurationSection altarConfigSection;
        for (String name : altars.keySet()) {
            if (altarConfig.contains(name)) {
                altarConfigSection = altarConfig.getConfigurationSection(name);
            } else {
                altarConfigSection = altarConfig.createSection(name);
            }
            altars.get(name).saveToConfig(altarConfigSection);
        }
        AltarConfigurationHandler.save();
    }
    public void loadAltars() {
        YamlConfiguration config = AltarConfigurationHandler.get();
        for (String name : config.getKeys(false)) {
            this.createAltar(name);
            try {
                this.altars.get(name).loadFromCofig(config.getConfigurationSection(name));
            } catch (ClassCastException ex) {
                this.plugin.getLogger().log(Level.WARNING, "Could not load altar " + name);
            }
        }
    }

    public Set<String> getAltarNames() {
        return this.altars.keySet();
    }
    public void startSelectionProcess(String altarName, Player player, boolean useTools) {
        if (useTools) {
            if (!this.selectionListener.isActivePlayer(player)) {
                this.selectionListener.addActivePlayer(player, this.altars.get(altarName));
                Inventory inv = player.getInventory();

                for (int i=0;i<9;i++) {
                    inv.setItem(i, null);
                }

                inv.setItem(2, AltarSelectionTools.getCancelItem());
                inv.setItem(4, AltarSelectionTools.getCornerSelectTool());
                inv.setItem(6, AltarSelectionTools.getFinishItem());

            }
        } else {
            // TODO: Implement a command based system for selecting corners of altar
        }
    }
}
