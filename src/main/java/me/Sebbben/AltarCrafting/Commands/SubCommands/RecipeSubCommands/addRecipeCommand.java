package me.Sebbben.AltarCrafting.Commands.SubCommands.RecipeSubCommands;

import me.Sebbben.AltarCrafting.AltarHandler;
import me.Sebbben.AltarCrafting.Commands.SubCommands.Subcommand;
import me.Sebbben.AltarCrafting.Files.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class addRecipeCommand extends Subcommand {

    public addRecipeCommand(AltarHandler altarHandler) {
        super(altarHandler);
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public List<String> getArgs(int numberOfArgs) {
        return altarHandler.getAltarNames().stream().toList();
    }

    @Override
    public void preform(Player player, String[] args) { // /ac recipe add <AltarName>

        if (args.length < 3) {
            player.sendMessage("You need to provide an altar to add the recipe to!");
            player.sendMessage("Use " + getUsage());
            return;
        }

        altarHandler.createRecipe(args[2]);

        player.sendMessage("New Recipe creation begun!");

        Inventory inv = player.getInventory();

        for (int i=0;i<9;i++) {
            inv.setItem(i, null);
        }

        inv.setItem(0, CustomItems.getCancelItem());
        inv.setItem(3, CustomItems.getMaterialsItem());
        inv.setItem(5, CustomItems.getResultItem());
        inv.setItem(8, CustomItems.getFinishItem());

    }

    @Override
    public String getUsage() {
        return "/ac " + getName() + "<AltarName>";
    }
}
