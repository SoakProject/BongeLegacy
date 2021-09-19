package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.NetherWartsState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class NetherWarts extends MaterialData {

    public NetherWarts() {
        super(BlockTypes.NETHER_WART.get().defaultState());
    }

    public NetherWarts(NetherWartsState state){
        this();
        this.setState(state);
    }

    public NetherWarts(Material material) {
        super(material);
    }

    public NetherWarts(Material material, byte data) {
        super(material, data);
    }

    public NetherWartsState getState(){
        int stage = this.spongeValue.get(Keys.GROWTH_STAGE).get();
        switch (stage){
            case 0: return NetherWartsState.SEEDED;
            case 1: return NetherWartsState.STAGE_ONE;
            case 2: return NetherWartsState.STAGE_TWO;
            default: return NetherWartsState.RIPE;
        }
    }

    public void setState(NetherWartsState state){
        int stage = 0;
        switch (state){
            case SEEDED: stage = 0; break;
            case STAGE_ONE: stage = 1; break;
            case STAGE_TWO: stage = 2; break;
            case RIPE: stage = 3; break;
            default: throw new NotImplementedException("NetherWarts.setState(NetherWartsState) unknown state of " + state.name());
        }
    }
}
