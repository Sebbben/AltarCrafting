package me.Sebbben.AltarCrafting.commands.AltarCommandFunctions;

import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.customSaveFiles.AltarConfigurationHandler;
import me.Sebbben.AltarCrafting.utils.commandUtils.CommandFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class SaveAltarsCommandFunction extends CommandFunction {
    @Override
    public List<String> getValidArgs(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        Main.getInstance().getAltarManager().saveAltars();
        AltarConfigurationHandler.save();
        return true;
    }
}
