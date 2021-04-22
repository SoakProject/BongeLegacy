package org.bonge.bukkit.r1_16.entity.living;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.bukkit.r1_16.entity.IEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.entity.memory.MemoryKey;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.util.Ticks;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ILivingEntity<T extends org.spongepowered.api.entity.living.Living> extends IEntity<T>, LivingEntity {

    @Override
    default void sendMessage(@Nullable UUID sender, @NotNull String message) {

    }

    @Override
    default void sendMessage(@Nullable UUID sender, @NotNull String[] messages) {

    }

    @Override
    default int getArrowCooldown() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void setArrowCooldown(int ticks) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default int getArrowsInBody() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default void setArrowsInBody(int count) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void attack(@NotNull Entity target) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void swingMainHand() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void swingOffHand() {
        throw new NotImplementedException("Not got to yet");

    }

    @NotNull
    @Override
    default Set<UUID> getCollidableExemptions() {
        throw new NotImplementedException("Not got to yet");

    }

    @NotNull
    @Override
    default EntityCategory getCategory() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default void setInvisible(boolean invisible) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean isInvisible() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default double getAbsorptionAmount() {
        return this.getSpongeValue().getDouble(Keys.DAMAGE_ABSORPTION).orElse(0);
    }

    @Override
    default void setAbsorptionAmount(double amount) {
        this.getSpongeValue().offer(Keys.DAMAGE_ABSORPTION, amount);
    }

    @Override
    default double getEyeHeight() {
        return this.getSpongeValue().get(Keys.EYE_HEIGHT).get();
    }

    @Override
    default double getEyeHeight(boolean ignorePose) {
        //TODO check ignorePose
        return this.getEyeHeight();
    }

    @Override
    default @NotNull Location getEyeLocation() {
        return Bonge.getInstance().convert(this.getSpongeValue().world().location(this.getSpongeValue().get(Keys.EYE_POSITION).get()));
    }

    @NotNull
    @Override
    default List<Block> getLineOfSight(@Nullable Set<Material> transparent, int maxDistance) {
        //TODO no idea how to do this
        /*List<Block> blocks = new ArrayList<>();
        BlockRay<World> ray = BlockRay.from(this.getSpongeValue()).distanceLimit(maxDistance).skipFilter(r -> {
            if(transparent == null){
                return false;
            }
            Material material;
            try {
                material = Bonge.getInstance().convert(Material.class, r.getLocation().getBlockType());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
            return transparent.stream().anyMatch(m -> m.equals(material));
        }).build();
        while(ray.hasNext()){
            blocks.add(new BongeBlock(ray.next().getLocation()));
        }
        return blocks;*/
        throw new NotImplementedException("LivingEntity.getLineOfSight(Set<Material>, int) not got to yet");
    }

    @Override
    default @NotNull Block getTargetBlock(@Nullable Set<Material> transparent, int maxDistance) {
        return null;
    }

    @NotNull
    @Override
    default List<Block> getLastTwoTargetBlocks(@Nullable Set<Material> transparent, int maxDistance) {
        return null;
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
        //TODO - there is a maximum?
        return 0;
    }

    @Override
    default void setMaximumNoDamageTicks(int ticks) {
        //TODO - there is a maximum?
    }

    @Override
    default double getLastDamage() {
        return this.getSpongeValue().get(Keys.LAST_DAMAGE_RECEIVED).orElse(0.0);

    }

    @Override
    default void setLastDamage(double damage) {
        this.getSpongeValue().offer(Keys.LAST_DAMAGE_RECEIVED, damage);
    }

    @Override
    default int getNoDamageTicks() {
        return (int) this.getSpongeValue().get(Keys.INVULNERABILITY_TICKS).get().ticks();
    }

    @Override
    default void setNoDamageTicks(int ticks) {
        this.getSpongeValue().offer(Keys.INVULNERABILITY_TICKS, Ticks.of(ticks));
    }

    @Nullable
    @Override
    default Player getKiller() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default boolean addPotionEffect(@NotNull PotionEffect effect) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean addPotionEffect(@NotNull PotionEffect effect, boolean force) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean addPotionEffects(@NotNull Collection<PotionEffect> effects) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean hasPotionEffect(@NotNull PotionEffectType type) {
        throw new NotImplementedException("Not got to yet");

    }

    @Nullable
    @Override
    default PotionEffect getPotionEffect(@NotNull PotionEffectType type) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void removePotionEffect(@NotNull PotionEffectType type) {
        throw new NotImplementedException("Not got to yet");

    }

    @NotNull
    @Override
    default Collection<PotionEffect> getActivePotionEffects() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean hasLineOfSight(@NotNull Entity other) {
        return this.getSpongeValue().canSee(((BongeAbstractEntity<?>) other).getSpongeValue());
    }

    @Override
    default boolean getRemoveWhenFarAway() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void setRemoveWhenFarAway(boolean remove) {
        throw new NotImplementedException("Not got to yet");

    }

    @Nullable
    @Override
    default EntityEquipment getEquipment() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void setCanPickupItems(boolean pickup) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean getCanPickupItems() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean isLeashed() {
        throw new NotImplementedException("Not got to yet");

    }

    @NotNull
    @Override
    default Entity getLeashHolder() throws IllegalStateException {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean setLeashHolder(@Nullable Entity holder) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean isGliding() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void setGliding(boolean gliding) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean isSwimming() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void setSwimming(boolean swimming) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean isRiptiding() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void setAI(boolean ai) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean hasAI() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void setCollidable(boolean collidable) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean isCollidable() {
        throw new NotImplementedException("Not got to yet");

    }

    @Nullable
    @Override
    default AttributeInstance getAttribute(@NotNull Attribute attribute) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void damage(double amount) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void damage(double amount, @Nullable Entity source) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default double getHealth() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void setHealth(double health) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default double getMaxHealth() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void setMaxHealth(double health) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void resetMaxHealth() {
        throw new NotImplementedException("Not got to yet");

    }

    @NotNull
    @Override
    default <P extends Projectile> P launchProjectile(@NotNull Class<? extends P> projectile) {
        throw new NotImplementedException("Not got to yet");

    }

    @NotNull
    @Override
    default <P extends Projectile> P launchProjectile(@NotNull Class<? extends P> projectile, @Nullable Vector velocity) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default @Nullable Block getTargetBlockExact(int maxDistance) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default @Nullable Block getTargetBlockExact(int maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default @Nullable RayTraceResult rayTraceBlocks(double maxDistance) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default @Nullable RayTraceResult rayTraceBlocks(double maxDistance, @NotNull FluidCollisionMode fluidCollisionMode) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default boolean isSleeping() {
        return this.getSpongeValue().get(Keys.IS_SLEEPING).get();
    }

    @Override
    default <T> @Nullable T getMemory(@NotNull MemoryKey<T> memoryKey) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default <T> void setMemory(@NotNull MemoryKey<T> memoryKey, @Nullable T memoryValue) {
        throw new NotImplementedException("Not got to yet");

    }
}
