package org.bonge.convert.world;

import org.bonge.convert.Converter;
import org.bukkit.block.BlockFace;
import org.spongepowered.api.util.Direction;

public class DirectionConverter implements Converter<BlockFace, Direction> {
    @Override
    public Class<Direction> getSpongeClass() {
        return Direction.class;
    }

    @Override
    public Class<BlockFace> getBukkitClass() {
        return BlockFace.class;
    }

    @Override
    public Direction from(BlockFace direction) {
        switch (direction){
            case NORTH: return Direction.NORTH;
            case NORTH_NORTH_EAST: return Direction.NORTH_NORTHEAST;
            case NORTH_EAST: return Direction.NORTHEAST;
            case EAST_NORTH_EAST: return Direction.EAST_NORTHEAST;
            case EAST: return Direction.EAST;
            case EAST_SOUTH_EAST: return Direction.EAST_SOUTHEAST;
            case SOUTH_EAST: return Direction.SOUTHEAST;
            case SOUTH_SOUTH_EAST: return Direction.SOUTH_SOUTHEAST;
            case SOUTH: return Direction.SOUTH;
            case SOUTH_SOUTH_WEST: return Direction.SOUTH_SOUTHWEST;
            case SOUTH_WEST: return Direction.SOUTHWEST;
            case WEST_SOUTH_WEST: return Direction.WEST_SOUTHWEST;
            case WEST: return Direction.WEST;
            case WEST_NORTH_WEST: return Direction.WEST_NORTHWEST;
            case NORTH_WEST: return Direction.NORTHWEST;
            case NORTH_NORTH_WEST: return Direction.NORTH_NORTHWEST;
            case UP: return Direction.UP;
            case DOWN: return Direction.DOWN;
            case SELF: return Direction.NONE;
            default: throw new IllegalStateException("Unknown direction of " + direction.name());
        }
    }

    @Override
    public BlockFace to(Direction direction) {
        switch (direction){
            case NORTH: return BlockFace.NORTH;
            case NORTH_NORTHEAST: return BlockFace.NORTH_NORTH_EAST;
            case NORTHEAST: return BlockFace.NORTH_EAST;
            case EAST_NORTHEAST: return BlockFace.EAST_NORTH_EAST;
            case EAST: return BlockFace.EAST;
            case EAST_SOUTHEAST: return BlockFace.EAST_SOUTH_EAST;
            case SOUTHEAST: return BlockFace.SOUTH_EAST;
            case SOUTH_SOUTHEAST: return BlockFace.SOUTH_SOUTH_EAST;
            case SOUTH: return BlockFace.SOUTH;
            case SOUTH_SOUTHWEST: return BlockFace.SOUTH_SOUTH_WEST;
            case SOUTHWEST: return BlockFace.SOUTH_WEST;
            case WEST_SOUTHWEST: return BlockFace.WEST_SOUTH_WEST;
            case WEST: return BlockFace.WEST;
            case WEST_NORTHWEST: return BlockFace.WEST_NORTH_WEST;
            case NORTHWEST: return BlockFace.NORTH_WEST;
            case NORTH_NORTHWEST: return BlockFace.NORTH_NORTH_WEST;
            case UP: return BlockFace.UP;
            case DOWN: return BlockFace.DOWN;
            case NONE: return BlockFace.SELF;
            default: throw new IllegalStateException("Unknown direction of " + direction.name());
        }
    }
}
