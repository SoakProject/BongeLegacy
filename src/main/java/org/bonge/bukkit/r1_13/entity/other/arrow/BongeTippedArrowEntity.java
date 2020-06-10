package org.bonge.bukkit.r1_13.entity.other.arrow;

import org.bukkit.Color;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TippedArrow;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class BongeTippedArrowEntity extends BongeAbstractArrowEntity<org.spongepowered.api.entity.projectile.arrow.TippedArrow> implements TippedArrow {

    public BongeTippedArrowEntity(org.spongepowered.api.entity.projectile.arrow.TippedArrow entity) {
        super(entity);
    }

    @Override
    public void setBasePotionData(PotionData data) {

    }

    @Override
    public PotionData getBasePotionData() {
        return null;
    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public boolean hasCustomEffects() {
        return false;
    }

    @Override
    public List<PotionEffect> getCustomEffects() {
        return null;
    }

    @Override
    public boolean addCustomEffect(PotionEffect effect, boolean overwrite) {
        return false;
    }

    @Override
    public boolean removeCustomEffect(PotionEffectType type) {
        return false;
    }

    @Override
    public boolean hasCustomEffect(PotionEffectType type) {
        return false;
    }

    @Override
    public void clearCustomEffects() {

    }

    @Override
    public EntityType getType() {
        return EntityType.TIPPED_ARROW;
    }
}
