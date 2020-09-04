package org.bonge.convert.block;

import org.bonge.convert.Converter;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.material.Redstone;
import org.spongepowered.api.data.type.WireAttachmentType;
import org.spongepowered.api.data.type.WireAttachmentTypes;

import java.io.IOException;

public class RedstoneConnectorConverter implements Converter<RedstoneWire.Connection, WireAttachmentType> {
    @Override
    public Class<WireAttachmentType> getSpongeClass() {
        return WireAttachmentType.class;
    }

    @Override
    public Class<RedstoneWire.Connection> getBukkitClass() {
        return RedstoneWire.Connection.class;
    }

    @Override
    public WireAttachmentType from(RedstoneWire.Connection value) throws IOException {
        switch (value){
            case UP: return WireAttachmentTypes.UP.get();
            case SIDE: return WireAttachmentTypes.SIDE.get();
            case NONE: return WireAttachmentTypes.NONE.get();
            default: throw new IOException("Unknown Bukkit RedstoneWire.Connection." + value.name());
        }
    }

    @Override
    public RedstoneWire.Connection to(WireAttachmentType value) throws IOException {
        if(value.equals(WireAttachmentTypes.UP.get())){
            return RedstoneWire.Connection.UP;
        }else if(value.equals(WireAttachmentTypes.SIDE.get())){
            return RedstoneWire.Connection.SIDE;
        }else if(value.equals(WireAttachmentTypes.NONE.get())){
            return RedstoneWire.Connection.NONE;
        }
        throw new IOException("Unknown Sponge WireAttachmentType." + value.getKey().getValue());
    }
}
