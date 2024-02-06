package me.Sebbben.AltarCrafting;

import me.Sebbben.AltarCrafting.commands.AltarCraftingCommand;
import me.Sebbben.AltarCrafting.customSaveFiles.AltarConfigurationHandler;
import me.Sebbben.AltarCrafting.listeners.AltarPlacedListener;
import me.Sebbben.AltarCrafting.managers.AltarBlueprintsManager;
import me.Sebbben.AltarCrafting.managers.AltarManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private AltarBlueprintsManager altarBlueprintsManager;
    private AltarManager altarManager;
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        AltarConfigurationHandler.setup();
        this.altarBlueprintsManager = new AltarBlueprintsManager();
        this.altarManager = new AltarManager();
        AltarCraftingCommand acCommand = new AltarCraftingCommand("altarCrafting");

        this.getCommand("altarCrafting").setExecutor(acCommand);
        this.getCommand("altarCrafting").setTabCompleter(acCommand);

        this.getServer().getPluginManager().registerEvents(new AltarPlacedListener(), this);
    }

    @Override
    public void onDisable() {
        this.altarBlueprintsManager.saveAltars();
    }

    public AltarBlueprintsManager getAltarBlueprintsManager() {
        return this.altarBlueprintsManager;
    }
    public AltarManager getAltarManager() {
        return this.altarManager;
    }
}
