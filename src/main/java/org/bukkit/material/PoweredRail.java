package org.bukkit.material;

import org.bukkit.Material;
import org.spongepowered.api.data.Keys;

@Deprecated
public class PoweredRail extends ExtendedRails implements Redstone{

    public PoweredRail(){
        this(Material.POWERED_RAIL);
    }

    public PoweredRail(Material type) {
        super(type);
    }

    public PoweredRail(Material type, byte data) {
        super(type, data);
    }

    public void setPowered(boolean isPowered){
        this.spongeValue = this.spongeValue.with(Keys.IS_POWERED, isPowered).get();
    }

    @Override
    public boolean isPowered() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }
}
