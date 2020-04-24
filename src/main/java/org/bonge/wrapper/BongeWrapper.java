package org.bonge.wrapper;

public class BongeWrapper<T extends Object> {

    protected T spongeValue;

    public BongeWrapper(T value){
        this.spongeValue = value;
    }

    public T getSpongeValue(){
        return this.spongeValue;
    }

    @Override
    public boolean equals(Object object){
        if(!(object instanceof BongeWrapper)){
            return false;
        }
        return this.getSpongeValue().equals(((BongeWrapper)object).getSpongeValue());
    }
}
