package org.bukkit.material;

import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Material;
import org.spongepowered.api.block.BlockTypes;

@Deprecated
public class Tripwire extends MaterialData {

    public Tripwire() {
        super(BlockTypes.TRIPWIRE.get().defaultState());
    }

    public Tripwire(Material material) {
        super(material);
    }

    public Tripwire(Material material, byte data) {
        super(material, data);
    }

    public boolean isActivated(){
        throw new NotImplementedException("Tripwire.isActivated() Not got to yet");
    }

    public void setActivated(boolean activated){
        throw new NotImplementedException("Tripwire.isActivated(boolean activated) Not got to yet");
    }

    public boolean isObjectTriggering(){
        throw new NotImplementedException("Tripwire.isObjectTriggering() Not got to yet");
    }

    public void setObjectTriggering(boolean triggering){
        throw new NotImplementedException("Tripwire.setObjectTriggering(boolean) Not got to yet");
    }
}
