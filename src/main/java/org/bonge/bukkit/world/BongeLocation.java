package org.bonge.bukkit.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.spongepowered.api.entity.Transform;

public class BongeLocation extends Location {

    public BongeLocation(Transform<org.spongepowered.api.world.World> transform){
        this(new BongeWorld(transform.getExtent()), transform.getPosition().getX(), transform.getPosition().getY(), transform.getPosition().getZ(), (float) transform.getYaw(), (float) transform.getPitch());
    }

    public BongeLocation(org.spongepowered.api.world.Location<org.spongepowered.api.world.World> loc){
        this(new BongeWorld(loc.getExtent()), loc.getX(), loc.getY(), loc.getZ());
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

    public org.spongepowered.api.world.Location<org.spongepowered.api.world.World> getSpongeLocation(){
        return ((BongeWorld)this.getWorld()).getSpongeValue().getLocation(this.getX(), this.getY(), this.getZ());
    }
}
