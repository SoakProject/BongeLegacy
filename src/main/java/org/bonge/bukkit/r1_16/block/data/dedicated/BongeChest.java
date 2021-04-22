package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.BongeDirectional;
import org.bonge.bukkit.r1_16.block.data.BongeWaterLogged;
import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.data.type.Chest;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.ChestAttachmentType;
import org.spongepowered.api.data.type.ChestAttachmentTypes;

public interface BongeChest extends IBongeBlockData, Chest, BongeDirectional, BongeWaterLogged {

    @Override
    @NotNull
    default Type getType() {
        ChestAttachmentType chest = this.getSpongeValue().get(Keys.CHEST_ATTACHMENT_TYPE).get();
        if(chest.equals(ChestAttachmentTypes.LEFT.get())){
            return Type.LEFT;
        }
        if(chest.equals(ChestAttachmentTypes.RIGHT.get())){
            return Type.RIGHT;
        }
        if(chest.equals(ChestAttachmentTypes.SINGLE.get())){
            return Type.SINGLE;
        }
        throw new NotImplementedException("Unknown sponge type of '" + chest.toString() +"'");
    }

    @Override
    default void setType(@NotNull Type type) {
        ChestAttachmentType type2;
        switch (type){
            case SINGLE: type2 = ChestAttachmentTypes.SINGLE.get(); break;
            case LEFT: type2 = ChestAttachmentTypes.LEFT.get(); break;
            case RIGHT: type2 = ChestAttachmentTypes.RIGHT.get(); break;
            default: throw new NotImplementedException("Unknown attachment for Bukkit of Chest.Type." + type.name());
        }
        this.setSpongeValue(this.getSpongeValue().with(Keys.CHEST_ATTACHMENT_TYPE, type2).get());
    }
}
