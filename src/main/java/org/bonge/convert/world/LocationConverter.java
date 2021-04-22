package org.bonge.convert.world;

import org.bonge.Bonge;
import org.bonge.convert.Converter;
import org.bukkit.Location;
import org.spongepowered.api.world.World;

import java.io.IOException;

public class LocationConverter implements Converter<Location, org.spongepowered.api.world.Location<? extends World, ? extends org.spongepowered.api.world.Location<?, ?>>> {
    @Override
    public Class<org.spongepowered.api.world.Location<? extends World, ? extends org.spongepowered.api.world.Location<?, ?>>> getSpongeClass() {
        return (Class<org.spongepowered.api.world.Location<? extends World, ? extends org.spongepowered.api.world.Location<?, ?>>>) (Object) org.spongepowered.api.world.Location.class;
    }

    @Override
    public Class<Location> getBukkitClass() {
        return Location.class;
    }

    @Override
    public org.spongepowered.api.world.Location<? extends World, ? extends org.spongepowered.api.world.Location<?, ?>> from(Location value) throws IOException {
        World world = Bonge.getInstance().convert(value.getWorld(), World.class);
        return world.location(value.getX(), value.getY(), value.getZ());
    }

    @Override
    public Location to(org.spongepowered.api.world.Location<? extends World, ?> value) throws IOException {
        org.bukkit.World world = Bonge.getInstance().convert(org.bukkit.World.class, value.world());
        return new Location(world, value.x(), value.y(), value.z());
    }
}
