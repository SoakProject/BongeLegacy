package org.bonge.bukkit.r1_16.world;

import org.bukkit.Location;
import org.bukkit.World;

public class BongeLocation extends Location {

    public BongeLocation(org.spongepowered.api.world.Location<? extends org.spongepowered.api.world.World, ? extends org.spongepowered.api.world.Location<?, ?>> loc){
        this(new BongeWorld(loc.world()), loc.x(), loc.y(), loc.z());
    }

    public BongeLocation(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public BongeLocation(World world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z, yaw, pitch);
    }

    public BongeLocation(Location location){
        super(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    public org.spongepowered.api.world.Location<? extends org.spongepowered.api.world.World, ? extends org.spongepowered.api.world.Location<?, ?>> getSpongeLocation(){
        return ((BongeWorld)this.getWorld()).getSpongeValue().location(this.getX(), this.getY(), this.getZ());
    }
}
