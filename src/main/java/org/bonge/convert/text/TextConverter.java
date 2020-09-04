package org.bonge.convert.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bonge.convert.Converter;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextConverter implements Converter<String, Component> {

    private static class ThreeHolder {

        private TextComponent component;
        private TextColor preColor;
        private Style style;
    }

    public static final char CODE = '§';
    public static final int BLACK = 0;
    public static final char BLACK_CHAR = '0';
    public static final char OBFUSCATED = 'k';
    public static final char BOLD = 'l';
    public static final char STRIKETHOUGH = 'm';
    public static final char UNDERLINE = 'n';
    public static final char ITALIC = 'o';
    public static final char RESET = 'r';

    public ThreeHolder build(TextComponent ret, String cache, TextColor preColor, Style preStyle){
        ThreeHolder holder = new ThreeHolder();
        holder.component = ret;
        holder.preColor = preColor;
        holder.style = preStyle;
        if(cache.charAt(0) == BLACK_CHAR){
            preColor = TextColor.of(BLACK);
        }/*else if(){
                   //OTHER FORMATS
                }*/
        TextComponent.@NonNull Builder tempBuilder = TextComponent.builder(cache.substring(1));
        if(preColor != null){
            tempBuilder.color(preColor);
        }
        if(preStyle != null){
            tempBuilder.style(preStyle);
        }
        TextComponent temp = tempBuilder.build();
        holder.preColor = preColor;
        holder.style = preStyle;
        if(ret == null){
            holder.component = temp;
            return holder;
        }
        holder.component = TextComponent.join(ret, temp);
        return holder;
    }

    @Override
    public Class<Component> getSpongeClass() {
        return Component.class;
    }

    @Override
    public Class<String> getBukkitClass() {
        return String.class;
    }

    @Override
    public Component from(String value){
        String cache = "";
        TextComponent ret = null;
        TextColor preColor = null;
        Style preStyle = null;

        for(int A = 0; A < value.length(); A++){
            char at = value.charAt(A);
            if(at == CODE){
                if(cache.length() == 0){
                    continue;
                }
                ThreeHolder holder = build(ret, cache, preColor, preStyle);
                ret = holder.component;
                preColor = holder.preColor;
                preStyle = holder.style;
            }else{
                cache += at;
            }
        }
        ThreeHolder holder = build(ret, cache, preColor, preStyle);
        ItemStack stack;
        return holder.component;
    }

    @Override
    public String to(Component value) {
        List<TextComponent> list = extract(value, new ArrayList<>());
        StringBuilder builder = new StringBuilder();
        list.forEach(c -> {
            String append = CODE + c.color().value() + c.content();
            for (Map.Entry<TextDecoration, TextDecoration.State> entry : c.decorations().entrySet()){
                if (entry.getValue().equals(TextDecoration.State.TRUE)){
                    Character code = null;
                    switch (entry.getKey()){
                        case OBFUSCATED:
                            code = TextConverter.OBFUSCATED;
                            break;
                        case BOLD:
                            code = TextConverter.BOLD;
                            break;
                        case STRIKETHROUGH:
                            code = TextConverter.STRIKETHOUGH;
                            break;
                        case UNDERLINED:
                            code = TextConverter.UNDERLINE;
                            break;
                        case ITALIC:
                            code = TextConverter.ITALIC;
                            break;
                        default: break;
                    }
                    if(code == null){
                        System.err.println("Unknown formatting code for TextDecoration of " + entry.getKey().toString() + " ignoring formatting");
                        continue;
                    }
                    append = CODE + code + append;
                }
            }
            builder.append(append).append(CODE).append(RESET);
        });
        String ret = builder.toString();
        if(ret.endsWith(CODE + RESET + "")){
            ret = ret.substring(0, ret.length() - 2);
        }
        return ret;
    }

    private List<TextComponent> extract(Component component, List<TextComponent> list){
        List<Component> children = component.children();
        if(children.isEmpty()) {
            if (component instanceof TextComponent){
                list.add((TextComponent) component);
            }
            return list;
        }
        for(Component child : children){
            extract(child, list);
        }
        return list;
    }
}
