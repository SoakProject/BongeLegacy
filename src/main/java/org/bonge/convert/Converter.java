package org.bonge.convert;

import java.io.IOException;

public interface Converter<B, S> {

    Class<S> getSpongeClass();
    Class<B> getBukkitClass();
    S from(B value) throws IOException;
    B to(S value) throws IOException;
}
