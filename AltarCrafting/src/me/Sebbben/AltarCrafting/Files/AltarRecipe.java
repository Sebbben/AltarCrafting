package me.Sebbben.AltarCrafting.Files;


import org.bukkit.inventory.ItemStack;

public class AltarRecipe {
    private ItemStack[] materials;
    private ItemStack[] result;

    public AltarRecipe(ItemStack[] materials, ItemStack[] result) {
        this.materials = materials;
        this.result = result;
    }

    public AltarRecipe() {
        return;
    }

    public ItemStack[] getMaterials() {
        return materials;
    }
    public void setMaterials(ItemStack[] materials) {
        this.materials = materials;
    }
    public ItemStack[] getResult() {
        return result;
    }
    public void setResult(ItemStack[] result) {
        this.result = result;
    }

    public boolean isComplete() {
        return materials != null && result != null;
    }
}
