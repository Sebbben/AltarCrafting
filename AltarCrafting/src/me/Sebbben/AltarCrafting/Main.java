package me.Sebbben.AltarCrafting;

import me.Sebbben.AltarCrafting.Commands.altarCommand;
import me.Sebbben.AltarCrafting.Commands.altarCraftingTabComplete;
import me.Sebbben.AltarCrafting.Listeners.CornerSelectListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("altarCrafting").setExecutor(new altarCommand());
        this.getCommand("altarCrafting").setTabCompleter(new altarCraftingTabComplete());
        this.getServer().getPluginManager().registerEvents(new CornerSelectListener(),this);
    }

    @Override
    public void onDisable() {

    }
}
