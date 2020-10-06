package org.bonge.bukkit.r1_15.block;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BongeTag<K extends Keyed> implements Tag<K> {

    private final Set<K> materials = new HashSet<>();
    private final NamespacedKey key;
    private final String extra;

    public BongeTag(NamespacedKey key, String extra, K... materials){
        this(key, extra, Arrays.asList(materials));
    }

    public BongeTag(NamespacedKey key, String extra, Collection<K> materials){
        this.key = key;
        this.extra = extra;
        this.materials.addAll(materials);
    }

    public String getExtra(){
        return this.extra;
    }

    @Override
    public boolean isTagged(@NotNull K item) {
        return this.getValues().stream().anyMatch(m -> m.equals(item));
    }

    @Override
    public @NotNull Set<K> getValues() {
        return this.materials;
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return this.key;
    }

    @Override
    public String toString(){
        return "Bonge[Namespace: " + this.key.toString() + "]";
    }
}
