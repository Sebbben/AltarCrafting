package me.Sebbben.AltarCrafting.Commands;

import me.Sebbben.AltarCrafting.AltarFiles.AltarHandler;
import me.Sebbben.AltarCrafting.CustomItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class altarCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        switch (args[0]) {
            case "createAltar":
                newAltarCommand((Player) sender, args);
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

    private void newAltarCommand(Player player, String[] args) {
        AltarHandler.newAltar(args[1]);

        player.sendMessage("New Altar selection begun!");

        Inventory inv = player.getInventory();

        for (int i=0;i<9;i++) {
            inv.setItem(i, null);
        }

        inv.setItem(4, CustomItems.getCornerSelectTool());
        inv.setItem(6, CustomItems.getFinishSelectItem());


    }





}
