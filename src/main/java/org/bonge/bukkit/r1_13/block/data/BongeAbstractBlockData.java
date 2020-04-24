package org.bonge.bukkit.r1_13.block.data;

import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;

import java.util.HashMap;
import java.util.Map;

public abstract class BongeAbstractBlockData extends BongeWrapper<org.spongepowered.api.block.BlockState> implements IBongeBlockData {

    private static final Map<BlockType, BongeAbstractBlockData> BLOCKS = new HashMap<>();

    public BongeAbstractBlockData(BlockState value) {
        super(value);
    }

    @Override
    public Material getMaterial() {
        return Material.getMaterial(this.spongeValue.getType());
    }

    @Override
    public String getAsString() {
        return this.spongeValue.getId();
    }

    @Override
    public BlockData merge(BlockData data) {
        BlockState state = this.spongeValue;
        BlockState state2 = ((BongeAbstractBlockData)data).spongeValue;
        return this.newInstance(state.merge(state2));
    }

    @Override
    public boolean matches(BlockData data) {
        return this.spongeValue.getId().equals(data.getAsString());
    }

    @Override
    public BlockData clone(){
        return newInstance(this.spongeValue);
    }

    private static void register(BlockType material, BongeAbstractBlockData clazz){
        BLOCKS.put(material, clazz);
    }

    public static BlockData of(org.spongepowered.api.block.BlockState state){
        BongeAbstractBlockData bongeState = BLOCKS.get(state.getType());
        if(bongeState != null){
            return bongeState.newInstance(state);
        }
        return new BongeSimpleBlockData(state);
    }
}
