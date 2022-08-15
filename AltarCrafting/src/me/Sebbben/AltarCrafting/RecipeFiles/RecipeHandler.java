package me.Sebbben.AltarCrafting.RecipeFiles;


import me.Sebbben.AltarCrafting.Listeners.RecipeConfigurationListener;
import me.Sebbben.AltarCrafting.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeHandler {
    private static HashMap<String, List<AltarRecipe>> recipes;
    private static AltarRecipe currentRecipe;
    private static String currentAltar;
    private static final RecipeConfigurationListener recipeConfigurationListener = new RecipeConfigurationListener();
    public static void newRecipe(String altar) {
        currentRecipe = new AltarRecipe();
        currentAltar = altar;
        Main.getInstance().getServer().getPluginManager().registerEvents(recipeConfigurationListener, Main.getInstance());
    }

    public static boolean isCreatingRecipe() {
        return currentRecipe != null;
    }

    public static void cancelRecipe() {
        currentRecipe = null;
    }

    public static boolean recipeIsComplete() {
        return currentRecipe.isComplete();
    }

    public static void finishRecipe() {
        recipes.computeIfAbsent(currentAltar, k -> new ArrayList<>());
        recipes.get(currentAltar).add(currentRecipe);
        currentAltar = null;
        currentRecipe = null;
    }
}
