package me.Sebbben.AltarCrafting.Commands.SubCommands;

import me.Sebbben.AltarCrafting.AltarHandler;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Subcommand {

    protected final AltarHandler altarHandler;

    public Subcommand(AltarHandler altarHandler) {
        this.altarHandler = altarHandler;
    }

    public abstract String getName();
    public abstract List<String> getArgs();
    public abstract void preform(Player player, String[] args);

    public abstract String getUsage();
}
