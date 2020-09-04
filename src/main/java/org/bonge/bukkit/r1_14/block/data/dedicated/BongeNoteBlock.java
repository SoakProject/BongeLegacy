package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.bukkit.r1_14.block.data.BongePowerable;
import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.data.type.NoteBlock;
import org.jetbrains.annotations.NotNull;

public interface BongeNoteBlock extends IBongeBlockData, NoteBlock, BongePowerable {

    @Override
    default @NotNull Instrument getInstrument() {
        throw new NotImplementedException("NoteBlock.getInstrument() not got to yet");
    }

    @Override
    default void setInstrument(@NotNull Instrument instrument) {
        throw new NotImplementedException("NoteBlock.setInstrument(Instrument) not got to yet");
    }

    @Override
    default @NotNull Note getNote() {
        throw new NotImplementedException("NoteBlock.getNote() not got to yet");
    }

    @Override
    default void setNote(@NotNull Note note) {
        throw new NotImplementedException("NoteBlock.setNote(Note) not got to yet"); }
}
