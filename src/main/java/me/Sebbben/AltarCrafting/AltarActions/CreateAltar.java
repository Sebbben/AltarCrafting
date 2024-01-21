package me.Sebbben.AltarCrafting.AltarActions;

import me.Sebbben.AltarCrafting.Files.Altar;
import me.Sebbben.AltarCrafting.AltarHandler;
import me.Sebbben.AltarCrafting.Files.AreaSelect;
import me.Sebbben.AltarCrafting.Listeners.CornerSelectListener;
import me.Sebbben.AltarCrafting.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class CreateAltar implements AltarAction {
    private final CornerSelectListener cornerSelectListener = new CornerSelectListener(this);

    private final AltarHandler altarHandler;
    private AreaSelect altarArea;

    private Altar altar;
    public CreateAltar(AltarHandler altarHandler, String name) {
        this.altarHandler = altarHandler;
        this.altar = new Altar(name);


        Main.getInstance().getServer().getPluginManager().registerEvents(cornerSelectListener,Main.getInstance());
    }


    @Override
    public void finish(Player player) {
        if (altarArea.hasTwoPositions()){
            player.sendMessage("Finished new Altar!");
            makeRelativeCoords();
            altarArea.showArea();
            altarArea = null;
            altarHandler.addAltar(altar.getName(), altar);
            PlayerInteractEvent.getHandlerList().unregister(cornerSelectListener);
        }

        Inventory inv = player.getInventory();

        for (int i=0;i<9;i++) {
            inv.setItem(i, null);
        }
    }

    @Override
    public void cancel(Player player) {
        Inventory inv = player.getInventory();

        for (int i=0;i<9;i++) {
            inv.setItem(i, null);
        }
    }

    public void setCorner(Location corner, Player player) {
        if (altarArea == null) {
            altarArea = new AreaSelect(player.getWorld());
            altarArea.setCorner1(corner);
            player.sendMessage("Corner 1 selected");
        } else {
            altarArea.setCorner2(corner);
            player.sendMessage("Corner 2 selected");
        }
    }


    private void makeRelativeCoords() {

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

        altar.setRelativePositions(altarRelativePositions);

    }



}
