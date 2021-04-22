package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

@Deprecated
public class CocoaPlant extends MaterialData implements Directional, Attachable {

    public enum CocoaPlantSize {
        SMALL(0),
        MEDIUM(1),
        LARGE(2);

        private int size;

        CocoaPlantSize(int size){
            this.size = size;
        }

        public int getSize(){
            return this.size;
        }

        public static CocoaPlantSize getValue(int size){
            CocoaPlantSize[] values = values();
            for(int A = 0; A < values.length; A++){
                if(values[A].size == size){
                    return values[A];
                }
            }
            throw new IndexOutOfBoundsException("No cocoaPlant size of " + size);
        }
    }

    public CocoaPlant() {
        super(BlockTypes.COCOA.get().defaultState());
    }

    public CocoaPlant(Material material) {
        super(material);
    }

    public CocoaPlant(Material material, byte data) {
        super(material, data);
    }

    public CocoaPlantSize getSize(){
        return CocoaPlantSize.getValue(this.spongeValue.get(Keys.SIZE).get());
    }

    public void setSize(CocoaPlantSize size){
        this.spongeValue = this.spongeValue.with(Keys.SIZE, size.size).get();
    }

    @Override
    public @NotNull BlockFace getAttachedFace() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get()).getOppositeFace();
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
    }

    @Override
    public @NotNull BlockFace getFacing() {
        return getAttachedFace().getOppositeFace();
    }
}
