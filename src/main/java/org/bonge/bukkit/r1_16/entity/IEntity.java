package org.bonge.bukkit.r1_16.entity;

import net.kyori.adventure.text.Component;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.server.BongeServer;
import org.bonge.bukkit.r1_16.world.BongeLocation;
import org.bonge.bukkit.r1_16.world.BongeWorld;
import org.bonge.util.WrappedArrayList;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pose;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.value.Value;
import org.spongepowered.api.util.Ticks;
import org.spongepowered.api.world.server.ServerLocation;
import org.spongepowered.math.vector.Vector3d;

import java.io.IOException;
import java.util.*;

public interface IEntity<E extends org.spongepowered.api.entity.Entity> extends Entity {

    E getSpongeValue();

    default EntityManager.KeyHashMap getData() {
        return ((BongeServer) Bukkit.getServer()).getEntityManager().getData(this);
    }

    @Override
    default void sendMessage(@Nullable UUID uuid, @NotNull String s) {

    }

    @Override
    default void sendMessage(@Nullable UUID uuid, @NotNull String[] strings) {

    }

    @Override
    default boolean isInWater() {
        return this.getSpongeValue().location().block().type().equals(BlockTypes.WATER.get());
    }

    @Override
    default @NotNull Location getLocation() {
        Location loc = this.getData().get(EntityManager.LOCATION);
        if (loc != null) {
            return loc;
        }
        BongeLocation location = new BongeLocation(this.getSpongeValue().location());
        location.setDirection(Bonge.getInstance().convert(this.getSpongeValue().rotation()));
        return location;
    }

    @Override
    default Location getLocation(Location location) {
        throw new NotImplementedException("Entity.getLocation(Location) not got to");
    }

    @Override
    default void setVelocity(@NotNull Vector vector) {
        this.getSpongeValue().offer(Keys.VELOCITY, Bonge.getInstance().convertDouble(vector));
    }

    @Override
    default @NotNull Vector getVelocity() {
        return Bonge.getInstance().convert(this.getSpongeValue().get(Keys.VELOCITY).get());
    }

    @Override
    default double getHeight() {
        return this.getSpongeValue().get(Keys.HEIGHT).get();
    }

    @Override
    default double getWidth() {
        return this.getSpongeValue().get(Keys.BASE_SIZE).get();
    }

    @Override
    default boolean isOnGround() {
        return this.getSpongeValue().onGround().get();
    }

    @Override
    default @NotNull World getWorld() {
        return new BongeWorld(this.getSpongeValue().world());
    }

    @Override
    default boolean teleport(@NotNull Location location) {
        return this.getSpongeValue().setLocationAndRotation((ServerLocation) Bonge.getInstance().convert(location), Bonge.getInstance().convertDouble(location.getDirection()));
    }

    @Override
    default boolean teleport(@NotNull Location location, PlayerTeleportEvent.@NotNull TeleportCause teleportCause) {
        return this.teleport(location);
    }

    @Override
    default boolean teleport(Entity entity) {
        return this.teleport(entity.getLocation());
    }

    @Override
    default boolean teleport(@NotNull Entity entity, PlayerTeleportEvent.@NotNull TeleportCause teleportCause) {
        return this.teleport(entity);
    }

    @Override
    default @NotNull List<Entity> getNearbyEntities(double v, double v1, double v2) {
        List<Entity> entities = new ArrayList<>(this.getWorld().getNearbyEntities(this.getLocation(), v, v1, v2));
        entities.remove(this);
        return Collections.unmodifiableList(entities);
    }

    @Override
    default int getEntityId() {
        return this.getSpongeValue().uniqueId().clockSequence();
    }

    @Override
    default int getFireTicks() {
        return (int) this.getSpongeValue().get(Keys.FIRE_TICKS).get().ticks();
    }

    @Override
    default int getMaxFireTicks() {
        return (int) this.getSpongeValue().get(Keys.FIRE_TICKS).get().ticks();
    }

    @Override
    default void setFireTicks(int i) {
        this.getSpongeValue().offer(Keys.FIRE_TICKS, Ticks.of(i));
    }

