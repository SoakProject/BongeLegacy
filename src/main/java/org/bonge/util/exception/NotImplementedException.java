package org.bonge.util.exception;

public class NotImplementedException extends IllegalStateException {

    public NotImplementedException(String reason){
        super("A Bukkit plugin made a call which is not implemented. It is not implemented due to " + reason);
    }
}
