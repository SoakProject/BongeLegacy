package org.bonge.wrapper;

public class BongeWrapper<T extends Object> {

    protected final T spongeValue;

    public BongeWrapper(T value){
        this.spongeValue = value;
    }

    public T getSpongeValue(){
        return this.spongeValue;
    }
}
