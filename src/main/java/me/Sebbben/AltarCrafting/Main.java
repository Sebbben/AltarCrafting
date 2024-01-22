package me.Sebbben.AltarCrafting;

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
    }

    @Override
    public void onDisable() {
    }

    public AltarManager getAltarManager() {
        return this.altarManager;
    }
}
