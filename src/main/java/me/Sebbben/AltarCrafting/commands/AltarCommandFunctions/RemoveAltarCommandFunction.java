package me.Sebbben.AltarCrafting.commands.AltarCommandFunctions;

import me.Sebbben.AltarCrafting.managers.AltarManager;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.utils.commandUtils.CommandFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class RemoveAltarCommandFunction extends CommandFunction {
    AltarManager altarManager = Main.getInstance().getAltarManager();
    @Override
    public List<String> getValidArgs(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            Set<String> names = Main.getInstance().getAltarManager().getAltarNames();
            List<String> valid = new ArrayList<>(names.size());
            valid.addAll(names);
            return valid;
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