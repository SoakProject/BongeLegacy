package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.RailDirection;
import org.spongepowered.api.data.type.RailDirections;

@Deprecated
public class Rails extends MaterialData {

    public Rails() {
        super(BlockTypes.RAIL.get().defaultState());
    }

    public Rails(Material material) {
        super(material);
    }

    public Rails(Material material, byte data) {
        super(material, data);
    }

    public boolean isOnSlope(){
        RailDirection direction = this.spongeValue.get(Keys.RAIL_DIRECTION).get();
        if(direction.equals(RailDirections.ASCENDING_EAST) || direction.equals(RailDirections.ASCENDING_NORTH) || direction.equals(RailDirections.ASCENDING_SOUTH) || direction.equals(RailDirections.ASCENDING_WEST)){
            return true;
        }
        return false;
    }

    public boolean isCurve(){
        RailDirection direction = this.spongeValue.get(Keys.RAIL_DIRECTION).get();
        if(direction.equals(RailDirections.NORTH_WEST) || direction.equals(RailDirections.NORTH_EAST) || direction.equals(RailDirections.SOUTH_WEST) || direction.equals(RailDirections.SOUTH_EAST)){
            return true;
        }
        return false;
    }

    //REQUIRED TO COMPILE
    protected byte getConvertedData(){
        return 0;
    }

    //ERR .... WHAT?
    public BlockFace getDirection(){
        throw new NotImplementedException("Rails.getDirection() not got to yet");
    }

    public void setDirection(BlockFace face, boolean sloping){
        throw new NotImplementedException("Rails.setDirection(BlockFace, boolean) not got to yet");
    }

}
