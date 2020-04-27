package org.bonge.convert.scoreboard;

import org.bonge.convert.Converter;
import org.bukkit.scoreboard.DisplaySlot;
import org.spongepowered.api.scoreboard.displayslot.DisplaySlots;

public class DisplaySlotConverter implements Converter<DisplaySlot, org.spongepowered.api.scoreboard.displayslot.DisplaySlot> {

    @Override
    public Class<org.spongepowered.api.scoreboard.displayslot.DisplaySlot> getSpongeClass(){
        return org.spongepowered.api.scoreboard.displayslot.DisplaySlot.class;
    }

    @Override
    public Class<DisplaySlot> getBukkitClass(){
        return DisplaySlot.class;
    }

    @Override
    public org.spongepowered.api.scoreboard.displayslot.DisplaySlot from(DisplaySlot value) {
        switch (value){
            case BELOW_NAME: return DisplaySlots.BELOW_NAME;
            case PLAYER_LIST: return DisplaySlots.LIST;
            case SIDEBAR: return DisplaySlots.SIDEBAR;
            default: throw new IllegalStateException("Unknown Display Slot of " + value.name());
        }
    }

    @Override
    public DisplaySlot to(org.spongepowered.api.scoreboard.displayslot.DisplaySlot value) {
        if(value.equals(DisplaySlots.BELOW_NAME)){
            return DisplaySlot.BELOW_NAME;
        }
        if(value.equals(DisplaySlots.LIST)){
            return DisplaySlot.PLAYER_LIST;
        }
        if(value.equals(DisplaySlots.SIDEBAR)){
            return DisplaySlot.SIDEBAR;
        }
        throw new IllegalStateException("Unknown Display Slot of " + value.getId());
    }
}
