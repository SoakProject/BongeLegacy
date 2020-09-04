package org.bonge.bukkit.r1_14.entity;

import net.kyori.adventure.text.Component;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.server.BongeServer;
import org.bonge.bukkit.r1_14.world.BongeLocation;
import org.bonge.bukkit.r1_14.world.BongeWorld;
import org.bonge.util.WrappedArrayList;
import org.bonge.util.exception.NotImplementedException;
import org.bonge.wrapper.BongeWrapper;
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
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.value.Value;
import org.spongepowered.api.world.ServerLocation;
import org.spongepowered.math.vector.Vector3d;

import java.io.IOException;
import java.util.*;

public abstract class BongeAbstractEntity<T extends org.spongepowered.api.entity.Entity> extends BongeWrapper<T> implements Entity {
    
    public BongeAbstractEntity(T entity){
        super(entity);
    }

    public EntityManager.KeyHashMap getData(){
        return ((BongeServer)Bukkit.getServer()).getEntityManager().getData(this);
    }



    @Override
    public @NotNull Location getLocation() {
        Location loc = this.getData().get(EntityManager.LOCATION);
        if(loc != null){
            return loc;
        }
        BongeLocation location = new BongeLocation(this.getSpongeValue().getLocation());
        location.setDirection(Bonge.getInstance().convert(this.spongeValue.getRotation()));
        return location;
    }

    @Override
    public Location getLocation(Location location) {
        throw new NotImplementedException("Entity.getLocation(Location) not got to");
    }

    @Override
    public void setVelocity(@NotNull Vector vector) {
        this.getSpongeValue().offer(Keys.VELOCITY, Bonge.getInstance().convertDouble(vector));
    }

    @Override
    public @NotNull Vector getVelocity() {
        return Bonge.getInstance().convert(this.getSpongeValue().get(Keys.VELOCITY).get());
    }

    @Override
    public double getHeight() {
        return this.getSpongeValue().get(Keys.HEIGHT).get();
    }

    @Override
    public double getWidth() {
        return this.getSpongeValue().get(Keys.BASE_SIZE).get();
    }

    @Override
    public boolean isOnGround() {
        return this.getSpongeValue().onGround().get();
    }

    @Override
    public @NotNull World getWorld() {
        return new BongeWorld(this.spongeValue.getWorld());
    }

    @Override
    public boolean teleport(@NotNull Location location) {
        return this.spongeValue.setLocationAndRotation((ServerLocation) Bonge.getInstance().convert(location), Bonge.getInstance().convertDouble(location.getDirection()));
    }

    @Override
    public boolean teleport(@NotNull Location location, PlayerTeleportEvent.@NotNull TeleportCause teleportCause) {
        return this.teleport(location);
    }

    @Override
    public boolean teleport(Entity entity) {
        return this.teleport(entity.getLocation());
    }

    @Override
    public boolean teleport(@NotNull Entity entity, PlayerTeleportEvent.@NotNull TeleportCause teleportCause) {
        return this.teleport(entity);
    }

    @Override
    public @NotNull List<Entity> getNearbyEntities(double v, double v1, double v2) {
        List<Entity> entities = new ArrayList<>(this.getWorld().getNearbyEntities(this.getLocation(), v, v1, v2));
        entities.remove(this);
        return Collections.unmodifiableList(entities);
    }

    @Override
    public int getEntityId() {
        return this.getSpongeValue().getUniqueId().clockSequence();
    }

    @Override
    public int getFireTicks() {
        return this.getSpongeValue().get(Keys.FIRE_TICKS).get();
    }

    @Override
    public int getMaxFireTicks() {
        return this.spongeValue.get(Keys.FIRE_TICKS).get();
    }

    @Override
    public void setFireTicks(int i) {
        this.spongeValue.offer(Keys.FIRE_TICKS, i);
    }

    @Override
    public void remove() {
        this.spongeValue.remove();
    }

