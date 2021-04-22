package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.Keys;

import java.io.IOException;

@Deprecated
public class Wool extends MaterialData implements Colorable {

    public Wool() {
        super(BlockTypes.WHITE_WOOL.get().defaultState());
    }

    public Wool(DyeColor color) {
        this();
        this.setColor(color);
    }

    public Wool(Material material) {
        super(material);
    }

    public Wool(Material material, byte data) {
        super(material, data);
    }

    @Override
    public @Nullable DyeColor getColor() {
        try {
            return Bonge.getInstance().convert(DyeColor.class, this.spongeValue.get(Keys.DYE_COLOR).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setColor(DyeColor color) {
        try {
            org.spongepowered.api.data.type.DyeColor colour = Bonge.getInstance().convert(color, org.spongepowered.api.data.type.DyeColor.class);
            this.spongeValue = this.spongeValue.with(Keys.DYE_COLOR, colour).get();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
