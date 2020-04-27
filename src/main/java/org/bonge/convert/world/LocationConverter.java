package org.bonge.convert.world;

import org.bonge.Bonge;
import org.bonge.convert.Converter;
import org.bukkit.Location;
import org.spongepowered.api.world.World;

import java.io.IOException;

public class LocationConverter implements Converter<Location, org.spongepowered.api.world.Location<World>> {
    @Override
    public Class<org.spongepowered.api.world.Location<World>> getSpongeClass() {
        return (Class<org.spongepowered.api.world.Location<World>>)(Object)org.spongepowered.api.world.Location.class;
    }

    @Override
    public Class<Location> getBukkitClass() {
        return Location.class;
    }

    @Override
    public org.spongepowered.api.world.Location<World> from(Location value) {
        try {
            World world = Bonge.getInstance().convert(World.class, value.getWorld());
            return world.getLocation(value.getX(), value.getY(), value.getZ());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Location to(org.spongepowered.api.world.Location<World> value) {
        try {
            org.bukkit.World world = Bonge.getInstance().convert(value.getExtent(), org.bukkit.World.class);
            return new Location(world, value.getX(), value.getY(), value.getZ());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
