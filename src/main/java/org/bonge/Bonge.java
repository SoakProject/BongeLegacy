package org.bonge;


import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainComponentSerializer;
import org.bonge.bukkit.r1_16.block.data.BongeAbstractBlockData;
import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bonge.bukkit.r1_16.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_16.inventory.BongeInventory;
import org.bonge.bukkit.r1_16.inventory.chest.CustomChestInventory;
import org.bonge.bukkit.r1_16.material.BongeMaterial;
import org.bonge.convert.Converter;
import org.bonge.convert.block.AxisConverter;
import org.bonge.convert.command.CommandAudienceSourceConverter;
import org.bonge.convert.inventory.InventoryConvert;
import org.bonge.convert.text.TextConverter;
import org.bonge.convert.world.DirectionConverter;
import org.bonge.convert.world.LocationConverter;
import org.bonge.convert.world.WorldConverter;
import org.bonge.convert.world.vector.Vector3dConverter;
import org.bonge.convert.world.vector.Vector3iConverter;
import org.bukkit.Axis;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ContainerTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.type.ViewableInventory;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.math.vector.Vector3d;
import org.spongepowered.math.vector.Vector3i;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Bonge {

    private final Set<Converter<?, ?>> converts = new HashSet<>();
    private final Set<BongeMaterial> materials = new HashSet<>();
    private Map<Predicate<BlockState>, Class<IBongeBlockData>> blockData = new HashMap<>();
    private Map<Predicate<BlockState>, Function<BlockState, BongeAbstractBlockData>> blockCreator = new HashMap<>();

    private static final Bonge instance = new Bonge();

    public static final Vector3dConverter VECTOR_3D = instance.register(new Vector3dConverter());
    public static final Vector3iConverter VECTOR_3I = instance.register(new Vector3iConverter());
    public static final DirectionConverter DIRECTION = instance.register(new DirectionConverter());
    public static final LocationConverter LOCATION = instance.register(new LocationConverter());
    public static final TextConverter TEXT = instance.register(new TextConverter());
    public static final InventoryConvert INVENTORY = instance.register(new InventoryConvert());
    public static final AxisConverter AXIS = instance.register(new AxisConverter());
    public static final CommandAudienceSourceConverter COMMAND_AUDIENCE = instance.register(new CommandAudienceSourceConverter());
    public static final WorldConverter WORLD = instance.register(new WorldConverter());

    public <B, S> Set<Converter<B, S>> getConverts(Class<B> bClass, Class<S> sClass) {
        return (Set<Converter<B, S>>) (Object) this.getConverts().stream().filter(c -> c.getBukkitClass().isAssignableFrom(bClass) && c.getSpongeClass().isAssignableFrom(sClass)).collect(Collectors.toSet());
    }

    public <B, S> Set<Converter<B, S>> getConverts(Class<B> bClass, S value) {
        return (Set<Converter<B, S>>) (Object) this.getConverts().stream().filter(c -> c.getSpongeClass().isInstance(value)).filter(c -> c.getBukkitClass().isAssignableFrom(bClass)).collect(Collectors.toSet());
    }

    public <B, S> Set<Converter<B, S>> getConverts(B value, Class<S> sClass) {
        return (Set<Converter<B, S>>) (Object) this.getConverts().stream().filter(c -> c.getSpongeClass().isAssignableFrom(sClass)).filter(c -> c.getBukkitClass().isInstance(value)).collect(Collectors.toSet());
    }

    public <C extends Converter<?, ?>> Set<C> getConverts(Class<C> converter) {
        return (Set<C>) this.getConverts().stream().filter(c -> converter.isInstance(c)).collect(Collectors.toSet());
    }

    public <B, S> B convert(Class<B> clazz, S value) throws IOException {
        Set<Converter<B, S>> set = this.getConverts(clazz, value);
        if (set.isEmpty()) {
            throw new IOException("No valid converts to convert Bukkit: " + clazz.getSimpleName() + " to Sponge: " + value.getClass().getSimpleName());
        }
        IOException e2 = null;
        for (Converter<B, S> converter : set) {
            try {
                return converter.to(value);
            } catch (IOException e) {
                e2 = e;
                continue;
            }
        }
        throw e2;
    }

    public <B, S> S convert(B value, Class<S> clazz) throws IOException {
        Set<Converter<B, S>> set = this.getConverts(value, clazz);
        if (set.isEmpty()) {
            throw new IOException("No valid converts to convert Bukkit: " + value.getClass().getSimpleName() + " to Sponge: " + clazz.getSimpleName());
        }
        IOException e2 = null;
        for (Converter<B, S> converter : set) {
            try {
                return converter.from(value);
            } catch (IOException e) {
                e2 = e;
                continue;
            }
        }
        throw e2;
    }

    //START OF COMMON CONVERTS

    public org.bukkit.World convert(World<?, ?> world) {
        return WORLD.to(world);
    }

    public World<?, ?> convert(org.bukkit.World world) {
        try {
            return WORLD.from(world);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public Axis convert(org.spongepowered.api.util.Axis axis) {
        return AXIS.to(axis);
    }

    public org.spongepowered.api.util.Axis convert(Axis axis) {
        return AXIS.from(axis);
    }

    public <I extends Inventory> BongeInventory<I> convert(I inv) throws IOException {
        try {
            return this.convert(BongeInventory.class, inv);
        } catch (IOException e) {
            if (inv instanceof ViewableInventory) {
                return new CustomChestInventory((ViewableInventory) inv);
            }
            throw e;
        }
    }

    public BongeInventory<? extends Inventory> convertForce(Inventory inv) {
        try {
            return this.convert(inv);
        } catch (IOException e) {
            return CustomChestInventory.of(ViewableInventory.builder().type(ContainerTypes.GENERIC_9X6).slots(inv.slots(), 0).completeStructure().build());
        }
    }

    public BongeMaterial.Item convert(ItemType type) {
        return Material.valueOf(type).getWrapper().toItem().orElseThrow(() -> new IllegalStateException("Item of " + PlainComponentSerializer.plain().serializeOr(type.asComponent(), "Unknown") + " cannot be converted from Sponge to Bukkit"));
    }

    public Optional<ItemType> convertItem(Material material) {
        BongeMaterial material1 = material.getWrapper();
        Optional<BongeMaterial.Item> opItem = material1.toItem();
        return opItem.map(BongeMaterial.Item::getSpongeItemType);
    }

    public BongeMaterial.Block convert(BlockType type) {
        return Material.valueOf(type).getWrapper().toBlock().orElseThrow(() -> new IllegalStateException("Block of " + PlainComponentSerializer.plain().serializeOr(type.asComponent(), "Unknown") + " cannot be converted from Sponge to Bukkit"));
    }

    public Optional<BlockType> convertBlock(Material material) {
        BongeMaterial material1 = material.getWrapper();
        Optional<BongeMaterial.Block> opBlock = material1.toBlock();
        return opBlock.map(BongeMaterial.Block::getSpongeBlockType);
    }

    public String convert(Component text) {
        return TEXT.to(text);
    }

    public Component convertText(String text) {
        return TEXT.from(text);
    }

    public Player convert(org.bukkit.entity.Player player) {
        return ((BongePlayer) player).getSpongeValue();
    }

    public BongePlayer convert(Player player) {
        return BongePlayer.getPlayer(player);
    }

    public BlockFace convert(Direction direction) {
        return DIRECTION.to(direction);
    }

    public Direction convert(BlockFace face) {
        return DIRECTION.from(face);
    }

    public Location<? extends World, ? extends Location<?, ?>> convert(org.bukkit.Location location) {
        try {
            return LOCATION.from(location);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public org.bukkit.Location convert(Location<? extends World, ? extends Location<?, ?>> location) {
        try {
            return LOCATION.to(location);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public Vector convert(Vector3i vector3i) {
        return VECTOR_3I.to(vector3i);
    }

    public Vector convert(Vector3d vector3d) {
        return VECTOR_3D.to(vector3d);
    }

    public Vector3i convertInt(Vector vector) {
        return VECTOR_3I.from(vector);
    }

    public Vector3d convertDouble(Vector vector) {
        return VECTOR_3D.from(vector);
    }

    public Audience convertAudience(CommandSender sender) {
        try {
            return COMMAND_AUDIENCE.from(sender);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public CommandSender convert(Audience audience) {
        try {
            return COMMAND_AUDIENCE.to(audience);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    //END OF COMMON CONVERTS

    public Set<BongeMaterial> getMaterials() {
        return Collections.unmodifiableSet(this.materials);
    }

    public Map<Predicate<BlockState>, Class<IBongeBlockData>> getBlockDataInterfaces() {
        return Collections.unmodifiableMap(this.blockData);
    }

    public Map<Predicate<BlockState>, Function<BlockState, BongeAbstractBlockData>> getKnownBlockDataInterfaces() {
        return Collections.unmodifiableMap(this.blockCreator);
    }

    public Set<Converter<?, ?>> getConverts() {
        return Collections.unmodifiableSet(this.converts);
    }

    public <T extends Converter<?, ?>> T register(T converter) {
        this.converts.add(converter);
        return converter;
    }

    public Bonge register(BongeMaterial materials) {
        this.materials.add(materials);
        return this;
    }

    public static Bonge getInstance() {
        return instance;
    }

    public static int randomInteger(Random random, int min, int max) {
        return random.nextInt(min + max) - min;
    }

    public static void createCrashFile(Plugin plugin, String where, Throwable throwable) {
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
