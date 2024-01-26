package me.Sebbben.AltarCrafting.commands.AltarCommandFunctions;

import me.Sebbben.AltarCrafting.managers.AltarManager;
import me.Sebbben.AltarCrafting.Main;
import me.Sebbben.AltarCrafting.utils.commandUtils.CommandFunction;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlaceAltarCommandFunction extends CommandFunction {
    private AltarManager altarManager = Main.getInstance().getAltarManager();
    @Override
    public List<String> getValidArgs(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 1) {
            Set<String> names = Main.getInstance().getAltarManager().getAltarNames();
            List<String> valid = new ArrayList<>(names.size());
            valid.addAll(names);
            return valid;
        }
        return null;
    }

    @Override
    public boolean execute(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command");
            return false;
        }
        Player player = (Player) sender;

        if (args.length < 1) {
            sender.sendMessage("Wrong usage of command");
        }

        Location location;
        BlockIterator iter = new BlockIterator(player, 10);

        Block last = null;
        while (iter.hasNext()) {
            last = iter.next();
            if (last.getType().equals(Material.AIR)) continue;
            break;
        }

        if (last == null) {
            sender.sendMessage("Could not find target block to place multiblock on");
            return false;
        }

        location = last.getLocation().add(0,1,0);

        this.altarManager.placeAltar(location, args[0]);
        return true;
    }
}
