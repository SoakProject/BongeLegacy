package org.bonge.bukkit.r1_13.inventory.item.recipe;

import org.bonge.convert.InventoryConvert;
import org.bonge.util.ArrayUtils;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;

import java.util.Optional;

public class BongeSharedRecipe implements BongeRecipe<ShapedRecipe> {

    private final ShapedRecipe recipe;

    public BongeSharedRecipe(ShapedRecipe recipe){
        this.recipe = recipe;
    }

    public int getColumns(){
        return ArrayUtils.getBest(String::length, (c, b) -> c > b, this.recipe.getShape()).get().length();
    }

    public int getRows(){
        return this.recipe.getShape().length;
    }

    public Optional<RecipeChoice> getChoice(int r, int c){
        try {
            char at = this.recipe.getShape()[r].charAt(c);
            return this.getChoice(at);
        }catch (IndexOutOfBoundsException e){
            return Optional.empty();
        }
    }

    public Optional<RecipeChoice> getChoice(int i){
        Character at = null;
        int count = 0;
        for(String row : this.recipe.getShape()){
            for(int col = 0; col < row.length(); col++){
                if(count == i){
                    at = row.charAt(col);
                    break;
                }
                count++;
            }
            if(at != null){
                break;
            }
        }
        if(at == null){
            return Optional.empty();
        }
        return this.getChoice(at);
    }

    @Override
    public ShapedRecipe getRecipe() {
        return this.recipe;
    }

    @Override
    public Optional<RecipeChoice> getChoice(char cha) {
        return Optional.ofNullable(this.recipe.getChoiceMap().get(cha));
    }

    @Override
    public boolean isValidInventory(InventoryArchetype archetype) {
        return archetype.equals(InventoryArchetypes.CRAFTING);
    }

    @Override
    public boolean updateInventory(Inventory inventory) {
        for(int A = 0; A < 9; A++){
            Optional<Slot> opSlot = inventory.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotIndex.of(A)));
            if(!opSlot.isPresent()){
                continue;
            }
            org.spongepowered.api.item.inventory.ItemStack stack = opSlot.get().peek().get();
            Optional<RecipeChoice> opChoice = this.getChoice(A);
            if(!opChoice.isPresent()){
                return false;
            }
            if (opChoice.get().test(InventoryConvert.getItemStack(stack))){
                continue;
            }
            return false;
        }
        inventory.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotIndex.of(0))).set(InventoryConvert.getItemStack(this.recipe.getResult()).get());
        return true;
    }
}
