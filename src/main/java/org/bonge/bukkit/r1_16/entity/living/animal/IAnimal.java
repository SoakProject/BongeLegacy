package org.bonge.bukkit.r1_16.entity.living.animal;

import org.bonge.bukkit.r1_16.entity.living.IAgable;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IAnimal<T extends org.spongepowered.api.entity.living.animal.Animal> extends IAgable<T>, Animals {

    @Override
    default boolean canBreed() {
        return IAgable.super.canBreed();
    }

    @Override
    default void setBreed(boolean breed) {
        IAgable.super.setBreed(breed);
    }

    @Override
    default boolean getAgeLock() {
        return IAgable.super.getAgeLock();
    }

    @Override
    default void setAgeLock(boolean lock) {
        IAgable.super.setAgeLock(lock);
    }

    @Override
    default UUID getBreedCause() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setBreedCause(UUID uuid) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default @NotNull List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default Block getTargetBlock(Set<Material> transparent, int maxDistance) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default int getRemainingAir() {
        return this.getSpongeValue().get(Keys.REMAINING_AIR).get();
    }

    @Override
    default void setRemainingAir(int ticks) {
        this.getSpongeValue().offer(Keys.REMAINING_AIR, ticks);
    }

    @Override
    default int getMaximumAir() {
        return this.getSpongeValue().get(Keys.MAX_AIR).get();
    }

    @Override
    default void setMaximumAir(int ticks) {
        this.getSpongeValue().offer(Keys.MAX_AIR, ticks);
    }

    @Override
    default int getMaximumNoDamageTicks() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setMaximumNoDamageTicks(int ticks) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default double getLastDamage() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setLastDamage(double damage) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default int getNoDamageTicks() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setNoDamageTicks(int ticks) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default Player getKiller() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean addPotionEffect(PotionEffect effect) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean addPotionEffect(PotionEffect effect, boolean force) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean addPotionEffects(Collection<PotionEffect> effects) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean hasPotionEffect(PotionEffectType type) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default PotionEffect getPotionEffect(PotionEffectType type) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void removePotionEffect(PotionEffectType type) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default Collection<PotionEffect> getActivePotionEffects() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean hasLineOfSight(Entity other) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean getRemoveWhenFarAway() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setRemoveWhenFarAway(boolean remove) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default EntityEquipment getEquipment() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setCanPickupItems(boolean pickup) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean getCanPickupItems() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean isLeashed() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default Entity getLeashHolder() throws IllegalStateException {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean setLeashHolder(Entity holder) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean isGliding() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setGliding(boolean gliding) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean isSwimming() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setSwimming(boolean swimming) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean isRiptiding() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setAI(boolean ai) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean hasAI() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setCollidable(boolean collidable) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean isCollidable() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default AttributeInstance getAttribute(Attribute attribute) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void damage(double amount) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void damage(double amount, Entity source) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default double getHealth() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setHealth(double health) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default double getMaxHealth() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setMaxHealth(double health) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void resetMaxHealth() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default <P extends Projectile> P launchProjectile(Class<? extends P> projectile) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default <P extends Projectile> P launchProjectile(Class<? extends P> projectile, Vector velocity) {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default boolean isLoveMode() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default int getLoveModeTicks() {
        throw new NotImplementedException("yet to look at");

    }

    @Override
    default void setLoveModeTicks(int ticks) {
        throw new NotImplementedException("yet to look at");

    }
}
