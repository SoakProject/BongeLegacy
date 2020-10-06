package org.bonge.bukkit.r1_15.block.data;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import org.bonge.Bonge;
import org.bonge.wrapper.BongeWrapper;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.block.BlockState;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class BongeAbstractBlockData extends BongeWrapper<org.spongepowered.api.block.BlockState> implements IBongeBlockData {

    public BongeAbstractBlockData(BlockState value) {
        super(value);
    }

    @Override
    public IBongeBlockData setSpongeValue(BlockState state) {
        this.spongeValue = state;
        return this;
    }

    @Override
    public @NotNull Material getMaterial() {
        try {
            return Bonge.getInstance().convert(Material.class, this.spongeValue.getType());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public @NotNull String getAsString() {
        return this.spongeValue.getKey().getValue();
    }

    @Override
    public @NotNull String getAsString(boolean check){
        return getAsString();
    }

    @Override
    public @NotNull BlockData merge(@NotNull BlockData data) {
        BlockState state = this.spongeValue;
        BlockState state2 = ((BongeAbstractBlockData)data).spongeValue;
        return this.newInstance(state.mergeWith(state2));
    }

    @Override
    public boolean matches(BlockData data) {
        return this.spongeValue.getKey().getValue().equals(data.getAsString());
    }

    @Override
    public @NotNull BlockData clone(){
        return newInstance(this.spongeValue);
    }

    public static BongeAbstractBlockData findDynamicClass(BlockState state) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Set<Map.Entry<Predicate<BlockState>, Function<BlockState, BongeAbstractBlockData>>> entries = Bonge.getInstance().getKnownBlockDataInterfaces().entrySet();
        Optional<Map.Entry<Predicate<BlockState>, Function<BlockState, BongeAbstractBlockData>>> opData = entries.stream().filter(e -> e.getKey().test(state)).findAny();
        if(opData.isPresent()){
            return opData.get().getValue().apply(state);
        }
        return buildDynamicClass(state);
    }

    public static BongeAbstractBlockData buildDynamicClass(BlockState state) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return buildDynamicClass(state, "Bonge");
    }

    public static BongeAbstractBlockData buildDynamicClass(BlockState state, String name) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return buildDynamicClass(state, name, new Type[]{});
    }

    public static BongeAbstractBlockData buildDynamicClass(BlockState state, String name, Type... alsoImplements) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return buildDynamicClass(Bonge.class.getClassLoader(), state, name, alsoImplements);
    }

    public static BongeAbstractBlockData buildDynamicClass(ClassLoader loader, BlockState state, String name, Type... alsoImplements) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<Type> interfaces = new ArrayList<>(Arrays.asList(alsoImplements));
        Bonge.getInstance().getBlockDataInterfaces().entrySet().stream().filter(e -> state.getKeys().stream().anyMatch(k -> k.equals(e.getKey()))).forEach(i -> interfaces.add(i.getValue().getGenericSuperclass()));
        DynamicType.Builder<BongeAbstractBlockData> builder = new ByteBuddy()
                .subclass(BongeAbstractBlockData.class, ConstructorStrategy.Default.IMITATE_SUPER_CLASS)
                .implement(interfaces)
                .name(name);
        builder.method(ElementMatchers.named("newInstance").and(ElementMatchers.returns(BongeAbstractBlockData.class).and(ElementMatchers.takesArguments(0)))).intercept(MethodCall.invoke(BongeAbstractBlockData.class.getConstructor(BlockState.class)).withArgument(0));
        Class<? extends BongeAbstractBlockData> clazz = builder.make().load(loader).getLoaded();
        return clazz.getConstructor(BlockState.class).newInstance(state);
    }
}
