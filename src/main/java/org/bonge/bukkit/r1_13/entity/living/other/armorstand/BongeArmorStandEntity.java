package org.bonge.bukkit.r1_13.entity.living.other.armorstand;

import com.flowpowered.math.vector.Vector3d;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.entity.living.BongeAbstractLivingEntity;
import org.bonge.bukkit.r1_13.world.BongeLocation;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.BodyPart;
import org.spongepowered.api.data.type.BodyParts;
import org.spongepowered.api.data.type.HandTypes;

import java.io.IOException;
import java.util.Map;

public class BongeArmorStandEntity extends BongeAbstractLivingEntity<org.spongepowered.api.entity.living.ArmorStand> implements ArmorStand {

    public BongeArmorStandEntity(org.spongepowered.api.entity.living.ArmorStand entity) {
        super(entity);
    }

    @Override
    public ItemStack getItemInHand() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.spongeValue.getItemInHand(HandTypes.MAIN_HAND).get();
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
    public ItemStack getBoots() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.spongeValue.getBoots().get();
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
        this.spongeValue.setBoots(is);
    }

    @Override
    public ItemStack getLeggings() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.spongeValue.getLeggings().get();
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
        this.spongeValue.setLeggings(is);
    }

    @Override
    public ItemStack getChestplate() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.spongeValue.getChestplate().get();
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
        this.spongeValue.setChestplate(is);
    }

    @Override
    public ItemStack getHelmet() {
        org.spongepowered.api.item.inventory.ItemStack stack = this.spongeValue.getHelmet().get();
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
        this.spongeValue.setHelmet(is);
    }

    @Override
    public EulerAngle getBodyPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.CHEST);
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setBodyPose(EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.CHEST)){
            map.replace(BodyParts.CHEST, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.CHEST, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public EulerAngle getLeftArmPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.LEFT_ARM);
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setLeftArmPose(EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.LEFT_ARM)){
            map.replace(BodyParts.LEFT_ARM, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.LEFT_ARM, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public EulerAngle getRightArmPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.RIGHT_ARM);
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setRightArmPose(EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.RIGHT_ARM)){
            map.replace(BodyParts.RIGHT_ARM, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.RIGHT_ARM, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public EulerAngle getLeftLegPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.LEFT_LEG);
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setLeftLegPose(EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.LEFT_LEG)){
            map.replace(BodyParts.LEFT_LEG, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.LEFT_LEG, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public EulerAngle getRightLegPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.RIGHT_LEG);
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setRightLegPose(EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.RIGHT_LEG)){
            map.replace(BodyParts.RIGHT_LEG, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.RIGHT_LEG, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public EulerAngle getHeadPose() {
        Vector3d vector3d = this.spongeValue.get(Keys.BODY_ROTATIONS).get().get(BodyParts.HEAD);
        return new EulerAngle(vector3d.getX(), vector3d.getY(), vector3d.getZ());
    }

    @Override
    public void setHeadPose(EulerAngle pose) {
        Map<BodyPart, Vector3d> map = this.spongeValue.get(Keys.BODY_ROTATIONS).get();
        if(map.containsKey(BodyParts.HEAD)){
            map.replace(BodyParts.HEAD, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }else{
            map.put(BodyParts.HEAD, new Vector3d(pose.getX(), pose.getY(), pose.getZ()));
        }
    }

    @Override
    public boolean hasBasePlate() {
        return this.spongeValue.get(Keys.ARMOR_STAND_HAS_BASE_PLATE).get();
    }

    @Override
    public void setBasePlate(boolean basePlate) {
        this.spongeValue.offer(Keys.ARMOR_STAND_HAS_BASE_PLATE, basePlate);
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
        return this.spongeValue.get(Keys.ARMOR_STAND_HAS_ARMS).get();
    }

    @Override
    public void setArms(boolean arms) {
        this.spongeValue.offer(Keys.ARMOR_STAND_HAS_ARMS, arms);
    }

    @Override
    public boolean isSmall() {
        return this.spongeValue.get(Keys.ARMOR_STAND_IS_SMALL).get();
    }

    @Override
    public void setSmall(boolean small) {
        this.spongeValue.offer(Keys.ARMOR_STAND_IS_SMALL, small);
    }

    @Override
    public boolean isMarker() {
        return this.spongeValue.get(Keys.ARMOR_STAND_MARKER).get();
    }

    @Override
    public void setMarker(boolean marker) {
        this.spongeValue.offer(Keys.ARMOR_STAND_MARKER, marker);
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
    public Location getEyeLocation() {
        return new BongeLocation(this.getLocation()).add(0, 1, 0);
    }

    @Override
    public EntityType getType() {
        return EntityType.ARMOR_STAND;
    }
}
