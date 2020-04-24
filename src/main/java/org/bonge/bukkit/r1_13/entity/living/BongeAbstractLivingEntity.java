package org.bonge.bukkit.r1_13.entity.living;

import org.bonge.bukkit.r1_13.block.BongeBlock;
import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.util.blockray.BlockRay;
import org.spongepowered.api.world.World;

import java.util.*;

public abstract class BongeAbstractLivingEntity<T extends org.spongepowered.api.entity.living.Living> extends BongeAbstractEntity<T> implements LivingEntity {

    public BongeAbstractLivingEntity(T entity) {
        super(entity);
    }

    @NotNull
    @Override
    public List<Block> getLineOfSight(@Nullable Set<Material> transparent, int maxDistance) {
        List<Block> blocks = new ArrayList<>();
        BlockRay<World> ray = BlockRay.from(this.spongeValue).distanceLimit(maxDistance).skipFilter(r -> {
            if(transparent == null){
                return false;
            }
            Material material = Material.getMaterial(r.getLocation().getBlockType());
            return transparent.stream().anyMatch(m -> m.equals(material));
        }).build();
        while(ray.hasNext()){
            blocks.add(new BongeBlock(ray.next().getLocation()));
        }
        return blocks;
    }

    @NotNull
    @Override
    public Block getTargetBlock(@Nullable Set<Material> transparent, int maxDistance) {
        return null;
    }

    @NotNull
    @Override
    public List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> transparent, int maxDistance) {
        return null;
    }

    @Override
    public int getRemainingAir() {
        return this.spongeValue.get(Keys.REMAINING_AIR).get();
    }

    @Override
    public void setRemainingAir(int ticks) {
        this.spongeValue.offer(Keys.REMAINING_AIR, ticks);
    }

    @Override
    public int getMaximumAir() {
        return this.spongeValue.get(Keys.MAX_AIR).get();
    }

    @Override
    public void setMaximumAir(int ticks) {
        this.spongeValue.offer(Keys.MAX_AIR, ticks);
    }

    @Override
    public int getMaximumNoDamageTicks() {
        //TODO - there is a maximum?
        return 0;
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {
        //TODO - there is a maximum?
    }

    @Override
    public double getLastDamage() {
        return this.spongeValue.get(Keys.LAST_DAMAGE).get().orElse(0.0);

    }

    @Override
    public void setLastDamage(double damage) {
        this.spongeValue.offer(Keys.LAST_DAMAGE, Optional.of(damage));
    }

    @Override
    public int getNoDamageTicks() {
        return this.spongeValue.get(Keys.INVULNERABILITY_TICKS).get();
    }

    @Override
    public void setNoDamageTicks(int ticks) {
        this.spongeValue.offer(Keys.INVULNERABILITY_TICKS, ticks);
    }

    @Nullable
    @Override
    public Player getKiller() {
        //TODO - damage event stuff
        return null;
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect effect) {
        return false;
    }

    @Override
    public boolean addPotionEffect(@NotNull PotionEffect effect, boolean force) {
        return false;
    }

    @Override
    public boolean addPotionEffects(@NotNull Collection<PotionEffect> effects) {
        return false;
    }

    @Override
    public boolean hasPotionEffect(@NotNull PotionEffectType type) {
        return false;
    }

    @Nullable
    @Override
    public PotionEffect getPotionEffect(@NotNull PotionEffectType type) {
        return null;
    }

    @Override
    public void removePotionEffect(@NotNull PotionEffectType type) {

    }

    @NotNull
    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return null;
    }

    @Override
    public boolean hasLineOfSight(@NotNull Entity other) {
        return this.spongeValue.canSee(((BongeAbstractEntity<?>)other).getSpongeValue());
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        //TODO find out what this is in sponge
        return false;
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {

    }

    @Nullable
    @Override
    public EntityEquipment getEquipment() {
        return null;
    }

    @Override
    public void setCanPickupItems(boolean pickup) {

    }

    @Override
    public boolean getCanPickupItems() {
        return false;
    }

    @Override
    public boolean isLeashed() {
        return false;
    }

    @NotNull
    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        return null;
    }

    @Override
    public boolean setLeashHolder(@Nullable Entity holder) {
        return false;
    }

    @Override
    public boolean isGliding() {
        return false;
    }

    @Override
    public void setGliding(boolean gliding) {

    }

    @Override
    public boolean isSwimming() {
        return false;
    }

    @Override
    public void setSwimming(boolean swimming) {

    }

    @Override
    public boolean isRiptiding() {
        return false;
    }

    @Override
    public void setAI(boolean ai) {

    }

    @Override
    public boolean hasAI() {
        return false;
    }

    @Override
    public void setCollidable(boolean collidable) {

    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Nullable
    @Override
    public AttributeInstance getAttribute(@NotNull Attribute attribute) {
        return null;
    }

    @Override
    public void damage(double amount) {

    }

    @Override
    public void damage(double amount, @Nullable Entity source) {

    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public void setHealth(double health) {

    }

    @Override
    public double getMaxHealth() {
        return 0;
    }

    @Override
    public void setMaxHealth(double health) {

    }

    @Override
    public void resetMaxHealth() {

    }

    @NotNull
    @Override
    public <P extends Projectile> P launchProjectile(@NotNull Class<? extends P> projectile) {
        return null;
    }

    @NotNull
    @Override
    public <P extends Projectile> P launchProjectile(@NotNull Class<? extends P> projectile, @Nullable Vector velocity) {
        return null;
    }
}
