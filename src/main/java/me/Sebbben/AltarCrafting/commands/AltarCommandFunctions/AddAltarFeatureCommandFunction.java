package me.Sebbben.AltarCrafting.commands.AltarCommandFunctions;

import me.Sebbben.AltarCrafting.managers.AltarBlueprintsManager;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.utils.commandUtils.CommandFunction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddAltarFeatureCommandFunction extends CommandFunction {
    AltarBlueprintsManager altarBlueprintsManager = Main.getInstance().getAltarBlueprintsManager();
    @Override
    public List<String> getValidArgs(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            Set<String> names = Main.getInstance().getAltarBlueprintsManager().getAltarNames();
            List<String> valid = new ArrayList<>(names.size());
            valid.addAll(names);
            return valid;
        } else if (args.length == 2) {
            Set<String> names = this.altarBlueprintsManager.getAltarFeatureNames();
            List<String> valid = new ArrayList<>(names.size());
            valid.addAll(names);
            return valid;
        }

        return null;
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 2) {
            sender.sendMessage("Wrong usage");
            return false;
        }

        this.altarBlueprintsManager.addAltarFeature(args[0],args[1]);
        return true;
    }
}
