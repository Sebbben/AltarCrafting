package me.Sebbben.AltarCrafting.Files;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class AreaSelect {

    private final ParticleSpawner ps;
    private final World world;
    private Location corner1;
    private Location corner2;


    public AreaSelect(World _world) {
        this.world = _world;
        this.ps = new ParticleSpawner(this.world);
    }

    public Location getCorner1() {
        return corner1;
    }
    public Location getCorner2() {
        return corner2;
    }


    public void setCorner1(Location corner1) {
        this.corner1 = corner1;
        this.ps.spawnParticleBox(corner1, corner1);
    }
    public void setCorner2(Location corner2) {
        this.corner2 = corner2;
        ps.spawnParticleBox(corner1, corner2);
    }

    public void showArea() {
        ps.spawnParticleBox(corner1, corner2);
    }

    public List<Block> getBlocks() {
        List<Block> blocks = new ArrayList<>();

        int lowestX = Math.min(this.corner1.getBlockX(), this.corner2.getBlockX());
        int lowestY = Math.min(this.corner1.getBlockY(), this.corner2.getBlockY());
        int lowestZ = Math.min(this.corner1.getBlockZ(), this.corner2.getBlockZ());

        int highestX = Math.max(this.corner1.getBlockX(), this.corner2.getBlockX());
        int highestY = Math.max(this.corner1.getBlockY(), this.corner2.getBlockY());
        int highestZ = Math.max(this.corner1.getBlockZ(), this.corner2.getBlockZ());

        Location loc = new Location(this.world, 0,0,0);

        for (int y = lowestY; y <= highestY; y++) {
            for (int x = lowestX; x <= highestX; x++) {
                for (int z = lowestZ; z <= highestZ; z++) {
                    loc.setX(x);
                    loc.setY(y);
                    loc.setZ(z);
                    blocks.add(loc.getBlock());
                }
            }
        }
        return blocks;
    }

    public boolean hasTwoPositions() {return (corner1 != null && corner2 != null);}

}
