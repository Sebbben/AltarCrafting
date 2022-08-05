package me.Sebbben.AltarCrafting.Commands;

import me.Sebbben.AltarCrafting.CustomItems;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class altarCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        if (args.length < 1) return false;

        switch (args[0]) {
            case "newAltar":
                newAltarCommand((Player) sender);
                break;
            case "saveAltar":
                break;
            case "addRecipe":
                break;
            default:
                return false;
        }

        return true;
    }

    private void newAltarCommand(Player player) {
        player.sendMessage("New Altar selection begun!");

        Inventory inv = player.getInventory();
        for (int i=0;i<9;i++) {
            inv.setItem(i, null);
        }

        inv.setItem(3, CustomItems.getCorner1Select());
        inv.setItem(5, CustomItems.getCorner2Select());

    }





}
