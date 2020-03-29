package org.bonge.config;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;

public class BongeConfig {

    public static class BongeNode<T> {

        private Object[] path;
        private Function<ConfigurationNode, T> function;
        private T value;

        public BongeNode(T value, Function<ConfigurationNode, T> function, Object... path){
            this.path = path;
            this.function = function;
            this.value = value;
        }

        public Function<ConfigurationNode, T> getFunction(){
            return this.function;
        }

        public T getValue(){
            return this.value;
        }

        public Object[] getPath(){
            return this.path;
        }

    }

    private static final Function<ConfigurationNode, File> TO_FILE = cn -> {
        String path = cn.getString();
        if(path == null){
            return null;
        }
        return new File(path);
    };

    private static final Function<ConfigurationNode, Boolean> TO_BOOLEAN = cn -> {
        if(cn.getString() == null){
            return null;
        }
        return cn.getBoolean();
    };

    public static final BongeNode<File> PATH_PLUGINS_FILE = new BongeNode<>(new File("plugins"), TO_FILE, "loading", "path", "plugins");
    public static final BongeNode<File> PATH_PLUGINS_CONFIG = new BongeNode<>(new File("plugins"), TO_FILE, "loading", "path", "data");


    private File file;
    private HoconConfigurationLoader loader;
    private CommentedConfigurationNode root;

    public BongeConfig(File file){
        this.file = file;
        this.loader = HoconConfigurationLoader.builder().setFile(file).build();
        try {
            this.root = this.loader.load();
        } catch (IOException e) {
            this.root = this.loader.createEmptyNode();
        }
        updateDefaults();
    }

    private void updateDefaults(){
        System.out.println("PATH_PLUGINS_CONFIG: " + this.get(PATH_PLUGINS_CONFIG).isPresent());
        System.out.println("PATH_PLUGINS_FILE: " + this.get(PATH_PLUGINS_FILE).isPresent());

        if (!this.get(PATH_PLUGINS_CONFIG).isPresent()){
            this.write(File::getPath, PATH_PLUGINS_CONFIG);
            this.writeComment("Where all the config files for plugins should go. Note that some plugins do not respect this. Those files will be in '/plugins'", PATH_PLUGINS_CONFIG.getPath());
        }
        if (!this.get(PATH_PLUGINS_FILE).isPresent()){
            this.write(File::getPath, PATH_PLUGINS_FILE);
            this.writeComment("Where all the bukkit files should be loaded from", PATH_PLUGINS_FILE.getPath());
        }
    }

    public File getFile(){
        return this.file;
    }

    public HoconConfigurationLoader getLoader(){
        return this.loader;
    }

    public ConfigurationNode getRoot(){
        return this.root;
    }

    public void writeComment(String comment, Object... path){
        this.root.getNode(path).setComment(comment);
    }

    public void write(Object value, Object... path){
        this.root.getNode(path).setValue(value);
    }

    public <T> void write(Function<T, Object> convert, BongeNode<T> node){
        this.root.getNode(node.getPath()).setValue(convert.apply(node.getValue()));
    }

    public <T> Optional<T> get(Function<ConfigurationNode, T> function, Object... path){
        return Optional.ofNullable(function.apply(this.root.getNode(path)));
    }

    public <T> Optional<T> get(BongeNode<T> node){
        return this.get(node.getFunction(), node.getPath());
    }

    public <T> T getOrElse(BongeNode<T> node){
        return this.get(node.getFunction(), node.getPath()).orElse(node.getValue());
    }

    public <T, A, R> R getCollection(Collector<T, A, R> collector, Function<ConfigurationNode, T> function, Object... path){
        return this.root.getNode(path).getChildrenList().stream().map(function).collect(collector);
    }

    public void save(){
        try {
            this.loader.save(this.root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
