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


        subcommands.add(new createAltarCommand(altarHandler));
        subcommands.add(new listAltarsCommand(altarHandler));
        subcommands.add(new removeAltarCommand(altarHandler));
        subcommands.add(new addRecipeCommand(altarHandler));
        subcommands.add(new listRecipesCommand(altarHandler));
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
            }
        } else if (args.length == 2) {
            for (Subcommand subCmd : subcommands) {
                if (subCmd.getName().startsWith(args[0])) {
                    if (subCmd.getArgs() == null) break;
                    for (String s : subCmd.getArgs()) {
                        if (s.startsWith(args[1])) {
                            options.add(s);
                        }
                    }
                }
            }
        }

        return options;
    }
}
