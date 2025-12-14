package me.Sebbben.AltarCrafting.commands.AltarCommandFunctions;

import me.Sebbben.AltarCrafting.managers.AltarBlueprintsManager;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.managers.AltarCreationManager;
import me.Sebbben.AltarCrafting.utils.commandUtils.CommandFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CreateAltarCommandFunction extends CommandFunction {
    private final AltarBlueprintsManager altarBlueprintsManager = Main.getInstance().getAltarBlueprintsManager();
    @Override
    public List<String> getValidArgs(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1)
            return List.of("<Altar Name>");
        else if (args.length == 2) {
            return List.of("tools", "notools");
        }
        return null;
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {

        if (args.length < 1) {
            sender.sendMessage("You must provide a name for the new Altar!");
            return false;
        } else if (args.length < 2) {
            sender.sendMessage("You must choose weather or not to use tools for defining multiblock");
            return false;
        }

        String altarName = args[0];
        AltarCreationManager manager = this.altarBlueprintsManager.startAltarCreation(altarName, (Player) sender);
        if (manager == null) {
            sender.sendMessage("There is already an alter with this name");
            return true;
        }
        if (args[1].equals("tools")) {
            manager.provideTools((Player) sender);
        }
        return true;
    }


}
