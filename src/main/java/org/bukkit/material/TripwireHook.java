package org.bukkit.material;

import org.bonge.Bonge;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;

@Deprecated
public class TripwireHook extends SimpleAttachableMaterialData implements Redstone {

    public TripwireHook(){
        this(Material.TRIPWIRE_HOOK);
    }

    public TripwireHook(BlockFace direction) {
        super(Material.TRIPWIRE_HOOK, direction);
    }

    public TripwireHook(Material type) {
        super(type);
    }

    public TripwireHook(Material type, byte data) {
        super(type, data);
    }

    public boolean isConnected(){
        return this.spongeValue.get(Keys.IS_ATTACHED).get();
    }

    public void setConnected(boolean attached){
        this.spongeValue = this.spongeValue.with(Keys.IS_ATTACHED, attached).get();
    }

    //TODO find out real key
    public boolean isActivated(){
        return this.spongeValue.get(Keys.IS_EXTENDED).get();
    }

    //TODO find out real key
    public void setActivated(boolean activated){
        this.spongeValue = this.spongeValue.with(Keys.IS_EXTENDED, activated).get();
    }

    @Override
    public @NotNull BlockFace getAttachedFace() {
        return Bonge.getInstance().convert(this.spongeValue.get(Keys.DIRECTION).get());
    }

    @Override
    public void setFacingDirection(@NotNull BlockFace face) {
        this.spongeValue = this.spongeValue.with(Keys.DIRECTION, Bonge.getInstance().convert(face)).get();
    }

    @Override
    public boolean isPowered() {
        return this.spongeValue.get(Keys.IS_POWERED).get();
    }
}
