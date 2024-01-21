package me.Sebbben.AltarCrafting.Files;


import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AltarRecipe {
    private List<ItemStack> materials;
    private List<ItemStack> result;

    public AltarRecipe(List<ItemStack> materials, List<ItemStack> result) {
        this.materials = materials;
        this.result = result;
    }

    public AltarRecipe() {
        return;
    }

    public List<ItemStack> getMaterials() {
        return materials;
    }
    public void setMaterials(List<ItemStack> materials) {
        this.materials = materials;
    }
    public List<ItemStack> getResult() {
        return result;
    }
    public void setResult(List<ItemStack> result) {
        this.result = result;
    }

    public boolean isComplete() {
        return materials != null && result != null;
    }
}
