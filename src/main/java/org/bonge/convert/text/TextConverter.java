package org.bonge.convert.text;

import org.bonge.convert.Converter;
import org.bukkit.ChatColor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.net.MalformedURLException;
import java.net.URL;

public class TextConverter implements Converter<String, Text> {

    @Override
    public Class<Text> getSpongeClass() {
        return Text.class;
    }

    @Override
    public Class<String> getBukkitClass() {
        return String.class;
    }

    @Override
    public Text from(String value){
        Text text = TextSerializers.FORMATTING_CODE.get().deserialize(value);
        try{
            URL url = new URL(ChatColor.stripColor(value));
            return text.toBuilder().onClick(TextActions.openUrl(url)).build();
        } catch (MalformedURLException e){
            return text;
        }
    }

    @Override
    public String to(Text value) {
        return TextSerializers.FORMATTING_CODE.get().serialize(value);
    }
}
