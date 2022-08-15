package me.Sebbben.AltarCrafting.Commands.SubCommands;

import org.bukkit.entity.Player;

import java.util.List;

public abstract class Subcommand {
    public abstract String getName();
    public abstract List<String> getArgs();
    public abstract void preform(Player player, String[] args);

    public abstract String getUsage();
}
