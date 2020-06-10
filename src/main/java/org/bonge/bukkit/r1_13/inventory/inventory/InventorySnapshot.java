package org.bonge.bukkit.r1_13.inventory.inventory;

import org.bonge.util.InventoryListIterator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;

public class InventorySnapshot<I extends Inventory> implements Inventory {

    private final int size;
    private int maxStack;
    private final String name;
    private final String title;
    private final InventoryType type;
    private final InventoryHolder holder;
    private final Location location;
    protected final I inv;
    protected final boolean apply;
    protected final Map<Integer, ItemStack> items = new HashMap<>();

    public InventorySnapshot(I inventory, boolean apply){
        this.size = inventory.getSize();
        this.maxStack = inventory.getMaxStackSize();
        this.name = inventory.getName();
        this.title = inventory.getTitle();
        this.type = inventory.getType();
        this.holder = inventory.getHolder();
        this.location = inventory.getLocation();
        this.inv = inventory;
        this.apply = apply;
        for (int slot = 0; slot < inventory.getSize(); slot++){
            ItemStack stack = inventory.getItem(0);
            if(stack == null){
                continue;
            }
            this.items.put(slot, stack);
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getMaxStackSize() {
        return this.maxStack;
    }

    @Override
    public void setMaxStackSize(int size) {
        this.maxStack = size;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public void setItem(int index, ItemStack item) {
        if(this.apply){
            this.inv.setItem(index, item);
        }
        if(this.items.containsKey(index)){
            this.items.replace(index, item);
            return;
        }
        this.items.put(index, item);
    }

    @Override
    public HashMap<Integer, ItemStack> addItem(ItemStack... items) throws IllegalArgumentException {
        if(this.apply){
            this.inv.addItem(items);
        }
        HashMap<Integer, ItemStack> map = new HashMap<>();
        int target = 0;
        for(int A = 0; A < this.size; A++){
            if(this.items.get(A) == null){
                map.put(A, items[target]);
                target++;
                if(target == items.length){
                    break;
                }
            }
        }
        if(map.size() != items.length){
            return new HashMap<>();
        }
        this.items.putAll(map);
        return map;
    }

    @Override
    public HashMap<Integer, ItemStack> removeItem(ItemStack... items) throws IllegalArgumentException {
        if(this.apply){
            this.inv.removeItem(items);
        }
        HashMap<Integer, ItemStack> slots = new HashMap<>();
        int target = 0;
        for(int A = 0; A < this.size; A++){
            ItemStack item = this.items.get(A);
            if (item.equals(items[target])){
                slots.put(A, item);
                target++;
                if(target == items.length){
                    break;
                }
            }
        }
        if(slots.size() != items.length){
            return new HashMap<>();
        }
        slots.keySet().forEach(this.items::remove);
        return slots;
    }

    @Override
    public ItemStack[] getContents() {
        return this.items.values().toArray(new ItemStack[this.items.size()]);
    }

    @Override
    public void setContents(ItemStack[] items) throws IllegalArgumentException {
        if(this.apply){
            this.inv.setContents(items);
        }
    }

    @Override
    public ItemStack[] getStorageContents() {
        return new ItemStack[0];
    }

    @Override
    public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
        if(this.apply){
            this.inv.setStorageContents(items);
        }
    }

    @Override
    public boolean contains(Material material) throws IllegalArgumentException {
        return this.contains(i -> i.getType().equals(material));
    }

    @Override
    public boolean contains(ItemStack item) {
        return this.contains(i -> i.equals(item));
    }

    @Override
    public boolean contains(Material material, int amount) throws IllegalArgumentException {
        return this.contains(i -> i.getType().equals(material) && i.getAmount() == amount);
    }

    @Override
    public boolean contains(ItemStack item, int amount) {
        return this.contains(i -> {
            ItemStack i2 = i.clone();
            i2.setAmount(item.getAmount());
            return i2.equals(item) && i.getAmount() == amount;
        });
    }

    private boolean contains(Predicate<ItemStack> predicate){
        return this.items.entrySet().stream().anyMatch(e -> predicate.test(e.getValue()));
    }

    @Override
    public boolean containsAtLeast(ItemStack item, int amount) {
        return false;
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(Material material) throws IllegalArgumentException {
        return all(e -> e.getValue().getType().equals(material));
    }

    @Override
    public HashMap<Integer, ? extends ItemStack> all(ItemStack item) {
        return all(e -> e.getValue().equals(item));
    }

    public HashMap<Integer, ? extends ItemStack> all(Predicate<Map.Entry<Integer, ItemStack>> function){
        HashMap<Integer, ItemStack> map = new HashMap<>();
        this.items.entrySet().stream().filter(function).forEach(e -> map.put(e.getKey(), e.getValue()));
        return map;
    }

    @Override
    public int first(Material material) throws IllegalArgumentException {
        return first(i -> i != null && i.getType().equals(material));
    }

    @Override
    public int first(ItemStack item) {
        return first(item::equals);
    }

    @Override
    public int firstEmpty() {
        return first(Objects::isNull);
    }

    public int first(Predicate<ItemStack> predicate){
        for(int A = 0; A < this.items.size(); A++){
            ItemStack stack = this.items.get(A);
            if(predicate.test(stack)){
                return A;
            }
        }
        return -1;
    }

    @Override
    public void remove(Material material) throws IllegalArgumentException {
        if(this.apply){
            this.inv.remove(material);
        }
        this.all(material).keySet().forEach(this.items::remove);
    }

    @Override
    public void remove(ItemStack item) {
        if(this.apply){
            this.inv.remove(item);
        }
        this.all(item).keySet().forEach(this.items::remove);
    }

    @Override
    public void clear(int index) {
        if(this.apply){
            this.inv.clear(index);
        }
        this.items.remove(index);
    }

    @Override
    public void clear() {
        if(this.apply){
            this.inv.clear();
        }
        this.items.clear();
    }

    @Override
    public List<HumanEntity> getViewers() {
        return new ArrayList<>();
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public InventoryType getType() {
        return this.type;
    }

    @Override
    public InventoryHolder getHolder() {
        return this.holder;
    }

    @Override
    public @NotNull ListIterator<ItemStack> iterator() {
        return new InventoryListIterator(this);
    }

    @Override
    public ListIterator<ItemStack> iterator(int index) {
        return new InventoryListIterator(this).setUntil(index);
    }

    @Override
    public Location getLocation() {
        return this.location;
    }
}
