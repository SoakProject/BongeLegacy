package org.bonge.convert.scoreboard;

import org.bonge.convert.Converter;
import org.bukkit.scoreboard.DisplaySlot;
import org.spongepowered.api.scoreboard.displayslot.DisplaySlots;

import java.io.IOException;

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
    public org.spongepowered.api.scoreboard.displayslot.DisplaySlot from(DisplaySlot value) throws IOException {
        switch (value){
            case BELOW_NAME: return DisplaySlots.BELOW_NAME.get();
            case PLAYER_LIST: return DisplaySlots.LIST.get();
            case SIDEBAR: return DisplaySlots.SIDEBAR_TEAM_BLACK.get();
            default: throw new IOException("Unknown Display Slot of " + value.name());
        }
    }

    @Override
    public DisplaySlot to(org.spongepowered.api.scoreboard.displayslot.DisplaySlot value) throws IOException{
        if(value.equals(DisplaySlots.BELOW_NAME.get())){
            return DisplaySlot.BELOW_NAME;
        }
        if(value.equals(DisplaySlots.LIST.get())){
            return DisplaySlot.PLAYER_LIST;
        }
        if(value.equals(DisplaySlots.SIDEBAR_TEAM_BLACK.get())){
            return DisplaySlot.SIDEBAR;
        }
        throw new IOException("Unknown Display Slot of " + value.toString());
    }
}
