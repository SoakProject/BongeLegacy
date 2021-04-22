package org.bonge.convert.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bonge.convert.Converter;

public class TextConverter implements Converter<String, Component> {

    @Override
    public Class<Component> getSpongeClass() {
        return Component.class;
    }

    @Override
    public Class<String> getBukkitClass() {
        return String.class;
    }

    @Override
    public Component from(String value) {
        return LegacyComponentSerializer.legacySection().deserialize(value);
    }

    @Override
    public String to(Component value) {
        return LegacyComponentSerializer.legacySection().serialize(value);
    }
}
