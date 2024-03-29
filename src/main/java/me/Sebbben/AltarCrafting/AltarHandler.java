package me.Sebbben.AltarCrafting;

import me.Sebbben.AltarCrafting.AltarActions.AddRecipe;
import me.Sebbben.AltarCrafting.AltarActions.AltarAction;
import me.Sebbben.AltarCrafting.AltarActions.AltarActionListener;
import me.Sebbben.AltarCrafting.AltarActions.CreateAltar;
import me.Sebbben.AltarCrafting.CustomConfigs.AltarsConfig;
import me.Sebbben.AltarCrafting.CustomConfigs.RecipesConfig;
import me.Sebbben.AltarCrafting.Files.Altar;
import me.Sebbben.AltarCrafting.Files.AltarRecipe;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.logging.Level;

public class AltarHandler {
    private final Main plugin;
    private final HashMap<String, Altar> altars = new HashMap<>();
    private final HashMap<String, List<AltarRecipe>> recipes = new HashMap<>();

    private AltarAction currentAction;
    public AltarHandler(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(new AltarActionListener(this), plugin);

    }

    public void saveAltars() {
        FileConfiguration config = AltarsConfig.get();
        for (Map.Entry<String, Altar> entry : altars.entrySet()) {
            String name = entry.getKey();
            for (Map.Entry<Vector<Integer>, Material> relPos : entry.getValue().getRelativePositions().entrySet()) {
                config.set(name + "." + relPos.getKey().toString(), relPos.getValue().toString());
            }
        }
        AltarsConfig.save();
    }
    public void loadAltars() {
        Set<String> rawAltars = AltarsConfig.get().getKeys(false);
        for (String key : rawAltars) {
            Altar altar = new Altar(key);

            HashMap<Vector<Integer>, Material> altarBlocks = new HashMap<>();

            Set<String> altarPositions = AltarsConfig.get().getConfigurationSection(key).getKeys(false);
            for (String position : altarPositions) {
                Vector<Integer> pos = new Vector<>();
                String newPosition = position.substring(1,position.length()-1);
                for (String s : newPosition.split(", ")) {
                    pos.add(Integer.decode(s));
                }
                Material mat = Material.matchMaterial(AltarsConfig.get().getString(key + "." + position));
                altarBlocks.put(pos, mat);
            }
            altar.setRelativePositions(altarBlocks);
            altars.put(key, altar);
        }
    }

    public void saveRecipes() {
        FileConfiguration config = RecipesConfig.get();
        for (Map.Entry<String, List<AltarRecipe>> entry : recipes.entrySet()) {
            String basePath = entry.getKey();
            for (int i=0; i<entry.getValue().size(); i++) {
                AltarRecipe recipe = entry.getValue().get(i);
                String recipePath = basePath + "." + i;

                for (int j=0; j<recipe.getMaterials().size(); j++) {

                    String materialPath = recipePath + ".materials." + j;
                    config.set(materialPath, recipe.getMaterials().get(j));
                }

                for (int j=0; j<recipe.getResult().size(); j++) {
                    String resultPath = recipePath + ".results." + j;
                    config.set(resultPath, recipe.getResult().get(j));
                }
            }
        }
        RecipesConfig.save();
    }

    public void loadRecipes() {
        FileConfiguration config = RecipesConfig.get();
        Set<String> altarRecipes = config.getKeys(false);
        for (String altar : altarRecipes) {
            for (String recipeNum : config.getConfigurationSection(altar).getKeys(false)) {
                List<ItemStack> materials = new ArrayList<>();
                List<ItemStack> results = new ArrayList<>();


                for (String materialNum : config.getConfigurationSection(altar + "." + recipeNum + ".materials").getKeys(false)) {
                    materials.add(config.getItemStack(altar + "." + recipeNum + ".materials." + materialNum));
                }
                for (String resultNum : config.getConfigurationSection(altar + "." + recipeNum + ".results").getKeys(false)) {
                    results.add(config.getItemStack(altar + "." + recipeNum + ".results." + resultNum));
                }

                AltarRecipe recipe = new AltarRecipe(materials, results);

                addRecipe(altar, recipe);

            }
        }
    }


    public void newAltar(String name) {
        currentAction = new CreateAltar(this, name);
    }
    public void addAltar(String name, Altar altar) {altars.put(name, altar);}

    public void finishAction(Player player) {
        if (currentAction == null) return;
        currentAction.finish(player);
        currentAction = null;
    }

    public Set<String> getAltarNames() {return altars.keySet();}

    public void cancelAction(Player player) {
        if (currentAction == null) return;
        currentAction.cancel(player);
        currentAction = null;
    }

    public void removeAltar(String altarName) {
        altars.remove(altarName);
        AltarsConfig.get().set(altarName, null);
        AltarsConfig.save();
    }

    public void createRecipe(String altarName) {
        currentAction = new AddRecipe(this, altarName);
    }

    public void addRecipe(String altarName, AltarRecipe recipe) {
        recipes.computeIfAbsent(altarName, k -> new ArrayList<>());
        recipes.get(altarName).add(recipe);
        saveRecipes();
    }

    public List<AltarRecipe> getRecipesFor(String altarName) {
        return recipes.get(altarName);
    }
}
