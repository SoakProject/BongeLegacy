package org.bukkit.material;

import org.bonge.Bonge;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.DoorHinge;
import org.spongepowered.api.data.type.DoorHinges;
import org.spongepowered.api.data.type.SlabPortion;
import org.spongepowered.api.data.type.SlabPortions;

@Deprecated
public class Door extends MaterialData implements Directional, Openable {

    public Door() {
        super(BlockTypes.OAK_DOOR.get().defaultState());
    }

    public Door(Material material) {
        super(material);
    }

    public Door(Material material,  BlockFace face) {
        this(material, face, false);
    }

    public Door(Material material, BlockFace face, boolean open) {
        super(material);
        this.setFacingDirection(face);
        this.setOpen(open);
    }

    public Door(Material material, boolean hinge) {
        super(material);
        this.setHinge(hinge);
    }

    public Door(TreeSpecies material, BlockFace face) {
        this(material, face, false);
    }

    public Door(TreeSpecies material, BlockFace face, boolean open) {
        this(getWoodDoorOfSpecies(material));
        setFacingDirection(face);
    }

    public Door(TreeSpecies material, boolean hinge) {
        this(getWoodDoorOfSpecies(material));
        this.setHinge(hinge);
    }

    public Door(Material material, byte data) {
        super(material, data);
    }

    public boolean isTopHalf(){
        SlabPortion portion = this.spongeValue.get(Keys.SLAB_PORTION).get();
        if(portion.equals(SlabPortions.TOP)){
            return true;
        }
        return false;
    }

    public void setTopHalf(boolean isTopHalf){
        this.spongeValue = this.spongeValue.with(Keys.SLAB_PORTION, (isTopHalf ? SlabPortions.TOP.get() : SlabPortions.BOTTOM.get())).get();
    }

    public BlockFace getHingeCorner(){
        return BlockFace.SELF;
    }

    public boolean getHinge(){
        DoorHinge hinge = this.spongeValue.get(Keys.DOOR_HINGE).get();
        if(hinge.equals(DoorHinges.RIGHT.get())){
            return true;
        }
        return false;
    }

    public void setHinge(boolean hinge){
        this.spongeValue = this.spongeValue.with(Keys.DOOR_HINGE, hinge ? DoorHinges.RIGHT.get() : DoorHinges.LEFT.get()).get();
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
    }

    @Override
    public @NotNull BlockFace getFacing() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get());
    }

    @Override
    public boolean isOpen() {
        return this.spongeValue.get(Keys.IS_OPEN).get();
    }

    @Override
    public void setOpen(boolean open) {
        this.spongeValue = this.spongeValue.with(Keys.IS_OPEN, open).get();
    }

    public static Material getWoodDoorOfSpecies(TreeSpecies species){
        //currently only assumes vanilla blocks
        switch (species){
            case GENERIC: return Material.OAK_DOOR;
            case REDWOOD: return Material.SPRUCE_DOOR;
            case BIRCH: return Material.BIRCH_DOOR;
            case JUNGLE: return Material.JUNGLE_DOOR;
            case ACACIA: return Material.ACACIA_DOOR;
            case DARK_OAK: return Material.DARK_OAK_DOOR;
            default: throw new NotImplementedException("Unknown type of " + species.name());
        }
    }
}
