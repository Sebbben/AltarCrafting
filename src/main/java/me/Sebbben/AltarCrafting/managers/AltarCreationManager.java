package me.Sebbben.AltarCrafting.managers;

import me.Sebbben.AltarCrafting.Altar;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.customItems.AltarSelectionTools;
import me.Sebbben.AltarCrafting.utils.particleUtils.ParticleSpawner;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class AltarCreationManager {
    private String altarName;
    private ItemStack[] playerInventoryBackup = new ItemStack[9];
    private Player creator;
    private Location corner1;
    private BoundingBox bounds;
    public AltarCreationManager(String altarName, Player creator) {
        this.altarName = altarName;
        this.creator = creator;
    }

    public void provideTools(Player player) {
        Inventory inv = player.getInventory();

        for (int i=0;i<9;i++) {
            this.playerInventoryBackup[i] = inv.getItem(i);
            inv.setItem(i, null);
        }

        inv.setItem(2, AltarSelectionTools.getCancelItem());
        inv.setItem(4, AltarSelectionTools.getCornerSelectTool());
        inv.setItem(6, AltarSelectionTools.getFinishItem());
    }

    public void restoreInventory(Player player) {
        Inventory inv = player.getInventory();
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, playerInventoryBackup[i]);
        }
    }
    /**
     * Takes a location and sets the corners of the altar to given corners when two corners have been provided.
     * @param location A location to set the next corner to.
     * @return A boolean telling whether there has been created a bounding box.
     */
    public void addCorner(Location location) {
        if (this.corner1 == null) {
            this.corner1 = location;

        } else {
            this.bounds = new BoundingBox(
                    this.corner1.getBlockX(),
                    this.corner1.getBlockY(),
                    this.corner1.getBlockZ(),
                    location.getBlockX(),
                    location.getBlockY(),
                    location.getBlockZ()
            );
            this.corner1 = null;
        }
    }

    public void spawnBoundsParticles(World world) {
        ParticleSpawner spawner = new ParticleSpawner(world);

        if (this.corner1 != null) {
            spawner.spawnParticleBox(this.corner1, this.corner1);
        } else if (this.bounds != null) {
            spawner.spawnParticleBox(this.bounds);
        }
    }

    public boolean isProcessComplete() {
        return this.bounds != null;
    }

    public Altar buildAltar() {
        Altar altar = new Altar(this.altarName);

        HashMap<String, ArrayList<int[]>> blocks = new HashMap<>();

        for (int x = 0;x<this.bounds.getWidthX()+1;x++) { // TODO: Change to BlockIterator
            for (int y = 0; y < this.bounds.getHeight()+1; y++) {
                for (int z = 0; z < this.bounds.getWidthZ()+1; z++) {
                    String type = Main.getInstance().getServer().getWorld("world").getBlockAt(
                            this.bounds.getMin().getBlockX() + x,
                            this.bounds.getMin().getBlockY() + y,
                            this.bounds.getMin().getBlockZ() + z
                    ).getType().name();
                    if (!blocks.containsKey(type)) {
                        blocks.put(type, new ArrayList<>());
                    }
                    blocks.get(type).add(new int[]{x, y, z});
                }
            }
        }

        altar.setBlocks(blocks);


        return altar;
    }
}