    @Override
    default void remove() {
        this.getSpongeValue().remove();
    }

    @Override
    default boolean isDead() {
        return this.getSpongeValue().isRemoved();
    }

    @Override
    default boolean isValid() {
        return true;
    }

    @Override
    default void sendMessage(@NotNull String s) {
        //TODO sending message to a none player entity?
    }

    @Override
    default void sendMessage(String[] strings) {
        //TODO sending message to a none player entity?
    }

    @Override
    default @NotNull Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    default @NotNull String getName() {
        Value.Mutable<Component> name = this.getSpongeValue().displayName();
        return Bonge.getInstance().convert(name.get());
    }

    @Override
    default boolean isPersistent() {
        return this.getSpongeValue().get(Keys.IS_PERSISTENT).get();
    }

    @Override
    default void setPersistent(boolean b) {
        this.getSpongeValue().offer(Keys.IS_PERSISTENT, b);
    }

    @Override
    @Deprecated
    default Entity getPassenger() {
        List<Entity> entities = this.getPassengers();
        if (entities.isEmpty()) {
            return null;
        }
        return entities.get(0);
    }

    @Override
    @Deprecated
    default boolean setPassenger(@NotNull Entity entity) {
        List<Entity> entities = this.getPassengers();
        entities.clear();
        return entities.add(entity);
    }

    @Override
    default @NotNull List<Entity> getPassengers() {
        Optional<List<org.spongepowered.api.entity.Entity>> opPassengers = this.getSpongeValue().get(Keys.PASSENGERS);
        if (!opPassengers.isPresent() || opPassengers.get().isEmpty()) {
            return Collections.emptyList();
        }

        return new WrappedArrayList<>(e -> {
            assert e != null;
            return opPassengers.get().add(((BongeAbstractEntity<?>) e).getSpongeValue());
        }, e -> {
            assert e != null;
            return opPassengers.get().remove(((BongeAbstractEntity<?>) e).getSpongeValue());
        });
    }

    @Override
    default boolean addPassenger(@NotNull Entity entity) {
        return this.getPassengers().add(entity);
    }

    @Override
    default boolean removePassenger(@NotNull Entity entity) {
        return this.getPassengers().remove(entity);
    }

    @Override
    default boolean isEmpty() {
        //TODO check what is meant to be empty
        return false;
    }

    @Override
    default boolean eject() {
        //TODO vehicle
        return false;
    }

    @Override
    default float getFallDistance() {
        return this.getSpongeValue().get(Keys.FALL_DISTANCE).get().floatValue();
    }

    @Override
    default void setFallDistance(float v) {
        this.getSpongeValue().offer(Keys.FALL_DISTANCE, (double) v);
    }

