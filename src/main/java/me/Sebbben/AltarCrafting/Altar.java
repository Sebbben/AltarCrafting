package me.Sebbben.AltarCrafting;

import com.google.common.util.concurrent.ClosingFuture;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;

public class Altar {
    private String name;
    private final ArrayList<AltarFeature> features = new ArrayList<>();
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

}
