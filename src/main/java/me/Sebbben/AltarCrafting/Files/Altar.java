package me.Sebbben.AltarCrafting.Files;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Vector;

public class Altar {
    private final String name;
    private HashMap<Vector<Integer>, Material> relativePositions;

    public Altar(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return relativePositions.size();
    }
    public HashMap<Vector<Integer>, Material> getRelativePositions() {
        return relativePositions;
    }

    public void setRelativePositions(HashMap<Vector<Integer>, Material> relativePositions) {
        this.relativePositions = relativePositions;
    }
}
