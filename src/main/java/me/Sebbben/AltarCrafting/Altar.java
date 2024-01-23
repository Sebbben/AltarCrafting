package me.Sebbben.AltarCrafting;

import com.google.common.util.concurrent.ClosingFuture;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;

public class Altar {
    private String name;
    private final ArrayList<AltarFeature> features = new ArrayList<>();
    private BoundingBox bounds;
    private Location tempCorner;

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

    public void updateBlocks() {}

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
}
