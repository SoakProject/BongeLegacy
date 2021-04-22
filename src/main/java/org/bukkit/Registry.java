package org.bukkit;

import org.array.utils.ArrayUtils;
import org.bonge.Bonge;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.registry.GameRegistry;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public interface Registry<T extends Keyed> extends Iterable<T> {

    //TODO Registry<Advancement> ADVANCEMENT = new SimpleRegistry<>(Advancement.class, AdvancementType.class);
    //TODO Registry<Art> ART = new SimpleRegistry<>(Art.class, ArtType.class);
    //TODO Registry<Biome> BIOME = new AdventureRegistry<>(Biome.class, BiomeType.class);
    //TODO Registry<KeyedBossBar> BOSS_BARS;
    //TODO Registry<Enchantment> ENCHANTMENT = new AdventureRegistry<>(Enchantment.class, EnchantmentType.class);
    //TODO Registry<EntityType> ENTITY_TYPE = new AdventureRegistry<>(EntityType.class, org.spongepowered.api.entity.EntityType.class);
    //TODO find sponge loot table Registry<LootTable> LOOT_TABLES = new SimpleRegistry<>(LootTable.class, org.spongepowered.api.util.weighted.LootTable.class);
    //TODO Registry<Statistic> STATISTIC = new AdventureRegistry<>(Statistic.class, org.spongepowered.api.statistic.Statistic.class);
    //TODO Registry<Material> MATERIAL = new AdventureListRegistry<>(() -> Arrays.asList(Material.values()));
    //TODO Registry<Villager.Profession> VILLAGER_PROFESSION = new AdventureRegistry<>(Villager.Profession.class, ProfessionType.class);
    //TODO Registry<Villager.Type> VILLAGER_TYPE = new AdventureRegistry<>(Villager.Type.class, VillagerType.class);
    //TODO find sponge alternative Registry<MemoryKey> MEMORY_MODULE_TYPE = new SimpleRegistry<>(MemoryKey.class, );

    final class AdventureListRegistry<T extends Keyed> implements Registry<T> {

        private Supplier<Collection<T>> supplier;

        public AdventureListRegistry(final T... collection) {
            this(Arrays.asList(collection));
        }

        public AdventureListRegistry(final Collection<T> collection) {
            this(() -> collection);
        }

        public AdventureListRegistry(Supplier<Collection<T>> supplier) {
            this.supplier = supplier;
        }

        @Override
        public T get(NamespacedKey key) {
            return this.supplier.get().stream().filter(e -> e.getKey().equals(key)).findAny().orElse(null);
        }

        @NotNull
        @Override
        public Iterator<T> iterator() {
            return this.supplier.get().iterator();
        }
    }

    final class AdventureRegistry<T extends Keyed, S extends net.kyori.adventure.key.Keyed> implements Registry<T> {

        private Class<S> sponge;
        private Class<T> bukkit;
        private Function<GameRegistry, org.spongepowered.api.adventure.AdventureRegistry.OfType<S>> function;

        public AdventureRegistry(Class<T> bukkit, Function<GameRegistry, org.spongepowered.api.adventure.AdventureRegistry.OfType<S>> function, Class<S> sponge) {
            this.sponge = sponge;
            this.bukkit = bukkit;
            this.function = function;
        }

        public Class<S> getSpongeKeyClass() {
            return this.sponge;
        }

        public Class<T> getBukkitKeyClass() {
            return this.bukkit;
        }

        @Override
        public T get(NamespacedKey key) {
            net.kyori.adventure.key.Keyed type = this.function.apply(Sponge.registry()).value(key.toString()).orElse(null);
            if (type == null) {
                return null;
            }
            try {
                return Bonge.getInstance().convert(this.getBukkitKeyClass(), type);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @NotNull
        @Override
        public Iterator<T> iterator() {
            org.spongepowered.api.adventure.AdventureRegistry.OfType<S> ofType = this.function.apply(Sponge.registry());
            List<S> collection = ofType.keys().stream().map(s -> ofType.value(s).orElse(null)).filter(Objects::nonNull).collect(Collectors.toList());
            return ArrayUtils.convert(e -> {
                try {
                    return Bonge.getInstance().convert(this.getBukkitKeyClass(), e);
                } catch (IOException ex) {
                    throw new IllegalStateException(ex);
                }
            }, collection).iterator();
        }
    }

    T get(NamespacedKey key);

}
