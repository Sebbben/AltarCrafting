package me.Sebbben.AltarCrafting.commands.AltarCommandFunctions;

import me.Sebbben.AltarCrafting.managers.AltarManager;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.utils.commandUtils.CommandFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ListAltarsCommandFunction extends CommandFunction {
    private AltarManager altarManager = Main.getInstance().getAltarManager();
    @Override
    public List<String> getValidArgs(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {

        for (String altarName : this.altarManager.getAltarNames()) {
            sender.sendMessage(altarName);
        }
        return true;
    }
}
