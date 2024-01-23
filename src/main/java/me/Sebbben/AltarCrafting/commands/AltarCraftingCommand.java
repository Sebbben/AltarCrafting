package me.Sebbben.AltarCrafting.commands;

import me.Sebbben.AltarCrafting.commands.AltarCommandFunctions.CreateAltarCommandFunction;
import me.Sebbben.AltarCrafting.commands.AltarCommandFunctions.ListAltarsCommandFunction;
import me.Sebbben.AltarCrafting.utils.commandUtils.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AltarCraftingCommand extends SubCommand implements TabExecutor {
    public AltarCraftingCommand(String commandName) {
        super("altarCrafting");

        this.addSubCommand("altar");
        this.getSubCommand("altar").addSubCommand("create").setCommandFunction(new CreateAltarCommandFunction());
        this.getSubCommand("altar").addSubCommand("list").setCommandFunction(new ListAltarsCommandFunction());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return this.execute(commandSender, command, s, strings);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return this.getTabCompletes(commandSender, command, s, strings);
    }
}
