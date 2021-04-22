package org.bukkit.material;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

@Deprecated
public class SmoothBrick extends TexturedMaterial {

    public SmoothBrick(){
        this(Material.SMOOTH_STONE);
    }

    public SmoothBrick(Material m) {
        super(m);
    }

    public SmoothBrick(Material type, byte data) {
        super(type, data);
    }

    @Override
    public List<Material> getTextures() {
        return Arrays.asList(Material.STONE, Material.MOSSY_COBBLESTONE, Material.COBBLESTONE, Material.SMOOTH_STONE);
    }
}
