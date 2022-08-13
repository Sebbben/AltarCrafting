package me.Sebbben.AltarCrafting.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class altarCraftingTabComplete implements TabCompleter {

    List<String> altarCommands = Arrays.asList(
            "createAltar",
            ""
    );

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> options = new ArrayList<>();
        if (args.length == 1) {
            for (String str : altarCommands)
                if (str.startsWith(args[0]))
                    options.add(str);
        }

        return options;
    }
}
