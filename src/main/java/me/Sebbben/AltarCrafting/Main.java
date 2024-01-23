package me.Sebbben.AltarCrafting;

import me.Sebbben.AltarCrafting.commands.AltarCraftingCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private AltarManager altarManager;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.altarManager = new AltarManager();
        AltarCraftingCommand acCommand = new AltarCraftingCommand("altarCrafting");
        this.getCommand("altarCrafting").setExecutor(acCommand);
        this.getCommand("altarCrafting").setTabCompleter(acCommand);
    }

    @Override
    public void onDisable() {}

    public AltarManager getAltarManager() {
        return this.altarManager;
    }
}
