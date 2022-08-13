package me.Sebbben.AltarCrafting.Commands;

import me.Sebbben.AltarCrafting.AltarFiles.AltarHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class altarCraftingTabComplete implements TabCompleter {

    List<String> altarCommands = Arrays.asList(
            "createAltar",
            "listAltars",
            "removeAltar"
    );

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> options = new ArrayList<>();
        if (args.length == 1) {
            for (String str : altarCommands)
                if (str.startsWith(args[0]))
                    options.add(str);
        } else if (args.length == 2) {
            if (Objects.equals(args[0], "removeAltar")) {
                for (String str : AltarHandler.getAltarNames())
                    if (str.startsWith(args[1]))
                        options.add(str);
            }
        }

        return options;
    }
}
