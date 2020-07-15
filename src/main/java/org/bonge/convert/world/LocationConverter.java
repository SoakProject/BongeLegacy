package org.bonge.convert.world;

import org.bonge.Bonge;
import org.bonge.convert.Converter;
import org.bukkit.Location;
import org.spongepowered.api.world.World;

import java.io.IOException;

public class LocationConverter implements Converter<Location, org.spongepowered.api.world.Location<? extends World>> {
    @Override
    public Class<org.spongepowered.api.world.Location<? extends World>> getSpongeClass() {
        return (Class<org.spongepowered.api.world.Location<? extends World>>)(Object)org.spongepowered.api.world.Location.class;
    }

    @Override
    public Class<Location> getBukkitClass() {
        return Location.class;
    }

    @Override
    public org.spongepowered.api.world.Location<? extends World> from(Location value) throws IOException{
        World world = Bonge.getInstance().convert(value.getWorld(), World.class);
        return world.getLocation(value.getX(), value.getY(), value.getZ());
    }

    @Override
    public Location to(org.spongepowered.api.world.Location<? extends World> value) throws IOException{
        org.bukkit.World world = Bonge.getInstance().convert(org.bukkit.World.class, value.getWorld());
        return new Location(world, value.getX(), value.getY(), value.getZ());
    }
}
