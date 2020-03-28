package org.bonge.convert;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import org.bonge.bukkit.world.BongeWorld;
import org.bukkit.util.Vector;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.world.World;

public class LocationConvert {

    public static Vector3d toVector3d(Vector vector){
        return new Vector3d(vector.getX(), vector.getY(), vector.getZ());
    }

    public static Vector3i toVector3i(Vector vector){
        return new Vector3i(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ());
    }

    public static Vector toVector(Vector3d vector){
        return new Vector(vector.getX(), vector.getY(), vector.getZ());
    }

    public static Vector toVector(Vector3i vector3i){
        return new Vector(vector3i.getX(), vector3i.getY(), vector3i.getZ());
    }

    public static org.spongepowered.api.world.Location<World> toLocation(org.bukkit.Location loc){
        return ((BongeWorld)loc.getWorld()).getSpongeValue().getLocation(loc.getX(), loc.getY(), loc.getZ());
    }

    public static Transform<World> toTransform(org.bukkit.Location loc){
        Transform<World> transform = new Transform<>(toLocation(loc));
        transform.addRotation(new Vector3d(loc.getPitch(), loc.getYaw(), 0));
        return transform;
    }
}
