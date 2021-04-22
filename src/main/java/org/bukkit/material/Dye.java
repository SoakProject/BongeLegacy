package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.block.BlockState;

@Deprecated
public class Dye extends MaterialData implements Colorable {
    
    public Dye(BlockState value) {
        super(value);
    }

    public Dye(Material material) {
        super(material);
    }

    public Dye(Material material, byte data) {
        super(material, data);
    }
    
    //oh shoot, none block based material
    /*public Dye(DyeColor color){
        this();
    }*/

    @Override
    public @Nullable DyeColor getColor() {
        throw new NotImplementedException("Not got to yet");
    }

    @Override
    public void setColor(DyeColor color) {
        throw new NotImplementedException("Not got to yet");
    }
}
