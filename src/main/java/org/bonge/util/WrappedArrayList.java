package org.bonge.util;

import com.google.common.base.Predicate;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.Identifiable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class WrappedArrayList<T> extends ArrayList<T> {

    public static class Direct<E, T> extends WrappedArrayList<T>{

        public Direct(Function<T, E> from, Function<E, T> to, Collection<E> linked) {
            super(element -> {
                System.out.println("Linked: " + linked + " | " + from + " | " + element);
                return linked.add(from.apply(element));
            }, element -> linked
                    .remove(from
                            .apply(element)));
            List<E> list = new ArrayList<>(linked);
            for(int A = 0; A < list.size(); A++){
                E orig = list.get(A);
                T fin = to.apply(orig);
                if(fin == null){
                    if (orig instanceof CatalogType) {
                        System.err.println("Could not convert " + ((CatalogType) orig).getId());
                    }else{
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
        if (this.onAdd.apply(value)){
            super.add(value);
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection){
        for(T value : collection){
            if (!this.onAdd.apply(value)){
                return false;
            }
        }
        return super.addAll(collection);
    }

    @Override
    public boolean remove(Object value){
        if (this.onRemove.apply((T)value)){
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
            if (!this.onRemove.apply((T)value)){
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

    public static <T, E> WrappedArrayList<T> ofImmutable(Function<E, T> function, Collection<E> collection){
        WrappedArrayList<T> list = new WrappedArrayList<T>((e) -> true, (e) -> true);
        collection.forEach(l -> list.add(function.apply(l)));
        list.onAdd = (e) -> false;
        list.onRemove = (e) -> false;
        return list;
    }
}
