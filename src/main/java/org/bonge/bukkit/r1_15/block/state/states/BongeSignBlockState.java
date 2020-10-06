package org.bonge.bukkit.r1_15.block.state.states;

import net.kyori.adventure.text.Component;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_15.block.state.BongeBlockState;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.DyeColor;
import org.bukkit.block.Sign;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.data.Keys;
import org.spongepowered.api.data.value.ListValue;

import java.io.IOException;
import java.util.Optional;

public class BongeSignBlockState extends BongeBlockState<org.spongepowered.api.block.entity.Sign> implements Sign {

    private static final String LINES_1 = "lines_1";
    private static final String LINES_2 = "lines_2";
    private static final String LINES_3 = "lines_3";
    private static final String LINES_4 = "lines_4";
    private static final String COLOUR = "colour";

    public BongeSignBlockState(org.spongepowered.api.block.entity.Sign value) {
        super(value);
    }

    @Override
    public String[] getLines() {
        ListValue.Mutable<Component> lines = this.spongeValue.lines();
        String line1 = this.<String>getChanges(LINES_1).orElse(Bonge.getInstance().convert(lines.get(0)));
        String line2 = this.<String>getChanges(LINES_2).orElse(Bonge.getInstance().convert(lines.get(1)));
        String line3 = this.<String>getChanges(LINES_3).orElse(Bonge.getInstance().convert(lines.get(2)));
        String line4 = this.<String>getChanges(LINES_4).orElse(Bonge.getInstance().convert(lines.get(3)));
        return new String[]{line1, line2, line3, line4};
    }

    @Override
    public @NotNull String getLine(int index) throws IndexOutOfBoundsException {
        ListValue.Mutable<Component> lines = this.spongeValue.lines();
        switch (index){
            case 0: return this.<String>getChanges(LINES_1).orElse(Bonge.getInstance().convert(lines.get(0)));
            case 1: return this.<String>getChanges(LINES_2).orElse(Bonge.getInstance().convert(lines.get(1)));
            case 2: return this.<String>getChanges(LINES_3).orElse(Bonge.getInstance().convert(lines.get(2)));
            case 3: return this.<String>getChanges(LINES_4).orElse(Bonge.getInstance().convert(lines.get(3)));
            default: throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void setLine(int index, @NotNull String line) throws IndexOutOfBoundsException {
        switch (index){
            case 0: this.setChange(LINES_1, line); break;
            case 1: this.setChange(LINES_2, line); break;
            case 2: this.setChange(LINES_3, line); break;
            case 3: this.setChange(LINES_4, line); break;
            default: throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public void setEditable(boolean editable) {
        throw new NotImplementedException("Method Sign(BlockState).setEditable(boolean) has not yet been looked at");
    }

    @Override
    public boolean update() {
        return update(false, true);
    }

    @Override
    public boolean update(boolean force) {
        return update(force, true);
    }

    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        ListValue.Mutable<Component> lines = this.spongeValue.lines();
        Optional<String> opLine1 = this.getChanges(LINES_1);
        opLine1.ifPresent(s -> lines.set(0, Bonge.getInstance().convertText(s)));
        Optional<String> opLine2 = this.getChanges(LINES_2);
        opLine2.ifPresent(s -> lines.set(1, Bonge.getInstance().convertText(s)));
        Optional<String> opLine3 = this.getChanges(LINES_3);
        opLine3.ifPresent(s -> lines.set(2, Bonge.getInstance().convertText(s)));
        Optional<String> opLine4 = this.getChanges(LINES_4);
        opLine4.ifPresent(s -> lines.set(3, Bonge.getInstance().convertText(s)));
        try {
            this.getSpongeValue().offer(Keys.DYE_COLOR, Bonge.getInstance().convert(this.getColor(), org.spongepowered.api.data.type.DyeColor.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public @NotNull PersistentDataContainer getPersistentDataContainer() {
        throw new NotImplementedException("TileState.getPersistentDataContainer() has not been looked at");
    }

    @Override
    public @Nullable DyeColor getColor() {
        Optional<DyeColor> opColour = this.getChanges(COLOUR);
        if(opColour.isPresent()){
            return opColour.get();
        }
        try {
            return Bonge.getInstance().convert(DyeColor.class, this.spongeValue.get(Keys.DYE_COLOR).get());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void setColor(DyeColor color) {
        this.setChange(COLOUR, color);
    }
}
