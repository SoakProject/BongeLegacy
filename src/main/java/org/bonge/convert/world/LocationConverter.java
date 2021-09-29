package org.bonge.convert.world;

import org.bonge.Bonge;
import org.bonge.convert.Converter;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.math.vector.Vector3d;
import org.spongepowered.math.vector.Vector3i;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LocationConverter implements Converter<Location, org.spongepowered.api.world.Location<? extends World<?, ?>, ? extends org.spongepowered.api.world.Location<?, ?>>> {

    public Location to(@Nullable ResourceKey key, @NotNull Vector3i pos) {
        if (key == null) {
            return new Location(null, pos.x(), pos
                    .y(), pos.z());
        }
        CompletableFuture<ServerWorld> future = Sponge.server().worldManager().loadWorld(key);
        try {
            ServerWorld world = future.get();
            return new Location(Bonge.getInstance().convert(world), pos.x(), pos.y(), pos.z());
        } catch (InterruptedException | ExecutionException e) {
            return this.to(null, pos);
        }

    }

    @Override
    public Class<org.spongepowered.api.world.Location<? extends World<?, ?>, ? extends org.spongepowered.api.world.Location<?, ?>>> getSpongeClass() {
        return (Class<org.spongepowered.api.world.Location<? extends World<?, ?>, ? extends org.spongepowered.api.world.Location<?, ?>>>) (Object) org.spongepowered.api.world.Location.class;
    }

    @Override
    public Class<Location> getBukkitClass() {
        return Location.class;
    }

    @Override
    public org.spongepowered.api.world.Location<? extends World<?, ?>, ? extends org.spongepowered.api.world.Location<?, ?>> from(Location value) throws IOException {
        World<?, ?> world = Bonge.getInstance().convert(value.getWorld(), World.class);
        return world.location(new Vector3d(value.getX(), value.getY(), value.getZ()));
    }

    @Override
    public Location to(org.spongepowered.api.world.Location<? extends World<?, ?>, ?> value) throws IOException {
        org.bukkit.World world = Bonge.getInstance().convert(org.bukkit.World.class, value.world());
        return new Location(world, value.x(), value.y(), value.z());
    }
}
