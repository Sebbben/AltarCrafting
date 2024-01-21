package me.Sebbben.AltarCrafting.Commands;

import me.Sebbben.AltarCrafting.AltarHandler;
import me.Sebbben.AltarCrafting.Commands.SubCommands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class altarCommandManager implements TabExecutor {

    private final List<Subcommand> subcommands = new ArrayList<>();
    private AltarHandler altarHandler;

    public altarCommandManager(AltarHandler altarHandler) {
        this.altarHandler = altarHandler;

        subcommands.add(new AltarCommands(altarHandler));
        subcommands.add(new RecipeCommands(altarHandler));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        for (Subcommand subCmd : subcommands) {
            if (args[0].equalsIgnoreCase(subCmd.getName())) {
                subCmd.preform(player, args);
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> options = new ArrayList<>();
        if (args.length == 1) {
            for (Subcommand subCmd : subcommands) {
                if (subCmd.getName().startsWith(args[0])) {
                    options.add(subCmd.getName());
                }
            }                          // /ac Recipe  list    Furnace
        } else if (args.length >= 2) { // /ac argLen1 argLen2 argLen3
            for (Subcommand subCmd : subcommands) {
                if (subCmd.getName().startsWith(args[0])) {
                    if (subCmd.getArgs(args.length) == null) break;
                    for (String s : subCmd.getArgs(args.length-1)) {
                        if (s.startsWith(args[args.length-1])) {
                            options.add(s);
                        }
                    }
                }
            }
        }

        return options;
    }
}
