package org.bonge.bukkit.r1_13.block.state.states;

import org.bonge.bukkit.r1_13.block.state.BongeBlockState;
import org.bonge.convert.InterfaceConvert;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.block.Sign;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.Optional;

public class BongeSignBlockState extends BongeBlockState<org.spongepowered.api.block.tileentity.Sign> implements Sign {

    private static final String LINES_1 = "lines_1";
    private static final String LINES_2 = "lines_2";
    private static final String LINES_3 = "lines_3";
    private static final String LINES_4 = "lines_4";

    public BongeSignBlockState(org.spongepowered.api.block.tileentity.Sign value) {
        super(value);
    }

    @Override
    public String[] getLines() {
        List<Text> lines = this.spongeValue.getSignData().asList();
        String line1 = this.<String>getChanges(LINES_1).orElse(InterfaceConvert.toString(lines.get(0)));
        String line2 = this.<String>getChanges(LINES_2).orElse(InterfaceConvert.toString(lines.get(1)));
        String line3 = this.<String>getChanges(LINES_3).orElse(InterfaceConvert.toString(lines.get(2)));
        String line4 = this.<String>getChanges(LINES_4).orElse(InterfaceConvert.toString(lines.get(3)));
        return new String[]{line1, line2, line3, line4};
    }

    @Override
    public String getLine(int index) throws IndexOutOfBoundsException {
        List<Text> lines = this.spongeValue.getSignData().asList();
        switch (index){
            case 0: return this.<String>getChanges(LINES_1).orElse(InterfaceConvert.toString(lines.get(0)));
            case 1: return this.<String>getChanges(LINES_2).orElse(InterfaceConvert.toString(lines.get(1)));
            case 2: return this.<String>getChanges(LINES_3).orElse(InterfaceConvert.toString(lines.get(2)));
            case 3: return this.<String>getChanges(LINES_4).orElse(InterfaceConvert.toString(lines.get(3)));
            default: throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void setLine(int index, String line) throws IndexOutOfBoundsException {
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
        List<Text> lines = this.spongeValue.get(Keys.SIGN_LINES).get();
        Optional<String> opLine1 = this.getChanges(LINES_1);
        if(opLine1.isPresent()){
            lines.set(0, InterfaceConvert.fromString(opLine1.get()));
        }
        Optional<String> opLine2 = this.getChanges(LINES_2);
        if(opLine2.isPresent()){
            lines.set(1, InterfaceConvert.fromString(opLine1.get()));
        }
        Optional<String> opLine3 = this.getChanges(LINES_3);
        if(opLine3.isPresent()){
            lines.set(2, InterfaceConvert.fromString(opLine1.get()));
        }
        Optional<String> opLine4 = this.getChanges(LINES_4);
        if(opLine4.isPresent()){
            lines.set(3, InterfaceConvert.fromString(opLine1.get()));
        }
        this.spongeValue.offer(Keys.SIGN_LINES, lines);
        return true;
    }
}
