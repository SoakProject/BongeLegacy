package org.bonge.bukkit.r1_16.entity.living.monster;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

public class BongeEnderman extends BongeAbstractEntity<org.spongepowered.api.entity.living.monster.Enderman> implements IMonster<org.spongepowered.api.entity.living.monster.Enderman>, Enderman {

    public BongeEnderman(org.spongepowered.api.entity.living.monster.Enderman entity) {
        super(entity);
    }

    @Override
    @Deprecated
    public @NotNull MaterialData getCarriedMaterial() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    @Deprecated
    public void setCarriedMaterial(@NotNull MaterialData material) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public BlockData getCarriedBlock() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setCarriedBlock(BlockData blockData) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public double getEyeHeight() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public @NotNull Location getEyeLocation() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ENDERMAN;
    }
}
