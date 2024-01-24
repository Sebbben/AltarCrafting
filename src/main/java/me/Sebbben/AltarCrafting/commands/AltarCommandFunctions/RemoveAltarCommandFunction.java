package me.Sebbben.AltarCrafting.commands.AltarCommandFunctions;

import me.Sebbben.AltarCrafting.AltarManager;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.utils.commandUtils.CommandFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Objects;

public class RemoveAltarCommandFunction extends CommandFunction {
    AltarManager altarManager = Main.getInstance().getAltarManager();
    @Override
    public List<String> getValidArgs(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            return (List<String>) this.altarManager.getAltarNames();
        }
        return null;
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2 || !Objects.equals(args[1], "confirm")) {
            sender.sendMessage("You must write 'confim' at the end of command to remove altar");
            return false;
        }

        this.altarManager.removeAltar(args[0]);
        return true;
    }
}
