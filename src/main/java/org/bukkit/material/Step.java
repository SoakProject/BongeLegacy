package org.bukkit.material;

import org.bukkit.Material;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.SlabPortions;

import java.util.Arrays;
import java.util.List;

@Deprecated
public class Step extends TexturedMaterial{

    public Step(){
        this(Material.STONE_SLAB);
    }

    public Step(Material m) {
        super(m);
    }

    public Step(Material type, byte data) {
        super(type, data);
    }

    public boolean isInverted(){
        return this.spongeValue.get(Keys.SLAB_PORTION).get().equals(SlabPortions.TOP.get());
    }

    public void setInverted(boolean inverted){
        this.spongeValue = this.spongeValue.with(Keys.SLAB_PORTION, inverted ? SlabPortions.TOP.get() : SlabPortions.BOTTOM.get()).get();
    }

    @Override
    public List<Material> getTextures() {
        return Arrays.asList(Material.STONE, Material.SANDSTONE, Material.OAK_WOOD, Material.COBBLESTONE, Material.BRICK, Material.SMOOTH_STONE, Material.NETHER_BRICK, Material.QUARTZ_BLOCK);
    }
}
