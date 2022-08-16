package me.Sebbben.AltarCrafting.Commands.SubCommands;

import me.Sebbben.AltarCrafting.AltarHandler;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Set;

public class listAltarsCommand extends Subcommand {


    public listAltarsCommand(AltarHandler altarHandler) {
        super(altarHandler);
    }

    @Override
    public String getName() {
        return "listAltars";
    }

    @Override
    public List<String> getArgs() {
        return null;
    }

    @Override
    public void preform(Player player, String[] args) {
        Set<String> names = altarHandler.getAltarNames();
        for (String name : names) {
            player.sendMessage(name);
        }
    }

    @Override
    public String getUsage() {
        return "/ac " + getName();
    }
}
