package org.bonge.convert.world;

import com.flowpowered.math.vector.Vector3d;
import org.bonge.Bonge;
import org.bonge.convert.Converter;
import org.bukkit.Location;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.world.World;

import java.io.IOException;

public class TransformConverter implements Converter<Location, Transform<World>> {
    @Override
    public Class<Transform<World>> getSpongeClass() {
        return (Class<Transform<World>>)(Object)Transform.class;
    }

    @Override
    public Class<Location> getBukkitClass() {
        return Location.class;
    }

    @Override
    public Transform<World> from(Location value) {
        try {
            World world = Bonge.getInstance().convert(World.class, value.getWorld());
            return new Transform<>(world, new Vector3d(value.getX(), value.getY(), value.getZ()), new Vector3d(value.getPitch(), value.getYaw(), 0));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Location to(Transform<World> value) {
        try {
            org.bukkit.World world = Bonge.getInstance().convert(value.getExtent(), org.bukkit.World.class);
            Location loc = new Location(world, value.getPosition().getX(), value.getPosition().getY(), value.getPosition().getZ());
            loc.setPitch((float)value.getPitch());
            loc.setYaw((float)value.getYaw());
            return loc;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
