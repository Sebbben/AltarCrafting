package me.Sebbben.AltarCrafting.managers;

import me.Sebbben.AltarCrafting.Altar;
import me.Sebbben.AltarCrafting.AltarFeature;
import me.Sebbben.AltarCrafting.AltarFeatures.ClickInteractFeature;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.customItems.AltarSelectionTools;
import me.Sebbben.AltarCrafting.customSaveFiles.AltarConfigurationHandler;
import me.Sebbben.AltarCrafting.listeners.AltarSelectionListener;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.BoundingBox;

import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

public class AltarManager {
    private final HashMap<String, Altar> altars = new HashMap<>();
    private final AltarSelectionListener selectionListener;
    private final Main plugin;
    private final HashMap<String, AltarFeature> altarFeatures = new HashMap<>();

    public AltarManager() {
        this.plugin = Main.getInstance();
        this.selectionListener = new AltarSelectionListener();
        this.plugin.getServer().getPluginManager().registerEvents(this.selectionListener, this.plugin);
        this.registerAltarFeatures();
        this.loadAltars();
    }

    private void registerAltarFeatures() {
        this.registerAltarFeature(new ClickInteractFeature());
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

    public void registerAltarFeature(AltarFeature altarFeature) {
        this.altarFeatures.put(altarFeature.getName(), altarFeature);
        Listener listener = (Listener) altarFeature;
        this.plugin.getServer().getPluginManager().registerEvents(listener, this.plugin);
    }

    public Set<String> getAltarFeatureNames() {
        return this.altarFeatures.keySet();
    }

    public void addAltarFeature(String altarName, String featureName) {
        this.altars.get(altarName).addFeature(this.altarFeatures.get(featureName));
    }

    public void placeAltar(Location location, String altarName) {
        World world = location.getWorld();
        Altar altar = this.altars.get(altarName);

        for (HashMap<String,String> block : altar.getBlocks()) {
            Location worldLoc = new Location(
                    world,
                    location.getBlockX()+Integer.parseInt(block.get("x")),
                    location.getBlockY()+Integer.parseInt(block.get("y")),
                    location.getBlockZ()+Integer.parseInt(block.get("z"))

            );
            Material mat = Material.matchMaterial(block.get("type"));
            world.getBlockAt(worldLoc).setType(mat);
        }
    }
}
