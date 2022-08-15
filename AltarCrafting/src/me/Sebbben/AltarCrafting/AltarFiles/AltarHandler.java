package me.Sebbben.AltarCrafting.AltarFiles;

import me.Sebbben.AltarCrafting.Files.AreaSelect;
import me.Sebbben.AltarCrafting.CustomConfigs.AltarsConfig;
import me.Sebbben.AltarCrafting.Listeners.CornerSelectListener;
import me.Sebbben.AltarCrafting.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.*;

public class AltarHandler {
    private static final HashMap<String, Altar> altars = new HashMap<>();
    private static final CornerSelectListener cornerSelectListener = new CornerSelectListener();
    private static Altar currentAltar;
    private static AreaSelect altarArea;

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
        Set<String> rawAltars = AltarsConfig.get().getKeys(false);
        for (String key : rawAltars) {
            Altar altar = new Altar(key);

            HashMap<Vector<Integer>, Material> altarBlocks = new HashMap<>();

            Set<String> altarPositions = AltarsConfig.get().getConfigurationSection(key).getKeys(false);
            for (String position : altarPositions) {
                Vector<Integer> pos = new Vector<>();
                String newPosition = position.substring(1,position.length()-1);
                for (String s : newPosition.split(", ")) {
                    pos.add(Integer.decode(s));
                }
                Material mat = Material.matchMaterial(AltarsConfig.get().getString(key + "." + position));
                altarBlocks.put(pos, mat);
            }
            altar.setRelativePositions(altarBlocks);
            altars.put(key, altar);
        }
    }

    public static void newAltar(String name) {
        currentAltar = new Altar(name);
        Main.getInstance().getServer().getPluginManager().registerEvents(cornerSelectListener,Main.getInstance());
    }
    public static void makeRelativeCoords() {

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
            return;
        }

        HashMap<Vector<Integer>, Material> altarRelativePositions = new HashMap<>();

        // Set all blocks position relative to cauldron
        for (Block block : blocks) {
            if (block.getType() == Material.AIR) continue;
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
        makeRelativeCoords();
        altarArea.showArea();
        altarArea = null;
        altars.put(currentAltar.getName(), currentAltar);
        saveAltars();
        currentAltar = null;
        PlayerInteractEvent.getHandlerList().unregister(cornerSelectListener);
    }

    public static Set<String> getAltarNames() {return altars.keySet();}

    public static void cancelAltar() {
        currentAltar = null;
    }

    public static void removeAltar(String altarName) {
        altars.remove(altarName);
        AltarsConfig.get().set(altarName, null);
        AltarsConfig.save();
    }

    public static boolean isCreatingAltar() {
        return currentAltar != null;
    }

    public static void setCorner(Location corner, Player player) {
        if (altarArea == null) {
            altarArea = new AreaSelect(player.getWorld());
            altarArea.setCorner1(corner);
            player.sendMessage("Corner 1 selected");
        } else {
            altarArea.setCorner2(corner);
            player.sendMessage("Corner 2 selected");
        }
    }

    public static boolean hasTwoCorners() {
        return altarArea.hasTwoPositions();
    }
}
