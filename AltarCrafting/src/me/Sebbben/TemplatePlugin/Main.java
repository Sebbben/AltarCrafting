package me.Sebbben.TemplatePlugin;

import me.Sebbben.TemplatePlugin.Commands.TemplateCommand;
import me.Sebbben.TemplatePlugin.Listeners.TemplateListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("test").setExecutor(new TemplateCommand());
        this.getServer().getPluginManager().registerEvents(new TemplateListener(),this);
    }

    @Override
    public void onDisable() {

    }
}
