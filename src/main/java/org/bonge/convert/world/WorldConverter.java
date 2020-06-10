package org.bonge.convert.world;

import org.bonge.bukkit.r1_13.world.BongeWorld;
import org.bonge.convert.Converter;
import org.bukkit.World;

import java.io.IOException;

public class WorldConverter implements Converter<World, org.spongepowered.api.world.World> {
    @Override
    public Class<org.spongepowered.api.world.World> getSpongeClass() {
        return org.spongepowered.api.world.World.class;
    }

    @Override
    public Class<World> getBukkitClass() {
        return World.class;
    }

    @Override
    public org.spongepowered.api.world.World from(World value) throws IOException {
        if(value instanceof BongeWorld){
            return ((BongeWorld)value).getSpongeValue();
        }
        throw new IOException("Unknown world class");
    }

    @Override
    public World to(org.spongepowered.api.world.World value) {
        return new BongeWorld(value);
    }
}
