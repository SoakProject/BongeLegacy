package org.bonge.convert.world.vector;

import com.flowpowered.math.vector.Vector3i;
import org.bonge.convert.Converter;
import org.bukkit.util.Vector;

public class Vector3iConverter implements Converter<Vector, Vector3i> {
    @Override
    public Class<Vector3i> getSpongeClass() {
        return Vector3i.class;
    }

    @Override
    public Class<Vector> getBukkitClass() {
        return Vector.class;
    }

    @Override
    public Vector3i from(Vector value) {
        return new Vector3i(value.getBlockX(), value.getBlockY(), value.getBlockZ());
    }

    @Override
    public Vector to(Vector3i value) {
        return new Vector(value.getX(), value.getY(), value.getZ());
    }
}
