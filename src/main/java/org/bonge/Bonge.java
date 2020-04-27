package org.bonge;


import org.bonge.convert.Converter;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.spongepowered.api.entity.Entity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Bonge {

    private Set<Converter<?, ?>> converts = new HashSet<>();
    private Set<Material> materials = new HashSet<>();

    private static final Bonge instance = new Bonge();

    public <B, S> Set<Converter<B, S>> getConverts(Class<B> bClass, Class<S> sClass){
        return (Set<Converter<B, S>>)(Object)this.getConverts().stream().filter(c -> c.getBukkitClass().isAssignableFrom(bClass) && c.getSpongeClass().isAssignableFrom(sClass)).collect(Collectors.toSet());
    }

    public <B, S> Set<Converter<B, S>> getConverts(Class<B> bClass, S value){
        return (Set<Converter<B, S>>)(Object)this.getConverts().stream().filter(c -> c.getSpongeClass().isInstance(value)).filter(c -> c.getBukkitClass().isAssignableFrom(bClass)).collect(Collectors.toSet());
    }

    public <B, S> Set<Converter<B, S>> getConverts(B value, Class<S> sClass){
        return (Set<Converter<B, S>>)(Object)this.getConverts().stream().filter(c -> c.getSpongeClass().isAssignableFrom(sClass)).filter(c -> c.getBukkitClass().isInstance(value)).collect(Collectors.toSet());
    }

    public <C extends Converter<?, ?>> Set<C> getConverts(Class<C> converter){
        return (Set<C>)this.getConverts().stream().filter(c -> converter.isInstance(c)).collect(Collectors.toSet());
    }

    public <B, S> B convert(Class<B> clazz, S value) throws IOException{
        Set<Converter<B, S>> set = this.getConverts(clazz, value);
        for(Converter<B, S> converter : set){
            try{
                return converter.to(value);
            }catch (Throwable e){
                continue;
            }
        }
        throw new IOException("Could not convert " + value + " to " + clazz.getName());
    }

    public <B, S> S convert(B value, Class<S> clazz) throws IOException{
        Set<Converter<B, S>> set = this.getConverts(value, clazz);
        for(Converter<B, S> converter : set){
            try{
                return converter.from(value);
            }catch (Throwable e){
                continue;
            }
        }
        throw new IOException("Could not convert " + value + " to " + clazz.getName());
    }

    public Set<Material> getMaterials(){
        return Collections.unmodifiableSet(this.materials);
    }

    public Set<Converter<?, ?>> getConverts(){
        return Collections.unmodifiableSet(this.converts);
    }

    public Bonge register(Converter<?, ?>... converters){
        this.converts.addAll(Arrays.asList(converters));
        return this;
    }

    public Bonge register(Material... materials){
        this.materials.addAll(Arrays.asList(materials));
        return this;
    }

    public static Bonge getInstance(){
        return instance;
    }

    public static int randomInteger(Random random, int min, int max){
        return random.nextInt(min + max) - min;
    }

    public static void createCrashFile(Plugin plugin, String where, Throwable throwable){
        LocalDateTime time = LocalDateTime.now();
        File file = new File("crash-reports/bonge/" + (plugin == null ? "" : plugin.getName() + "/" + where + "/") + time.getYear() + " " + time.getMonth().name() + " " + time.getDayOfMonth() + " at " + time.getHour() + "." + time.getMinute() + "." + time.getSecond() + "." + time.getNano() + ".txt");
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            throwable.printStackTrace(new PrintWriter(writer));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
