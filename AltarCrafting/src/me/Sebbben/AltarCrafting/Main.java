package me.Sebbben.AltarCrafting;

import me.Sebbben.AltarCrafting.CustomConfigs.AltarsConfig;
import me.Sebbben.AltarCrafting.Commands.altarCommandManager;
import me.Sebbben.AltarCrafting.Listeners.PreventUsageOfCustomItems;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {

    private static Main instance;
    private AltarHandler altarHandler;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        this.getServer().getPluginManager().registerEvents(new PreventUsageOfCustomItems(),this);

        // Setup configs
        saveDefaultConfig();

        AltarsConfig.setup(this);
        AltarsConfig.get().options().copyDefaults(true);
        AltarsConfig.save();

        altarHandler = new AltarHandler(this);
        altarHandler.loadAltars();

        altarCommandManager manager = new altarCommandManager(altarHandler);

        this.getCommand("altarCrafting").setExecutor(manager);
        this.getCommand("altarCrafting").setTabCompleter(manager);

    }

    @Override
    public void onDisable() {
        altarHandler.saveAltars();
    }

    public void log(Level level, String s) {
        getLogger().log(level, s);
    }

}
