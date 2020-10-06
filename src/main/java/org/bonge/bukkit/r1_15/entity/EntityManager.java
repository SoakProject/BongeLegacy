package org.bonge.bukkit.r1_15.entity;

import org.bonge.bukkit.r1_15.inventory.BongeInventorySnapshot;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.MetadataValue;
import org.spongepowered.api.item.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EntityManager {

    public static final Key<Map<MetadataValue, String>> METADATA = new Key<>("Metadata");
    public static final Key<Location> LOCATION = new Key<>("Location");
    public static final Key<BongeInventorySnapshot<? extends Inventory>> INVENTORY = new Key<>("Inventory");

    public static final class Key<T> {

        private final String key;

        private Key(String key){
            this.key = key;
        }

        public String getKey(){
            return this.key;
        }

    }

    public static final class KeyHashMap extends HashMap<String, Object> {

        public <T> void putOrReplace(Key<T> key, T value){
            if(this.containsKey(key.getKey())){
                this.replace(key.getKey(), value);
                return;
            }
            this.put(key.getKey(), value);
        }

        public void remove(Key<?> key){
            this.remove(key.getKey());
        }

        public <T> void replace(Key<T> key, T value){
            this.replace(key.getKey(), value);
        }

        public <T> void put(Key<T> key, T value){
            this.put(key.getKey(), value);
        }

        public <T> T get(Key<T> key){
            return (T)this.get(key.getKey());
        }

        public <T> T getOrDefault(Key<T> key, T value){
            return (T)this.getOrDefault(key.getKey(), value);
        }
    }

    private final Map<UUID, KeyHashMap> persistentData = new HashMap<>();

    public KeyHashMap getData(Entity entity){
        return this.getData(entity.getUniqueId());
    }

    public KeyHashMap getData(UUID uuid){
        KeyHashMap set = this.persistentData.get(uuid);
        if(set != null){
            return set;
        }
        set = new KeyHashMap();
        this.persistentData.put(uuid, set);
        return set;
    }


}
