package me.Sebbben.AltarCrafting;

import me.Sebbben.AltarCrafting.AltarFiles.AltarHandler;
import me.Sebbben.AltarCrafting.AltarFiles.AltarsConfig;
import me.Sebbben.AltarCrafting.Commands.altarCommand;
import me.Sebbben.AltarCrafting.Commands.altarCraftingTabComplete;
import me.Sebbben.AltarCrafting.Listeners.PreventUsageOfCustomItems;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;



        this.getCommand("altarCrafting").setExecutor(new altarCommand());
        this.getCommand("altarCrafting").setTabCompleter(new altarCraftingTabComplete());
        this.getServer().getPluginManager().registerEvents(new PreventUsageOfCustomItems(),this);

        // Setup configs
        saveDefaultConfig();



        AltarsConfig.setup(this);
        AltarsConfig.get().options().copyDefaults(true);
        AltarsConfig.save();


        AltarHandler.loadAltars();

    }

    @Override
    public void onDisable() {
        AltarHandler.saveAltars();
    }



}
