package org.bonge.bukkit.r1_13.inventory.item.recipe;

import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetype;

import java.util.Optional;

public interface BongeRecipe <R extends Recipe>{

    R getRecipe();

    Optional<RecipeChoice> getChoice(char cha);

    boolean isValidInventory(InventoryArchetype archetype);
    boolean updateInventory(Inventory inventory);

    default boolean isValidInventory(Inventory inventory){
        return isValidInventory(inventory.getArchetype());
    }

    static <R extends Recipe> Optional<BongeRecipe<R>> of(R recipe){
        if(recipe instanceof ShapedRecipe){
            return Optional.of((BongeRecipe<R>) new BongeSharedRecipe((ShapedRecipe) recipe));
        }
        return Optional.empty();
    }

}
