package me.Sebbben.AltarCrafting.managers;

import it.unimi.dsi.fastutil.Hash;
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

import java.util.*;
import java.util.logging.Level;

public class AltarBlueprintsManager {
    private final HashMap<String, Altar> altars = new HashMap<>();
    private final Main plugin;
    private final HashMap<String, AltarFeature> altarFeatures = new HashMap<>();
    private final HashMap<UUID, AltarCreationManager> creationProcesses = new HashMap<>();

    public AltarBlueprintsManager() {
        this.plugin = Main.getInstance();
        this.plugin.getServer().getPluginManager().registerEvents(new AltarSelectionListener(this), this.plugin);
        this.registerAltarFeatures();
        this.loadAltars();
    }

    private void registerAltarFeatures() {
        this.registerAltarFeature(new ClickInteractFeature());
    }

    public AltarCreationManager createAltar(String name, Player creator) {
        if (this.altars.containsKey(name)) {
            return null;
        }

        this.creationProcesses.put(creator.getUniqueId(), new AltarCreationManager(name, creator));
        return this.creationProcesses.get(creator.getUniqueId());
    }
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
            Altar altar = new Altar(name);
            try {
                altar.loadFromCofig(config.getConfigurationSection(name));
                this.altars.put(name, altar);
            } catch (ClassCastException ex) {
                this.plugin.getLogger().log(Level.WARNING, "Could not load altar " + name);
                this.plugin.getLogger().log(Level.WARNING, ex.toString());
            }

        }
    }

    public Set<String> getAltarNames() {
        return this.altars.keySet();
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

        HashMap<String, ArrayList<int[]>> blocks = altar.getBlocks();
        
        for (String type : blocks.keySet()) {
            for (int[] coords : blocks.get(type)) {
                Location worldLoc = new Location(
                        world,
                        location.getBlockX()+coords[0],
                        location.getBlockY()+coords[1],
                        location.getBlockZ()+coords[2]
                );
                Material mat = Material.matchMaterial(type);
                world.getBlockAt(worldLoc).setType(mat);
            }

        }
    }

    public AltarCreationManager getAltarCreationManager(Player player) {
        return this.creationProcesses.get(player.getUniqueId());
    }

    public void finishAltar(Player player) {
        if (!this.creationProcesses.containsKey(player.getUniqueId())) {
            return;
        }

        AltarCreationManager manager = this.creationProcesses.get(player.getUniqueId());

        if (manager.isProcessComplete()) {
            Altar altar = manager.buildAltar();
            this.altars.put(altar.getName(), altar);
            manager.restoreInventory(player);
            player.sendMessage(altar.getName() + " has been created!");
        } else {
            player.sendMessage("Creation process is not complete");
        }


    }

    public void cancelCreation(Player player) {
        AltarCreationManager manager = this.creationProcesses.get(player.getUniqueId());
        manager.restoreInventory(player);
        this.creationProcesses.remove(player.getUniqueId());
    }
}
