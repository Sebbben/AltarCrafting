package me.Sebbben.AltarCrafting.Commands.SubCommands.RecipeSubCommands;

import me.Sebbben.AltarCrafting.AltarHandler;
import me.Sebbben.AltarCrafting.Commands.SubCommands.Subcommand;
import me.Sebbben.AltarCrafting.CustomInventories.RecipeListInv;
import me.Sebbben.AltarCrafting.Files.AltarRecipe;
import org.bukkit.entity.Player;
import java.util.List;

public class listRecipesCommand extends Subcommand {

    public listRecipesCommand(AltarHandler altarHandler) {
        super(altarHandler);
    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public List<String> getArgs(int numberOfArgs) {
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


        player.openInventory(new RecipeListInv().getInventory(recipes));
    }

    @Override
    public String getUsage() {
        return "/ac" + getName() + " <Altar Name>";
    }
}
