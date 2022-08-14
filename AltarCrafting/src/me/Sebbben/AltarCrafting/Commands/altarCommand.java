package me.Sebbben.AltarCrafting.Commands;

import me.Sebbben.AltarCrafting.AltarFiles.AltarHandler;
import me.Sebbben.AltarCrafting.CustomItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Set;

public class altarCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        switch (args[0]) {
            case "createAltar":
                newAltarCommand(player, args);
                break;
            case "listAltars":
                listAltarsCommand(player);
                break;
            case "removeAltar":
                removeAltarCommand(player, args);
                break;




            case "addRecipe":
                addRecipeCommand(player, args);
            default:
                return false;
        }

        return true;
    }

    private void addRecipeCommand(Player player, String[] args) {
        
    }

    private void removeAltarCommand(Player player, String[] args) {
        if (args.length >= 2) {
            AltarHandler.removeAltar(args[1]);
            player.sendMessage(args[1] + " was removed");
        }
    }

    private void listAltarsCommand(Player player) {
        Set<String> names = AltarHandler.getAltarNames();
        for (String name : names) {
            player.sendMessage(name);
        }
    }

    private void newAltarCommand(Player player, String[] args) {

        if (AltarHandler.getAltarNames().contains(args[1])) {
            player.sendMessage("There is already an altar with this name!");
            player.sendMessage("Use another name or remove the other altar");
            return;
        }

        AltarHandler.newAltar(args[1]);

        player.sendMessage("New Altar selection begun!");

        Inventory inv = player.getInventory();

        for (int i=0;i<9;i++) {
            inv.setItem(i, null);
        }

        inv.setItem(2,CustomItems.getCancelItem());
        inv.setItem(4, CustomItems.getCornerSelectTool());
        inv.setItem(6, CustomItems.getFinishSelectItem());


    }





}
