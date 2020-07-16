package org.bonge.util;

import org.spongepowered.api.CatalogType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class WrappedArrayList<T> extends ArrayList<T> {

    public static class Direct<E, T> extends WrappedArrayList<T>{

        public Direct(Function<T, E> from, Function<E, T> to, Collection<E> linked) {
            super(element -> linked.add(from.apply(element)), element -> linked
                    .remove(from
                            .apply(element)));
            List<E> list = new ArrayList<>(linked);
            for (E orig : list) {
                T fin = to.apply(orig);
                if (fin == null) {
                    if (orig instanceof CatalogType) {
                        System.err.println("Could not convert " + ((CatalogType) orig).getKey().getValue());
                    } else {
                        System.err.println("Could not convert " + orig.toString());
                    }
                    continue;
                }
                this.add(fin);
            }
        }
    }

    protected Predicate<T> onAdd;
    protected Predicate<T> onRemove;

    public WrappedArrayList(Predicate<T> onAdd, Predicate<T> onRemove){
        this.onAdd = onAdd;
        this.onRemove = onRemove;
    }

    @Override
    public boolean add(T value){
        if (this.onAdd.test(value)){
            super.add(value);
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection){
        for(T value : collection){
            if (!this.onAdd.test(value)){
                return false;
            }
        }
        return super.addAll(collection);
    }

    @Override
    public boolean remove(Object value){
        if (this.onRemove.test((T)value)){
            return super.remove(value);
        }
        return false;
    }

    @Override
    public T remove(int count){
        T value = this.get(count);
        this.remove(value);
        return value;
    }

    @Override
    public boolean removeAll(Collection<?> collection){
        for(Object value : collection){
            if (!this.onRemove.test((T)value)){
                return false;
            }
        }
        return super.removeAll(collection);
    }

    @Override
    public void clear(){
        while(!this.isEmpty()){
            this.remove(0);
        }
    }
}
