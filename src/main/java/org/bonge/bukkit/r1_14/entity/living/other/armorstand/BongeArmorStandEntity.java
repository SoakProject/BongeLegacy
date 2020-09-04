package org.bonge.bukkit.r1_14.entity.living.other.armorstand;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_14.entity.living.BongeAbstractLivingEntity;
import org.bonge.bukkit.r1_14.world.BongeLocation;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.BodyPart;
import org.spongepowered.api.data.type.BodyParts;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.math.vector.Vector3d;

import java.io.IOException;
import java.util.Map;

public class BongeArmorStandEntity extends BongeAbstractLivingEntity<org.spongepowered.api.entity.living.ArmorStand> implements ArmorStand {

    public BongeArmorStandEntity(org.spongepowered.api.entity.living.ArmorStand entity) {
        super(entity);
    }

    @Override
    public @NotNull ItemStack getItemInHand() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.spongeValue.getItemInHand(HandTypes.MAIN_HAND);
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setItemInHand(ItemStack item) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(item, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.spongeValue.setItemInHand(HandTypes.MAIN_HAND, is);
    }

    @Override
    public @NotNull ItemStack getBoots() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.spongeValue.getFeet();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setBoots(ItemStack item) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(item, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.spongeValue.setFeet(is);
    }

    @Override
    public @NotNull ItemStack getLeggings() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.spongeValue.getLegs();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setLeggings(ItemStack item) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(item, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.spongeValue.setLegs(is);
    }

    @Override
    public @NotNull ItemStack getChestplate() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.spongeValue.getChest();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setChestplate(ItemStack item) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(item, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.spongeValue.setChest(is);
    }

    @Override
    public @NotNull ItemStack getHelmet() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.spongeValue.getHead();
        try {
            return Bonge.getInstance().convert(ItemStack.class, stack);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setHelmet(ItemStack item) {
        org.spongepowered.api.item.inventory.ItemStack is;
        try {
            is = Bonge.getInstance().convert(item, org.spongepowered.api.item.inventory.ItemStack.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        this.spongeValue.setHead(is);
    }

    @Override
    public @NotNull EulerAngle getBodyPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.CHEST.get());
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setBodyPose(@NotNull EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.CHEST.get())){
            map.replace(BodyParts.CHEST.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.CHEST.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public @NotNull EulerAngle getLeftArmPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.LEFT_ARM.get());
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setLeftArmPose(@NotNull EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.LEFT_ARM.get())){
            map.replace(BodyParts.LEFT_ARM.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.LEFT_ARM.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public @NotNull EulerAngle getRightArmPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.RIGHT_ARM);
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setRightArmPose(@NotNull EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.RIGHT_ARM.get())){
            map.replace(BodyParts.RIGHT_ARM.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.RIGHT_ARM.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public @NotNull EulerAngle getLeftLegPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.LEFT_LEG);
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setLeftLegPose(@NotNull EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.LEFT_LEG.get())){
            map.replace(BodyParts.LEFT_LEG.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.LEFT_LEG.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public @NotNull EulerAngle getRightLegPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.RIGHT_LEG);
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setRightLegPose(@NotNull EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.RIGHT_LEG.get())){
            map.replace(BodyParts.RIGHT_LEG.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.RIGHT_LEG.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public @NotNull EulerAngle getHeadPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.HEAD);
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setHeadPose(@NotNull EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.HEAD.get())){
            map.replace(BodyParts.HEAD.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.HEAD.get(), new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public boolean hasBasePlate() {
        return this.spongeValue.get(Keys.HAS_BASE_PLATE).get();
    }

    @Override
    public void setBasePlate(boolean basePlate) {
        this.spongeValue.offer(Keys.HAS_BASE_PLATE, basePlate);
    }

    @Override
    public boolean isVisible() {
        return this.spongeValue.get(Keys.VANISH).get();
    }

    @Override
    public void setVisible(boolean visible) {
        this.spongeValue.offer(Keys.VANISH, visible);
    }

    @Override
    public boolean hasArms() {
        return this.spongeValue.get(Keys.HAS_ARMS).get();
    }

    @Override
    public void setArms(boolean arms) {
        this.spongeValue.offer(Keys.HAS_ARMS, arms);
    }

    @Override
    public boolean isSmall() {
        return this.spongeValue.get(Keys.IS_SMALL).get();
    }

    @Override
    public void setSmall(boolean small) {
        this.spongeValue.offer(Keys.IS_SMALL, small);
    }

    @Override
    public boolean isMarker() {
        return this.spongeValue.get(Keys.HAS_MARKER).get();
    }

    @Override
    public void setMarker(boolean marker) {
        this.spongeValue.offer(Keys.HAS_MARKER, marker);
    }

    @Override
    public double getEyeHeight() {
        return 1;
    }

    @Override
    public double getEyeHeight(boolean ignorePose) {
        return 1;
    }

    @Override
    public @NotNull Location getEyeLocation() {
        return new BongeLocation(this.getLocation()).add(0, 1, 0);
    }

    @Override
    public @NotNull EntityType getType() {
        return EntityType.ARMOR_STAND;
    }
}
