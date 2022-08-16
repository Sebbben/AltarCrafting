package me.Sebbben.AltarCrafting.Commands.SubCommands;

import me.Sebbben.AltarCrafting.AltarHandler;
import me.Sebbben.AltarCrafting.Files.AltarRecipe;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class listRecipesCommand extends Subcommand{

    public listRecipesCommand(AltarHandler altarHandler) {
        super(altarHandler);
    }

    @Override
    public String getName() {
        return "listRecipes";
    }

    @Override
    public List<String> getArgs() {
        return altarHandler.getAltarNames().stream().toList();
    }

    @Override
    public void preform(Player player, String[] args) { // /ac listRecipes <Altar Name>
        if (args.length < 2) {
            player.sendMessage("You need to provide an altar to list recipes");
            player.sendMessage("Use " + getUsage());
            return;
        }


        List<AltarRecipe> recipes = altarHandler.getRecipesFor(args[1]);
        if (recipes == null) {
            player.sendMessage("This altar does not have any recipes!");
            player.sendMessage("Add some by using /ac addRecipe <Altar Name>");
            return;
        }
        for (AltarRecipe recipe : recipes) {
            List<String> mats = new ArrayList<>();
            for (ItemStack mat : recipe.getMaterials()) {
                player.sendMessage(mat.getType().toString());
            }
        }
    }

    @Override
    public String getUsage() {
        return "/ac" + getName() + " <Altar Name>";
    }
}
