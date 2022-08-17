package me.Sebbben.AltarCrafting.Commands.SubCommands;

import me.Sebbben.AltarCrafting.AltarHandler;
import me.Sebbben.AltarCrafting.Commands.SubCommands.AltarSubCommands.createAltarCommand;
import me.Sebbben.AltarCrafting.Commands.SubCommands.AltarSubCommands.listAltarsCommand;
import me.Sebbben.AltarCrafting.Commands.SubCommands.AltarSubCommands.removeAltarCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AltarCommands extends Subcommand {

    private final List<Subcommand> subcommands = new ArrayList<>();
    public AltarCommands(AltarHandler altarHandler) {
        super(altarHandler);

        subcommands.add(new createAltarCommand(altarHandler));
        subcommands.add(new listAltarsCommand(altarHandler));
        subcommands.add(new removeAltarCommand(altarHandler));

    }

    @Override
    public String getName() {
        return "altar";
    }

    @Override
    public List<String> getArgs(int numberOfArgs) {
        if (numberOfArgs > 1) {
            List<String> args = new ArrayList<>();
            for (Subcommand subCmd : subcommands) {
                if (subCmd.getArgs(numberOfArgs-1) != null)
                    args.addAll(subCmd.getArgs(numberOfArgs-1));
            }
            return args;
        }
        List<String> args = new ArrayList<>();
        for (Subcommand subCmd : subcommands) {
            args.add(subCmd.getName());
        }
        return args;
    }

    @Override
    public void preform(Player player, String[] args) {
        for (Subcommand subCmd : subcommands) {
            if (args[1].equalsIgnoreCase(subCmd.getName())) {
                subCmd.preform(player, args);
            }
        }
    }

    @Override
    public String getUsage() {
        return "/ac " + getName() + " <Sub command> <Args>";
    }
}
