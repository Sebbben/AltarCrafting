package me.Sebbben.AltarCrafting.Commands.SubCommands;

import me.Sebbben.AltarCrafting.AltarFiles.AltarHandler;
import me.Sebbben.AltarCrafting.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;

public class createAltarCommand extends Subcommand{

    @Override
    public String getName() {
        return "createAltar";
    }

    @Override
    public List<String> getArgs() {
        return Arrays.asList("Altar Name");
    }

    @Override
    public void preform(Player player, String[] args) {
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

        inv.setItem(2, CustomItems.getCancelItem());
        inv.setItem(4, CustomItems.getCornerSelectTool());
        inv.setItem(6, CustomItems.getFinishSelectItem());

    }
}
