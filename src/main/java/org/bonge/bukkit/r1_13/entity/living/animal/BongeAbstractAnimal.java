package org.bonge.bukkit.r1_13.entity.living.animal;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.loot.LootTable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.spongepowered.api.data.key.Keys;

import java.io.IOException;
import java.util.*;

public abstract class BongeAbstractAnimal<T extends org.spongepowered.api.entity.living.animal.Animal> extends BongeAbstractEntity<T> implements Animals {

    public BongeAbstractAnimal(T entity) {
        super(entity);
    }

    @Override
    public UUID getBreedCause() {
        return null;
    }

    @Override
    public void setBreedCause(UUID uuid) {

    }

    @Override
    public int getAge() {
        return this.spongeValue.get(Keys.AGE).get();
    }

    @Override
    public void setAge(int age) {
        this.spongeValue.offer(Keys.AGE, age);
    }

    @Override
    public void setAgeLock(boolean lock) {

    }

    @Override
    public boolean getAgeLock() {
        return false;
    }

    @Override
    public void setBaby() {
        this.spongeValue.offer(Keys.IS_ADULT, false);
    }

    @Override
    public void setAdult() {
        this.spongeValue.offer(Keys.IS_ADULT, true);
    }

    @Override
    public boolean isAdult() {
        return this.spongeValue.get(Keys.IS_ADULT).get();
    }

    @Override
    public boolean canBreed() {
        return this.spongeValue.get(Keys.CAN_BREED).get();
    }

    @Override
    public void setBreed(boolean breed) {
        this.spongeValue.offer(Keys.CAN_BREED, breed);
    }

    @Override
    public void setTarget(LivingEntity target) {
        this.spongeValue.setTarget(((BongeAbstractEntity<?>)target).getSpongeValue());
    }

    @Override
    public LivingEntity getTarget() {
        Optional<org.spongepowered.api.entity.Entity> opEntity = this.spongeValue.getTarget();
        if(!opEntity.isPresent()){
            return null;
        }
        Entity entity = null;
        try {
            entity = Bonge.getInstance().convert(Entity.class, opEntity.get());
            if(entity instanceof LivingEntity){
                return (LivingEntity)entity;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
        return null;
    }

    @Override
    public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
        return null;
    }

    @Override
    public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
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
        return 0;
    }

    @Override
    public void setMaximumNoDamageTicks(int ticks) {

    }

    @Override
    public double getLastDamage() {
        return 0;
    }

    @Override
    public void setLastDamage(double damage) {

    }

    @Override
    public int getNoDamageTicks() {
        return 0;
    }

    @Override
    public void setNoDamageTicks(int ticks) {

    }

    @Override
    public Player getKiller() {
        return null;
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect) {
        return false;
    }

    @Override
    public boolean addPotionEffect(PotionEffect effect, boolean force) {
        return false;
    }

    @Override
    public boolean addPotionEffects(Collection<PotionEffect> effects) {
        return false;
    }

    @Override
    public boolean hasPotionEffect(PotionEffectType type) {
        return false;
    }

    @Override
    public PotionEffect getPotionEffect(PotionEffectType type) {
        return null;
    }

    @Override
    public void removePotionEffect(PotionEffectType type) {

    }

    @Override
    public Collection<PotionEffect> getActivePotionEffects() {
        return null;
    }

    @Override
    public boolean hasLineOfSight(Entity other) {
        return false;
    }

    @Override
    public boolean getRemoveWhenFarAway() {
        return false;
    }

    @Override
    public void setRemoveWhenFarAway(boolean remove) {

    }

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

    @Override
    public Entity getLeashHolder() throws IllegalStateException {
        return null;
    }

    @Override
    public boolean setLeashHolder(Entity holder) {
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

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        return null;
    }

    @Override
    public void damage(double amount) {

    }

    @Override
    public void damage(double amount, Entity source) {

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

    @Override
    public void setLootTable(LootTable table) {

    }

    @Override
    public LootTable getLootTable() {
        return null;
    }

    @Override
    public void setSeed(long seed) {

    }

    @Override
    public long getSeed() {
        return 0;
    }

    @Override
    public <P extends Projectile> P launchProjectile(Class<? extends P> projectile) {
        return null;
    }

    @Override
    public <P extends Projectile> P launchProjectile(Class<? extends P> projectile, Vector velocity) {
        return null;
    }
}
