package org.bukkit.material;

import org.bukkit.Material;
import org.spongepowered.api.data.Keys;

@Deprecated
public class DetectorRail extends ExtendedRails implements PressureSensor {

    public DetectorRail(){
        this(Material.DETECTOR_RAIL);
    }

    public DetectorRail(Material type) {
        super(type);
    }

    public DetectorRail(Material type, byte data) {
        super(type, data);
    }

    public void setPressed(boolean isPressed){
        this.spongeValue = this.spongeValue.with(Keys.IS_POWERED, isPressed).get();
    }

    @Override
    public boolean isPressed() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }
}
