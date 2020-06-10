package org.bonge.convert.world;

import com.flowpowered.math.vector.Vector3d;
import org.bonge.Bonge;
import org.bonge.convert.Converter;
import org.bukkit.Location;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.world.World;

import java.io.IOException;

public class TransformConverter implements Converter<Location, Transform> {
    @Override
    public Class<Transform> getSpongeClass() {
        return Transform.class;
    }

    @Override
    public Class<Location> getBukkitClass() {
        return Location.class;
    }

    @Override
    public Transform from(Location value) throws IOException{
        World world = Bonge.getInstance().convert(value.getWorld(), World.class);
        return new Transform<>(world, new Vector3d(value.getX(), value.getY(), value.getZ()), new Vector3d(value.getPitch(), value.getYaw(), 0));
    }

    @Override
    public Location to(Transform value) throws IOException{
        org.bukkit.World world = Bonge.getInstance().convert(org.bukkit.World.class, value.getExtent());
        Location loc = new Location(world, value.getPosition().getX(), value.getPosition().getY(), value.getPosition().getZ());
        loc.setPitch((float)value.getPitch());
        loc.setYaw((float)value.getYaw());
        return loc;
    }
}
