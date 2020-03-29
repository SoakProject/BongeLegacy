package org.bonge.convert;

import org.bukkit.block.BlockFace;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.scoreboard.DisplaySlot;
import org.spongepowered.api.boss.BossBarColor;
import org.spongepowered.api.boss.BossBarColors;
import org.spongepowered.api.boss.BossBarOverlay;
import org.spongepowered.api.boss.BossBarOverlays;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.entity.living.player.gamemode.GameModes;
import org.spongepowered.api.scoreboard.displayslot.DisplaySlots;
import org.spongepowered.api.util.Direction;

public class EnumConvert {

    public static DisplaySlot getDisplaySlot(org.spongepowered.api.scoreboard.displayslot.DisplaySlot slot){
        if(slot.equals(DisplaySlots.BELOW_NAME)){
            return DisplaySlot.BELOW_NAME;
        }
        if(slot.equals(DisplaySlots.LIST)){
            return DisplaySlot.PLAYER_LIST;
        }
        if(slot.equals(DisplaySlots.SIDEBAR)){
            return DisplaySlot.SIDEBAR;
        }
        throw new IllegalStateException("Unknown Display Slot of " + slot.getId());
    }

    public static org.spongepowered.api.scoreboard.displayslot.DisplaySlot getDisplaySlot(DisplaySlot slot){
        switch (slot){
            case BELOW_NAME: return DisplaySlots.BELOW_NAME;
            case PLAYER_LIST: return DisplaySlots.LIST;
            case SIDEBAR: return DisplaySlots.SIDEBAR;
            default: throw new IllegalStateException("Unknown Display Slot of " + slot.name());
        }
    }

    public static BossBarOverlay getStyle(BarStyle style){
        switch (style){
            case SOLID: return BossBarOverlays.PROGRESS;
            case SEGMENTED_6: return BossBarOverlays.NOTCHED_6;
            case SEGMENTED_10: return BossBarOverlays.NOTCHED_10;
            case SEGMENTED_12: return BossBarOverlays.NOTCHED_12;
            case SEGMENTED_20: return BossBarOverlays.NOTCHED_20;
            default: throw new IllegalStateException("Unknown style of " + style.name());
        }
    }

    public static org.bukkit.boss.BarStyle getStyle(org.spongepowered.api.boss.BossBarOverlay overlay){
        if(overlay.equals(BossBarOverlays.NOTCHED_6)){
            return BarStyle.SEGMENTED_6;
        }else if(overlay.equals(BossBarOverlays.NOTCHED_10)){
            return BarStyle.SEGMENTED_10;
        }else if(overlay.equals(BossBarOverlays.NOTCHED_12)){
            return BarStyle.SEGMENTED_12;
        }else if(overlay.equals(BossBarOverlays.NOTCHED_20)) {
            return BarStyle.SEGMENTED_20;
        }else if(overlay.equals(BossBarOverlays.PROGRESS)){
            return BarStyle.SOLID;
        }else{
            throw new IllegalStateException("Unknown style of " + overlay.getId());
        }
    }

    public static org.bukkit.boss.BarColor getColour(BossBarColor colour){
        if(colour.equals(BossBarColors.BLUE)){
            return BarColor.BLUE;
        }else if(colour.equals(BossBarColors.GREEN)){
            return BarColor.GREEN;
        }else if(colour.equals(BossBarColors.PINK)){
            return BarColor.PINK;
        }else if(colour.equals(BossBarColors.PURPLE)){
            return BarColor.PURPLE;
        }else if(colour.equals(BossBarColors.RED)){
            return BarColor.RED;
        }else if(colour.equals(BossBarColors.WHITE)){
            return BarColor.WHITE;
        }else if(colour.equals(BossBarColors.YELLOW)){
            return BarColor.YELLOW;
        }else{
            throw new IllegalStateException("Unknown bar colour of " + colour.getId());
        }
    }

    public static BossBarColor getColour(BarColor colour){
        switch (colour){
            case PINK: return BossBarColors.PINK;
            case BLUE: return BossBarColors.BLUE;
            case RED: return BossBarColors.RED;
            case GREEN: return BossBarColors.GREEN;
            case YELLOW: return BossBarColors.YELLOW;
            case PURPLE: return BossBarColors.PURPLE;
            case WHITE: return BossBarColors.WHITE;
            default: throw new IllegalStateException("Unknown bar colour of " + colour.name());
        }
    }

    public static org.bukkit.block.BlockFace getFace(Direction direction){
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

    public static Direction getDirection(org.bukkit.block.BlockFace direction){
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

    public static org.bukkit.GameMode getGamemode(GameMode gamemode){
        if (gamemode == GameModes.ADVENTURE){
            return org.bukkit.GameMode.ADVENTURE;
        }
        if (gamemode == GameModes.CREATIVE){
            return org.bukkit.GameMode.CREATIVE;
        }
        if (gamemode == GameModes.SPECTATOR){
            return org.bukkit.GameMode.SPECTATOR;
        }
        return org.bukkit.GameMode.SURVIVAL;
    }

    public static GameMode getGamemode(org.bukkit.GameMode mode){
        switch (mode){
            case ADVENTURE: return GameModes.ADVENTURE;
            case CREATIVE: return GameModes.CREATIVE;
            case SPECTATOR: return GameModes.SPECTATOR;
            default: return GameModes.SURVIVAL;
        }
    }
}
