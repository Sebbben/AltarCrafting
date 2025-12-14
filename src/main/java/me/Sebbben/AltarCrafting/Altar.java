package me.Sebbben.AltarCrafting;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.BoundingBox;
import org.checkerframework.checker.units.qual.A;

import java.util.*;
import java.util.logging.Level;

public class Altar {
    private String name;
    private final ArrayList<AltarFeature> features = new ArrayList<>();
    private HashMap<String,ArrayList<int[]>> blocks;

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

    }



    public void saveToConfig(ConfigurationSection configSection) {
        ConfigurationSection blocksConfigSection = configSection.createSection("blocks");
        configSection.set("blocks", this.blocks);
    }
    public void loadFromCofig(ConfigurationSection config) {
        this.blocks = new HashMap<>();

        ConfigurationSection blocksSection = config.getConfigurationSection("blocks");
        for (String material : blocksSection.getKeys(false)) {
            this.blocks.put(material, new ArrayList<>());
            for (Object coords : blocksSection.getList(material)) {
                ArrayList<Integer> coordsArr = (ArrayList<Integer>) coords;
                blocks.get(material).add(new int[]{coordsArr.get(0), coordsArr.get(1), coordsArr.get(2)});
            }
        }
    }

    public void rename(String newName) {
        this.name = newName;
    }

    public void setBlocks(HashMap<String,ArrayList<int[]>> blocks) {
        this.blocks = blocks;
    }

    public HashMap<String, ArrayList<int[]>> getBlocks() {
        return this.blocks;
    }
}
