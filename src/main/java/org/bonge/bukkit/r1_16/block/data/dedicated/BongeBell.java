package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.block.data.BongeDirectional;
import org.bukkit.block.data.type.Bell;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.type.AttachmentSurface;

import java.io.IOException;

public interface BongeBell extends Bell, BongeDirectional {

    @Override
    default Bell.Attachment getAttachment(){
        try {
            return Bonge.getInstance().convert(Attachment.class, this.getSpongeValue().get(Keys.ATTACHMENT_SURFACE).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    default void setAttachment(@NotNull Attachment attachment){
        try {
            this.setSpongeValue(this.getSpongeValue().with(Keys.ATTACHMENT_SURFACE, Bonge.getInstance().convert(attachment, AttachmentSurface.class)).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
