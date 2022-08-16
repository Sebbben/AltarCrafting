package me.Sebbben.AltarCrafting.Commands.SubCommands;

import me.Sebbben.AltarCrafting.AltarHandler;
import org.bukkit.entity.Player;

import java.util.List;

public class removeAltarCommand extends Subcommand {



    public removeAltarCommand(AltarHandler altarHandler) {
        super(altarHandler);
    }

    @Override
    public String getName() {
        return "removeAltar";
    }

    @Override
    public List<String> getArgs() {
        return altarHandler.getAltarNames().stream().toList();
    }

    @Override
    public void preform(Player player, String[] args) {
        if (args.length >= 2) {
            altarHandler.removeAltar(args[1]);
            player.sendMessage(args[1] + " was removed");
        }
    }

    @Override
    public String getUsage() {
        return "/ac " + getName() + " <AltarName>";
    }
}
