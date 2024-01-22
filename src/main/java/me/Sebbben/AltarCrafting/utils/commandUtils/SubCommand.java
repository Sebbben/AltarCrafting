package me.Sebbben.AltarCrafting.utils.commandUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SubCommand {
    private final HashMap<String, SubCommand> subCommands = new HashMap<>();
    private CommandFunction commandFunction;
    private String commandName;

    public SubCommand(String commandName) {
        this.commandName = commandName;
    }

    public SubCommand addSubCommand(String commandName) {this.subCommands.put(commandName, new SubCommand(commandName)); return this.subCommands.get(commandName);}

    public SubCommand getSubCommand(String subCommandPath) {
        if (this.subCommands.containsKey(subCommandPath)) return this.subCommands.get(subCommandPath);
        String subCommand = subCommandPath.substring(0,subCommandPath.indexOf("."));
        if (this.subCommands.containsKey(subCommand)) {
            return this.subCommands.get(subCommand).getSubCommand(subCommandPath.substring(subCommand.indexOf(".",subCommandPath.length())));
        } else return null;
    }

    public void setCommandFunction(CommandFunction commandFunction) {this.commandFunction = commandFunction;}

    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            // execute a command without args
            if (this.commandFunction != null) return this.commandFunction.execute(sender, command, label, args);
            // cant execute command because the command needs arguments
            return false;
        }

        String[] newArgs = Arrays.copyOfRange(args, 1, args.length);

        if (this.subCommands.containsKey(args[0])) {
            return this.subCommands.get(args[0]).execute(sender, command, label, newArgs); // Execute subcommand, but remove the first arg
        } else if (this.commandFunction != null) {
            return this.commandFunction.execute(sender, command, label, args);
        } else {
            return false;
        }
    }

    public List<String> getTabCompletes(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || (this.subCommands.isEmpty() && this.commandFunction == null)) {
            return new ArrayList<>(); //  No function
        }

        if (!this.subCommands.isEmpty()) {
            if (this.subCommands.containsKey(args[0])) {
                return this.subCommands.get(args[0]).getTabCompletes(sender, command, label, Arrays.copyOfRange(args, 1, args.length));
            } else {
                ArrayList<String> out = new ArrayList<>();
                StringUtil.copyPartialMatches(args[0], this.subCommands.keySet(), out);
                return out;
            }
        } else {
            return this.commandFunction.getValidArgs(sender, command, label, args); // last argument
        }
    }


}