    @Override
    default void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
        //TODO check if sponge has such a thing
    }

    @Override
    default EntityDamageEvent getLastDamageCause() {
        //TODO check if sponge has such a thing
        return null;
    }

    @Override
    default @NotNull UUID getUniqueId() {
        return this.getSpongeValue().uniqueId();
    }

    @Override
    default int getTicksLived() {
        return 0;
    }

    @Override
    default void setTicksLived(int i) {

    }

    @Override
    default void playEffect(@NotNull EntityEffect entityEffect) {

    }

    @Override
    default boolean isInsideVehicle() {
        return getVehicle() != null;
    }

    @Override
    default boolean leaveVehicle() {
        return this.getSpongeValue().remove(Keys.VEHICLE).isSuccessful();
    }

    @Override
    default Entity getVehicle() {
        Optional<org.spongepowered.api.entity.Entity> opVe = this.getSpongeValue().get(Keys.VEHICLE);
        if (!opVe.isPresent()) {
            return null;
        }
        try {
            return Bonge.getInstance().convert(Entity.class, opVe.get());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    default void setCustomNameVisible(boolean b) {
        this.getSpongeValue().offer(Keys.IS_CUSTOM_NAME_VISIBLE, b);
    }

    @Override
    default boolean isCustomNameVisible() {
        return this.getSpongeValue().get(Keys.IS_CUSTOM_NAME_VISIBLE).get();
    }

    @Override
    default void setGlowing(boolean b) {
        this.getSpongeValue().offer(Keys.IS_GLOWING, b);
    }

    @Override
    default boolean isGlowing() {
        return this.getSpongeValue().get(Keys.IS_GLOWING).get();
    }

    @Override
    default void setInvulnerable(boolean b) {
        this.getSpongeValue().offer(Keys.INVULNERABLE, b);
    }

    @Override
    default boolean isInvulnerable() {
        return this.getSpongeValue().get(Keys.INVULNERABLE).get();
    }

    @Override
    default boolean isSilent() {
        return this.getSpongeValue().get(Keys.IS_SILENT).get();
    }

    @Override
    default void setSilent(boolean b) {
        this.getSpongeValue().offer(Keys.IS_SILENT, b);
    }

    @Override
    default boolean hasGravity() {
        return this.getSpongeValue().gravityAffected().get();
    }

    @Override
    default void setGravity(boolean b) {
        this.getSpongeValue().gravityAffected().set(b);
    }

    @Override
    default int getPortalCooldown() {
        return (int) this.getSpongeValue().get(Keys.COOLDOWN).orElse(Ticks.zero()).ticks();
    }

    @Override
    default void setPortalCooldown(int i) {
        this.getSpongeValue().offer(Keys.COOLDOWN, Ticks.of(i));
    }

    @Override
    default @NotNull Set<String> getScoreboardTags() {
        return null;
    }

    @Override
    default boolean addScoreboardTag(@NotNull String s) {
        return false;
    }

    @Override
    default boolean removeScoreboardTag(@NotNull String s) {
        return false;
    }

    @Override
    default @NotNull PistonMoveReaction getPistonMoveReaction() {
        return null;
    }

    @Override
    default @NotNull BlockFace getFacing() {
        return null;
    }

    @Override
    default String getCustomName() {
        return null;
    }

    @Override
    default void setCustomName(String s) {

    }

    @Override
    default void setMetadata(@NotNull String s, @NotNull MetadataValue metadataValue) {

    }

    @Override
    default @NotNull List<MetadataValue> getMetadata(@NotNull String s) {
        Map<MetadataValue, String> map = this.getData().getOrDefault(EntityManager.METADATA, new HashMap<>());
        List<MetadataValue> list = new ArrayList<>();
        map.entrySet().stream().filter(m -> m.getValue().equals(s)).forEach(e -> list.add(e.getKey()));
        return list;
    }

    @Override
    default boolean hasMetadata(@NotNull String s) {
        return !this.getMetadata(s).isEmpty();
    }

    @Override
    default void removeMetadata(@NotNull String s, @NotNull Plugin plugin) {
        Map<MetadataValue, String> map = this.getData().getOrDefault(EntityManager.METADATA, new HashMap<>());
        map.entrySet().stream().filter(m -> m.getValue().equals(s)).filter(m -> m.getKey().getOwningPlugin().equals(plugin)).findAny().ifPresent(v -> map.remove(v.getKey()));
    }

    @Override
    default @NotNull BoundingBox getBoundingBox() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default void setRotation(float yaw, float pitch) {
        this.getSpongeValue().setRotation(new Vector3d(yaw, pitch, this.getSpongeValue().rotation().z()));
    }

    @Override
    default @NotNull Pose getPose() {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default @NotNull PersistentDataContainer getPersistentDataContainer() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default boolean isPermissionSet(@NotNull String s) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default boolean isPermissionSet(@NotNull Permission permission) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default boolean hasPermission(@NotNull String s) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default boolean hasPermission(@NotNull Permission permission) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b, int i) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default PermissionAttachment addAttachment(@NotNull Plugin plugin, int i) {
        throw new NotImplementedException("Not got to yet");

    }

    @Override
    default void removeAttachment(@NotNull PermissionAttachment permissionAttachment) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default void recalculatePermissions() {
    }

    @Override
    default @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return Collections.emptySet();
    }

    @Override
    @Deprecated
    default boolean isOp() {
        return false;
    }

    @Override
    @Deprecated
    default void setOp(boolean b) {

    }

}
