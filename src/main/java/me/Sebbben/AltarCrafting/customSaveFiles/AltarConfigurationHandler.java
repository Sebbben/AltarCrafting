package me.Sebbben.AltarCrafting.customSaveFiles;

import me.Sebbben.AltarCrafting.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AltarConfigurationHandler {
    private static File file;
    private static YamlConfiguration config;

    public static void setup() {
        file = new File(Main.getInstance().getDataFolder(), "AltarConfig.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public static YamlConfiguration get() {
        return config;
    }
    public static void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println("Could not save to AltarConfig.yml");
        }
    }

}
