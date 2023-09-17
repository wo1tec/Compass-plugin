package org.storm.mc.compass;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Compass extends JavaPlugin {

    private String specialCompassDisplayName;

    @Override
    public void onEnable() {
        loadConfig();
        getServer().getPluginManager().registerEvents(new CompassListener(specialCompassDisplayName), this);

        registerCustomCompassRecipe();
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();

        specialCompassDisplayName = ChatColor.translateAlternateColorCodes('&', getConfig().getString("special_compass.display_name"));
    }

    private void registerCustomCompassRecipe() {
        ItemStack specialCompass = new ItemStack(Material.COMPASS);
        ItemMeta meta = specialCompass.getItemMeta();
        meta.setDisplayName(specialCompassDisplayName);
        specialCompass.setItemMeta(meta);

        NamespacedKey key = new NamespacedKey(this, "special_coordinates_compass");
        ShapedRecipe recipe = new ShapedRecipe(key, specialCompass);

        List<String> recipePattern = getConfig().getStringList("special_compass.recipe");
        recipe.shape(recipePattern.toArray(new String[0]));

        ConfigurationSection ingredientsSection = getConfig().getConfigurationSection("special_compass.ingredients");
        if (ingredientsSection != null) {
            for (String symbol : ingredientsSection.getKeys(false)) {
                char ingredientSymbol = symbol.charAt(0);
                String ingredientName = ingredientsSection.getString(symbol);
                Material ingredientMaterial = Material.matchMaterial(ingredientName);

                if (ingredientMaterial != null) {
                    recipe.setIngredient(ingredientSymbol, ingredientMaterial);
                }
            }
        }
        Bukkit.addRecipe(recipe);
    }
}

