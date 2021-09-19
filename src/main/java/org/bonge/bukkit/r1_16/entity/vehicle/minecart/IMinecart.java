package org.bonge.bukkit.r1_16.entity.vehicle.minecart;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.entity.IEntity;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Minecart;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.FallingBlock;
import org.spongepowered.api.world.Location;

import java.io.IOException;
import java.util.Collections;

public interface IMinecart<M extends org.spongepowered.api.entity.vehicle.minecart.MinecartLike> extends IEntity<M>, Minecart {

    @Override
    default void setVelocity(@NotNull Vector vector) {
        IEntity.super.setVelocity(vector);
    }

    @Override
    @NotNull
    default Vector getVelocity() {
        return IEntity.super.getVelocity();
    }

    @Override
    default void setDamage(double v) {
        this.getSpongeValue().offer(Keys.HEALTH, v);
    }

    @Override
    default double getDamage() {
        return this.getSpongeValue().get(Keys.HEALTH).orElseThrow(() -> new IllegalStateException("Could not get health of minecart"));
    }

    @Override
    default double getMaxSpeed() {
        return this.getSpongeValue().get(Keys.MAX_SPEED).orElseThrow(() -> new IllegalStateException("Could not get max speed of minecart"));
    }

    @Override
    default void setMaxSpeed(double v) {
        this.getSpongeValue().offer(Keys.MAX_SPEED, v);
    }

    @Override
    default boolean isSlowWhenEmpty() {
        return this.getSpongeValue().get(Keys.SLOWS_UNOCCUPIED).orElse(false);
    }

    @Override
    default void setSlowWhenEmpty(boolean b) {
        this.getSpongeValue().offer(Keys.SLOWS_UNOCCUPIED, b);

    }

    @NotNull
    @Override
    default Vector getFlyingVelocityMod() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default void setFlyingVelocityMod(@NotNull Vector vector) {
        throw new NotImplementedException("Not got to yet");
    }

    @NotNull
    @Override
    default Vector getDerailedVelocityMod() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default void setDerailedVelocityMod(@NotNull Vector vector) {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    @Deprecated
    default void setDisplayBlock(@Nullable MaterialData materialData) {
        throw new NotImplementedException("Deprecated");
    }

    @NotNull
    @Override
    @Deprecated
    default MaterialData getDisplayBlock() {
        throw new NotImplementedException("Deprecated");
    }

    @Override
    default void setDisplayBlockData(@Nullable BlockData blockData) {
        Location<?, ?> loc = this.getSpongeValue().location();
        FallingBlock entity = loc.world().createEntity(EntityTypes.FALLING_BLOCK, loc.position());
        BlockState blockState;
        try {
            blockState = Bonge.getInstance().convert(blockData, BlockState.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        entity.blockState().set(blockState);
        this.getSpongeValue().passengers().set(Collections.singletonList(entity));
    }

    @NotNull
    @Override
    default BlockData getDisplayBlockData() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    default void setDisplayBlockOffset(int i) {

    }

    @Override
    default int getDisplayBlockOffset() {
        return 0;
    }
}
