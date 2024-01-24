package me.Sebbben.AltarCrafting;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Altar {
    private String name;
    private final ArrayList<AltarFeature> features = new ArrayList<>();
    private BoundingBox bounds;
    private Location tempCorner;
    private ArrayList<HashMap<String, String>> blocks;

    public Altar(String name) {
        this.name = name;
    }

    public Altar(ConfigurationSection config) {}

    public String getName() {
        return name;
    }

    public ArrayList<AltarFeature> getFeatures() {
        return features;
    }
    public void addFeature(AltarFeature feature) {
        this.features.add(feature);
    }
    public void removeFeature(AltarFeature feature) {
        this.features.remove(feature);
    }
    public ConfigurationSection generateAltarConfig() {
        return null;
    }

    public void place(Location location) {}

    public void updateBlocks() {
        this.blocks = new ArrayList<>();
        for (int x = 0;x<this.bounds.getWidthX()+1;x++) {
            for (int y = 0; y < this.bounds.getHeight()+1; y++) {
                for (int z = 0; z < this.bounds.getWidthZ()+1; z++) {
                    HashMap<String, String> values = new HashMap<>();
                    Material blockMat = Main.getInstance().getServer().getWorld("world").getBlockAt(
                            this.bounds.getMin().getBlockX() + x,
                            this.bounds.getMin().getBlockY() + y,
                            this.bounds.getMin().getBlockZ() + z
                    ).getType();
                    values.put("type", blockMat.name());
                    values.put("x", String.valueOf(x));
                    values.put("y", String.valueOf(y));
                    values.put("z", String.valueOf(z));
                    this.blocks.add(values);
                }
            }
        }
        Main.getInstance().getLogger().log(Level.WARNING, String.valueOf(this.blocks));
    }

    /**
     * Takes a location and sets the corners of the altar to given corners when two corners have been provided.
     * @param location A location to set the next corner to.
     * @return A boolean telling whether there has been created a bounding box.
     */
    public boolean addCorner(Location location) {
        if (this.tempCorner == null) {
            this.tempCorner = location;
            return false;
        } else {
            this.bounds = new BoundingBox(
                    this.tempCorner.getBlockX(),
                    this.tempCorner.getBlockY(),
                    this.tempCorner.getBlockZ(),
                    location.getBlockX(),
                    location.getBlockY(),
                    location.getBlockZ()
            );
            this.tempCorner = null;
            return true;
        }
    }

    public BoundingBox getBounds() {
        return this.bounds;
    }

    public void saveToConfig(ConfigurationSection configSection) {
        ConfigurationSection blocksConfigSection = configSection.createSection("blocks");
        configSection.set("blocks", this.blocks);
    }
    public void loadFromCofig(ConfigurationSection config) {
        this.blocks = new ArrayList<>();
        for (Map<?, ?> block : config.getMapList("blocks")) {
            this.blocks.add((HashMap<String, String>) block);
        }
    }
}
