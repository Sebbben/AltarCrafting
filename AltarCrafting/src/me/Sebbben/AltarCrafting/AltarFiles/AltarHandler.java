package me.Sebbben.AltarCrafting.AltarFiles;

import me.Sebbben.AltarCrafting.AreaSelect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class AltarHandler {

    private static HashMap<String, Altar> altars = new HashMap<>();

    private static Altar currentAltar;

    public static void saveAltars() {
        FileConfiguration config = AltarsConfig.get();
        for (Map.Entry<String, Altar> entry : altars.entrySet()) {
            String name = entry.getKey();
            for (Map.Entry<Vector<Integer>, Material> relPos : entry.getValue().getRelativePositions().entrySet()) {
                config.set(name + "." + relPos.getKey().toString(), relPos.getValue().toString());
            }
        }
        AltarsConfig.save();
    }
    public static void loadAltars() {
        Object rawAltars = AltarsConfig.get().getKeys(false);
        System.out.println(rawAltars);
    }

    public static void newAltar(String name) {
        currentAltar = new Altar(name);
    }
    public static void setAltarArea(AreaSelect altarArea, Player player) {

        List<Block> blocks = altarArea.getBlocks();


        // Find the cauldrons world position, used for calculating the relative position of the other blocks
        Location cauldronLocation = null;
        for (Block block : blocks) {
            if (block.getType() == Material.WATER_CAULDRON) {
                cauldronLocation = block.getLocation();
                break;
            }
        }

        if (cauldronLocation == null) {
            player.sendMessage("Could not find water cauldron in altar. Try placing a water cauldron or filling the cauldron with water");
            return;
        }

        HashMap<Vector<Integer>, Material> altarRelativePositions = new HashMap<>();

        // Set all blocks position relative to cauldron
        for (Block block : blocks) {
            Vector<Integer> coords = new Vector<>();
            Location relativePos = block.getLocation().clone().subtract(cauldronLocation);
            coords.add(relativePos.getBlockX());
            coords.add(relativePos.getBlockY());
            coords.add(relativePos.getBlockZ());
            altarRelativePositions.put(coords, block.getType());
        }

        currentAltar.setRelativePositions(altarRelativePositions);

    }

    public static void finishAltar() {
        altars.put(currentAltar.getName(), currentAltar);
        System.out.println("New altar added: " + currentAltar.getName());
        System.out.println("Size: " + currentAltar.getSize());
        saveAltars();
        currentAltar = null;
    }
}
