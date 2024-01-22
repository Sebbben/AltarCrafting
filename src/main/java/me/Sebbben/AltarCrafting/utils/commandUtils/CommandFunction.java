package me.Sebbben.AltarCrafting.utils.commandUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class CommandFunction {
    public abstract List<String> getValidArgs(CommandSender sender, Command command, String label, String[] args);
    public abstract boolean execute(CommandSender sender, Command command, String label, String[] args);
}