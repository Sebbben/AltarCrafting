package me.Sebbben.AltarCrafting.commands.AltarCommandFunctions;

import me.Sebbben.AltarCrafting.AltarManager;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.utils.commandUtils.CommandFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CreateAltarCommandFunction extends CommandFunction {
    private final AltarManager altarManager = Main.getInstance().getAltarManager();
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
        }
        String altarName = args[1];
        this.altarManager.createAltar(altarName);

        if (args.length == 2) {
            boolean useTools = args[1].equals("tools");
            this.altarManager.startSelectionProcess(altarName, (Player) sender, useTools);
        }

        return true;
    }


}
