package org.bonge.bukkit.r1_16.entity.living.animal.horse.Llama;

import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TraderLlama;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class BongeTraderLlama extends BongeAbstractEntity<org.spongepowered.api.entity.living.animal.horse.llama.TraderLlama> implements BongeAbstractLlama<org.spongepowered.api.entity.living.animal.horse.llama.TraderLlama>, TraderLlama {

    public BongeTraderLlama(org.spongepowered.api.entity.living.animal.horse.llama.TraderLlama entity) {
        super(entity);
    }

    @Override
    public void setVelocity(@NotNull Vector vector) {
        BongeAbstractLlama.super.setVelocity(vector);
    }

    @Override
    public @NotNull Vector getVelocity() {
        return BongeAbstractLlama.super.getVelocity();
    }

    @NotNull
    @Override
    public EntityType getType() {
        return EntityType.TRADER_LLAMA;
    }
}
