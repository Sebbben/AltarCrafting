package me.Sebbben.AltarCrafting;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;

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

    public Set<String> getTypes() {
        return this.blocks.keySet();
    }

    public boolean isComplete(Location location) {

        World world = location.getWorld();
        if (world == null) return false;

        for (int[] assumedOffset : this.blocks.get(location.getBlock().getType().name())) {
            int validRotations = 15;
            for (String mat : this.blocks.keySet()) {
                for (int[] offset : this.blocks.get(mat)) {
                    int[] rel_offset = new int[]{offset[0]-assumedOffset[0], offset[1]-assumedOffset[1], offset[2]-assumedOffset[2]};

                    // No rotation
                    if ((validRotations&1) == 1 && !world.getBlockAt(
                            location.getBlockX() + rel_offset[0],
                            location.getBlockY() + rel_offset[1],
                            location.getBlockZ() + rel_offset[2]).getType().name().equals(mat)) {
                        validRotations &= 14;
                    }
                    // 90 deg rotation, x => z z => -x
                    if ((validRotations&2) == 2 && !world.getBlockAt(
                            location.getBlockX() + rel_offset[2],
                            location.getBlockY() + rel_offset[1],
                            location.getBlockZ() - rel_offset[0]).getType().name().equals(mat)) {
                        validRotations &= 13;
                    }
                    // 180 deg rotation, x <=> -x, z <=> -z
                    if ((validRotations&4) == 4 && !world.getBlockAt(
                            location.getBlockX() - rel_offset[0],
                            location.getBlockY() + rel_offset[1],
                            location.getBlockZ() - rel_offset[2]).getType().name().equals(mat)) {
                        validRotations &= 11;
                    }
                    // 270 deg rotation, x => -z z => x
                    if ((validRotations&8) == 8 && !world.getBlockAt(
                            location.getBlockX() - rel_offset[2],
                            location.getBlockY() + rel_offset[1],
                            location.getBlockZ() + rel_offset[0]).getType().name().equals(mat)) {
                        validRotations &= 7;
                    }

                    if (validRotations == 0) break;
                }
                if (validRotations == 0) break;
            }
            if (validRotations != 0) return true;
        }
        return false;
    }

}
