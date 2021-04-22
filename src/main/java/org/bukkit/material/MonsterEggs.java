package org.bukkit.material;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

@Deprecated
public class MonsterEggs extends TexturedMaterial{

    public MonsterEggs(){
        super(Material.ZOMBIE_SPAWN_EGG);
    }

    public MonsterEggs(Material m) {
        super(m);
    }

    @Deprecated
    public MonsterEggs(Material type, byte data) {
        super(type, data);
    }

    @Override
    public List<Material> getTextures() {
        return Arrays.asList(Material.STONE, Material.COBBLESTONE, Material.SMOOTH_STONE);
    }
}
