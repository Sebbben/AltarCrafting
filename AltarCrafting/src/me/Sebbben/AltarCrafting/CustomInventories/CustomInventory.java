package me.Sebbben.AltarCrafting.CustomInventories;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class CustomInventory implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return Bukkit.createInventory(this, getSize(), getName());
    }

    public abstract void onClose(InventoryCloseEvent e);

    protected abstract int getSize();
    protected abstract String getName();

}
