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

        int validDirections = 15;
        for (String mat : this.blocks.keySet()) {
            for (int[] coords : this.blocks.get(mat)) {

                if ( // Normal
                    !world.getBlockAt(
                        location.getBlockX() + coords[0],
                        location.getBlockY() + coords[1],
                        location.getBlockZ() + coords[2]
                    ).getType().name().equals(mat)
                ) {

                    validDirections &= 14;

                }

                if ( // Flip X
                    !world.getBlockAt(
                            location.getBlockX() - coords[0],
                            location.getBlockY() + coords[1],
                            location.getBlockZ() + coords[2]
                    ).getType().name().equals(mat)
                ) {

                    validDirections &= 13;

                }

                if ( // Flip Y
                    !world.getBlockAt(
                            location.getBlockX() + coords[0],
                            location.getBlockY() - coords[1],
                            location.getBlockZ() + coords[2]
                    ).getType().name().equals(mat)
                ) {

                    validDirections &= 11;

                }

                if ( // Flip XY
                        !world.getBlockAt(
                                location.getBlockX() - coords[0],
                                location.getBlockY() - coords[1],
                                location.getBlockZ() + coords[2]
                        ).getType().name().equals(mat)
                ) {

                    validDirections &= 7;

                }
                Main.getInstance().getLogger().log(Level.WARNING, String.valueOf(validDirections));

                if (validDirections == 0) return false;
            }
        }

        return true;
    }
}
