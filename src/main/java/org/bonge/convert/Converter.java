package org.bonge.convert;

public interface Converter<B, S> {

    Class<S> getSpongeClass();
    Class<B> getBukkitClass();
    S from(B value);
    B to(S value);
}