    @Override
    public boolean isDead() {
        return this.getSpongeValue().isRemoved();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void sendMessage(@NotNull String s) {
        //TODO sending message to a none player entity?
    }

    @Override
    public void sendMessage(String[] strings) {
        //TODO sending message to a none player entity?
    }

    @Override
    public Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public String getName() {
        Value.Mutable<Component> name = this.getSpongeValue().displayName();
        return Bonge.getInstance().convert(name.get());
    }

    @Override
    public boolean isPersistent() {
        return this.getSpongeValue().get(Keys.IS_PERSISTENT).get();
    }

    @Override
    public void setPersistent(boolean b) {
        this.getSpongeValue().offer(Keys.IS_PERSISTENT, b);
    }

    @Override
    @Deprecated
    public Entity getPassenger() {
        List<Entity> entities = this.getPassengers();
        if(entities.isEmpty()){
            return null;
        }
        return entities.get(0);
    }

    @Override
    @Deprecated
    public boolean setPassenger(Entity entity) {
        List<Entity> entities = this.getPassengers();
        entities.clear();
        return entities.add(entity);
    }

    @Override
    public @NotNull List<Entity> getPassengers() {
        Optional<List<org.spongepowered.api.entity.Entity>> opPassengers = this.spongeValue.get(Keys.PASSENGERS);
        if((opPassengers.isPresent() && opPassengers.get().isEmpty()) || !opPassengers.isPresent()){
            return Collections.emptyList();
        }

        return new WrappedArrayList<>(e -> {
            assert e != null;
            return opPassengers.get().add(((BongeAbstractEntity<?>)e).spongeValue);
        }, e -> {
            assert e != null;
            return opPassengers.get().remove(((BongeAbstractEntity<?>)e).spongeValue);
        });
    }

    @Override
    public boolean addPassenger(@NotNull Entity entity) {
        return this.getPassengers().add(entity);
    }

    @Override
    public boolean removePassenger(@NotNull Entity entity) {
        return this.getPassengers().remove(entity);
    }

    @Override
    public boolean isEmpty() {
        //TODO check what is meant to be empty
        return false;
    }

    @Override
    public boolean eject() {
        //TODO vehicle
        return false;
    }

    @Override
    public float getFallDistance() {
        return this.getSpongeValue().get(Keys.FALL_DISTANCE).get().floatValue();
    }

    @Override
    public void setFallDistance(float v) {
        this.getSpongeValue().offer(Keys.FALL_DISTANCE, (double)v);
    }

    @Override
    public void setLastDamageCause(EntityDamageEvent entityDamageEvent) {
        //TODO check if sponge has such a thing
    }

    @Override
    public EntityDamageEvent getLastDamageCause() {
        //TODO check if sponge has such a thing
        return null;
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return this.getSpongeValue().getUniqueId();
    }

    @Override
    public int getTicksLived() {
        return 0;
    }

    @Override
    public void setTicksLived(int i) {

    }

    @Override
    public void playEffect(@NotNull EntityEffect entityEffect) {

    }

    @Override
    public boolean isInsideVehicle() {
        return getVehicle() != null;
    }

    @Override
    public boolean leaveVehicle() {
        return this.spongeValue.remove(Keys.VEHICLE).isSuccessful();
    }

    @Override
    public Entity getVehicle() {
        Optional<org.spongepowered.api.entity.Entity> opVe = this.spongeValue.get(Keys.VEHICLE);
        if(!opVe.isPresent()){
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
    public void setCustomNameVisible(boolean b) {
        this.getSpongeValue().offer(Keys.IS_CUSTOM_NAME_VISIBLE, b);
    }

    @Override
    public boolean isCustomNameVisible() {
        return this.getSpongeValue().get(Keys.IS_CUSTOM_NAME_VISIBLE).get();
    }

    @Override
    public void setGlowing(boolean b) {
        this.getSpongeValue().offer(Keys.IS_GLOWING, b);
    }

    @Override
    public boolean isGlowing() {
        return this.getSpongeValue().get(Keys.IS_GLOWING).get();
    }

    @Override
    public void setInvulnerable(boolean b) {
        this.getSpongeValue().offer(Keys.INVULNERABLE, b);
    }

    @Override
    public boolean isInvulnerable() {
        return this.getSpongeValue().get(Keys.INVULNERABLE).get();
    }

    @Override
    public boolean isSilent() {
        return this.getSpongeValue().get(Keys.IS_SILENT).get();
    }

    @Override
    public void setSilent(boolean b) {
        this.getSpongeValue().offer(Keys.IS_SILENT, b);
    }

    @Override
    public boolean hasGravity() {
        return this.getSpongeValue().gravityAffected().get();
    }

    @Override
    public void setGravity(boolean b) {
        this.getSpongeValue().gravityAffected().set(b);
    }

    @Override
    public int getPortalCooldown() {
        return this.spongeValue.get(Keys.COOLDOWN).orElse(0);
    }

    @Override
    public void setPortalCooldown(int i) {
        this.spongeValue.offer(Keys.COOLDOWN, i);
    }

    @Override
    public @NotNull Set<String> getScoreboardTags() {
        return null;
    }

    @Override
    public boolean addScoreboardTag(@NotNull String s) {
        return false;
    }

    @Override
    public boolean removeScoreboardTag(@NotNull String s) {
        return false;
    }

    @Override
    public @NotNull PistonMoveReaction getPistonMoveReaction() {
        return null;
    }

    @Override
    public @NotNull BlockFace getFacing() {
        return null;
    }

    @Override
    public String getCustomName() {
        return null;
    }

    @Override
    public void setCustomName(String s) {

    }

    @Override
    public void setMetadata(@NotNull String s, @NotNull MetadataValue metadataValue) {

    }

    @Override
    public @NotNull List<MetadataValue> getMetadata(@NotNull String s) {
        Map<MetadataValue, String> map = this.getData().getOrDefault(EntityManager.METADATA, new HashMap<>());
        List<MetadataValue> list = new ArrayList<>();
        map.entrySet().stream().filter(m -> m.getValue().equals(s)).forEach(e -> list.add(e.getKey()));
        return list;
    }

    @Override
    public boolean hasMetadata(@NotNull String s) {
        return !this.getMetadata(s).isEmpty();
    }

    @Override
    public void removeMetadata(@NotNull String s, @NotNull Plugin plugin) {
        Map<MetadataValue, String> map = this.getData().getOrDefault(EntityManager.METADATA, new HashMap<>());
        map.entrySet().stream().filter(m -> m.getValue().equals(s)).filter(m -> m.getKey().getOwningPlugin().equals(plugin)).findAny().ifPresent(v -> map.remove(v.getKey()));
    }

    @Override
    public @NotNull BoundingBox getBoundingBox() {
        return null;
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        this.spongeValue.setRotation(new Vector3d(yaw, pitch, this.spongeValue.getRotation().getZ()));
    }

    @Override
    public @NotNull Pose getPose() {
        return null;
    }

    @Override
    public @NotNull PersistentDataContainer getPersistentDataContainer() {
        return null;
    }

    @Override
    public boolean isPermissionSet(@NotNull String s) {
        return false;
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission permission) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull String s) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Permission permission) {
        return false;
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String s, boolean b, int i) {
        return null;
    }

    @Override
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, int i) {
        return null;
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment permissionAttachment) {

    }

    @Override
    public void recalculatePermissions() {

    }

    @Override
    public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return null;
    }

    @Override
    @Deprecated
    public boolean isOp() {
        return false;
    }

    @Override
    @Deprecated
    public void setOp(boolean b) {

    }
}
