package org.bonge.bukkit.r1_14.entity.living.monster;

import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;

public class BongeEnderman extends BongeAbstractMonster<org.spongepowered.api.entity.living.monster.Enderman> implements Enderman {

    public BongeEnderman(org.spongepowered.api.entity.living.monster.Enderman entity) {
        super(entity);
    }

    @Override
    @Deprecated
    public MaterialData getCarriedMaterial() {
        return null;
    }

    @Override
    @Deprecated
    public void setCarriedMaterial(MaterialData material) {

    }

    @Override
    public BlockData getCarriedBlock() {
        return null;
    }

    @Override
    public void setCarriedBlock(BlockData blockData) {

    }

    @Override
    public double getEyeHeight() {
        return 0;
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return 0;
    }

    @Override
    public Location getEyeLocation() {
        return null;
    }

    @Override
    public EntityType getType() {
        return EntityType.ENDERMAN;
    }
}
