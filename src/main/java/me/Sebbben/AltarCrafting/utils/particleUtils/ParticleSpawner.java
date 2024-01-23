package me.Sebbben.AltarCrafting.utils.particleUtils;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.BoundingBox;

public class ParticleSpawner {

    private final int particlesPerBlock = 5;
    private final World world;

    public ParticleSpawner(World _world) {
        this.world = _world;
    }

    public void spawnParticleBox(BoundingBox bounds) {
        this.spawnParticleBox(
                new Location(
                        this.world,
                        bounds.getMinX(),
                        bounds.getMinY(),
                        bounds.getMinZ()
                ),new Location(
                        this.world,
                        bounds.getMaxX(),
                        bounds.getMaxY(),
                        bounds.getMaxZ()
                )
        );
    }
    public void spawnParticleBox(Location l1, Location l2) {

        double x1 = l1.getX();
        double y1 = l1.getY();
        double z1 = l1.getZ();

        double x2 = l2.getX();
        double y2 = l2.getY();
        double z2 = l2.getZ();

        if (x1 > x2) x1 +=1; else x2 += 1;
        if (y1 > y2) y1 +=1; else y2 += 1;
        if (z1 > z2) z1 +=1; else z2 += 1;



        // Bottom rect
        spawnParticleLine(new Location(world, x1,y1,z1), new Location(world, x1,y1,z2));
        spawnParticleLine(new Location(world, x1,y1,z1), new Location(world, x2,y1,z1));
        spawnParticleLine(new Location(world, x1,y1,z2), new Location(world, x2,y1,z2));
        spawnParticleLine(new Location(world, x2,y1,z1), new Location(world, x2,y1,z2));

        // Lines up
        spawnParticleLine(new Location(world, x1,y1,z1), new Location(world, x1,y2,z1));
        spawnParticleLine(new Location(world, x2,y1,z1), new Location(world, x2,y2,z1));
        spawnParticleLine(new Location(world, x1,y1,z2), new Location(world, x1,y2,z2));
        spawnParticleLine(new Location(world, x2,y1,z2), new Location(world, x2,y2,z2));

        // Top rect

        spawnParticleLine(new Location(world, x1,y2,z1), new Location(world, x1,y2,z2));
        spawnParticleLine(new Location(world, x1,y2,z1), new Location(world, x2,y2,z1));
        spawnParticleLine(new Location(world, x1,y2,z2), new Location(world, x2,y2,z2));
        spawnParticleLine(new Location(world, x2,y2,z1), new Location(world, x2,y2,z2));
    }



    public void spawnParticleLine(Location l1, Location l2) {
        ParticleBuilder pb = new ParticleBuilder(Particle.VILLAGER_HAPPY);
        double xDiff = l2.getX() - l1.getX();
        double yDiff = l2.getY() - l1.getY();
        double zDiff = l2.getZ() - l1.getZ();

        int numberOfParticles = (int) Math.floor(Math.max(Math.abs(xDiff),Math.max(Math.abs(yDiff),Math.abs(zDiff))) * particlesPerBlock);

        double xInterval = xDiff/numberOfParticles;
        double yInterval = yDiff/numberOfParticles;
        double zInterval = zDiff/numberOfParticles;


        for (int i=0;i<numberOfParticles;i++) {
            Location pLoc = l1.clone().add(xInterval*i, yInterval*i, zInterval*i);
            pb.location(pLoc);
            pb.spawn();
        }

    }
}