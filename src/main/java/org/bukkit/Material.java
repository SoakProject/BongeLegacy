package org.bukkit;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.array.utils.ArrayUtils;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_16.material.BongeMaterial;
import org.bonge.bukkit.r1_16.material.UnknownMaterial;
import org.bonge.bukkit.r1_16.material.block.BlockMaterial;
import org.bonge.bukkit.r1_16.material.item.ItemMaterial;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Material implements Keyed {

    public static final Material AIR = new Material(new BlockMaterial(BlockTypes.AIR.get(), "AIR"));
    public static final Material STONE = new Material(new BlockMaterial(BlockTypes.STONE.get(), "STONE"));
    public static final Material GRANITE = new Material(new BlockMaterial(BlockTypes.GRANITE.get(), "GRANITE"));
    public static final Material POLISHED_GRANITE = new Material(new BlockMaterial(BlockTypes.POLISHED_GRANITE.get(), "POLISHED_GRANITE"));
    public static final Material DIORITE = new Material(new BlockMaterial(BlockTypes.DIORITE.get(), "DIORITE"));
    public static final Material POLISHED_DIORITE = new Material(new BlockMaterial(BlockTypes.POLISHED_DIORITE.get(), "POLISHED_DIORITE"));
    public static final Material ANDESITE = new Material(new BlockMaterial(BlockTypes.ANDESITE.get(), "ANDESITE"));
    public static final Material POLISHED_ANDESITE = new Material(new BlockMaterial(BlockTypes.POLISHED_ANDESITE.get(), "POLISHED_ANDESITE"));
    public static final Material GRASS_BLOCK = new Material(new BlockMaterial(BlockTypes.GRASS_BLOCK.get(), "GRASS_BLOCK"));
    public static final Material DIRT = new Material(new BlockMaterial(BlockTypes.DIRT.get(), "DIRT"));
    public static final Material COARSE_DIRT = new Material(new BlockMaterial(BlockTypes.COARSE_DIRT.get(), "COARSE_DIRT"));
    public static final Material PODZOL = new Material(new BlockMaterial(BlockTypes.PODZOL.get(), "PODZOL"));
    public static final Material CRIMSON_NYLIUM = new Material(new BlockMaterial(BlockTypes.CRIMSON_NYLIUM.get(), "CRIMSON_NYLIUM"));
    public static final Material WARPED_NYLIUM = new Material(new BlockMaterial(BlockTypes.WARPED_NYLIUM.get(), "WARPED_NYLIUM"));
    public static final Material COBBLESTONE = new Material(new BlockMaterial(BlockTypes.COBBLESTONE.get(), "COBBLESTONE"));
    public static final Material OAK_PLANKS = new Material(new BlockMaterial(BlockTypes.OAK_PLANKS.get(), "OAK_PLANKS"));
    public static final Material SPRUCE_PLANKS = new Material(new BlockMaterial(BlockTypes.SPRUCE_PLANKS.get(), "SPRUCE_PLANKS"));
    public static final Material BIRCH_PLANKS = new Material(new BlockMaterial(BlockTypes.BIRCH_PLANKS.get(), "BIRCH_PLANKS"));
    public static final Material JUNGLE_PLANKS = new Material(new BlockMaterial(BlockTypes.JUNGLE_PLANKS.get(), "JUNGLE_PLANKS"));
    public static final Material ACACIA_PLANKS = new Material(new BlockMaterial(BlockTypes.ACACIA_PLANKS.get(), "ACACIA_PLANKS"));
    public static final Material DARK_OAK_PLANKS = new Material(new BlockMaterial(BlockTypes.DARK_OAK_PLANKS.get(), "DARK_OAK_PLANKS"));
    public static final Material CRIMSON_PLANKS = new Material(new BlockMaterial(BlockTypes.CRIMSON_PLANKS.get(), "CRIMSON_PLANKS"));
    public static final Material WARPED_PLANKS = new Material(new BlockMaterial(BlockTypes.WARPED_PLANKS.get(), "WARPED_PLANKS"));
    public static final Material OAK_SAPLING = new Material(new BlockMaterial(BlockTypes.OAK_SAPLING.get(), "OAK_SAPLING"));
    public static final Material SPRUCE_SAPLING = new Material(new BlockMaterial(BlockTypes.SPRUCE_SAPLING.get(), "SPRUCE_SAPLING"));
    public static final Material BIRCH_SAPLING = new Material(new BlockMaterial(BlockTypes.BIRCH_SAPLING.get(), "BIRCH_SAPLING"));
    public static final Material JUNGLE_SAPLING = new Material(new BlockMaterial(BlockTypes.JUNGLE_SAPLING.get(), "JUNGLE_SAPLING"));
    public static final Material ACACIA_SAPLING = new Material(new BlockMaterial(BlockTypes.ACACIA_SAPLING.get(), "ACACIA_SAPLING"));
    public static final Material DARK_OAK_SAPLING = new Material(new BlockMaterial(BlockTypes.DARK_OAK_SAPLING.get(), "DARK_OAK_SAPLING"));
    public static final Material BEDROCK = new Material(new BlockMaterial(BlockTypes.BEDROCK.get(), "BEDROCK"));
    public static final Material SAND = new Material(new BlockMaterial(BlockTypes.SAND.get(), "SAND"));
    public static final Material RED_SAND = new Material(new BlockMaterial(BlockTypes.RED_SAND.get(), "RED_SAND"));
    public static final Material GRAVEL = new Material(new BlockMaterial(BlockTypes.GRAVEL.get(), "GRAVEL"));
    public static final Material GOLD_ORE = new Material(new BlockMaterial(BlockTypes.GOLD_ORE.get(), "GOLD_ORE"));
    public static final Material IRON_ORE = new Material(new BlockMaterial(BlockTypes.IRON_ORE.get(), "IRON_ORE"));
    public static final Material COAL_ORE = new Material(new BlockMaterial(BlockTypes.COAL_ORE.get(), "COAL_ORE"));
    public static final Material NETHER_GOLD_ORE = new Material(new BlockMaterial(BlockTypes.NETHER_GOLD_ORE.get(), "NETHER_GOLD_ORE"));
    public static final Material OAK_LOG = new Material(new BlockMaterial(BlockTypes.OAK_LOG.get(), "OAK_LOG"));
    public static final Material SPRUCE_LOG = new Material(new BlockMaterial(BlockTypes.SPRUCE_LOG.get(), "SPRUCE_LOG"));
    public static final Material BIRCH_LOG = new Material(new BlockMaterial(BlockTypes.BIRCH_LOG.get(), "BIRCH_LOG"));
    public static final Material JUNGLE_LOG = new Material(new BlockMaterial(BlockTypes.JUNGLE_LOG.get(), "JUNGLE_LOG"));
    public static final Material ACACIA_LOG = new Material(new BlockMaterial(BlockTypes.ACACIA_LOG.get(), "ACACIA_LOG"));
    public static final Material DARK_OAK_LOG = new Material(new BlockMaterial(BlockTypes.DARK_OAK_LOG.get(), "DARK_OAK_LOG"));
    public static final Material CRIMSON_STEM = new Material(new BlockMaterial(BlockTypes.CRIMSON_STEM.get(), "CRIMSON_STEM"));
    public static final Material WARPED_STEM = new Material(new BlockMaterial(BlockTypes.WARPED_STEM.get(), "WARPED_STEM"));
    public static final Material STRIPPED_OAK_LOG = new Material(new BlockMaterial(BlockTypes.STRIPPED_OAK_LOG.get(), "STRIPPED_OAK_LOG"));
    public static final Material STRIPPED_SPRUCE_LOG = new Material(new BlockMaterial(BlockTypes.STRIPPED_SPRUCE_LOG.get(), "STRIPPED_SPRUCE_LOG"));
    public static final Material STRIPPED_BIRCH_LOG = new Material(new BlockMaterial(BlockTypes.STRIPPED_BIRCH_LOG.get(), "STRIPPED_BIRCH_LOG"));
    public static final Material STRIPPED_JUNGLE_LOG = new Material(new BlockMaterial(BlockTypes.STRIPPED_JUNGLE_LOG.get(), "STRIPPED_JUNGLE_LOG"));
    public static final Material STRIPPED_ACACIA_LOG = new Material(new BlockMaterial(BlockTypes.STRIPPED_ACACIA_LOG.get(), "STRIPPED_ACACIA_LOG"));
    public static final Material STRIPPED_DARK_OAK_LOG = new Material(new BlockMaterial(BlockTypes.STRIPPED_DARK_OAK_LOG.get(), "STRIPPED_DARK_OAK_LOG"));
    public static final Material STRIPPED_CRIMSON_STEM = new Material(new BlockMaterial(BlockTypes.STRIPPED_CRIMSON_STEM.get(), "STRIPPED_CRIMSON_STEM"));
    public static final Material STRIPPED_WARPED_STEM = new Material(new BlockMaterial(BlockTypes.STRIPPED_WARPED_STEM.get(), "STRIPPED_WARPED_STEM"));
    public static final Material STRIPPED_OAK_WOOD = new Material(new BlockMaterial(BlockTypes.STRIPPED_OAK_WOOD.get(), "STRIPPED_OAK_WOOD"));
    public static final Material STRIPPED_SPRUCE_WOOD = new Material(new BlockMaterial(BlockTypes.STRIPPED_SPRUCE_WOOD.get(), "STRIPPED_SPRUCE_WOOD"));
    public static final Material STRIPPED_BIRCH_WOOD = new Material(new BlockMaterial(BlockTypes.STRIPPED_BIRCH_WOOD.get(), "STRIPPED_BIRCH_WOOD"));
    public static final Material STRIPPED_JUNGLE_WOOD = new Material(new BlockMaterial(BlockTypes.STRIPPED_JUNGLE_WOOD.get(), "STRIPPED_JUNGLE_WOOD"));
    public static final Material STRIPPED_ACACIA_WOOD = new Material(new BlockMaterial(BlockTypes.STRIPPED_ACACIA_WOOD.get(), "STRIPPED_ACACIA_WOOD"));
    public static final Material STRIPPED_DARK_OAK_WOOD = new Material(new BlockMaterial(BlockTypes.STRIPPED_DARK_OAK_WOOD.get(), "STRIPPED_DARK_OAK_WOOD"));
    public static final Material STRIPPED_CRIMSON_HYPHAE = new Material(new BlockMaterial(BlockTypes.STRIPPED_CRIMSON_HYPHAE.get(), "STRIPPED_CRIMSON_HYPHAE"));
    public static final Material STRIPPED_WARPED_HYPHAE = new Material(new BlockMaterial(BlockTypes.STRIPPED_WARPED_HYPHAE.get(), "STRIPPED_WARPED_HYPHAE"));
    public static final Material OAK_WOOD = new Material(new BlockMaterial(BlockTypes.OAK_WOOD.get(), "OAK_WOOD"));
    public static final Material SPRUCE_WOOD = new Material(new BlockMaterial(BlockTypes.SPRUCE_WOOD.get(), "SPRUCE_WOOD"));
    public static final Material BIRCH_WOOD = new Material(new BlockMaterial(BlockTypes.BIRCH_WOOD.get(), "BIRCH_WOOD"));
    public static final Material JUNGLE_WOOD = new Material(new BlockMaterial(BlockTypes.JUNGLE_WOOD.get(), "JUNGLE_WOOD"));
    public static final Material ACACIA_WOOD = new Material(new BlockMaterial(BlockTypes.ACACIA_WOOD.get(), "ACACIA_WOOD"));
    public static final Material DARK_OAK_WOOD = new Material(new BlockMaterial(BlockTypes.DARK_OAK_WOOD.get(), "DARK_OAK_WOOD"));
    public static final Material CRIMSON_HYPHAE = new Material(new BlockMaterial(BlockTypes.CRIMSON_HYPHAE.get(), "CRIMSON_HYPHAE"));
    public static final Material WARPED_HYPHAE = new Material(new BlockMaterial(BlockTypes.WARPED_HYPHAE.get(), "WARPED_HYPHAE"));
    public static final Material OAK_LEAVES = new Material(new BlockMaterial(BlockTypes.OAK_LEAVES.get(), "OAK_LEAVES"));
    public static final Material SPRUCE_LEAVES = new Material(new BlockMaterial(BlockTypes.SPRUCE_LEAVES.get(), "SPRUCE_LEAVES"));
    public static final Material BIRCH_LEAVES = new Material(new BlockMaterial(BlockTypes.BIRCH_LEAVES.get(), "BIRCH_LEAVES"));
    public static final Material JUNGLE_LEAVES = new Material(new BlockMaterial(BlockTypes.JUNGLE_LEAVES.get(), "JUNGLE_LEAVES"));
    public static final Material ACACIA_LEAVES = new Material(new BlockMaterial(BlockTypes.ACACIA_LEAVES.get(), "ACACIA_LEAVES"));
    public static final Material DARK_OAK_LEAVES = new Material(new BlockMaterial(BlockTypes.DARK_OAK_LEAVES.get(), "DARK_OAK_LEAVES"));
    public static final Material SPONGE = new Material(new BlockMaterial(BlockTypes.SPONGE.get(), "SPONGE"));
    public static final Material WET_SPONGE = new Material(new BlockMaterial(BlockTypes.WET_SPONGE.get(), "WET_SPONGE"));
    public static final Material GLASS = new Material(new BlockMaterial(BlockTypes.GLASS.get(), "GLASS"));
    public static final Material LAPIS_ORE = new Material(new BlockMaterial(BlockTypes.LAPIS_ORE.get(), "LAPIS_ORE"));
    public static final Material LAPIS_BLOCK = new Material(new BlockMaterial(BlockTypes.LAPIS_BLOCK.get(), "LAPIS_BLOCK"));
    public static final Material DISPENSER = new Material(new BlockMaterial(BlockTypes.DISPENSER.get(), "DISPENSER"));
    public static final Material SANDSTONE = new Material(new BlockMaterial(BlockTypes.SANDSTONE.get(), "SANDSTONE"));
    public static final Material CHISELED_SANDSTONE = new Material(new BlockMaterial(BlockTypes.CHISELED_SANDSTONE.get(), "CHISELED_SANDSTONE"));
    public static final Material CUT_SANDSTONE = new Material(new BlockMaterial(BlockTypes.CUT_SANDSTONE.get(), "CUT_SANDSTONE"));
    public static final Material NOTE_BLOCK = new Material(new BlockMaterial(BlockTypes.NOTE_BLOCK.get(), "NOTE_BLOCK"));
    public static final Material POWERED_RAIL = new Material(new BlockMaterial(BlockTypes.POWERED_RAIL.get(), "POWERED_RAIL"));
    public static final Material DETECTOR_RAIL = new Material(new BlockMaterial(BlockTypes.DETECTOR_RAIL.get(), "DETECTOR_RAIL"));
    public static final Material STICKY_PISTON = new Material(new BlockMaterial(BlockTypes.STICKY_PISTON.get(), "STICKY_PISTON"));
    public static final Material COBWEB = new Material(new BlockMaterial(BlockTypes.COBWEB.get(), "COBWEB"));
    public static final Material GRASS = new Material(new BlockMaterial(BlockTypes.GRASS.get(), "GRASS"));
    public static final Material FERN = new Material(new BlockMaterial(BlockTypes.FERN.get(), "FERN"));
    public static final Material DEAD_BUSH = new Material(new BlockMaterial(BlockTypes.DEAD_BUSH.get(), "DEAD_BUSH"));
    public static final Material SEAGRASS = new Material(new BlockMaterial(BlockTypes.SEAGRASS.get(), "SEAGRASS"));
    public static final Material SEA_PICKLE = new Material(new BlockMaterial(BlockTypes.SEA_PICKLE.get(), "SEA_PICKLE"));
    public static final Material PISTON = new Material(new BlockMaterial(BlockTypes.PISTON.get(), "PISTON"));
    public static final Material WHITE_WOOL = new Material(new BlockMaterial(BlockTypes.WHITE_WOOL.get(), "WHITE_WOOL"));
    public static final Material ORANGE_WOOL = new Material(new BlockMaterial(BlockTypes.ORANGE_WOOL.get(), "ORANGE_WOOL"));
    public static final Material MAGENTA_WOOL = new Material(new BlockMaterial(BlockTypes.MAGENTA_WOOL.get(), "MAGENTA_WOOL"));
    public static final Material LIGHT_BLUE_WOOL = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_WOOL.get(), "LIGHT_BLUE_WOOL"));
    public static final Material YELLOW_WOOL = new Material(new BlockMaterial(BlockTypes.YELLOW_WOOL.get(), "YELLOW_WOOL"));
    public static final Material LIME_WOOL = new Material(new BlockMaterial(BlockTypes.LIME_WOOL.get(), "LIME_WOOL"));
    public static final Material PINK_WOOL = new Material(new BlockMaterial(BlockTypes.PINK_WOOL.get(), "PINK_WOOL"));
    public static final Material GRAY_WOOL = new Material(new BlockMaterial(BlockTypes.GRAY_WOOL.get(), "GRAY_WOOL"));
    public static final Material LIGHT_GRAY_WOOL = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_WOOL.get(), "LIGHT_GRAY_WOOL"));
    public static final Material CYAN_WOOL = new Material(new BlockMaterial(BlockTypes.CYAN_WOOL.get(), "CYAN_WOOL"));
    public static final Material PURPLE_WOOL = new Material(new BlockMaterial(BlockTypes.PURPLE_WOOL.get(), "PURPLE_WOOL"));
    public static final Material BLUE_WOOL = new Material(new BlockMaterial(BlockTypes.BLUE_WOOL.get(), "BLUE_WOOL"));
    public static final Material BROWN_WOOL = new Material(new BlockMaterial(BlockTypes.BROWN_WOOL.get(), "BROWN_WOOL"));
    public static final Material GREEN_WOOL = new Material(new BlockMaterial(BlockTypes.GREEN_WOOL.get(), "GREEN_WOOL"));
    public static final Material RED_WOOL = new Material(new BlockMaterial(BlockTypes.RED_WOOL.get(), "RED_WOOL"));
    public static final Material BLACK_WOOL = new Material(new BlockMaterial(BlockTypes.BLACK_WOOL.get(), "BLACK_WOOL"));
    public static final Material DANDELION = new Material(new BlockMaterial(BlockTypes.DANDELION.get(), "DANDELION"));
    public static final Material POPPY = new Material(new BlockMaterial(BlockTypes.POPPY.get(), "POPPY"));
    public static final Material BLUE_ORCHID = new Material(new BlockMaterial(BlockTypes.BLUE_ORCHID.get(), "BLUE_ORCHID"));
    public static final Material ALLIUM = new Material(new BlockMaterial(BlockTypes.ALLIUM.get(), "ALLIUM"));
    public static final Material AZURE_BLUET = new Material(new BlockMaterial(BlockTypes.AZURE_BLUET.get(), "AZURE_BLUET"));
    public static final Material RED_TULIP = new Material(new BlockMaterial(BlockTypes.RED_TULIP.get(), "RED_TULIP"));
    public static final Material ORANGE_TULIP = new Material(new BlockMaterial(BlockTypes.ORANGE_TULIP.get(), "ORANGE_TULIP"));
    public static final Material WHITE_TULIP = new Material(new BlockMaterial(BlockTypes.WHITE_TULIP.get(), "WHITE_TULIP"));
    public static final Material PINK_TULIP = new Material(new BlockMaterial(BlockTypes.PINK_TULIP.get(), "PINK_TULIP"));
    public static final Material OXEYE_DAISY = new Material(new BlockMaterial(BlockTypes.OXEYE_DAISY.get(), "OXEYE_DAISY"));
    public static final Material CORNFLOWER = new Material(new BlockMaterial(BlockTypes.CORNFLOWER.get(), "CORNFLOWER"));
    public static final Material LILY_OF_THE_VALLEY = new Material(new BlockMaterial(BlockTypes.LILY_OF_THE_VALLEY.get(), "LILY_OF_THE_VALLEY"));
    public static final Material WITHER_ROSE = new Material(new BlockMaterial(BlockTypes.WITHER_ROSE.get(), "WITHER_ROSE"));
    public static final Material BROWN_MUSHROOM = new Material(new BlockMaterial(BlockTypes.BROWN_MUSHROOM.get(), "BROWN_MUSHROOM"));
    public static final Material RED_MUSHROOM = new Material(new BlockMaterial(BlockTypes.RED_MUSHROOM.get(), "RED_MUSHROOM"));
    public static final Material CRIMSON_FUNGUS = new Material(new BlockMaterial(BlockTypes.CRIMSON_FUNGUS.get(), "CRIMSON_FUNGUS"));
    public static final Material WARPED_FUNGUS = new Material(new BlockMaterial(BlockTypes.WARPED_FUNGUS.get(), "WARPED_FUNGUS"));
    public static final Material CRIMSON_ROOTS = new Material(new BlockMaterial(BlockTypes.CRIMSON_ROOTS.get(), "CRIMSON_ROOTS"));
    public static final Material WARPED_ROOTS = new Material(new BlockMaterial(BlockTypes.WARPED_ROOTS.get(), "WARPED_ROOTS"));
    public static final Material NETHER_SPROUTS = new Material(new BlockMaterial(BlockTypes.NETHER_SPROUTS.get(), "NETHER_SPROUTS"));
    public static final Material WEEPING_VINES = new Material(new BlockMaterial(BlockTypes.WEEPING_VINES.get(), "WEEPING_VINES"));
    public static final Material TWISTING_VINES = new Material(new BlockMaterial(BlockTypes.TWISTING_VINES.get(), "TWISTING_VINES"));
    public static final Material SUGAR_CANE = new Material(new BlockMaterial(BlockTypes.SUGAR_CANE.get(), "SUGAR_CANE"));
    public static final Material KELP = new Material(new BlockMaterial(BlockTypes.KELP.get(), "KELP"));
    public static final Material BAMBOO = new Material(new BlockMaterial(BlockTypes.BAMBOO.get(), "BAMBOO"));
    public static final Material GOLD_BLOCK = new Material(new BlockMaterial(BlockTypes.GOLD_BLOCK.get(), "GOLD_BLOCK"));
    public static final Material IRON_BLOCK = new Material(new BlockMaterial(BlockTypes.IRON_BLOCK.get(), "IRON_BLOCK"));
    public static final Material OAK_SLAB = new Material(new BlockMaterial(BlockTypes.OAK_SLAB.get(), "OAK_SLAB"));
    public static final Material SPRUCE_SLAB = new Material(new BlockMaterial(BlockTypes.SPRUCE_SLAB.get(), "SPRUCE_SLAB"));
    public static final Material BIRCH_SLAB = new Material(new BlockMaterial(BlockTypes.BIRCH_SLAB.get(), "BIRCH_SLAB"));
    public static final Material JUNGLE_SLAB = new Material(new BlockMaterial(BlockTypes.JUNGLE_SLAB.get(), "JUNGLE_SLAB"));
    public static final Material ACACIA_SLAB = new Material(new BlockMaterial(BlockTypes.ACACIA_SLAB.get(), "ACACIA_SLAB"));
    public static final Material DARK_OAK_SLAB = new Material(new BlockMaterial(BlockTypes.DARK_OAK_SLAB.get(), "DARK_OAK_SLAB"));
    public static final Material CRIMSON_SLAB = new Material(new BlockMaterial(BlockTypes.CRIMSON_SLAB.get(), "CRIMSON_SLAB"));
    public static final Material WARPED_SLAB = new Material(new BlockMaterial(BlockTypes.WARPED_SLAB.get(), "WARPED_SLAB"));
    public static final Material STONE_SLAB = new Material(new BlockMaterial(BlockTypes.STONE_SLAB.get(), "STONE_SLAB"));
    public static final Material SMOOTH_STONE_SLAB = new Material(new BlockMaterial(BlockTypes.SMOOTH_STONE_SLAB.get(), "SMOOTH_STONE_SLAB"));
    public static final Material SANDSTONE_SLAB = new Material(new BlockMaterial(BlockTypes.SANDSTONE_SLAB.get(), "SANDSTONE_SLAB"));
    public static final Material CUT_SANDSTONE_SLAB = new Material(new BlockMaterial(BlockTypes.CUT_SANDSTONE_SLAB.get(), "CUT_SANDSTONE_SLAB"));
    public static final Material PETRIFIED_OAK_SLAB = new Material(new BlockMaterial(BlockTypes.PETRIFIED_OAK_SLAB.get(), "PETRIFIED_OAK_SLAB"));
    public static final Material COBBLESTONE_SLAB = new Material(new BlockMaterial(BlockTypes.COBBLESTONE_SLAB.get(), "COBBLESTONE_SLAB"));
    public static final Material BRICK_SLAB = new Material(new BlockMaterial(BlockTypes.BRICK_SLAB.get(), "BRICK_SLAB"));
    public static final Material STONE_BRICK_SLAB = new Material(new BlockMaterial(BlockTypes.STONE_BRICK_SLAB.get(), "STONE_BRICK_SLAB"));
    public static final Material NETHER_BRICK_SLAB = new Material(new BlockMaterial(BlockTypes.NETHER_BRICK_SLAB.get(), "NETHER_BRICK_SLAB"));
    public static final Material QUARTZ_SLAB = new Material(new BlockMaterial(BlockTypes.QUARTZ_SLAB.get(), "QUARTZ_SLAB"));
    public static final Material RED_SANDSTONE_SLAB = new Material(new BlockMaterial(BlockTypes.RED_SANDSTONE_SLAB.get(), "RED_SANDSTONE_SLAB"));
    public static final Material CUT_RED_SANDSTONE_SLAB = new Material(new BlockMaterial(BlockTypes.CUT_RED_SANDSTONE_SLAB.get(), "CUT_RED_SANDSTONE_SLAB"));
    public static final Material PURPUR_SLAB = new Material(new BlockMaterial(BlockTypes.PURPUR_SLAB.get(), "PURPUR_SLAB"));
    public static final Material PRISMARINE_SLAB = new Material(new BlockMaterial(BlockTypes.PRISMARINE_SLAB.get(), "PRISMARINE_SLAB"));
    public static final Material PRISMARINE_BRICK_SLAB = new Material(new BlockMaterial(BlockTypes.PRISMARINE_BRICK_SLAB.get(), "PRISMARINE_BRICK_SLAB"));
    public static final Material DARK_PRISMARINE_SLAB = new Material(new BlockMaterial(BlockTypes.DARK_PRISMARINE_SLAB.get(), "DARK_PRISMARINE_SLAB"));
    public static final Material SMOOTH_QUARTZ = new Material(new BlockMaterial(BlockTypes.SMOOTH_QUARTZ.get(), "SMOOTH_QUARTZ"));
    public static final Material SMOOTH_RED_SANDSTONE = new Material(new BlockMaterial(BlockTypes.SMOOTH_RED_SANDSTONE.get(), "SMOOTH_RED_SANDSTONE"));
    public static final Material SMOOTH_SANDSTONE = new Material(new BlockMaterial(BlockTypes.SMOOTH_SANDSTONE.get(), "SMOOTH_SANDSTONE"));
    public static final Material SMOOTH_STONE = new Material(new BlockMaterial(BlockTypes.SMOOTH_STONE.get(), "SMOOTH_STONE"));
    public static final Material BRICKS = new Material(new BlockMaterial(BlockTypes.BRICKS.get(), "BRICKS"));
    public static final Material TNT = new Material(new BlockMaterial(BlockTypes.TNT.get(), "TNT"));
    public static final Material BOOKSHELF = new Material(new BlockMaterial(BlockTypes.BOOKSHELF.get(), "BOOKSHELF"));
    public static final Material MOSSY_COBBLESTONE = new Material(new BlockMaterial(BlockTypes.MOSSY_COBBLESTONE.get(), "MOSSY_COBBLESTONE"));
    public static final Material OBSIDIAN = new Material(new BlockMaterial(BlockTypes.OBSIDIAN.get(), "OBSIDIAN"));
    public static final Material TORCH = new Material(new BlockMaterial(BlockTypes.TORCH.get(), "TORCH"));
    public static final Material END_ROD = new Material(new BlockMaterial(BlockTypes.END_ROD.get(), "END_ROD"));
    public static final Material CHORUS_PLANT = new Material(new BlockMaterial(BlockTypes.CHORUS_PLANT.get(), "CHORUS_PLANT"));
    public static final Material CHORUS_FLOWER = new Material(new BlockMaterial(BlockTypes.CHORUS_FLOWER.get(), "CHORUS_FLOWER"));
    public static final Material PURPUR_BLOCK = new Material(new BlockMaterial(BlockTypes.PURPUR_BLOCK.get(), "PURPUR_BLOCK"));
    public static final Material PURPUR_PILLAR = new Material(new BlockMaterial(BlockTypes.PURPUR_PILLAR.get(), "PURPUR_PILLAR"));
    public static final Material PURPUR_STAIRS = new Material(new BlockMaterial(BlockTypes.PURPUR_STAIRS.get(), "PURPUR_STAIRS"));
    public static final Material SPAWNER = new Material(new BlockMaterial(BlockTypes.SPAWNER.get(), "SPAWNER"));
    public static final Material OAK_STAIRS = new Material(new BlockMaterial(BlockTypes.OAK_STAIRS.get(), "OAK_STAIRS"));
    public static final Material CHEST = new Material(new BlockMaterial(BlockTypes.CHEST.get(), "CHEST"));
    public static final Material DIAMOND_ORE = new Material(new BlockMaterial(BlockTypes.DIAMOND_ORE.get(), "DIAMOND_ORE"));
    public static final Material DIAMOND_BLOCK = new Material(new BlockMaterial(BlockTypes.DIAMOND_BLOCK.get(), "DIAMOND_BLOCK"));
    public static final Material CRAFTING_TABLE = new Material(new BlockMaterial(BlockTypes.CRAFTING_TABLE.get(), "CRAFTING_TABLE"));
    public static final Material FARMLAND = new Material(new BlockMaterial(BlockTypes.FARMLAND.get(), "FARMLAND"));
    public static final Material FURNACE = new Material(new BlockMaterial(BlockTypes.FURNACE.get(), "FURNACE"));
    public static final Material LADDER = new Material(new BlockMaterial(BlockTypes.LADDER.get(), "LADDER"));
    public static final Material RAIL = new Material(new BlockMaterial(BlockTypes.RAIL.get(), "RAIL"));
    public static final Material COBBLESTONE_STAIRS = new Material(new BlockMaterial(BlockTypes.COBBLESTONE_STAIRS.get(), "COBBLESTONE_STAIRS"));
    public static final Material LEVER = new Material(new BlockMaterial(BlockTypes.LEVER.get(), "LEVER"));
    public static final Material STONE_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.STONE_PRESSURE_PLATE.get(), "STONE_PRESSURE_PLATE"));
    public static final Material OAK_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.OAK_PRESSURE_PLATE.get(), "OAK_PRESSURE_PLATE"));
    public static final Material SPRUCE_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.SPRUCE_PRESSURE_PLATE.get(), "SPRUCE_PRESSURE_PLATE"));
    public static final Material BIRCH_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.BIRCH_PRESSURE_PLATE.get(), "BIRCH_PRESSURE_PLATE"));
    public static final Material JUNGLE_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.JUNGLE_PRESSURE_PLATE.get(), "JUNGLE_PRESSURE_PLATE"));
    public static final Material ACACIA_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.ACACIA_PRESSURE_PLATE.get(), "ACACIA_PRESSURE_PLATE"));
    public static final Material DARK_OAK_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.DARK_OAK_PRESSURE_PLATE.get(), "DARK_OAK_PRESSURE_PLATE"));
    public static final Material CRIMSON_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.CRIMSON_PRESSURE_PLATE.get(), "CRIMSON_PRESSURE_PLATE"));
    public static final Material WARPED_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.WARPED_PRESSURE_PLATE.get(), "WARPED_PRESSURE_PLATE"));
    public static final Material POLISHED_BLACKSTONE_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.POLISHED_BLACKSTONE_PRESSURE_PLATE.get(), "POLISHED_BLACKSTONE_PRESSURE_PLATE"));
    public static final Material REDSTONE_ORE = new Material(new BlockMaterial(BlockTypes.REDSTONE_ORE.get(), "REDSTONE_ORE"));
    public static final Material REDSTONE_TORCH = new Material(new BlockMaterial(BlockTypes.REDSTONE_TORCH.get(), "REDSTONE_TORCH"));
    public static final Material SNOW = new Material(new BlockMaterial(BlockTypes.SNOW.get(), "SNOW"));
    public static final Material ICE = new Material(new BlockMaterial(BlockTypes.ICE.get(), "ICE"));
    public static final Material SNOW_BLOCK = new Material(new BlockMaterial(BlockTypes.SNOW_BLOCK.get(), "SNOW_BLOCK"));
    public static final Material CACTUS = new Material(new BlockMaterial(BlockTypes.CACTUS.get(), "CACTUS"));
    public static final Material CLAY = new Material(new BlockMaterial(BlockTypes.CLAY.get(), "CLAY"));
    public static final Material JUKEBOX = new Material(new BlockMaterial(BlockTypes.JUKEBOX.get(), "JUKEBOX"));
    public static final Material OAK_FENCE = new Material(new BlockMaterial(BlockTypes.OAK_FENCE.get(), "OAK_FENCE"));
    public static final Material SPRUCE_FENCE = new Material(new BlockMaterial(BlockTypes.SPRUCE_FENCE.get(), "SPRUCE_FENCE"));
    public static final Material BIRCH_FENCE = new Material(new BlockMaterial(BlockTypes.BIRCH_FENCE.get(), "BIRCH_FENCE"));
    public static final Material JUNGLE_FENCE = new Material(new BlockMaterial(BlockTypes.JUNGLE_FENCE.get(), "JUNGLE_FENCE"));
    public static final Material ACACIA_FENCE = new Material(new BlockMaterial(BlockTypes.ACACIA_FENCE.get(), "ACACIA_FENCE"));
    public static final Material DARK_OAK_FENCE = new Material(new BlockMaterial(BlockTypes.DARK_OAK_FENCE.get(), "DARK_OAK_FENCE"));
    public static final Material CRIMSON_FENCE = new Material(new BlockMaterial(BlockTypes.CRIMSON_FENCE.get(), "CRIMSON_FENCE"));
    public static final Material WARPED_FENCE = new Material(new BlockMaterial(BlockTypes.WARPED_FENCE.get(), "WARPED_FENCE"));
    public static final Material PUMPKIN = new Material(new BlockMaterial(BlockTypes.PUMPKIN.get(), "PUMPKIN"));
    public static final Material CARVED_PUMPKIN = new Material(new BlockMaterial(BlockTypes.CARVED_PUMPKIN.get(), "CARVED_PUMPKIN"));
    public static final Material NETHERRACK = new Material(new BlockMaterial(BlockTypes.NETHERRACK.get(), "NETHERRACK"));
    public static final Material SOUL_SAND = new Material(new BlockMaterial(BlockTypes.SOUL_SAND.get(), "SOUL_SAND"));
    public static final Material SOUL_SOIL = new Material(new BlockMaterial(BlockTypes.SOUL_SOIL.get(), "SOUL_SOIL"));
    public static final Material BASALT = new Material(new BlockMaterial(BlockTypes.BASALT.get(), "BASALT"));
    public static final Material POLISHED_BASALT = new Material(new BlockMaterial(BlockTypes.POLISHED_BASALT.get(), "POLISHED_BASALT"));
    public static final Material SOUL_TORCH = new Material(new BlockMaterial(BlockTypes.SOUL_TORCH.get(), "SOUL_TORCH"));
    public static final Material GLOWSTONE = new Material(new BlockMaterial(BlockTypes.GLOWSTONE.get(), "GLOWSTONE"));
    public static final Material JACK_O_LANTERN = new Material(new BlockMaterial(BlockTypes.JACK_O_LANTERN.get(), "JACK_O_LANTERN"));
    public static final Material OAK_TRAPDOOR = new Material(new BlockMaterial(BlockTypes.OAK_TRAPDOOR.get(), "OAK_TRAPDOOR"));
    public static final Material SPRUCE_TRAPDOOR = new Material(new BlockMaterial(BlockTypes.SPRUCE_TRAPDOOR.get(), "SPRUCE_TRAPDOOR"));
    public static final Material BIRCH_TRAPDOOR = new Material(new BlockMaterial(BlockTypes.BIRCH_TRAPDOOR.get(), "BIRCH_TRAPDOOR"));
    public static final Material JUNGLE_TRAPDOOR = new Material(new BlockMaterial(BlockTypes.JUNGLE_TRAPDOOR.get(), "JUNGLE_TRAPDOOR"));
    public static final Material ACACIA_TRAPDOOR = new Material(new BlockMaterial(BlockTypes.ACACIA_TRAPDOOR.get(), "ACACIA_TRAPDOOR"));
    public static final Material DARK_OAK_TRAPDOOR = new Material(new BlockMaterial(BlockTypes.DARK_OAK_TRAPDOOR.get(), "DARK_OAK_TRAPDOOR"));
    public static final Material CRIMSON_TRAPDOOR = new Material(new BlockMaterial(BlockTypes.CRIMSON_TRAPDOOR.get(), "CRIMSON_TRAPDOOR"));
    public static final Material WARPED_TRAPDOOR = new Material(new BlockMaterial(BlockTypes.WARPED_TRAPDOOR.get(), "WARPED_TRAPDOOR"));
    public static final Material INFESTED_STONE = new Material(new BlockMaterial(BlockTypes.INFESTED_STONE.get(), "INFESTED_STONE"));
    public static final Material INFESTED_COBBLESTONE = new Material(new BlockMaterial(BlockTypes.INFESTED_COBBLESTONE.get(), "INFESTED_COBBLESTONE"));
    public static final Material INFESTED_STONE_BRICKS = new Material(new BlockMaterial(BlockTypes.INFESTED_STONE_BRICKS.get(), "INFESTED_STONE_BRICKS"));
    public static final Material INFESTED_MOSSY_STONE_BRICKS = new Material(new BlockMaterial(BlockTypes.INFESTED_MOSSY_STONE_BRICKS.get(), "INFESTED_MOSSY_STONE_BRICKS"));
    public static final Material INFESTED_CRACKED_STONE_BRICKS = new Material(new BlockMaterial(BlockTypes.INFESTED_CRACKED_STONE_BRICKS.get(), "INFESTED_CRACKED_STONE_BRICKS"));
    public static final Material INFESTED_CHISELED_STONE_BRICKS = new Material(new BlockMaterial(BlockTypes.INFESTED_CHISELED_STONE_BRICKS.get(), "INFESTED_CHISELED_STONE_BRICKS"));
    public static final Material STONE_BRICKS = new Material(new BlockMaterial(BlockTypes.STONE_BRICKS.get(), "STONE_BRICKS"));
    public static final Material MOSSY_STONE_BRICKS = new Material(new BlockMaterial(BlockTypes.MOSSY_STONE_BRICKS.get(), "MOSSY_STONE_BRICKS"));
    public static final Material CRACKED_STONE_BRICKS = new Material(new BlockMaterial(BlockTypes.CRACKED_STONE_BRICKS.get(), "CRACKED_STONE_BRICKS"));
    public static final Material CHISELED_STONE_BRICKS = new Material(new BlockMaterial(BlockTypes.CHISELED_STONE_BRICKS.get(), "CHISELED_STONE_BRICKS"));
    public static final Material BROWN_MUSHROOM_BLOCK = new Material(new BlockMaterial(BlockTypes.BROWN_MUSHROOM_BLOCK.get(), "BROWN_MUSHROOM_BLOCK"));
    public static final Material RED_MUSHROOM_BLOCK = new Material(new BlockMaterial(BlockTypes.RED_MUSHROOM_BLOCK.get(), "RED_MUSHROOM_BLOCK"));
    public static final Material MUSHROOM_STEM = new Material(new BlockMaterial(BlockTypes.MUSHROOM_STEM.get(), "MUSHROOM_STEM"));
    public static final Material IRON_BARS = new Material(new BlockMaterial(BlockTypes.IRON_BARS.get(), "IRON_BARS"));
    public static final Material CHAIN = new Material(new BlockMaterial(BlockTypes.CHAIN.get(), "CHAIN"));
    public static final Material GLASS_PANE = new Material(new BlockMaterial(BlockTypes.GLASS_PANE.get(), "GLASS_PANE"));
    public static final Material MELON = new Material(new BlockMaterial(BlockTypes.MELON.get(), "MELON"));
    public static final Material VINE = new Material(new BlockMaterial(BlockTypes.VINE.get(), "VINE"));
    public static final Material OAK_FENCE_GATE = new Material(new BlockMaterial(BlockTypes.OAK_FENCE_GATE.get(), "OAK_FENCE_GATE"));
    public static final Material SPRUCE_FENCE_GATE = new Material(new BlockMaterial(BlockTypes.SPRUCE_FENCE_GATE.get(), "SPRUCE_FENCE_GATE"));
    public static final Material BIRCH_FENCE_GATE = new Material(new BlockMaterial(BlockTypes.BIRCH_FENCE_GATE.get(), "BIRCH_FENCE_GATE"));
    public static final Material JUNGLE_FENCE_GATE = new Material(new BlockMaterial(BlockTypes.JUNGLE_FENCE_GATE.get(), "JUNGLE_FENCE_GATE"));
    public static final Material ACACIA_FENCE_GATE = new Material(new BlockMaterial(BlockTypes.ACACIA_FENCE_GATE.get(), "ACACIA_FENCE_GATE"));
    public static final Material DARK_OAK_FENCE_GATE = new Material(new BlockMaterial(BlockTypes.DARK_OAK_FENCE_GATE.get(), "DARK_OAK_FENCE_GATE"));
    public static final Material CRIMSON_FENCE_GATE = new Material(new BlockMaterial(BlockTypes.CRIMSON_FENCE_GATE.get(), "CRIMSON_FENCE_GATE"));
    public static final Material WARPED_FENCE_GATE = new Material(new BlockMaterial(BlockTypes.WARPED_FENCE_GATE.get(), "WARPED_FENCE_GATE"));
    public static final Material BRICK_STAIRS = new Material(new BlockMaterial(BlockTypes.BRICK_STAIRS.get(), "BRICK_STAIRS"));
    public static final Material STONE_BRICK_STAIRS = new Material(new BlockMaterial(BlockTypes.STONE_BRICK_STAIRS.get(), "STONE_BRICK_STAIRS"));
    public static final Material MYCELIUM = new Material(new BlockMaterial(BlockTypes.MYCELIUM.get(), "MYCELIUM"));
    public static final Material LILY_PAD = new Material(new BlockMaterial(BlockTypes.LILY_PAD.get(), "LILY_PAD"));
    public static final Material NETHER_BRICKS = new Material(new BlockMaterial(BlockTypes.NETHER_BRICKS.get(), "NETHER_BRICKS"));
    public static final Material CRACKED_NETHER_BRICKS = new Material(new BlockMaterial(BlockTypes.CRACKED_NETHER_BRICKS.get(), "CRACKED_NETHER_BRICKS"));
    public static final Material CHISELED_NETHER_BRICKS = new Material(new BlockMaterial(BlockTypes.CHISELED_NETHER_BRICKS.get(), "CHISELED_NETHER_BRICKS"));
    public static final Material NETHER_BRICK_FENCE = new Material(new BlockMaterial(BlockTypes.NETHER_BRICK_FENCE.get(), "NETHER_BRICK_FENCE"));
    public static final Material NETHER_BRICK_STAIRS = new Material(new BlockMaterial(BlockTypes.NETHER_BRICK_STAIRS.get(), "NETHER_BRICK_STAIRS"));
    public static final Material ENCHANTING_TABLE = new Material(new BlockMaterial(BlockTypes.ENCHANTING_TABLE.get(), "ENCHANTING_TABLE"));
    public static final Material END_PORTAL_FRAME = new Material(new BlockMaterial(BlockTypes.END_PORTAL_FRAME.get(), "END_PORTAL_FRAME"));
    public static final Material END_STONE = new Material(new BlockMaterial(BlockTypes.END_STONE.get(), "END_STONE"));
    public static final Material END_STONE_BRICKS = new Material(new BlockMaterial(BlockTypes.END_STONE_BRICKS.get(), "END_STONE_BRICKS"));
    public static final Material DRAGON_EGG = new Material(new BlockMaterial(BlockTypes.DRAGON_EGG.get(), "DRAGON_EGG"));
    public static final Material REDSTONE_LAMP = new Material(new BlockMaterial(BlockTypes.REDSTONE_LAMP.get(), "REDSTONE_LAMP"));
    public static final Material SANDSTONE_STAIRS = new Material(new BlockMaterial(BlockTypes.SANDSTONE_STAIRS.get(), "SANDSTONE_STAIRS"));
    public static final Material EMERALD_ORE = new Material(new BlockMaterial(BlockTypes.EMERALD_ORE.get(), "EMERALD_ORE"));
    public static final Material ENDER_CHEST = new Material(new BlockMaterial(BlockTypes.ENDER_CHEST.get(), "ENDER_CHEST"));
    public static final Material TRIPWIRE_HOOK = new Material(new BlockMaterial(BlockTypes.TRIPWIRE_HOOK.get(), "TRIPWIRE_HOOK"));
    public static final Material EMERALD_BLOCK = new Material(new BlockMaterial(BlockTypes.EMERALD_BLOCK.get(), "EMERALD_BLOCK"));
    public static final Material SPRUCE_STAIRS = new Material(new BlockMaterial(BlockTypes.SPRUCE_STAIRS.get(), "SPRUCE_STAIRS"));
    public static final Material BIRCH_STAIRS = new Material(new BlockMaterial(BlockTypes.BIRCH_STAIRS.get(), "BIRCH_STAIRS"));
    public static final Material JUNGLE_STAIRS = new Material(new BlockMaterial(BlockTypes.JUNGLE_STAIRS.get(), "JUNGLE_STAIRS"));
    public static final Material CRIMSON_STAIRS = new Material(new BlockMaterial(BlockTypes.CRIMSON_STAIRS.get(), "CRIMSON_STAIRS"));
    public static final Material WARPED_STAIRS = new Material(new BlockMaterial(BlockTypes.WARPED_STAIRS.get(), "WARPED_STAIRS"));
    public static final Material COMMAND_BLOCK = new Material(new BlockMaterial(BlockTypes.COMMAND_BLOCK.get(), "COMMAND_BLOCK"));
    public static final Material BEACON = new Material(new BlockMaterial(BlockTypes.BEACON.get(), "BEACON"));
    public static final Material COBBLESTONE_WALL = new Material(new BlockMaterial(BlockTypes.COBBLESTONE_WALL.get(), "COBBLESTONE_WALL"));
    public static final Material MOSSY_COBBLESTONE_WALL = new Material(new BlockMaterial(BlockTypes.MOSSY_COBBLESTONE_WALL.get(), "MOSSY_COBBLESTONE_WALL"));
    public static final Material BRICK_WALL = new Material(new BlockMaterial(BlockTypes.BRICK_WALL.get(), "BRICK_WALL"));
    public static final Material PRISMARINE_WALL = new Material(new BlockMaterial(BlockTypes.PRISMARINE_WALL.get(), "PRISMARINE_WALL"));
    public static final Material RED_SANDSTONE_WALL = new Material(new BlockMaterial(BlockTypes.RED_SANDSTONE_WALL.get(), "RED_SANDSTONE_WALL"));
    public static final Material MOSSY_STONE_BRICK_WALL = new Material(new BlockMaterial(BlockTypes.MOSSY_STONE_BRICK_WALL.get(), "MOSSY_STONE_BRICK_WALL"));
    public static final Material GRANITE_WALL = new Material(new BlockMaterial(BlockTypes.GRANITE_WALL.get(), "GRANITE_WALL"));
    public static final Material STONE_BRICK_WALL = new Material(new BlockMaterial(BlockTypes.STONE_BRICK_WALL.get(), "STONE_BRICK_WALL"));
    public static final Material NETHER_BRICK_WALL = new Material(new BlockMaterial(BlockTypes.NETHER_BRICK_WALL.get(), "NETHER_BRICK_WALL"));
    public static final Material ANDESITE_WALL = new Material(new BlockMaterial(BlockTypes.ANDESITE_WALL.get(), "ANDESITE_WALL"));
    public static final Material RED_NETHER_BRICK_WALL = new Material(new BlockMaterial(BlockTypes.RED_NETHER_BRICK_WALL.get(), "RED_NETHER_BRICK_WALL"));
    public static final Material SANDSTONE_WALL = new Material(new BlockMaterial(BlockTypes.SANDSTONE_WALL.get(), "SANDSTONE_WALL"));
    public static final Material END_STONE_BRICK_WALL = new Material(new BlockMaterial(BlockTypes.END_STONE_BRICK_WALL.get(), "END_STONE_BRICK_WALL"));
    public static final Material DIORITE_WALL = new Material(new BlockMaterial(BlockTypes.DIORITE_WALL.get(), "DIORITE_WALL"));
    public static final Material BLACKSTONE_WALL = new Material(new BlockMaterial(BlockTypes.BLACKSTONE_WALL.get(), "BLACKSTONE_WALL"));
    public static final Material POLISHED_BLACKSTONE_WALL = new Material(new BlockMaterial(BlockTypes.POLISHED_BLACKSTONE_WALL.get(), "POLISHED_BLACKSTONE_WALL"));
    public static final Material POLISHED_BLACKSTONE_BRICK_WALL = new Material(new BlockMaterial(BlockTypes.POLISHED_BLACKSTONE_BRICK_WALL.get(), "POLISHED_BLACKSTONE_BRICK_WALL"));
    public static final Material STONE_BUTTON = new Material(new BlockMaterial(BlockTypes.STONE_BUTTON.get(), "STONE_BUTTON"));
    public static final Material OAK_BUTTON = new Material(new BlockMaterial(BlockTypes.OAK_BUTTON.get(), "OAK_BUTTON"));
    public static final Material SPRUCE_BUTTON = new Material(new BlockMaterial(BlockTypes.SPRUCE_BUTTON.get(), "SPRUCE_BUTTON"));
    public static final Material BIRCH_BUTTON = new Material(new BlockMaterial(BlockTypes.BIRCH_BUTTON.get(), "BIRCH_BUTTON"));
    public static final Material JUNGLE_BUTTON = new Material(new BlockMaterial(BlockTypes.JUNGLE_BUTTON.get(), "JUNGLE_BUTTON"));
    public static final Material ACACIA_BUTTON = new Material(new BlockMaterial(BlockTypes.ACACIA_BUTTON.get(), "ACACIA_BUTTON"));
    public static final Material DARK_OAK_BUTTON = new Material(new BlockMaterial(BlockTypes.DARK_OAK_BUTTON.get(), "DARK_OAK_BUTTON"));
    public static final Material CRIMSON_BUTTON = new Material(new BlockMaterial(BlockTypes.CRIMSON_BUTTON.get(), "CRIMSON_BUTTON"));
    public static final Material WARPED_BUTTON = new Material(new BlockMaterial(BlockTypes.WARPED_BUTTON.get(), "WARPED_BUTTON"));
    public static final Material POLISHED_BLACKSTONE_BUTTON = new Material(new BlockMaterial(BlockTypes.POLISHED_BLACKSTONE_BUTTON.get(), "POLISHED_BLACKSTONE_BUTTON"));
    public static final Material ANVIL = new Material(new BlockMaterial(BlockTypes.ANVIL.get(), "ANVIL"));
    public static final Material CHIPPED_ANVIL = new Material(new BlockMaterial(BlockTypes.CHIPPED_ANVIL.get(), "CHIPPED_ANVIL"));
    public static final Material DAMAGED_ANVIL = new Material(new BlockMaterial(BlockTypes.DAMAGED_ANVIL.get(), "DAMAGED_ANVIL"));
    public static final Material TRAPPED_CHEST = new Material(new BlockMaterial(BlockTypes.TRAPPED_CHEST.get(), "TRAPPED_CHEST"));
    public static final Material LIGHT_WEIGHTED_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.LIGHT_WEIGHTED_PRESSURE_PLATE.get(), "LIGHT_WEIGHTED_PRESSURE_PLATE"));
    public static final Material HEAVY_WEIGHTED_PRESSURE_PLATE = new Material(new BlockMaterial(BlockTypes.HEAVY_WEIGHTED_PRESSURE_PLATE.get(), "HEAVY_WEIGHTED_PRESSURE_PLATE"));
    public static final Material DAYLIGHT_DETECTOR = new Material(new BlockMaterial(BlockTypes.DAYLIGHT_DETECTOR.get(), "DAYLIGHT_DETECTOR"));
    public static final Material REDSTONE_BLOCK = new Material(new BlockMaterial(BlockTypes.REDSTONE_BLOCK.get(), "REDSTONE_BLOCK"));
    public static final Material NETHER_QUARTZ_ORE = new Material(new BlockMaterial(BlockTypes.NETHER_QUARTZ_ORE.get(), "NETHER_QUARTZ_ORE"));
    public static final Material HOPPER = new Material(new BlockMaterial(BlockTypes.HOPPER.get(), "HOPPER"));
    public static final Material CHISELED_QUARTZ_BLOCK = new Material(new BlockMaterial(BlockTypes.CHISELED_QUARTZ_BLOCK.get(), "CHISELED_QUARTZ_BLOCK"));
    public static final Material QUARTZ_BLOCK = new Material(new BlockMaterial(BlockTypes.QUARTZ_BLOCK.get(), "QUARTZ_BLOCK"));
    public static final Material QUARTZ_BRICKS = new Material(new BlockMaterial(BlockTypes.QUARTZ_BRICKS.get(), "QUARTZ_BRICKS"));
    public static final Material QUARTZ_PILLAR = new Material(new BlockMaterial(BlockTypes.QUARTZ_PILLAR.get(), "QUARTZ_PILLAR"));
    public static final Material QUARTZ_STAIRS = new Material(new BlockMaterial(BlockTypes.QUARTZ_STAIRS.get(), "QUARTZ_STAIRS"));
    public static final Material ACTIVATOR_RAIL = new Material(new BlockMaterial(BlockTypes.ACTIVATOR_RAIL.get(), "ACTIVATOR_RAIL"));
    public static final Material DROPPER = new Material(new BlockMaterial(BlockTypes.DROPPER.get(), "DROPPER"));
    public static final Material WHITE_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.WHITE_TERRACOTTA.get(), "WHITE_TERRACOTTA"));
    public static final Material ORANGE_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.ORANGE_TERRACOTTA.get(), "ORANGE_TERRACOTTA"));
    public static final Material MAGENTA_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.MAGENTA_TERRACOTTA.get(), "MAGENTA_TERRACOTTA"));
    public static final Material LIGHT_BLUE_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_TERRACOTTA.get(), "LIGHT_BLUE_TERRACOTTA"));
    public static final Material YELLOW_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.YELLOW_TERRACOTTA.get(), "YELLOW_TERRACOTTA"));
    public static final Material LIME_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.LIME_TERRACOTTA.get(), "LIME_TERRACOTTA"));
    public static final Material PINK_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.PINK_TERRACOTTA.get(), "PINK_TERRACOTTA"));
    public static final Material GRAY_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.GRAY_TERRACOTTA.get(), "GRAY_TERRACOTTA"));
    public static final Material LIGHT_GRAY_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_TERRACOTTA.get(), "LIGHT_GRAY_TERRACOTTA"));
    public static final Material CYAN_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.CYAN_TERRACOTTA.get(), "CYAN_TERRACOTTA"));
    public static final Material PURPLE_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.PURPLE_TERRACOTTA.get(), "PURPLE_TERRACOTTA"));
    public static final Material BLUE_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.BLUE_TERRACOTTA.get(), "BLUE_TERRACOTTA"));
    public static final Material BROWN_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.BROWN_TERRACOTTA.get(), "BROWN_TERRACOTTA"));
    public static final Material GREEN_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.GREEN_TERRACOTTA.get(), "GREEN_TERRACOTTA"));
    public static final Material RED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.RED_TERRACOTTA.get(), "RED_TERRACOTTA"));
    public static final Material BLACK_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.BLACK_TERRACOTTA.get(), "BLACK_TERRACOTTA"));
    public static final Material BARRIER = new Material(new BlockMaterial(BlockTypes.BARRIER.get(), "BARRIER"));
    public static final Material IRON_TRAPDOOR = new Material(new BlockMaterial(BlockTypes.IRON_TRAPDOOR.get(), "IRON_TRAPDOOR"));
    public static final Material HAY_BLOCK = new Material(new BlockMaterial(BlockTypes.HAY_BLOCK.get(), "HAY_BLOCK"));
    public static final Material WHITE_CARPET = new Material(new BlockMaterial(BlockTypes.WHITE_CARPET.get(), "WHITE_CARPET"));
    public static final Material ORANGE_CARPET = new Material(new BlockMaterial(BlockTypes.ORANGE_CARPET.get(), "ORANGE_CARPET"));
    public static final Material MAGENTA_CARPET = new Material(new BlockMaterial(BlockTypes.MAGENTA_CARPET.get(), "MAGENTA_CARPET"));
    public static final Material LIGHT_BLUE_CARPET = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_CARPET.get(), "LIGHT_BLUE_CARPET"));
    public static final Material YELLOW_CARPET = new Material(new BlockMaterial(BlockTypes.YELLOW_CARPET.get(), "YELLOW_CARPET"));
    public static final Material LIME_CARPET = new Material(new BlockMaterial(BlockTypes.LIME_CARPET.get(), "LIME_CARPET"));
    public static final Material PINK_CARPET = new Material(new BlockMaterial(BlockTypes.PINK_CARPET.get(), "PINK_CARPET"));
    public static final Material GRAY_CARPET = new Material(new BlockMaterial(BlockTypes.GRAY_CARPET.get(), "GRAY_CARPET"));
    public static final Material LIGHT_GRAY_CARPET = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_CARPET.get(), "LIGHT_GRAY_CARPET"));
    public static final Material CYAN_CARPET = new Material(new BlockMaterial(BlockTypes.CYAN_CARPET.get(), "CYAN_CARPET"));
    public static final Material PURPLE_CARPET = new Material(new BlockMaterial(BlockTypes.PURPLE_CARPET.get(), "PURPLE_CARPET"));
    public static final Material BLUE_CARPET = new Material(new BlockMaterial(BlockTypes.BLUE_CARPET.get(), "BLUE_CARPET"));
    public static final Material BROWN_CARPET = new Material(new BlockMaterial(BlockTypes.BROWN_CARPET.get(), "BROWN_CARPET"));
    public static final Material GREEN_CARPET = new Material(new BlockMaterial(BlockTypes.GREEN_CARPET.get(), "GREEN_CARPET"));
    public static final Material RED_CARPET = new Material(new BlockMaterial(BlockTypes.RED_CARPET.get(), "RED_CARPET"));
    public static final Material BLACK_CARPET = new Material(new BlockMaterial(BlockTypes.BLACK_CARPET.get(), "BLACK_CARPET"));
    public static final Material TERRACOTTA = new Material(new BlockMaterial(BlockTypes.TERRACOTTA.get(), "TERRACOTTA"));
    public static final Material COAL_BLOCK = new Material(new BlockMaterial(BlockTypes.COAL_BLOCK.get(), "COAL_BLOCK"));
    public static final Material PACKED_ICE = new Material(new BlockMaterial(BlockTypes.PACKED_ICE.get(), "PACKED_ICE"));
    public static final Material ACACIA_STAIRS = new Material(new BlockMaterial(BlockTypes.ACACIA_STAIRS.get(), "ACACIA_STAIRS"));
    public static final Material DARK_OAK_STAIRS = new Material(new BlockMaterial(BlockTypes.DARK_OAK_STAIRS.get(), "DARK_OAK_STAIRS"));
    public static final Material SLIME_BLOCK = new Material(new BlockMaterial(BlockTypes.SLIME_BLOCK.get(), "SLIME_BLOCK"));
    public static final Material GRASS_PATH = new Material(new BlockMaterial(BlockTypes.GRASS_PATH.get(), "GRASS_PATH"));
    public static final Material SUNFLOWER = new Material(new BlockMaterial(BlockTypes.SUNFLOWER.get(), "SUNFLOWER"));
    public static final Material LILAC = new Material(new BlockMaterial(BlockTypes.LILAC.get(), "LILAC"));
    public static final Material ROSE_BUSH = new Material(new BlockMaterial(BlockTypes.ROSE_BUSH.get(), "ROSE_BUSH"));
    public static final Material PEONY = new Material(new BlockMaterial(BlockTypes.PEONY.get(), "PEONY"));
    public static final Material TALL_GRASS = new Material(new BlockMaterial(BlockTypes.TALL_GRASS.get(), "TALL_GRASS"));
    public static final Material LARGE_FERN = new Material(new BlockMaterial(BlockTypes.LARGE_FERN.get(), "LARGE_FERN"));
    public static final Material WHITE_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.WHITE_STAINED_GLASS.get(), "WHITE_STAINED_GLASS"));
    public static final Material ORANGE_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.ORANGE_STAINED_GLASS.get(), "ORANGE_STAINED_GLASS"));
    public static final Material MAGENTA_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.MAGENTA_STAINED_GLASS.get(), "MAGENTA_STAINED_GLASS"));
    public static final Material LIGHT_BLUE_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_STAINED_GLASS.get(), "LIGHT_BLUE_STAINED_GLASS"));
    public static final Material YELLOW_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.YELLOW_STAINED_GLASS.get(), "YELLOW_STAINED_GLASS"));
    public static final Material LIME_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.LIME_STAINED_GLASS.get(), "LIME_STAINED_GLASS"));
    public static final Material PINK_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.PINK_STAINED_GLASS.get(), "PINK_STAINED_GLASS"));
    public static final Material GRAY_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.GRAY_STAINED_GLASS.get(), "GRAY_STAINED_GLASS"));
    public static final Material LIGHT_GRAY_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_STAINED_GLASS.get(), "LIGHT_GRAY_STAINED_GLASS"));
    public static final Material CYAN_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.CYAN_STAINED_GLASS.get(), "CYAN_STAINED_GLASS"));
    public static final Material PURPLE_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.PURPLE_STAINED_GLASS.get(), "PURPLE_STAINED_GLASS"));
    public static final Material BLUE_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.BLUE_STAINED_GLASS.get(), "BLUE_STAINED_GLASS"));
    public static final Material BROWN_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.BROWN_STAINED_GLASS.get(), "BROWN_STAINED_GLASS"));
    public static final Material GREEN_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.GREEN_STAINED_GLASS.get(), "GREEN_STAINED_GLASS"));
    public static final Material RED_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.RED_STAINED_GLASS.get(), "RED_STAINED_GLASS"));
    public static final Material BLACK_STAINED_GLASS = new Material(new BlockMaterial(BlockTypes.BLACK_STAINED_GLASS.get(), "BLACK_STAINED_GLASS"));
    public static final Material WHITE_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.WHITE_STAINED_GLASS_PANE.get(), "WHITE_STAINED_GLASS_PANE"));
    public static final Material ORANGE_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.ORANGE_STAINED_GLASS_PANE.get(), "ORANGE_STAINED_GLASS_PANE"));
    public static final Material MAGENTA_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.MAGENTA_STAINED_GLASS_PANE.get(), "MAGENTA_STAINED_GLASS_PANE"));
    public static final Material LIGHT_BLUE_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_STAINED_GLASS_PANE.get(), "LIGHT_BLUE_STAINED_GLASS_PANE"));
    public static final Material YELLOW_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.YELLOW_STAINED_GLASS_PANE.get(), "YELLOW_STAINED_GLASS_PANE"));
    public static final Material LIME_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.LIME_STAINED_GLASS_PANE.get(), "LIME_STAINED_GLASS_PANE"));
    public static final Material PINK_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.PINK_STAINED_GLASS_PANE.get(), "PINK_STAINED_GLASS_PANE"));
    public static final Material GRAY_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.GRAY_STAINED_GLASS_PANE.get(), "GRAY_STAINED_GLASS_PANE"));
    public static final Material LIGHT_GRAY_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_STAINED_GLASS_PANE.get(), "LIGHT_GRAY_STAINED_GLASS_PANE"));
    public static final Material CYAN_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.CYAN_STAINED_GLASS_PANE.get(), "CYAN_STAINED_GLASS_PANE"));
    public static final Material PURPLE_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.PURPLE_STAINED_GLASS_PANE.get(), "PURPLE_STAINED_GLASS_PANE"));
    public static final Material BLUE_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.BLUE_STAINED_GLASS_PANE.get(), "BLUE_STAINED_GLASS_PANE"));
    public static final Material BROWN_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.BROWN_STAINED_GLASS_PANE.get(), "BROWN_STAINED_GLASS_PANE"));
    public static final Material GREEN_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.GREEN_STAINED_GLASS_PANE.get(), "GREEN_STAINED_GLASS_PANE"));
    public static final Material RED_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.RED_STAINED_GLASS_PANE.get(), "RED_STAINED_GLASS_PANE"));
    public static final Material BLACK_STAINED_GLASS_PANE = new Material(new BlockMaterial(BlockTypes.BLACK_STAINED_GLASS_PANE.get(), "BLACK_STAINED_GLASS_PANE"));
    public static final Material PRISMARINE = new Material(new BlockMaterial(BlockTypes.PRISMARINE.get(), "PRISMARINE"));
    public static final Material PRISMARINE_BRICKS = new Material(new BlockMaterial(BlockTypes.PRISMARINE_BRICKS.get(), "PRISMARINE_BRICKS"));
    public static final Material DARK_PRISMARINE = new Material(new BlockMaterial(BlockTypes.DARK_PRISMARINE.get(), "DARK_PRISMARINE"));
    public static final Material PRISMARINE_STAIRS = new Material(new BlockMaterial(BlockTypes.PRISMARINE_STAIRS.get(), "PRISMARINE_STAIRS"));
    public static final Material PRISMARINE_BRICK_STAIRS = new Material(new BlockMaterial(BlockTypes.PRISMARINE_BRICK_STAIRS.get(), "PRISMARINE_BRICK_STAIRS"));
    public static final Material DARK_PRISMARINE_STAIRS = new Material(new BlockMaterial(BlockTypes.DARK_PRISMARINE_STAIRS.get(), "DARK_PRISMARINE_STAIRS"));
    public static final Material SEA_LANTERN = new Material(new BlockMaterial(BlockTypes.SEA_LANTERN.get(), "SEA_LANTERN"));
    public static final Material RED_SANDSTONE = new Material(new BlockMaterial(BlockTypes.RED_SANDSTONE.get(), "RED_SANDSTONE"));
    public static final Material CHISELED_RED_SANDSTONE = new Material(new BlockMaterial(BlockTypes.CHISELED_RED_SANDSTONE.get(), "CHISELED_RED_SANDSTONE"));
    public static final Material CUT_RED_SANDSTONE = new Material(new BlockMaterial(BlockTypes.CUT_RED_SANDSTONE.get(), "CUT_RED_SANDSTONE"));
    public static final Material RED_SANDSTONE_STAIRS = new Material(new BlockMaterial(BlockTypes.RED_SANDSTONE_STAIRS.get(), "RED_SANDSTONE_STAIRS"));
    public static final Material REPEATING_COMMAND_BLOCK = new Material(new BlockMaterial(BlockTypes.REPEATING_COMMAND_BLOCK.get(), "REPEATING_COMMAND_BLOCK"));
    public static final Material CHAIN_COMMAND_BLOCK = new Material(new BlockMaterial(BlockTypes.CHAIN_COMMAND_BLOCK.get(), "CHAIN_COMMAND_BLOCK"));
    public static final Material MAGMA_BLOCK = new Material(new BlockMaterial(BlockTypes.MAGMA_BLOCK.get(), "MAGMA_BLOCK"));
    public static final Material NETHER_WART_BLOCK = new Material(new BlockMaterial(BlockTypes.NETHER_WART_BLOCK.get(), "NETHER_WART_BLOCK"));
    public static final Material WARPED_WART_BLOCK = new Material(new BlockMaterial(BlockTypes.WARPED_WART_BLOCK.get(), "WARPED_WART_BLOCK"));
    public static final Material RED_NETHER_BRICKS = new Material(new BlockMaterial(BlockTypes.RED_NETHER_BRICKS.get(), "RED_NETHER_BRICKS"));
    public static final Material BONE_BLOCK = new Material(new BlockMaterial(BlockTypes.BONE_BLOCK.get(), "BONE_BLOCK"));
    public static final Material STRUCTURE_VOID = new Material(new BlockMaterial(BlockTypes.STRUCTURE_VOID.get(), "STRUCTURE_VOID"));
    public static final Material OBSERVER = new Material(new BlockMaterial(BlockTypes.OBSERVER.get(), "OBSERVER"));
    public static final Material SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.SHULKER_BOX.get(), "SHULKER_BOX"));
    public static final Material WHITE_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.WHITE_SHULKER_BOX.get(), "WHITE_SHULKER_BOX"));
    public static final Material ORANGE_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.ORANGE_SHULKER_BOX.get(), "ORANGE_SHULKER_BOX"));
    public static final Material MAGENTA_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.MAGENTA_SHULKER_BOX.get(), "MAGENTA_SHULKER_BOX"));
    public static final Material LIGHT_BLUE_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_SHULKER_BOX.get(), "LIGHT_BLUE_SHULKER_BOX"));
    public static final Material YELLOW_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.YELLOW_SHULKER_BOX.get(), "YELLOW_SHULKER_BOX"));
    public static final Material LIME_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.LIME_SHULKER_BOX.get(), "LIME_SHULKER_BOX"));
    public static final Material PINK_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.PINK_SHULKER_BOX.get(), "PINK_SHULKER_BOX"));
    public static final Material GRAY_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.GRAY_SHULKER_BOX.get(), "GRAY_SHULKER_BOX"));
    public static final Material LIGHT_GRAY_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_SHULKER_BOX.get(), "LIGHT_GRAY_SHULKER_BOX"));
    public static final Material CYAN_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.CYAN_SHULKER_BOX.get(), "CYAN_SHULKER_BOX"));
    public static final Material PURPLE_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.PURPLE_SHULKER_BOX.get(), "PURPLE_SHULKER_BOX"));
    public static final Material BLUE_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.BLUE_SHULKER_BOX.get(), "BLUE_SHULKER_BOX"));
    public static final Material BROWN_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.BROWN_SHULKER_BOX.get(), "BROWN_SHULKER_BOX"));
    public static final Material GREEN_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.GREEN_SHULKER_BOX.get(), "GREEN_SHULKER_BOX"));
    public static final Material RED_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.RED_SHULKER_BOX.get(), "RED_SHULKER_BOX"));
    public static final Material BLACK_SHULKER_BOX = new Material(new BlockMaterial(BlockTypes.BLACK_SHULKER_BOX.get(), "BLACK_SHULKER_BOX"));
    public static final Material WHITE_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.WHITE_GLAZED_TERRACOTTA.get(), "WHITE_GLAZED_TERRACOTTA"));
    public static final Material ORANGE_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.ORANGE_GLAZED_TERRACOTTA.get(), "ORANGE_GLAZED_TERRACOTTA"));
    public static final Material MAGENTA_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.MAGENTA_GLAZED_TERRACOTTA.get(), "MAGENTA_GLAZED_TERRACOTTA"));
    public static final Material LIGHT_BLUE_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_GLAZED_TERRACOTTA.get(), "LIGHT_BLUE_GLAZED_TERRACOTTA"));
    public static final Material YELLOW_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.YELLOW_GLAZED_TERRACOTTA.get(), "YELLOW_GLAZED_TERRACOTTA"));
    public static final Material LIME_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.LIME_GLAZED_TERRACOTTA.get(), "LIME_GLAZED_TERRACOTTA"));
    public static final Material PINK_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.PINK_GLAZED_TERRACOTTA.get(), "PINK_GLAZED_TERRACOTTA"));
    public static final Material GRAY_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.GRAY_GLAZED_TERRACOTTA.get(), "GRAY_GLAZED_TERRACOTTA"));
    public static final Material LIGHT_GRAY_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_GLAZED_TERRACOTTA.get(), "LIGHT_GRAY_GLAZED_TERRACOTTA"));
    public static final Material CYAN_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.CYAN_GLAZED_TERRACOTTA.get(), "CYAN_GLAZED_TERRACOTTA"));
    public static final Material PURPLE_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.PURPLE_GLAZED_TERRACOTTA.get(), "PURPLE_GLAZED_TERRACOTTA"));
    public static final Material BLUE_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.BLUE_GLAZED_TERRACOTTA.get(), "BLUE_GLAZED_TERRACOTTA"));
    public static final Material BROWN_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.BROWN_GLAZED_TERRACOTTA.get(), "BROWN_GLAZED_TERRACOTTA"));
    public static final Material GREEN_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.GREEN_GLAZED_TERRACOTTA.get(), "GREEN_GLAZED_TERRACOTTA"));
    public static final Material RED_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.RED_GLAZED_TERRACOTTA.get(), "RED_GLAZED_TERRACOTTA"));
    public static final Material BLACK_GLAZED_TERRACOTTA = new Material(new BlockMaterial(BlockTypes.BLACK_GLAZED_TERRACOTTA.get(), "BLACK_GLAZED_TERRACOTTA"));
    public static final Material WHITE_CONCRETE = new Material(new BlockMaterial(BlockTypes.WHITE_CONCRETE.get(), "WHITE_CONCRETE"));
    public static final Material ORANGE_CONCRETE = new Material(new BlockMaterial(BlockTypes.ORANGE_CONCRETE.get(), "ORANGE_CONCRETE"));
    public static final Material MAGENTA_CONCRETE = new Material(new BlockMaterial(BlockTypes.MAGENTA_CONCRETE.get(), "MAGENTA_CONCRETE"));
    public static final Material LIGHT_BLUE_CONCRETE = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_CONCRETE.get(), "LIGHT_BLUE_CONCRETE"));
    public static final Material YELLOW_CONCRETE = new Material(new BlockMaterial(BlockTypes.YELLOW_CONCRETE.get(), "YELLOW_CONCRETE"));
    public static final Material LIME_CONCRETE = new Material(new BlockMaterial(BlockTypes.LIME_CONCRETE.get(), "LIME_CONCRETE"));
    public static final Material PINK_CONCRETE = new Material(new BlockMaterial(BlockTypes.PINK_CONCRETE.get(), "PINK_CONCRETE"));
    public static final Material GRAY_CONCRETE = new Material(new BlockMaterial(BlockTypes.GRAY_CONCRETE.get(), "GRAY_CONCRETE"));
    public static final Material LIGHT_GRAY_CONCRETE = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_CONCRETE.get(), "LIGHT_GRAY_CONCRETE"));
    public static final Material CYAN_CONCRETE = new Material(new BlockMaterial(BlockTypes.CYAN_CONCRETE.get(), "CYAN_CONCRETE"));
    public static final Material PURPLE_CONCRETE = new Material(new BlockMaterial(BlockTypes.PURPLE_CONCRETE.get(), "PURPLE_CONCRETE"));
    public static final Material BLUE_CONCRETE = new Material(new BlockMaterial(BlockTypes.BLUE_CONCRETE.get(), "BLUE_CONCRETE"));
    public static final Material BROWN_CONCRETE = new Material(new BlockMaterial(BlockTypes.BROWN_CONCRETE.get(), "BROWN_CONCRETE"));
    public static final Material GREEN_CONCRETE = new Material(new BlockMaterial(BlockTypes.GREEN_CONCRETE.get(), "GREEN_CONCRETE"));
    public static final Material RED_CONCRETE = new Material(new BlockMaterial(BlockTypes.RED_CONCRETE.get(), "RED_CONCRETE"));
    public static final Material BLACK_CONCRETE = new Material(new BlockMaterial(BlockTypes.BLACK_CONCRETE.get(), "BLACK_CONCRETE"));
    public static final Material WHITE_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.WHITE_CONCRETE_POWDER.get(), "WHITE_CONCRETE_POWDER"));
    public static final Material ORANGE_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.ORANGE_CONCRETE_POWDER.get(), "ORANGE_CONCRETE_POWDER"));
    public static final Material MAGENTA_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.MAGENTA_CONCRETE_POWDER.get(), "MAGENTA_CONCRETE_POWDER"));
    public static final Material LIGHT_BLUE_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_CONCRETE_POWDER.get(), "LIGHT_BLUE_CONCRETE_POWDER"));
    public static final Material YELLOW_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.YELLOW_CONCRETE_POWDER.get(), "YELLOW_CONCRETE_POWDER"));
    public static final Material LIME_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.LIME_CONCRETE_POWDER.get(), "LIME_CONCRETE_POWDER"));
    public static final Material PINK_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.PINK_CONCRETE_POWDER.get(), "PINK_CONCRETE_POWDER"));
    public static final Material GRAY_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.GRAY_CONCRETE_POWDER.get(), "GRAY_CONCRETE_POWDER"));
    public static final Material LIGHT_GRAY_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_CONCRETE_POWDER.get(), "LIGHT_GRAY_CONCRETE_POWDER"));
    public static final Material CYAN_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.CYAN_CONCRETE_POWDER.get(), "CYAN_CONCRETE_POWDER"));
    public static final Material PURPLE_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.PURPLE_CONCRETE_POWDER.get(), "PURPLE_CONCRETE_POWDER"));
    public static final Material BLUE_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.BLUE_CONCRETE_POWDER.get(), "BLUE_CONCRETE_POWDER"));
    public static final Material BROWN_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.BROWN_CONCRETE_POWDER.get(), "BROWN_CONCRETE_POWDER"));
    public static final Material GREEN_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.GREEN_CONCRETE_POWDER.get(), "GREEN_CONCRETE_POWDER"));
    public static final Material RED_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.RED_CONCRETE_POWDER.get(), "RED_CONCRETE_POWDER"));
    public static final Material BLACK_CONCRETE_POWDER = new Material(new BlockMaterial(BlockTypes.BLACK_CONCRETE_POWDER.get(), "BLACK_CONCRETE_POWDER"));
    public static final Material TURTLE_EGG = new Material(new BlockMaterial(BlockTypes.TURTLE_EGG.get(), "TURTLE_EGG"));
    public static final Material DEAD_TUBE_CORAL_BLOCK = new Material(new BlockMaterial(BlockTypes.DEAD_TUBE_CORAL_BLOCK.get(), "DEAD_TUBE_CORAL_BLOCK"));
    public static final Material DEAD_BRAIN_CORAL_BLOCK = new Material(new BlockMaterial(BlockTypes.DEAD_BRAIN_CORAL_BLOCK.get(), "DEAD_BRAIN_CORAL_BLOCK"));
    public static final Material DEAD_BUBBLE_CORAL_BLOCK = new Material(new BlockMaterial(BlockTypes.DEAD_BUBBLE_CORAL_BLOCK.get(), "DEAD_BUBBLE_CORAL_BLOCK"));
    public static final Material DEAD_FIRE_CORAL_BLOCK = new Material(new BlockMaterial(BlockTypes.DEAD_FIRE_CORAL_BLOCK.get(), "DEAD_FIRE_CORAL_BLOCK"));
    public static final Material DEAD_HORN_CORAL_BLOCK = new Material(new BlockMaterial(BlockTypes.DEAD_HORN_CORAL_BLOCK.get(), "DEAD_HORN_CORAL_BLOCK"));
    public static final Material TUBE_CORAL_BLOCK = new Material(new BlockMaterial(BlockTypes.TUBE_CORAL_BLOCK.get(), "TUBE_CORAL_BLOCK"));
    public static final Material BRAIN_CORAL_BLOCK = new Material(new BlockMaterial(BlockTypes.BRAIN_CORAL_BLOCK.get(), "BRAIN_CORAL_BLOCK"));
    public static final Material BUBBLE_CORAL_BLOCK = new Material(new BlockMaterial(BlockTypes.BUBBLE_CORAL_BLOCK.get(), "BUBBLE_CORAL_BLOCK"));
    public static final Material FIRE_CORAL_BLOCK = new Material(new BlockMaterial(BlockTypes.FIRE_CORAL_BLOCK.get(), "FIRE_CORAL_BLOCK"));
    public static final Material HORN_CORAL_BLOCK = new Material(new BlockMaterial(BlockTypes.HORN_CORAL_BLOCK.get(), "HORN_CORAL_BLOCK"));
    public static final Material TUBE_CORAL = new Material(new BlockMaterial(BlockTypes.TUBE_CORAL.get(), "TUBE_CORAL"));
    public static final Material BRAIN_CORAL = new Material(new BlockMaterial(BlockTypes.BRAIN_CORAL.get(), "BRAIN_CORAL"));
    public static final Material BUBBLE_CORAL = new Material(new BlockMaterial(BlockTypes.BUBBLE_CORAL.get(), "BUBBLE_CORAL"));
    public static final Material FIRE_CORAL = new Material(new BlockMaterial(BlockTypes.FIRE_CORAL.get(), "FIRE_CORAL"));
    public static final Material HORN_CORAL = new Material(new BlockMaterial(BlockTypes.HORN_CORAL.get(), "HORN_CORAL"));
    public static final Material DEAD_BRAIN_CORAL = new Material(new BlockMaterial(BlockTypes.DEAD_BRAIN_CORAL.get(), "DEAD_BRAIN_CORAL"));
    public static final Material DEAD_BUBBLE_CORAL = new Material(new BlockMaterial(BlockTypes.DEAD_BUBBLE_CORAL.get(), "DEAD_BUBBLE_CORAL"));
    public static final Material DEAD_FIRE_CORAL = new Material(new BlockMaterial(BlockTypes.DEAD_FIRE_CORAL.get(), "DEAD_FIRE_CORAL"));
    public static final Material DEAD_HORN_CORAL = new Material(new BlockMaterial(BlockTypes.DEAD_HORN_CORAL.get(), "DEAD_HORN_CORAL"));
    public static final Material DEAD_TUBE_CORAL = new Material(new BlockMaterial(BlockTypes.DEAD_TUBE_CORAL.get(), "DEAD_TUBE_CORAL"));
    public static final Material TUBE_CORAL_FAN = new Material(new BlockMaterial(BlockTypes.TUBE_CORAL_FAN.get(), "TUBE_CORAL_FAN"));
    public static final Material BRAIN_CORAL_FAN = new Material(new BlockMaterial(BlockTypes.BRAIN_CORAL_FAN.get(), "BRAIN_CORAL_FAN"));
    public static final Material BUBBLE_CORAL_FAN = new Material(new BlockMaterial(BlockTypes.BUBBLE_CORAL_FAN.get(), "BUBBLE_CORAL_FAN"));
    public static final Material FIRE_CORAL_FAN = new Material(new BlockMaterial(BlockTypes.FIRE_CORAL_FAN.get(), "FIRE_CORAL_FAN"));
    public static final Material HORN_CORAL_FAN = new Material(new BlockMaterial(BlockTypes.HORN_CORAL_FAN.get(), "HORN_CORAL_FAN"));
    public static final Material DEAD_TUBE_CORAL_FAN = new Material(new BlockMaterial(BlockTypes.DEAD_TUBE_CORAL_FAN.get(), "DEAD_TUBE_CORAL_FAN"));
    public static final Material DEAD_BRAIN_CORAL_FAN = new Material(new BlockMaterial(BlockTypes.DEAD_BRAIN_CORAL_FAN.get(), "DEAD_BRAIN_CORAL_FAN"));
    public static final Material DEAD_BUBBLE_CORAL_FAN = new Material(new BlockMaterial(BlockTypes.DEAD_BUBBLE_CORAL_FAN.get(), "DEAD_BUBBLE_CORAL_FAN"));
    public static final Material DEAD_FIRE_CORAL_FAN = new Material(new BlockMaterial(BlockTypes.DEAD_FIRE_CORAL_FAN.get(), "DEAD_FIRE_CORAL_FAN"));
    public static final Material DEAD_HORN_CORAL_FAN = new Material(new BlockMaterial(BlockTypes.DEAD_HORN_CORAL_FAN.get(), "DEAD_HORN_CORAL_FAN"));
    public static final Material BLUE_ICE = new Material(new BlockMaterial(BlockTypes.BLUE_ICE.get(), "BLUE_ICE"));
    public static final Material CONDUIT = new Material(new BlockMaterial(BlockTypes.CONDUIT.get(), "CONDUIT"));
    public static final Material POLISHED_GRANITE_STAIRS = new Material(new BlockMaterial(BlockTypes.POLISHED_GRANITE_STAIRS.get(), "POLISHED_GRANITE_STAIRS"));
    public static final Material SMOOTH_RED_SANDSTONE_STAIRS = new Material(new BlockMaterial(BlockTypes.SMOOTH_RED_SANDSTONE_STAIRS.get(), "SMOOTH_RED_SANDSTONE_STAIRS"));
    public static final Material MOSSY_STONE_BRICK_STAIRS = new Material(new BlockMaterial(BlockTypes.MOSSY_STONE_BRICK_STAIRS.get(), "MOSSY_STONE_BRICK_STAIRS"));
    public static final Material POLISHED_DIORITE_STAIRS = new Material(new BlockMaterial(BlockTypes.POLISHED_DIORITE_STAIRS.get(), "POLISHED_DIORITE_STAIRS"));
    public static final Material MOSSY_COBBLESTONE_STAIRS = new Material(new BlockMaterial(BlockTypes.MOSSY_COBBLESTONE_STAIRS.get(), "MOSSY_COBBLESTONE_STAIRS"));
    public static final Material END_STONE_BRICK_STAIRS = new Material(new BlockMaterial(BlockTypes.END_STONE_BRICK_STAIRS.get(), "END_STONE_BRICK_STAIRS"));
    public static final Material STONE_STAIRS = new Material(new BlockMaterial(BlockTypes.STONE_STAIRS.get(), "STONE_STAIRS"));
    public static final Material SMOOTH_SANDSTONE_STAIRS = new Material(new BlockMaterial(BlockTypes.SMOOTH_SANDSTONE_STAIRS.get(), "SMOOTH_SANDSTONE_STAIRS"));
    public static final Material SMOOTH_QUARTZ_STAIRS = new Material(new BlockMaterial(BlockTypes.SMOOTH_QUARTZ_STAIRS.get(), "SMOOTH_QUARTZ_STAIRS"));
    public static final Material GRANITE_STAIRS = new Material(new BlockMaterial(BlockTypes.GRANITE_STAIRS.get(), "GRANITE_STAIRS"));
    public static final Material ANDESITE_STAIRS = new Material(new BlockMaterial(BlockTypes.ANDESITE_STAIRS.get(), "ANDESITE_STAIRS"));
    public static final Material RED_NETHER_BRICK_STAIRS = new Material(new BlockMaterial(BlockTypes.RED_NETHER_BRICK_STAIRS.get(), "RED_NETHER_BRICK_STAIRS"));
    public static final Material POLISHED_ANDESITE_STAIRS = new Material(new BlockMaterial(BlockTypes.POLISHED_ANDESITE_STAIRS.get(), "POLISHED_ANDESITE_STAIRS"));
    public static final Material DIORITE_STAIRS = new Material(new BlockMaterial(BlockTypes.DIORITE_STAIRS.get(), "DIORITE_STAIRS"));
    public static final Material POLISHED_GRANITE_SLAB = new Material(new BlockMaterial(BlockTypes.POLISHED_GRANITE_SLAB.get(), "POLISHED_GRANITE_SLAB"));
    public static final Material SMOOTH_RED_SANDSTONE_SLAB = new Material(new BlockMaterial(BlockTypes.SMOOTH_RED_SANDSTONE_SLAB.get(), "SMOOTH_RED_SANDSTONE_SLAB"));
    public static final Material MOSSY_STONE_BRICK_SLAB = new Material(new BlockMaterial(BlockTypes.MOSSY_STONE_BRICK_SLAB.get(), "MOSSY_STONE_BRICK_SLAB"));
    public static final Material POLISHED_DIORITE_SLAB = new Material(new BlockMaterial(BlockTypes.POLISHED_DIORITE_SLAB.get(), "POLISHED_DIORITE_SLAB"));
    public static final Material MOSSY_COBBLESTONE_SLAB = new Material(new BlockMaterial(BlockTypes.MOSSY_COBBLESTONE_SLAB.get(), "MOSSY_COBBLESTONE_SLAB"));
    public static final Material END_STONE_BRICK_SLAB = new Material(new BlockMaterial(BlockTypes.END_STONE_BRICK_SLAB.get(), "END_STONE_BRICK_SLAB"));
    public static final Material SMOOTH_SANDSTONE_SLAB = new Material(new BlockMaterial(BlockTypes.SMOOTH_SANDSTONE_SLAB.get(), "SMOOTH_SANDSTONE_SLAB"));
    public static final Material SMOOTH_QUARTZ_SLAB = new Material(new BlockMaterial(BlockTypes.SMOOTH_QUARTZ_SLAB.get(), "SMOOTH_QUARTZ_SLAB"));
    public static final Material GRANITE_SLAB = new Material(new BlockMaterial(BlockTypes.GRANITE_SLAB.get(), "GRANITE_SLAB"));
    public static final Material ANDESITE_SLAB = new Material(new BlockMaterial(BlockTypes.ANDESITE_SLAB.get(), "ANDESITE_SLAB"));
    public static final Material RED_NETHER_BRICK_SLAB = new Material(new BlockMaterial(BlockTypes.RED_NETHER_BRICK_SLAB.get(), "RED_NETHER_BRICK_SLAB"));
    public static final Material POLISHED_ANDESITE_SLAB = new Material(new BlockMaterial(BlockTypes.POLISHED_ANDESITE_SLAB.get(), "POLISHED_ANDESITE_SLAB"));
    public static final Material DIORITE_SLAB = new Material(new BlockMaterial(BlockTypes.DIORITE_SLAB.get(), "DIORITE_SLAB"));
    public static final Material SCAFFOLDING = new Material(new BlockMaterial(BlockTypes.SCAFFOLDING.get(), "SCAFFOLDING"));
    public static final Material IRON_DOOR = new Material(new BlockMaterial(BlockTypes.IRON_DOOR.get(), "IRON_DOOR"));
    public static final Material OAK_DOOR = new Material(new BlockMaterial(BlockTypes.OAK_DOOR.get(), "OAK_DOOR"));
    public static final Material SPRUCE_DOOR = new Material(new BlockMaterial(BlockTypes.SPRUCE_DOOR.get(), "SPRUCE_DOOR"));
    public static final Material BIRCH_DOOR = new Material(new BlockMaterial(BlockTypes.BIRCH_DOOR.get(), "BIRCH_DOOR"));
    public static final Material JUNGLE_DOOR = new Material(new BlockMaterial(BlockTypes.JUNGLE_DOOR.get(), "JUNGLE_DOOR"));
    public static final Material ACACIA_DOOR = new Material(new BlockMaterial(BlockTypes.ACACIA_DOOR.get(), "ACACIA_DOOR"));
    public static final Material DARK_OAK_DOOR = new Material(new BlockMaterial(BlockTypes.DARK_OAK_DOOR.get(), "DARK_OAK_DOOR"));
    public static final Material CRIMSON_DOOR = new Material(new BlockMaterial(BlockTypes.CRIMSON_DOOR.get(), "CRIMSON_DOOR"));
    public static final Material WARPED_DOOR = new Material(new BlockMaterial(BlockTypes.WARPED_DOOR.get(), "WARPED_DOOR"));
    public static final Material REPEATER = new Material(new BlockMaterial(BlockTypes.REPEATER.get(), "REPEATER"));
    public static final Material COMPARATOR = new Material(new BlockMaterial(BlockTypes.COMPARATOR.get(), "COMPARATOR"));
    public static final Material STRUCTURE_BLOCK = new Material(new BlockMaterial(BlockTypes.STRUCTURE_BLOCK.get(), "STRUCTURE_BLOCK"));
    public static final Material JIGSAW = new Material(new BlockMaterial(BlockTypes.JIGSAW.get(), "JIGSAW"));
    public static final Material TURTLE_HELMET = new Material(new ItemMaterial(ItemTypes.TURTLE_HELMET.get(), "TURTLE_HELMET"));
    public static final Material SCUTE = new Material(new ItemMaterial(ItemTypes.SCUTE.get(), "SCUTE"));
    public static final Material FLINT_AND_STEEL = new Material(new ItemMaterial(ItemTypes.FLINT_AND_STEEL.get(), "FLINT_AND_STEEL"));
    public static final Material APPLE = new Material(new ItemMaterial(ItemTypes.APPLE.get(), "APPLE"));
    public static final Material BOW = new Material(new ItemMaterial(ItemTypes.BOW.get(), "BOW"));
    public static final Material ARROW = new Material(new ItemMaterial(ItemTypes.ARROW.get(), "ARROW"));
    public static final Material COAL = new Material(new ItemMaterial(ItemTypes.COAL.get(), "COAL"));
    public static final Material CHARCOAL = new Material(new ItemMaterial(ItemTypes.CHARCOAL.get(), "CHARCOAL"));
    public static final Material DIAMOND = new Material(new ItemMaterial(ItemTypes.DIAMOND.get(), "DIAMOND"));
    public static final Material IRON_INGOT = new Material(new ItemMaterial(ItemTypes.IRON_INGOT.get(), "IRON_INGOT"));
    public static final Material GOLD_INGOT = new Material(new ItemMaterial(ItemTypes.GOLD_INGOT.get(), "GOLD_INGOT"));
    public static final Material NETHERITE_INGOT = new Material(new ItemMaterial(ItemTypes.NETHERITE_INGOT.get(), "NETHERITE_INGOT"));
    public static final Material NETHERITE_SCRAP = new Material(new ItemMaterial(ItemTypes.NETHERITE_SCRAP.get(), "NETHERITE_SCRAP"));
    public static final Material WOODEN_SWORD = new Material(new ItemMaterial(ItemTypes.WOODEN_SWORD.get(), "WOODEN_SWORD"));
    public static final Material WOODEN_SHOVEL = new Material(new ItemMaterial(ItemTypes.WOODEN_SHOVEL.get(), "WOODEN_SHOVEL"));
    public static final Material WOODEN_PICKAXE = new Material(new ItemMaterial(ItemTypes.WOODEN_PICKAXE.get(), "WOODEN_PICKAXE"));
    public static final Material WOODEN_AXE = new Material(new ItemMaterial(ItemTypes.WOODEN_AXE.get(), "WOODEN_AXE"));
    public static final Material WOODEN_HOE = new Material(new ItemMaterial(ItemTypes.WOODEN_HOE.get(), "WOODEN_HOE"));
    public static final Material STONE_SWORD = new Material(new ItemMaterial(ItemTypes.STONE_SWORD.get(), "STONE_SWORD"));
    public static final Material STONE_SHOVEL = new Material(new ItemMaterial(ItemTypes.STONE_SHOVEL.get(), "STONE_SHOVEL"));
    public static final Material STONE_PICKAXE = new Material(new ItemMaterial(ItemTypes.STONE_PICKAXE.get(), "STONE_PICKAXE"));
    public static final Material STONE_AXE = new Material(new ItemMaterial(ItemTypes.STONE_AXE.get(), "STONE_AXE"));
    public static final Material STONE_HOE = new Material(new ItemMaterial(ItemTypes.STONE_HOE.get(), "STONE_HOE"));
    public static final Material GOLDEN_SWORD = new Material(new ItemMaterial(ItemTypes.GOLDEN_SWORD.get(), "GOLDEN_SWORD"));
    public static final Material GOLDEN_SHOVEL = new Material(new ItemMaterial(ItemTypes.GOLDEN_SHOVEL.get(), "GOLDEN_SHOVEL"));
    public static final Material GOLDEN_PICKAXE = new Material(new ItemMaterial(ItemTypes.GOLDEN_PICKAXE.get(), "GOLDEN_PICKAXE"));
    public static final Material GOLDEN_AXE = new Material(new ItemMaterial(ItemTypes.GOLDEN_AXE.get(), "GOLDEN_AXE"));
    public static final Material GOLDEN_HOE = new Material(new ItemMaterial(ItemTypes.GOLDEN_HOE.get(), "GOLDEN_HOE"));
    public static final Material IRON_SWORD = new Material(new ItemMaterial(ItemTypes.IRON_SWORD.get(), "IRON_SWORD"));
    public static final Material IRON_SHOVEL = new Material(new ItemMaterial(ItemTypes.IRON_SHOVEL.get(), "IRON_SHOVEL"));
    public static final Material IRON_PICKAXE = new Material(new ItemMaterial(ItemTypes.IRON_PICKAXE.get(), "IRON_PICKAXE"));
    public static final Material IRON_AXE = new Material(new ItemMaterial(ItemTypes.IRON_AXE.get(), "IRON_AXE"));
    public static final Material IRON_HOE = new Material(new ItemMaterial(ItemTypes.IRON_HOE.get(), "IRON_HOE"));
    public static final Material DIAMOND_SWORD = new Material(new ItemMaterial(ItemTypes.DIAMOND_SWORD.get(), "DIAMOND_SWORD"));
    public static final Material DIAMOND_SHOVEL = new Material(new ItemMaterial(ItemTypes.DIAMOND_SHOVEL.get(), "DIAMOND_SHOVEL"));
    public static final Material DIAMOND_PICKAXE = new Material(new ItemMaterial(ItemTypes.DIAMOND_PICKAXE.get(), "DIAMOND_PICKAXE"));
    public static final Material DIAMOND_AXE = new Material(new ItemMaterial(ItemTypes.DIAMOND_AXE.get(), "DIAMOND_AXE"));
    public static final Material DIAMOND_HOE = new Material(new ItemMaterial(ItemTypes.DIAMOND_HOE.get(), "DIAMOND_HOE"));
    public static final Material NETHERITE_SWORD = new Material(new ItemMaterial(ItemTypes.NETHERITE_SWORD.get(), "NETHERITE_SWORD"));
    public static final Material NETHERITE_SHOVEL = new Material(new ItemMaterial(ItemTypes.NETHERITE_SHOVEL.get(), "NETHERITE_SHOVEL"));
    public static final Material NETHERITE_PICKAXE = new Material(new ItemMaterial(ItemTypes.NETHERITE_PICKAXE.get(), "NETHERITE_PICKAXE"));
    public static final Material NETHERITE_AXE = new Material(new ItemMaterial(ItemTypes.NETHERITE_AXE.get(), "NETHERITE_AXE"));
    public static final Material NETHERITE_HOE = new Material(new ItemMaterial(ItemTypes.NETHERITE_HOE.get(), "NETHERITE_HOE"));
    public static final Material STICK = new Material(new ItemMaterial(ItemTypes.STICK.get(), "STICK"));
    public static final Material BOWL = new Material(new ItemMaterial(ItemTypes.BOWL.get(), "BOWL"));
    public static final Material MUSHROOM_STEW = new Material(new ItemMaterial(ItemTypes.MUSHROOM_STEW.get(), "MUSHROOM_STEW"));
    public static final Material STRING = new Material(new ItemMaterial(ItemTypes.STRING.get(), "STRING"));
    public static final Material FEATHER = new Material(new ItemMaterial(ItemTypes.FEATHER.get(), "FEATHER"));
    public static final Material GUNPOWDER = new Material(new ItemMaterial(ItemTypes.GUNPOWDER.get(), "GUNPOWDER"));
    public static final Material WHEAT_SEEDS = new Material(new ItemMaterial(ItemTypes.WHEAT_SEEDS.get(), "WHEAT_SEEDS"));
    public static final Material WHEAT = new Material(new BlockMaterial(BlockTypes.WHEAT.get(), "WHEAT"));
    public static final Material BREAD = new Material(new ItemMaterial(ItemTypes.BREAD.get(), "BREAD"));
    public static final Material LEATHER_HELMET = new Material(new ItemMaterial(ItemTypes.LEATHER_HELMET.get(), "LEATHER_HELMET"));
    public static final Material LEATHER_CHESTPLATE = new Material(new ItemMaterial(ItemTypes.LEATHER_CHESTPLATE.get(), "LEATHER_CHESTPLATE"));
    public static final Material LEATHER_LEGGINGS = new Material(new ItemMaterial(ItemTypes.LEATHER_LEGGINGS.get(), "LEATHER_LEGGINGS"));
    public static final Material LEATHER_BOOTS = new Material(new ItemMaterial(ItemTypes.LEATHER_BOOTS.get(), "LEATHER_BOOTS"));
    public static final Material CHAINMAIL_HELMET = new Material(new ItemMaterial(ItemTypes.CHAINMAIL_HELMET.get(), "CHAINMAIL_HELMET"));
    public static final Material CHAINMAIL_CHESTPLATE = new Material(new ItemMaterial(ItemTypes.CHAINMAIL_CHESTPLATE.get(), "CHAINMAIL_CHESTPLATE"));
    public static final Material CHAINMAIL_LEGGINGS = new Material(new ItemMaterial(ItemTypes.CHAINMAIL_LEGGINGS.get(), "CHAINMAIL_LEGGINGS"));
    public static final Material CHAINMAIL_BOOTS = new Material(new ItemMaterial(ItemTypes.CHAINMAIL_BOOTS.get(), "CHAINMAIL_BOOTS"));
    public static final Material IRON_HELMET = new Material(new ItemMaterial(ItemTypes.IRON_HELMET.get(), "IRON_HELMET"));
    public static final Material IRON_CHESTPLATE = new Material(new ItemMaterial(ItemTypes.IRON_CHESTPLATE.get(), "IRON_CHESTPLATE"));
    public static final Material IRON_LEGGINGS = new Material(new ItemMaterial(ItemTypes.IRON_LEGGINGS.get(), "IRON_LEGGINGS"));
    public static final Material IRON_BOOTS = new Material(new ItemMaterial(ItemTypes.IRON_BOOTS.get(), "IRON_BOOTS"));
    public static final Material DIAMOND_HELMET = new Material(new ItemMaterial(ItemTypes.DIAMOND_HELMET.get(), "DIAMOND_HELMET"));
    public static final Material DIAMOND_CHESTPLATE = new Material(new ItemMaterial(ItemTypes.DIAMOND_CHESTPLATE.get(), "DIAMOND_CHESTPLATE"));
    public static final Material DIAMOND_LEGGINGS = new Material(new ItemMaterial(ItemTypes.DIAMOND_LEGGINGS.get(), "DIAMOND_LEGGINGS"));
    public static final Material DIAMOND_BOOTS = new Material(new ItemMaterial(ItemTypes.DIAMOND_BOOTS.get(), "DIAMOND_BOOTS"));
    public static final Material GOLDEN_HELMET = new Material(new ItemMaterial(ItemTypes.GOLDEN_HELMET.get(), "GOLDEN_HELMET"));
    public static final Material GOLDEN_CHESTPLATE = new Material(new ItemMaterial(ItemTypes.GOLDEN_CHESTPLATE.get(), "GOLDEN_CHESTPLATE"));
    public static final Material GOLDEN_LEGGINGS = new Material(new ItemMaterial(ItemTypes.GOLDEN_LEGGINGS.get(), "GOLDEN_LEGGINGS"));
    public static final Material GOLDEN_BOOTS = new Material(new ItemMaterial(ItemTypes.GOLDEN_BOOTS.get(), "GOLDEN_BOOTS"));
    public static final Material NETHERITE_HELMET = new Material(new ItemMaterial(ItemTypes.NETHERITE_HELMET.get(), "NETHERITE_HELMET"));
    public static final Material NETHERITE_CHESTPLATE = new Material(new ItemMaterial(ItemTypes.NETHERITE_CHESTPLATE.get(), "NETHERITE_CHESTPLATE"));
    public static final Material NETHERITE_LEGGINGS = new Material(new ItemMaterial(ItemTypes.NETHERITE_LEGGINGS.get(), "NETHERITE_LEGGINGS"));
    public static final Material NETHERITE_BOOTS = new Material(new ItemMaterial(ItemTypes.NETHERITE_BOOTS.get(), "NETHERITE_BOOTS"));
    public static final Material FLINT = new Material(new ItemMaterial(ItemTypes.FLINT.get(), "FLINT"));
    public static final Material PORKCHOP = new Material(new ItemMaterial(ItemTypes.PORKCHOP.get(), "PORKCHOP"));
    public static final Material COOKED_PORKCHOP = new Material(new ItemMaterial(ItemTypes.COOKED_PORKCHOP.get(), "COOKED_PORKCHOP"));
    public static final Material PAINTING = new Material(new ItemMaterial(ItemTypes.PAINTING.get(), "PAINTING"));
    public static final Material GOLDEN_APPLE = new Material(new ItemMaterial(ItemTypes.GOLDEN_APPLE.get(), "GOLDEN_APPLE"));
    public static final Material ENCHANTED_GOLDEN_APPLE = new Material(new ItemMaterial(ItemTypes.ENCHANTED_GOLDEN_APPLE.get(), "ENCHANTED_GOLDEN_APPLE"));
    public static final Material OAK_SIGN = new Material(new BlockMaterial(BlockTypes.OAK_SIGN.get(), "OAK_SIGN"));
    public static final Material SPRUCE_SIGN = new Material(new BlockMaterial(BlockTypes.SPRUCE_SIGN.get(), "SPRUCE_SIGN"));
    public static final Material BIRCH_SIGN = new Material(new BlockMaterial(BlockTypes.BIRCH_SIGN.get(), "BIRCH_SIGN"));
    public static final Material JUNGLE_SIGN = new Material(new BlockMaterial(BlockTypes.JUNGLE_SIGN.get(), "JUNGLE_SIGN"));
    public static final Material ACACIA_SIGN = new Material(new BlockMaterial(BlockTypes.ACACIA_SIGN.get(), "ACACIA_SIGN"));
    public static final Material DARK_OAK_SIGN = new Material(new BlockMaterial(BlockTypes.DARK_OAK_SIGN.get(), "DARK_OAK_SIGN"));
    public static final Material CRIMSON_SIGN = new Material(new BlockMaterial(BlockTypes.CRIMSON_SIGN.get(), "CRIMSON_SIGN"));
    public static final Material WARPED_SIGN = new Material(new BlockMaterial(BlockTypes.WARPED_SIGN.get(), "WARPED_SIGN"));
    public static final Material BUCKET = new Material(new ItemMaterial(ItemTypes.BUCKET.get(), "BUCKET"));
    public static final Material WATER_BUCKET = new Material(new ItemMaterial(ItemTypes.WATER_BUCKET.get(), "WATER_BUCKET"));
    public static final Material LAVA_BUCKET = new Material(new ItemMaterial(ItemTypes.LAVA_BUCKET.get(), "LAVA_BUCKET"));
    public static final Material MINECART = new Material(new ItemMaterial(ItemTypes.MINECART.get(), "MINECART"));
    public static final Material SADDLE = new Material(new ItemMaterial(ItemTypes.SADDLE.get(), "SADDLE"));
    public static final Material REDSTONE = new Material(new ItemMaterial(ItemTypes.REDSTONE.get(), "REDSTONE"));
    public static final Material SNOWBALL = new Material(new ItemMaterial(ItemTypes.SNOWBALL.get(), "SNOWBALL"));
    public static final Material OAK_BOAT = new Material(new ItemMaterial(ItemTypes.OAK_BOAT.get(), "OAK_BOAT"));
    public static final Material LEATHER = new Material(new ItemMaterial(ItemTypes.LEATHER.get(), "LEATHER"));
    public static final Material MILK_BUCKET = new Material(new ItemMaterial(ItemTypes.MILK_BUCKET.get(), "MILK_BUCKET"));
    public static final Material PUFFERFISH_BUCKET = new Material(new ItemMaterial(ItemTypes.PUFFERFISH_BUCKET.get(), "PUFFERFISH_BUCKET"));
    public static final Material SALMON_BUCKET = new Material(new ItemMaterial(ItemTypes.SALMON_BUCKET.get(), "SALMON_BUCKET"));
    public static final Material COD_BUCKET = new Material(new ItemMaterial(ItemTypes.COD_BUCKET.get(), "COD_BUCKET"));
    public static final Material TROPICAL_FISH_BUCKET = new Material(new ItemMaterial(ItemTypes.TROPICAL_FISH_BUCKET.get(), "TROPICAL_FISH_BUCKET"));
    public static final Material BRICK = new Material(new ItemMaterial(ItemTypes.BRICK.get(), "BRICK"));
    public static final Material CLAY_BALL = new Material(new ItemMaterial(ItemTypes.CLAY_BALL.get(), "CLAY_BALL"));
    public static final Material DRIED_KELP_BLOCK = new Material(new BlockMaterial(BlockTypes.DRIED_KELP_BLOCK.get(), "DRIED_KELP_BLOCK"));
    public static final Material PAPER = new Material(new ItemMaterial(ItemTypes.PAPER.get(), "PAPER"));
    public static final Material BOOK = new Material(new ItemMaterial(ItemTypes.BOOK.get(), "BOOK"));
    public static final Material SLIME_BALL = new Material(new ItemMaterial(ItemTypes.SLIME_BALL.get(), "SLIME_BALL"));
    public static final Material CHEST_MINECART = new Material(new ItemMaterial(ItemTypes.CHEST_MINECART.get(), "CHEST_MINECART"));
    public static final Material FURNACE_MINECART = new Material(new ItemMaterial(ItemTypes.FURNACE_MINECART.get(), "FURNACE_MINECART"));
    public static final Material EGG = new Material(new ItemMaterial(ItemTypes.EGG.get(), "EGG"));
    public static final Material COMPASS = new Material(new ItemMaterial(ItemTypes.COMPASS.get(), "COMPASS"));
    public static final Material FISHING_ROD = new Material(new ItemMaterial(ItemTypes.FISHING_ROD.get(), "FISHING_ROD"));
    public static final Material CLOCK = new Material(new ItemMaterial(ItemTypes.CLOCK.get(), "CLOCK"));
    public static final Material GLOWSTONE_DUST = new Material(new ItemMaterial(ItemTypes.GLOWSTONE_DUST.get(), "GLOWSTONE_DUST"));
    public static final Material COD = new Material(new ItemMaterial(ItemTypes.COD.get(), "COD"));
    public static final Material SALMON = new Material(new ItemMaterial(ItemTypes.SALMON.get(), "SALMON"));
    public static final Material TROPICAL_FISH = new Material(new ItemMaterial(ItemTypes.TROPICAL_FISH.get(), "TROPICAL_FISH"));
    public static final Material PUFFERFISH = new Material(new ItemMaterial(ItemTypes.PUFFERFISH.get(), "PUFFERFISH"));
    public static final Material COOKED_COD = new Material(new ItemMaterial(ItemTypes.COOKED_COD.get(), "COOKED_COD"));
    public static final Material COOKED_SALMON = new Material(new ItemMaterial(ItemTypes.COOKED_SALMON.get(), "COOKED_SALMON"));
    public static final Material INK_SAC = new Material(new ItemMaterial(ItemTypes.INK_SAC.get(), "INK_SAC"));
    public static final Material COCOA_BEANS = new Material(new ItemMaterial(ItemTypes.COCOA_BEANS.get(), "COCOA_BEANS"));
    public static final Material LAPIS_LAZULI = new Material(new ItemMaterial(ItemTypes.LAPIS_LAZULI.get(), "LAPIS_LAZULI"));
    public static final Material WHITE_DYE = new Material(new ItemMaterial(ItemTypes.WHITE_DYE.get(), "WHITE_DYE"));
    public static final Material ORANGE_DYE = new Material(new ItemMaterial(ItemTypes.ORANGE_DYE.get(), "ORANGE_DYE"));
    public static final Material MAGENTA_DYE = new Material(new ItemMaterial(ItemTypes.MAGENTA_DYE.get(), "MAGENTA_DYE"));
    public static final Material LIGHT_BLUE_DYE = new Material(new ItemMaterial(ItemTypes.LIGHT_BLUE_DYE.get(), "LIGHT_BLUE_DYE"));
    public static final Material YELLOW_DYE = new Material(new ItemMaterial(ItemTypes.YELLOW_DYE.get(), "YELLOW_DYE"));
    public static final Material LIME_DYE = new Material(new ItemMaterial(ItemTypes.LIME_DYE.get(), "LIME_DYE"));
    public static final Material PINK_DYE = new Material(new ItemMaterial(ItemTypes.PINK_DYE.get(), "PINK_DYE"));
    public static final Material GRAY_DYE = new Material(new ItemMaterial(ItemTypes.GRAY_DYE.get(), "GRAY_DYE"));
    public static final Material LIGHT_GRAY_DYE = new Material(new ItemMaterial(ItemTypes.LIGHT_GRAY_DYE.get(), "LIGHT_GRAY_DYE"));
    public static final Material CYAN_DYE = new Material(new ItemMaterial(ItemTypes.CYAN_DYE.get(), "CYAN_DYE"));
    public static final Material PURPLE_DYE = new Material(new ItemMaterial(ItemTypes.PURPLE_DYE.get(), "PURPLE_DYE"));
    public static final Material BLUE_DYE = new Material(new ItemMaterial(ItemTypes.BLUE_DYE.get(), "BLUE_DYE"));
    public static final Material BROWN_DYE = new Material(new ItemMaterial(ItemTypes.BROWN_DYE.get(), "BROWN_DYE"));
    public static final Material GREEN_DYE = new Material(new ItemMaterial(ItemTypes.GREEN_DYE.get(), "GREEN_DYE"));
    public static final Material RED_DYE = new Material(new ItemMaterial(ItemTypes.RED_DYE.get(), "RED_DYE"));
    public static final Material BLACK_DYE = new Material(new ItemMaterial(ItemTypes.BLACK_DYE.get(), "BLACK_DYE"));
    public static final Material BONE_MEAL = new Material(new ItemMaterial(ItemTypes.BONE_MEAL.get(), "BONE_MEAL"));
    public static final Material BONE = new Material(new ItemMaterial(ItemTypes.BONE.get(), "BONE"));
    public static final Material SUGAR = new Material(new ItemMaterial(ItemTypes.SUGAR.get(), "SUGAR"));
    public static final Material CAKE = new Material(new BlockMaterial(BlockTypes.CAKE.get(), "CAKE"));
    public static final Material WHITE_BED = new Material(new BlockMaterial(BlockTypes.WHITE_BED.get(), "WHITE_BED"));
    public static final Material ORANGE_BED = new Material(new BlockMaterial(BlockTypes.ORANGE_BED.get(), "ORANGE_BED"));
    public static final Material MAGENTA_BED = new Material(new BlockMaterial(BlockTypes.MAGENTA_BED.get(), "MAGENTA_BED"));
    public static final Material LIGHT_BLUE_BED = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_BED.get(), "LIGHT_BLUE_BED"));
    public static final Material YELLOW_BED = new Material(new BlockMaterial(BlockTypes.YELLOW_BED.get(), "YELLOW_BED"));
    public static final Material LIME_BED = new Material(new BlockMaterial(BlockTypes.LIME_BED.get(), "LIME_BED"));
    public static final Material PINK_BED = new Material(new BlockMaterial(BlockTypes.PINK_BED.get(), "PINK_BED"));
    public static final Material GRAY_BED = new Material(new BlockMaterial(BlockTypes.GRAY_BED.get(), "GRAY_BED"));
    public static final Material LIGHT_GRAY_BED = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_BED.get(), "LIGHT_GRAY_BED"));
    public static final Material CYAN_BED = new Material(new BlockMaterial(BlockTypes.CYAN_BED.get(), "CYAN_BED"));
    public static final Material PURPLE_BED = new Material(new BlockMaterial(BlockTypes.PURPLE_BED.get(), "PURPLE_BED"));
    public static final Material BLUE_BED = new Material(new BlockMaterial(BlockTypes.BLUE_BED.get(), "BLUE_BED"));
    public static final Material BROWN_BED = new Material(new BlockMaterial(BlockTypes.BROWN_BED.get(), "BROWN_BED"));
    public static final Material GREEN_BED = new Material(new BlockMaterial(BlockTypes.GREEN_BED.get(), "GREEN_BED"));
    public static final Material RED_BED = new Material(new BlockMaterial(BlockTypes.RED_BED.get(), "RED_BED"));
    public static final Material BLACK_BED = new Material(new BlockMaterial(BlockTypes.BLACK_BED.get(), "BLACK_BED"));
    public static final Material COOKIE = new Material(new ItemMaterial(ItemTypes.COOKIE.get(), "COOKIE"));
    public static final Material FILLED_MAP = new Material(new ItemMaterial(ItemTypes.FILLED_MAP.get(), "FILLED_MAP"));
    public static final Material SHEARS = new Material(new ItemMaterial(ItemTypes.SHEARS.get(), "SHEARS"));
    public static final Material MELON_SLICE = new Material(new ItemMaterial(ItemTypes.MELON_SLICE.get(), "MELON_SLICE"));
    public static final Material DRIED_KELP = new Material(new ItemMaterial(ItemTypes.DRIED_KELP.get(), "DRIED_KELP"));
    public static final Material PUMPKIN_SEEDS = new Material(new ItemMaterial(ItemTypes.PUMPKIN_SEEDS.get(), "PUMPKIN_SEEDS"));
    public static final Material MELON_SEEDS = new Material(new ItemMaterial(ItemTypes.MELON_SEEDS.get(), "MELON_SEEDS"));
    public static final Material BEEF = new Material(new ItemMaterial(ItemTypes.BEEF.get(), "BEEF"));
    public static final Material COOKED_BEEF = new Material(new ItemMaterial(ItemTypes.COOKED_BEEF.get(), "COOKED_BEEF"));
    public static final Material CHICKEN = new Material(new ItemMaterial(ItemTypes.CHICKEN.get(), "CHICKEN"));
    public static final Material COOKED_CHICKEN = new Material(new ItemMaterial(ItemTypes.COOKED_CHICKEN.get(), "COOKED_CHICKEN"));
    public static final Material ROTTEN_FLESH = new Material(new ItemMaterial(ItemTypes.ROTTEN_FLESH.get(), "ROTTEN_FLESH"));
    public static final Material ENDER_PEARL = new Material(new ItemMaterial(ItemTypes.ENDER_PEARL.get(), "ENDER_PEARL"));
    public static final Material BLAZE_ROD = new Material(new ItemMaterial(ItemTypes.BLAZE_ROD.get(), "BLAZE_ROD"));
    public static final Material GHAST_TEAR = new Material(new ItemMaterial(ItemTypes.GHAST_TEAR.get(), "GHAST_TEAR"));
    public static final Material GOLD_NUGGET = new Material(new ItemMaterial(ItemTypes.GOLD_NUGGET.get(), "GOLD_NUGGET"));
    public static final Material NETHER_WART = new Material(new BlockMaterial(BlockTypes.NETHER_WART.get(), "NETHER_WART"));
    public static final Material POTION = new Material(new ItemMaterial(ItemTypes.POTION.get(), "POTION"));
    public static final Material GLASS_BOTTLE = new Material(new ItemMaterial(ItemTypes.GLASS_BOTTLE.get(), "GLASS_BOTTLE"));
    public static final Material SPIDER_EYE = new Material(new ItemMaterial(ItemTypes.SPIDER_EYE.get(), "SPIDER_EYE"));
    public static final Material FERMENTED_SPIDER_EYE = new Material(new ItemMaterial(ItemTypes.FERMENTED_SPIDER_EYE.get(), "FERMENTED_SPIDER_EYE"));
    public static final Material BLAZE_POWDER = new Material(new ItemMaterial(ItemTypes.BLAZE_POWDER.get(), "BLAZE_POWDER"));
    public static final Material MAGMA_CREAM = new Material(new ItemMaterial(ItemTypes.MAGMA_CREAM.get(), "MAGMA_CREAM"));
    public static final Material BREWING_STAND = new Material(new BlockMaterial(BlockTypes.BREWING_STAND.get(), "BREWING_STAND"));
    public static final Material CAULDRON = new Material(new BlockMaterial(BlockTypes.CAULDRON.get(), "CAULDRON"));
    public static final Material ENDER_EYE = new Material(new ItemMaterial(ItemTypes.ENDER_EYE.get(), "ENDER_EYE"));
    public static final Material GLISTERING_MELON_SLICE = new Material(new ItemMaterial(ItemTypes.GLISTERING_MELON_SLICE.get(), "GLISTERING_MELON_SLICE"));
    public static final Material BAT_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.BAT_SPAWN_EGG.get(), "BAT_SPAWN_EGG"));
    public static final Material BEE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.BEE_SPAWN_EGG.get(), "BEE_SPAWN_EGG"));
    public static final Material BLAZE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.BLAZE_SPAWN_EGG.get(), "BLAZE_SPAWN_EGG"));
    public static final Material CAT_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.CAT_SPAWN_EGG.get(), "CAT_SPAWN_EGG"));
    public static final Material CAVE_SPIDER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.CAVE_SPIDER_SPAWN_EGG.get(), "CAVE_SPIDER_SPAWN_EGG"));
    public static final Material CHICKEN_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.CHICKEN_SPAWN_EGG.get(), "CHICKEN_SPAWN_EGG"));
    public static final Material COD_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.COD_SPAWN_EGG.get(), "COD_SPAWN_EGG"));
    public static final Material COW_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.COW_SPAWN_EGG.get(), "COW_SPAWN_EGG"));
    public static final Material CREEPER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.CREEPER_SPAWN_EGG.get(), "CREEPER_SPAWN_EGG"));
    public static final Material DOLPHIN_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.DOLPHIN_SPAWN_EGG.get(), "DOLPHIN_SPAWN_EGG"));
    public static final Material DONKEY_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.DONKEY_SPAWN_EGG.get(), "DONKEY_SPAWN_EGG"));
    public static final Material DROWNED_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.DROWNED_SPAWN_EGG.get(), "DROWNED_SPAWN_EGG"));
    public static final Material ELDER_GUARDIAN_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.ELDER_GUARDIAN_SPAWN_EGG.get(), "ELDER_GUARDIAN_SPAWN_EGG"));
    public static final Material ENDERMAN_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.ENDERMAN_SPAWN_EGG.get(), "ENDERMAN_SPAWN_EGG"));
    public static final Material ENDERMITE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.ENDERMITE_SPAWN_EGG.get(), "ENDERMITE_SPAWN_EGG"));
    public static final Material EVOKER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.EVOKER_SPAWN_EGG.get(), "EVOKER_SPAWN_EGG"));
    public static final Material FOX_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.FOX_SPAWN_EGG.get(), "FOX_SPAWN_EGG"));
    public static final Material GHAST_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.GHAST_SPAWN_EGG.get(), "GHAST_SPAWN_EGG"));
    public static final Material GUARDIAN_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.GUARDIAN_SPAWN_EGG.get(), "GUARDIAN_SPAWN_EGG"));
    public static final Material HOGLIN_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.HOGLIN_SPAWN_EGG.get(), "HOGLIN_SPAWN_EGG"));
    public static final Material HORSE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.HORSE_SPAWN_EGG.get(), "HORSE_SPAWN_EGG"));
    public static final Material HUSK_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.HUSK_SPAWN_EGG.get(), "HUSK_SPAWN_EGG"));
    public static final Material LLAMA_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.LLAMA_SPAWN_EGG.get(), "LLAMA_SPAWN_EGG"));
    public static final Material MAGMA_CUBE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.MAGMA_CUBE_SPAWN_EGG.get(), "MAGMA_CUBE_SPAWN_EGG"));
    public static final Material MOOSHROOM_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.MOOSHROOM_SPAWN_EGG.get(), "MOOSHROOM_SPAWN_EGG"));
    public static final Material MULE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.MULE_SPAWN_EGG.get(), "MULE_SPAWN_EGG"));
    public static final Material OCELOT_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.OCELOT_SPAWN_EGG.get(), "OCELOT_SPAWN_EGG"));
    public static final Material PANDA_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.PANDA_SPAWN_EGG.get(), "PANDA_SPAWN_EGG"));
    public static final Material PARROT_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.PARROT_SPAWN_EGG.get(), "PARROT_SPAWN_EGG"));
    public static final Material PHANTOM_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.PHANTOM_SPAWN_EGG.get(), "PHANTOM_SPAWN_EGG"));
    public static final Material PIG_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.PIG_SPAWN_EGG.get(), "PIG_SPAWN_EGG"));
    public static final Material PIGLIN_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.PIGLIN_SPAWN_EGG.get(), "PIGLIN_SPAWN_EGG"));
    public static final Material PIGLIN_BRUTE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.PIGLIN_BRUTE_SPAWN_EGG.get(), "PIGLIN_BRUTE_SPAWN_EGG"));
    public static final Material PILLAGER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.PILLAGER_SPAWN_EGG.get(), "PILLAGER_SPAWN_EGG"));
    public static final Material POLAR_BEAR_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.POLAR_BEAR_SPAWN_EGG.get(), "POLAR_BEAR_SPAWN_EGG"));
    public static final Material PUFFERFISH_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.PUFFERFISH_SPAWN_EGG.get(), "PUFFERFISH_SPAWN_EGG"));
    public static final Material RABBIT_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.RABBIT_SPAWN_EGG.get(), "RABBIT_SPAWN_EGG"));
    public static final Material RAVAGER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.RAVAGER_SPAWN_EGG.get(), "RAVAGER_SPAWN_EGG"));
    public static final Material SALMON_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.SALMON_SPAWN_EGG.get(), "SALMON_SPAWN_EGG"));
    public static final Material SHEEP_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.SHEEP_SPAWN_EGG.get(), "SHEEP_SPAWN_EGG"));
    public static final Material SHULKER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.SHULKER_SPAWN_EGG.get(), "SHULKER_SPAWN_EGG"));
    public static final Material SILVERFISH_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.SILVERFISH_SPAWN_EGG.get(), "SILVERFISH_SPAWN_EGG"));
    public static final Material SKELETON_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.SKELETON_SPAWN_EGG.get(), "SKELETON_SPAWN_EGG"));
    public static final Material SKELETON_HORSE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.SKELETON_HORSE_SPAWN_EGG.get(), "SKELETON_HORSE_SPAWN_EGG"));
    public static final Material SLIME_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.SLIME_SPAWN_EGG.get(), "SLIME_SPAWN_EGG"));
    public static final Material SPIDER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.SPIDER_SPAWN_EGG.get(), "SPIDER_SPAWN_EGG"));
    public static final Material SQUID_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.SQUID_SPAWN_EGG.get(), "SQUID_SPAWN_EGG"));
    public static final Material STRAY_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.STRAY_SPAWN_EGG.get(), "STRAY_SPAWN_EGG"));
    public static final Material STRIDER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.STRIDER_SPAWN_EGG.get(), "STRIDER_SPAWN_EGG"));
    public static final Material TRADER_LLAMA_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.TRADER_LLAMA_SPAWN_EGG.get(), "TRADER_LLAMA_SPAWN_EGG"));
    public static final Material TROPICAL_FISH_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.TROPICAL_FISH_SPAWN_EGG.get(), "TROPICAL_FISH_SPAWN_EGG"));
    public static final Material TURTLE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.TURTLE_SPAWN_EGG.get(), "TURTLE_SPAWN_EGG"));
    public static final Material VEX_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.VEX_SPAWN_EGG.get(), "VEX_SPAWN_EGG"));
    public static final Material VILLAGER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.VILLAGER_SPAWN_EGG.get(), "VILLAGER_SPAWN_EGG"));
    public static final Material VINDICATOR_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.VINDICATOR_SPAWN_EGG.get(), "VINDICATOR_SPAWN_EGG"));
    public static final Material WANDERING_TRADER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.WANDERING_TRADER_SPAWN_EGG.get(), "WANDERING_TRADER_SPAWN_EGG"));
    public static final Material WITCH_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.WITCH_SPAWN_EGG.get(), "WITCH_SPAWN_EGG"));
    public static final Material WITHER_SKELETON_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.WITHER_SKELETON_SPAWN_EGG.get(), "WITHER_SKELETON_SPAWN_EGG"));
    public static final Material WOLF_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.WOLF_SPAWN_EGG.get(), "WOLF_SPAWN_EGG"));
    public static final Material ZOGLIN_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.ZOGLIN_SPAWN_EGG.get(), "ZOGLIN_SPAWN_EGG"));
    public static final Material ZOMBIE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.ZOMBIE_SPAWN_EGG.get(), "ZOMBIE_SPAWN_EGG"));
    public static final Material ZOMBIE_HORSE_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.ZOMBIE_HORSE_SPAWN_EGG.get(), "ZOMBIE_HORSE_SPAWN_EGG"));
    public static final Material ZOMBIE_VILLAGER_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.ZOMBIE_VILLAGER_SPAWN_EGG.get(), "ZOMBIE_VILLAGER_SPAWN_EGG"));
    public static final Material ZOMBIFIED_PIGLIN_SPAWN_EGG = new Material(new ItemMaterial(ItemTypes.ZOMBIFIED_PIGLIN_SPAWN_EGG.get(), "ZOMBIFIED_PIGLIN_SPAWN_EGG"));
    public static final Material EXPERIENCE_BOTTLE = new Material(new ItemMaterial(ItemTypes.EXPERIENCE_BOTTLE.get(), "EXPERIENCE_BOTTLE"));
    public static final Material FIRE_CHARGE = new Material(new ItemMaterial(ItemTypes.FIRE_CHARGE.get(), "FIRE_CHARGE"));
    public static final Material WRITABLE_BOOK = new Material(new ItemMaterial(ItemTypes.WRITABLE_BOOK.get(), "WRITABLE_BOOK"));
    public static final Material WRITTEN_BOOK = new Material(new ItemMaterial(ItemTypes.WRITTEN_BOOK.get(), "WRITTEN_BOOK"));
    public static final Material EMERALD = new Material(new ItemMaterial(ItemTypes.EMERALD.get(), "EMERALD"));
    public static final Material ITEM_FRAME = new Material(new ItemMaterial(ItemTypes.ITEM_FRAME.get(), "ITEM_FRAME"));
    public static final Material FLOWER_POT = new Material(new BlockMaterial(BlockTypes.FLOWER_POT.get(), "FLOWER_POT"));
    public static final Material CARROT = new Material(new ItemMaterial(ItemTypes.CARROT.get(), "CARROT"));
    public static final Material POTATO = new Material(new ItemMaterial(ItemTypes.POTATO.get(), "POTATO"));
    public static final Material BAKED_POTATO = new Material(new ItemMaterial(ItemTypes.BAKED_POTATO.get(), "BAKED_POTATO"));
    public static final Material POISONOUS_POTATO = new Material(new ItemMaterial(ItemTypes.POISONOUS_POTATO.get(), "POISONOUS_POTATO"));
    public static final Material MAP = new Material(new ItemMaterial(ItemTypes.MAP.get(), "MAP"));
    public static final Material GOLDEN_CARROT = new Material(new ItemMaterial(ItemTypes.GOLDEN_CARROT.get(), "GOLDEN_CARROT"));
    public static final Material SKELETON_SKULL = new Material(new BlockMaterial(BlockTypes.SKELETON_SKULL.get(), "SKELETON_SKULL"));
    public static final Material WITHER_SKELETON_SKULL = new Material(new BlockMaterial(BlockTypes.WITHER_SKELETON_SKULL.get(), "WITHER_SKELETON_SKULL"));
    public static final Material PLAYER_HEAD = new Material(new BlockMaterial(BlockTypes.PLAYER_HEAD.get(), "PLAYER_HEAD"));
    public static final Material ZOMBIE_HEAD = new Material(new BlockMaterial(BlockTypes.ZOMBIE_HEAD.get(), "ZOMBIE_HEAD"));
    public static final Material CREEPER_HEAD = new Material(new BlockMaterial(BlockTypes.CREEPER_HEAD.get(), "CREEPER_HEAD"));
    public static final Material DRAGON_HEAD = new Material(new BlockMaterial(BlockTypes.DRAGON_HEAD.get(), "DRAGON_HEAD"));
    public static final Material CARROT_ON_A_STICK = new Material(new ItemMaterial(ItemTypes.CARROT_ON_A_STICK.get(), "CARROT_ON_A_STICK"));
    public static final Material WARPED_FUNGUS_ON_A_STICK = new Material(new ItemMaterial(ItemTypes.WARPED_FUNGUS_ON_A_STICK.get(), "WARPED_FUNGUS_ON_A_STICK"));
    public static final Material NETHER_STAR = new Material(new ItemMaterial(ItemTypes.NETHER_STAR.get(), "NETHER_STAR"));
    public static final Material PUMPKIN_PIE = new Material(new ItemMaterial(ItemTypes.PUMPKIN_PIE.get(), "PUMPKIN_PIE"));
    public static final Material FIREWORK_ROCKET = new Material(new ItemMaterial(ItemTypes.FIREWORK_ROCKET.get(), "FIREWORK_ROCKET"));
    public static final Material FIREWORK_STAR = new Material(new ItemMaterial(ItemTypes.FIREWORK_STAR.get(), "FIREWORK_STAR"));
    public static final Material ENCHANTED_BOOK = new Material(new ItemMaterial(ItemTypes.ENCHANTED_BOOK.get(), "ENCHANTED_BOOK"));
    public static final Material NETHER_BRICK = new Material(new ItemMaterial(ItemTypes.NETHER_BRICK.get(), "NETHER_BRICK"));
    public static final Material QUARTZ = new Material(new ItemMaterial(ItemTypes.QUARTZ.get(), "QUARTZ"));
    public static final Material TNT_MINECART = new Material(new ItemMaterial(ItemTypes.TNT_MINECART.get(), "TNT_MINECART"));
    public static final Material HOPPER_MINECART = new Material(new ItemMaterial(ItemTypes.HOPPER_MINECART.get(), "HOPPER_MINECART"));
    public static final Material PRISMARINE_SHARD = new Material(new ItemMaterial(ItemTypes.PRISMARINE_SHARD.get(), "PRISMARINE_SHARD"));
    public static final Material PRISMARINE_CRYSTALS = new Material(new ItemMaterial(ItemTypes.PRISMARINE_CRYSTALS.get(), "PRISMARINE_CRYSTALS"));
    public static final Material RABBIT = new Material(new ItemMaterial(ItemTypes.RABBIT.get(), "RABBIT"));
    public static final Material COOKED_RABBIT = new Material(new ItemMaterial(ItemTypes.COOKED_RABBIT.get(), "COOKED_RABBIT"));
    public static final Material RABBIT_STEW = new Material(new ItemMaterial(ItemTypes.RABBIT_STEW.get(), "RABBIT_STEW"));
    public static final Material RABBIT_FOOT = new Material(new ItemMaterial(ItemTypes.RABBIT_FOOT.get(), "RABBIT_FOOT"));
    public static final Material RABBIT_HIDE = new Material(new ItemMaterial(ItemTypes.RABBIT_HIDE.get(), "RABBIT_HIDE"));
    public static final Material ARMOR_STAND = new Material(new ItemMaterial(ItemTypes.ARMOR_STAND.get(), "ARMOR_STAND"));
    public static final Material IRON_HORSE_ARMOR = new Material(new ItemMaterial(ItemTypes.IRON_HORSE_ARMOR.get(), "IRON_HORSE_ARMOR"));
    public static final Material GOLDEN_HORSE_ARMOR = new Material(new ItemMaterial(ItemTypes.GOLDEN_HORSE_ARMOR.get(), "GOLDEN_HORSE_ARMOR"));
    public static final Material DIAMOND_HORSE_ARMOR = new Material(new ItemMaterial(ItemTypes.DIAMOND_HORSE_ARMOR.get(), "DIAMOND_HORSE_ARMOR"));
    public static final Material LEATHER_HORSE_ARMOR = new Material(new ItemMaterial(ItemTypes.LEATHER_HORSE_ARMOR.get(), "LEATHER_HORSE_ARMOR"));
    public static final Material LEAD = new Material(new ItemMaterial(ItemTypes.LEAD.get(), "LEAD"));
    public static final Material NAME_TAG = new Material(new ItemMaterial(ItemTypes.NAME_TAG.get(), "NAME_TAG"));
    public static final Material COMMAND_BLOCK_MINECART = new Material(new ItemMaterial(ItemTypes.COMMAND_BLOCK_MINECART.get(), "COMMAND_BLOCK_MINECART"));
    public static final Material MUTTON = new Material(new ItemMaterial(ItemTypes.MUTTON.get(), "MUTTON"));
    public static final Material COOKED_MUTTON = new Material(new ItemMaterial(ItemTypes.COOKED_MUTTON.get(), "COOKED_MUTTON"));
    public static final Material WHITE_BANNER = new Material(new BlockMaterial(BlockTypes.WHITE_BANNER.get(), "WHITE_BANNER"));
    public static final Material ORANGE_BANNER = new Material(new BlockMaterial(BlockTypes.ORANGE_BANNER.get(), "ORANGE_BANNER"));
    public static final Material MAGENTA_BANNER = new Material(new BlockMaterial(BlockTypes.MAGENTA_BANNER.get(), "MAGENTA_BANNER"));
    public static final Material LIGHT_BLUE_BANNER = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_BANNER.get(), "LIGHT_BLUE_BANNER"));
    public static final Material YELLOW_BANNER = new Material(new BlockMaterial(BlockTypes.YELLOW_BANNER.get(), "YELLOW_BANNER"));
    public static final Material LIME_BANNER = new Material(new BlockMaterial(BlockTypes.LIME_BANNER.get(), "LIME_BANNER"));
    public static final Material PINK_BANNER = new Material(new BlockMaterial(BlockTypes.PINK_BANNER.get(), "PINK_BANNER"));
    public static final Material GRAY_BANNER = new Material(new BlockMaterial(BlockTypes.GRAY_BANNER.get(), "GRAY_BANNER"));
    public static final Material LIGHT_GRAY_BANNER = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_BANNER.get(), "LIGHT_GRAY_BANNER"));
    public static final Material CYAN_BANNER = new Material(new BlockMaterial(BlockTypes.CYAN_BANNER.get(), "CYAN_BANNER"));
    public static final Material PURPLE_BANNER = new Material(new BlockMaterial(BlockTypes.PURPLE_BANNER.get(), "PURPLE_BANNER"));
    public static final Material BLUE_BANNER = new Material(new BlockMaterial(BlockTypes.BLUE_BANNER.get(), "BLUE_BANNER"));
    public static final Material BROWN_BANNER = new Material(new BlockMaterial(BlockTypes.BROWN_BANNER.get(), "BROWN_BANNER"));
    public static final Material GREEN_BANNER = new Material(new BlockMaterial(BlockTypes.GREEN_BANNER.get(), "GREEN_BANNER"));
    public static final Material RED_BANNER = new Material(new BlockMaterial(BlockTypes.RED_BANNER.get(), "RED_BANNER"));
    public static final Material BLACK_BANNER = new Material(new BlockMaterial(BlockTypes.BLACK_BANNER.get(), "BLACK_BANNER"));
    public static final Material END_CRYSTAL = new Material(new ItemMaterial(ItemTypes.END_CRYSTAL.get(), "END_CRYSTAL"));
    public static final Material CHORUS_FRUIT = new Material(new ItemMaterial(ItemTypes.CHORUS_FRUIT.get(), "CHORUS_FRUIT"));
    public static final Material POPPED_CHORUS_FRUIT = new Material(new ItemMaterial(ItemTypes.POPPED_CHORUS_FRUIT.get(), "POPPED_CHORUS_FRUIT"));
    public static final Material BEETROOT = new Material(new ItemMaterial(ItemTypes.BEETROOT.get(), "BEETROOT"));
    public static final Material BEETROOT_SEEDS = new Material(new ItemMaterial(ItemTypes.BEETROOT_SEEDS.get(), "BEETROOT_SEEDS"));
    public static final Material BEETROOT_SOUP = new Material(new ItemMaterial(ItemTypes.BEETROOT_SOUP.get(), "BEETROOT_SOUP"));
    public static final Material DRAGON_BREATH = new Material(new ItemMaterial(ItemTypes.DRAGON_BREATH.get(), "DRAGON_BREATH"));
    public static final Material SPLASH_POTION = new Material(new ItemMaterial(ItemTypes.SPLASH_POTION.get(), "SPLASH_POTION"));
    public static final Material SPECTRAL_ARROW = new Material(new ItemMaterial(ItemTypes.SPECTRAL_ARROW.get(), "SPECTRAL_ARROW"));
    public static final Material TIPPED_ARROW = new Material(new ItemMaterial(ItemTypes.TIPPED_ARROW.get(), "TIPPED_ARROW"));
    public static final Material LINGERING_POTION = new Material(new ItemMaterial(ItemTypes.LINGERING_POTION.get(), "LINGERING_POTION"));
    public static final Material SHIELD = new Material(new ItemMaterial(ItemTypes.SHIELD.get(), "SHIELD"));
    public static final Material ELYTRA = new Material(new ItemMaterial(ItemTypes.ELYTRA.get(), "ELYTRA"));
    public static final Material SPRUCE_BOAT = new Material(new ItemMaterial(ItemTypes.SPRUCE_BOAT.get(), "SPRUCE_BOAT"));
    public static final Material BIRCH_BOAT = new Material(new ItemMaterial(ItemTypes.BIRCH_BOAT.get(), "BIRCH_BOAT"));
    public static final Material JUNGLE_BOAT = new Material(new ItemMaterial(ItemTypes.JUNGLE_BOAT.get(), "JUNGLE_BOAT"));
    public static final Material ACACIA_BOAT = new Material(new ItemMaterial(ItemTypes.ACACIA_BOAT.get(), "ACACIA_BOAT"));
    public static final Material DARK_OAK_BOAT = new Material(new ItemMaterial(ItemTypes.DARK_OAK_BOAT.get(), "DARK_OAK_BOAT"));
    public static final Material TOTEM_OF_UNDYING = new Material(new ItemMaterial(ItemTypes.TOTEM_OF_UNDYING.get(), "TOTEM_OF_UNDYING"));
    public static final Material SHULKER_SHELL = new Material(new ItemMaterial(ItemTypes.SHULKER_SHELL.get(), "SHULKER_SHELL"));
    public static final Material IRON_NUGGET = new Material(new ItemMaterial(ItemTypes.IRON_NUGGET.get(), "IRON_NUGGET"));
    public static final Material KNOWLEDGE_BOOK = new Material(new ItemMaterial(ItemTypes.KNOWLEDGE_BOOK.get(), "KNOWLEDGE_BOOK"));
    public static final Material DEBUG_STICK = new Material(new ItemMaterial(ItemTypes.DEBUG_STICK.get(), "DEBUG_STICK"));
    public static final Material MUSIC_DISC_13 = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_13.get(), "MUSIC_DISC_13"));
    public static final Material MUSIC_DISC_CAT = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_CAT.get(), "MUSIC_DISC_CAT"));
    public static final Material MUSIC_DISC_BLOCKS = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_BLOCKS.get(), "MUSIC_DISC_BLOCKS"));
    public static final Material MUSIC_DISC_CHIRP = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_CHIRP.get(), "MUSIC_DISC_CHIRP"));
    public static final Material MUSIC_DISC_FAR = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_FAR.get(), "MUSIC_DISC_FAR"));
    public static final Material MUSIC_DISC_MALL = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_MALL.get(), "MUSIC_DISC_MALL"));
    public static final Material MUSIC_DISC_MELLOHI = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_MELLOHI.get(), "MUSIC_DISC_MELLOHI"));
    public static final Material MUSIC_DISC_STAL = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_STAL.get(), "MUSIC_DISC_STAL"));
    public static final Material MUSIC_DISC_STRAD = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_STRAD.get(), "MUSIC_DISC_STRAD"));
    public static final Material MUSIC_DISC_WARD = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_WARD.get(), "MUSIC_DISC_WARD"));
    public static final Material MUSIC_DISC_11 = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_11.get(), "MUSIC_DISC_11"));
    public static final Material MUSIC_DISC_WAIT = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_WAIT.get(), "MUSIC_DISC_WAIT"));
    public static final Material MUSIC_DISC_PIGSTEP = new Material(new ItemMaterial(ItemTypes.MUSIC_DISC_PIGSTEP.get(), "MUSIC_DISC_PIGSTEP"));
    public static final Material TRIDENT = new Material(new ItemMaterial(ItemTypes.TRIDENT.get(), "TRIDENT"));
    public static final Material PHANTOM_MEMBRANE = new Material(new ItemMaterial(ItemTypes.PHANTOM_MEMBRANE.get(), "PHANTOM_MEMBRANE"));
    public static final Material NAUTILUS_SHELL = new Material(new ItemMaterial(ItemTypes.NAUTILUS_SHELL.get(), "NAUTILUS_SHELL"));
    public static final Material HEART_OF_THE_SEA = new Material(new ItemMaterial(ItemTypes.HEART_OF_THE_SEA.get(), "HEART_OF_THE_SEA"));
    public static final Material CROSSBOW = new Material(new ItemMaterial(ItemTypes.CROSSBOW.get(), "CROSSBOW"));
    public static final Material SUSPICIOUS_STEW = new Material(new ItemMaterial(ItemTypes.SUSPICIOUS_STEW.get(), "SUSPICIOUS_STEW"));
    public static final Material LOOM = new Material(new BlockMaterial(BlockTypes.LOOM.get(), "LOOM"));
    public static final Material FLOWER_BANNER_PATTERN = new Material(new ItemMaterial(ItemTypes.FLOWER_BANNER_PATTERN.get(), "FLOWER_BANNER_PATTERN"));
    public static final Material CREEPER_BANNER_PATTERN = new Material(new ItemMaterial(ItemTypes.CREEPER_BANNER_PATTERN.get(), "CREEPER_BANNER_PATTERN"));
    public static final Material SKULL_BANNER_PATTERN = new Material(new ItemMaterial(ItemTypes.SKULL_BANNER_PATTERN.get(), "SKULL_BANNER_PATTERN"));
    public static final Material MOJANG_BANNER_PATTERN = new Material(new ItemMaterial(ItemTypes.MOJANG_BANNER_PATTERN.get(), "MOJANG_BANNER_PATTERN"));
    public static final Material GLOBE_BANNER_PATTERN = new Material(new ItemMaterial(ItemTypes.GLOBE_BANNER_PATTERN.get(), "GLOBE_BANNER_PATTERN"));
    public static final Material PIGLIN_BANNER_PATTERN = new Material(new ItemMaterial(ItemTypes.PIGLIN_BANNER_PATTERN.get(), "PIGLIN_BANNER_PATTERN"));
    public static final Material COMPOSTER = new Material(new BlockMaterial(BlockTypes.COMPOSTER.get(), "COMPOSTER"));
    public static final Material BARREL = new Material(new BlockMaterial(BlockTypes.BARREL.get(), "BARREL"));
    public static final Material SMOKER = new Material(new BlockMaterial(BlockTypes.SMOKER.get(), "SMOKER"));
    public static final Material BLAST_FURNACE = new Material(new BlockMaterial(BlockTypes.BLAST_FURNACE.get(), "BLAST_FURNACE"));
    public static final Material CARTOGRAPHY_TABLE = new Material(new BlockMaterial(BlockTypes.CARTOGRAPHY_TABLE.get(), "CARTOGRAPHY_TABLE"));
    public static final Material FLETCHING_TABLE = new Material(new BlockMaterial(BlockTypes.FLETCHING_TABLE.get(), "FLETCHING_TABLE"));
    public static final Material GRINDSTONE = new Material(new BlockMaterial(BlockTypes.GRINDSTONE.get(), "GRINDSTONE"));
    public static final Material LECTERN = new Material(new BlockMaterial(BlockTypes.LECTERN.get(), "LECTERN"));
    public static final Material SMITHING_TABLE = new Material(new BlockMaterial(BlockTypes.SMITHING_TABLE.get(), "SMITHING_TABLE"));
    public static final Material STONECUTTER = new Material(new BlockMaterial(BlockTypes.STONECUTTER.get(), "STONECUTTER"));
    public static final Material BELL = new Material(new BlockMaterial(BlockTypes.BELL.get(), "BELL"));
    public static final Material LANTERN = new Material(new BlockMaterial(BlockTypes.LANTERN.get(), "LANTERN"));
    public static final Material SOUL_LANTERN = new Material(new BlockMaterial(BlockTypes.SOUL_LANTERN.get(), "SOUL_LANTERN"));
    public static final Material SWEET_BERRIES = new Material(new ItemMaterial(ItemTypes.SWEET_BERRIES.get(), "SWEET_BERRIES"));
    public static final Material CAMPFIRE = new Material(new BlockMaterial(BlockTypes.CAMPFIRE.get(), "CAMPFIRE"));
    public static final Material SOUL_CAMPFIRE = new Material(new BlockMaterial(BlockTypes.SOUL_CAMPFIRE.get(), "SOUL_CAMPFIRE"));
    public static final Material SHROOMLIGHT = new Material(new BlockMaterial(BlockTypes.SHROOMLIGHT.get(), "SHROOMLIGHT"));
    public static final Material HONEYCOMB = new Material(new ItemMaterial(ItemTypes.HONEYCOMB.get(), "HONEYCOMB"));
    public static final Material BEE_NEST = new Material(new BlockMaterial(BlockTypes.BEE_NEST.get(), "BEE_NEST"));
    public static final Material BEEHIVE = new Material(new BlockMaterial(BlockTypes.BEEHIVE.get(), "BEEHIVE"));
    public static final Material HONEY_BOTTLE = new Material(new ItemMaterial(ItemTypes.HONEY_BOTTLE.get(), "HONEY_BOTTLE"));
    public static final Material HONEY_BLOCK = new Material(new BlockMaterial(BlockTypes.HONEY_BLOCK.get(), "HONEY_BLOCK"));
    public static final Material HONEYCOMB_BLOCK = new Material(new BlockMaterial(BlockTypes.HONEYCOMB_BLOCK.get(), "HONEYCOMB_BLOCK"));
    public static final Material LODESTONE = new Material(new BlockMaterial(BlockTypes.LODESTONE.get(), "LODESTONE"));
    public static final Material NETHERITE_BLOCK = new Material(new BlockMaterial(BlockTypes.NETHERITE_BLOCK.get(), "NETHERITE_BLOCK"));
    public static final Material ANCIENT_DEBRIS = new Material(new BlockMaterial(BlockTypes.ANCIENT_DEBRIS.get(), "ANCIENT_DEBRIS"));
    public static final Material TARGET = new Material(new BlockMaterial(BlockTypes.TARGET.get(), "TARGET"));
    public static final Material CRYING_OBSIDIAN = new Material(new BlockMaterial(BlockTypes.CRYING_OBSIDIAN.get(), "CRYING_OBSIDIAN"));
    public static final Material BLACKSTONE = new Material(new BlockMaterial(BlockTypes.BLACKSTONE.get(), "BLACKSTONE"));
    public static final Material BLACKSTONE_SLAB = new Material(new BlockMaterial(BlockTypes.BLACKSTONE_SLAB.get(), "BLACKSTONE_SLAB"));
    public static final Material BLACKSTONE_STAIRS = new Material(new BlockMaterial(BlockTypes.BLACKSTONE_STAIRS.get(), "BLACKSTONE_STAIRS"));
    public static final Material GILDED_BLACKSTONE = new Material(new BlockMaterial(BlockTypes.GILDED_BLACKSTONE.get(), "GILDED_BLACKSTONE"));
    public static final Material POLISHED_BLACKSTONE = new Material(new BlockMaterial(BlockTypes.POLISHED_BLACKSTONE.get(), "POLISHED_BLACKSTONE"));
    public static final Material POLISHED_BLACKSTONE_SLAB = new Material(new BlockMaterial(BlockTypes.POLISHED_BLACKSTONE_SLAB.get(), "POLISHED_BLACKSTONE_SLAB"));
    public static final Material POLISHED_BLACKSTONE_STAIRS = new Material(new BlockMaterial(BlockTypes.POLISHED_BLACKSTONE_STAIRS.get(), "POLISHED_BLACKSTONE_STAIRS"));
    public static final Material CHISELED_POLISHED_BLACKSTONE = new Material(new BlockMaterial(BlockTypes.CHISELED_POLISHED_BLACKSTONE.get(), "CHISELED_POLISHED_BLACKSTONE"));
    public static final Material POLISHED_BLACKSTONE_BRICKS = new Material(new BlockMaterial(BlockTypes.POLISHED_BLACKSTONE_BRICKS.get(), "POLISHED_BLACKSTONE_BRICKS"));
    public static final Material POLISHED_BLACKSTONE_BRICK_SLAB = new Material(new BlockMaterial(BlockTypes.POLISHED_BLACKSTONE_BRICK_SLAB.get(), "POLISHED_BLACKSTONE_BRICK_SLAB"));
    public static final Material POLISHED_BLACKSTONE_BRICK_STAIRS = new Material(new BlockMaterial(BlockTypes.POLISHED_BLACKSTONE_BRICK_STAIRS.get(), "POLISHED_BLACKSTONE_BRICK_STAIRS"));
    public static final Material CRACKED_POLISHED_BLACKSTONE_BRICKS = new Material(new BlockMaterial(BlockTypes.CRACKED_POLISHED_BLACKSTONE_BRICKS.get(), "CRACKED_POLISHED_BLACKSTONE_BRICKS"));
    public static final Material RESPAWN_ANCHOR = new Material(new BlockMaterial(BlockTypes.RESPAWN_ANCHOR.get(), "RESPAWN_ANCHOR"));
    public static final Material WATER = new Material(new BlockMaterial(BlockTypes.WATER.get(), "WATER"));
    public static final Material LAVA = new Material(new BlockMaterial(BlockTypes.LAVA.get(), "LAVA"));
    public static final Material TALL_SEAGRASS = new Material(new BlockMaterial(BlockTypes.TALL_SEAGRASS.get(), "TALL_SEAGRASS"));
    public static final Material PISTON_HEAD = new Material(new BlockMaterial(BlockTypes.PISTON_HEAD.get(), "PISTON_HEAD"));
    public static final Material MOVING_PISTON = new Material(new BlockMaterial(BlockTypes.MOVING_PISTON.get(), "MOVING_PISTON"));
    public static final Material WALL_TORCH = new Material(new BlockMaterial(BlockTypes.WALL_TORCH.get(), "WALL_TORCH"));
    public static final Material FIRE = new Material(new BlockMaterial(BlockTypes.FIRE.get(), "FIRE"));
    public static final Material SOUL_FIRE = new Material(new BlockMaterial(BlockTypes.SOUL_FIRE.get(), "SOUL_FIRE"));
    public static final Material REDSTONE_WIRE = new Material(new BlockMaterial(BlockTypes.REDSTONE_WIRE.get(), "REDSTONE_WIRE"));
    public static final Material OAK_WALL_SIGN = new Material(new BlockMaterial(BlockTypes.OAK_WALL_SIGN.get(), "OAK_WALL_SIGN"));
    public static final Material SPRUCE_WALL_SIGN = new Material(new BlockMaterial(BlockTypes.SPRUCE_WALL_SIGN.get(), "SPRUCE_WALL_SIGN"));
    public static final Material BIRCH_WALL_SIGN = new Material(new BlockMaterial(BlockTypes.BIRCH_WALL_SIGN.get(), "BIRCH_WALL_SIGN"));
    public static final Material ACACIA_WALL_SIGN = new Material(new BlockMaterial(BlockTypes.ACACIA_WALL_SIGN.get(), "ACACIA_WALL_SIGN"));
    public static final Material JUNGLE_WALL_SIGN = new Material(new BlockMaterial(BlockTypes.JUNGLE_WALL_SIGN.get(), "JUNGLE_WALL_SIGN"));
    public static final Material DARK_OAK_WALL_SIGN = new Material(new BlockMaterial(BlockTypes.DARK_OAK_WALL_SIGN.get(), "DARK_OAK_WALL_SIGN"));
    public static final Material REDSTONE_WALL_TORCH = new Material(new BlockMaterial(BlockTypes.REDSTONE_WALL_TORCH.get(), "REDSTONE_WALL_TORCH"));
    public static final Material SOUL_WALL_TORCH = new Material(new BlockMaterial(BlockTypes.SOUL_WALL_TORCH.get(), "SOUL_WALL_TORCH"));
    public static final Material NETHER_PORTAL = new Material(new BlockMaterial(BlockTypes.NETHER_PORTAL.get(), "NETHER_PORTAL"));
    public static final Material ATTACHED_PUMPKIN_STEM = new Material(new BlockMaterial(BlockTypes.ATTACHED_PUMPKIN_STEM.get(), "ATTACHED_PUMPKIN_STEM"));
    public static final Material ATTACHED_MELON_STEM = new Material(new BlockMaterial(BlockTypes.ATTACHED_MELON_STEM.get(), "ATTACHED_MELON_STEM"));
    public static final Material PUMPKIN_STEM = new Material(new BlockMaterial(BlockTypes.PUMPKIN_STEM.get(), "PUMPKIN_STEM"));
    public static final Material MELON_STEM = new Material(new BlockMaterial(BlockTypes.MELON_STEM.get(), "MELON_STEM"));
    public static final Material END_PORTAL = new Material(new BlockMaterial(BlockTypes.END_PORTAL.get(), "END_PORTAL"));
    public static final Material COCOA = new Material(new BlockMaterial(BlockTypes.COCOA.get(), "COCOA"));
    public static final Material TRIPWIRE = new Material(new BlockMaterial(BlockTypes.TRIPWIRE.get(), "TRIPWIRE"));
    public static final Material POTTED_OAK_SAPLING = new Material(new BlockMaterial(BlockTypes.POTTED_OAK_SAPLING.get(), "POTTED_OAK_SAPLING"));
    public static final Material POTTED_SPRUCE_SAPLING = new Material(new BlockMaterial(BlockTypes.POTTED_SPRUCE_SAPLING.get(), "POTTED_SPRUCE_SAPLING"));
    public static final Material POTTED_BIRCH_SAPLING = new Material(new BlockMaterial(BlockTypes.POTTED_BIRCH_SAPLING.get(), "POTTED_BIRCH_SAPLING"));
    public static final Material POTTED_JUNGLE_SAPLING = new Material(new BlockMaterial(BlockTypes.POTTED_JUNGLE_SAPLING.get(), "POTTED_JUNGLE_SAPLING"));
    public static final Material POTTED_ACACIA_SAPLING = new Material(new BlockMaterial(BlockTypes.POTTED_ACACIA_SAPLING.get(), "POTTED_ACACIA_SAPLING"));
    public static final Material POTTED_DARK_OAK_SAPLING = new Material(new BlockMaterial(BlockTypes.POTTED_DARK_OAK_SAPLING.get(), "POTTED_DARK_OAK_SAPLING"));
    public static final Material POTTED_FERN = new Material(new BlockMaterial(BlockTypes.POTTED_FERN.get(), "POTTED_FERN"));
    public static final Material POTTED_DANDELION = new Material(new BlockMaterial(BlockTypes.POTTED_DANDELION.get(), "POTTED_DANDELION"));
    public static final Material POTTED_POPPY = new Material(new BlockMaterial(BlockTypes.POTTED_POPPY.get(), "POTTED_POPPY"));
    public static final Material POTTED_BLUE_ORCHID = new Material(new BlockMaterial(BlockTypes.POTTED_BLUE_ORCHID.get(), "POTTED_BLUE_ORCHID"));
    public static final Material POTTED_ALLIUM = new Material(new BlockMaterial(BlockTypes.POTTED_ALLIUM.get(), "POTTED_ALLIUM"));
    public static final Material POTTED_AZURE_BLUET = new Material(new BlockMaterial(BlockTypes.POTTED_AZURE_BLUET.get(), "POTTED_AZURE_BLUET"));
    public static final Material POTTED_RED_TULIP = new Material(new BlockMaterial(BlockTypes.POTTED_RED_TULIP.get(), "POTTED_RED_TULIP"));
    public static final Material POTTED_ORANGE_TULIP = new Material(new BlockMaterial(BlockTypes.POTTED_ORANGE_TULIP.get(), "POTTED_ORANGE_TULIP"));
    public static final Material POTTED_WHITE_TULIP = new Material(new BlockMaterial(BlockTypes.POTTED_WHITE_TULIP.get(), "POTTED_WHITE_TULIP"));
    public static final Material POTTED_PINK_TULIP = new Material(new BlockMaterial(BlockTypes.POTTED_PINK_TULIP.get(), "POTTED_PINK_TULIP"));
    public static final Material POTTED_OXEYE_DAISY = new Material(new BlockMaterial(BlockTypes.POTTED_OXEYE_DAISY.get(), "POTTED_OXEYE_DAISY"));
    public static final Material POTTED_CORNFLOWER = new Material(new BlockMaterial(BlockTypes.POTTED_CORNFLOWER.get(), "POTTED_CORNFLOWER"));
    public static final Material POTTED_LILY_OF_THE_VALLEY = new Material(new BlockMaterial(BlockTypes.POTTED_LILY_OF_THE_VALLEY.get(), "POTTED_LILY_OF_THE_VALLEY"));
    public static final Material POTTED_WITHER_ROSE = new Material(new BlockMaterial(BlockTypes.POTTED_WITHER_ROSE.get(), "POTTED_WITHER_ROSE"));
    public static final Material POTTED_RED_MUSHROOM = new Material(new BlockMaterial(BlockTypes.POTTED_RED_MUSHROOM.get(), "POTTED_RED_MUSHROOM"));
    public static final Material POTTED_BROWN_MUSHROOM = new Material(new BlockMaterial(BlockTypes.POTTED_BROWN_MUSHROOM.get(), "POTTED_BROWN_MUSHROOM"));
    public static final Material POTTED_DEAD_BUSH = new Material(new BlockMaterial(BlockTypes.POTTED_DEAD_BUSH.get(), "POTTED_DEAD_BUSH"));
    public static final Material POTTED_CACTUS = new Material(new BlockMaterial(BlockTypes.POTTED_CACTUS.get(), "POTTED_CACTUS"));
    public static final Material CARROTS = new Material(new BlockMaterial(BlockTypes.CARROTS.get(), "CARROTS"));
    public static final Material POTATOES = new Material(new BlockMaterial(BlockTypes.POTATOES.get(), "POTATOES"));
    public static final Material SKELETON_WALL_SKULL = new Material(new BlockMaterial(BlockTypes.SKELETON_WALL_SKULL.get(), "SKELETON_WALL_SKULL"));
    public static final Material WITHER_SKELETON_WALL_SKULL = new Material(new BlockMaterial(BlockTypes.WITHER_SKELETON_WALL_SKULL.get(), "WITHER_SKELETON_WALL_SKULL"));
    public static final Material ZOMBIE_WALL_HEAD = new Material(new BlockMaterial(BlockTypes.ZOMBIE_WALL_HEAD.get(), "ZOMBIE_WALL_HEAD"));
    public static final Material PLAYER_WALL_HEAD = new Material(new BlockMaterial(BlockTypes.PLAYER_WALL_HEAD.get(), "PLAYER_WALL_HEAD"));
    public static final Material CREEPER_WALL_HEAD = new Material(new BlockMaterial(BlockTypes.CREEPER_WALL_HEAD.get(), "CREEPER_WALL_HEAD"));
    public static final Material DRAGON_WALL_HEAD = new Material(new BlockMaterial(BlockTypes.DRAGON_WALL_HEAD.get(), "DRAGON_WALL_HEAD"));
    public static final Material WHITE_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.WHITE_WALL_BANNER.get(), "WHITE_WALL_BANNER"));
    public static final Material ORANGE_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.ORANGE_WALL_BANNER.get(), "ORANGE_WALL_BANNER"));
    public static final Material MAGENTA_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.MAGENTA_WALL_BANNER.get(), "MAGENTA_WALL_BANNER"));
    public static final Material LIGHT_BLUE_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.LIGHT_BLUE_WALL_BANNER.get(), "LIGHT_BLUE_WALL_BANNER"));
    public static final Material YELLOW_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.YELLOW_WALL_BANNER.get(), "YELLOW_WALL_BANNER"));
    public static final Material LIME_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.LIME_WALL_BANNER.get(), "LIME_WALL_BANNER"));
    public static final Material PINK_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.PINK_WALL_BANNER.get(), "PINK_WALL_BANNER"));
    public static final Material GRAY_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.GRAY_WALL_BANNER.get(), "GRAY_WALL_BANNER"));
    public static final Material LIGHT_GRAY_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.LIGHT_GRAY_WALL_BANNER.get(), "LIGHT_GRAY_WALL_BANNER"));
    public static final Material CYAN_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.CYAN_WALL_BANNER.get(), "CYAN_WALL_BANNER"));
    public static final Material PURPLE_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.PURPLE_WALL_BANNER.get(), "PURPLE_WALL_BANNER"));
    public static final Material BLUE_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.BLUE_WALL_BANNER.get(), "BLUE_WALL_BANNER"));
    public static final Material BROWN_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.BROWN_WALL_BANNER.get(), "BROWN_WALL_BANNER"));
    public static final Material GREEN_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.GREEN_WALL_BANNER.get(), "GREEN_WALL_BANNER"));
    public static final Material RED_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.RED_WALL_BANNER.get(), "RED_WALL_BANNER"));
    public static final Material BLACK_WALL_BANNER = new Material(new BlockMaterial(BlockTypes.BLACK_WALL_BANNER.get(), "BLACK_WALL_BANNER"));
    public static final Material BEETROOTS = new Material(new BlockMaterial(BlockTypes.BEETROOTS.get(), "BEETROOTS"));
    public static final Material END_GATEWAY = new Material(new BlockMaterial(BlockTypes.END_GATEWAY.get(), "END_GATEWAY"));
    public static final Material FROSTED_ICE = new Material(new BlockMaterial(BlockTypes.FROSTED_ICE.get(), "FROSTED_ICE"));
    public static final Material KELP_PLANT = new Material(new BlockMaterial(BlockTypes.KELP_PLANT.get(), "KELP_PLANT"));
    public static final Material DEAD_TUBE_CORAL_WALL_FAN = new Material(new BlockMaterial(BlockTypes.DEAD_TUBE_CORAL_WALL_FAN.get(), "DEAD_TUBE_CORAL_WALL_FAN"));
    public static final Material DEAD_BRAIN_CORAL_WALL_FAN = new Material(new BlockMaterial(BlockTypes.DEAD_BRAIN_CORAL_WALL_FAN.get(), "DEAD_BRAIN_CORAL_WALL_FAN"));
    public static final Material DEAD_BUBBLE_CORAL_WALL_FAN = new Material(new BlockMaterial(BlockTypes.DEAD_BUBBLE_CORAL_WALL_FAN.get(), "DEAD_BUBBLE_CORAL_WALL_FAN"));
    public static final Material DEAD_FIRE_CORAL_WALL_FAN = new Material(new BlockMaterial(BlockTypes.DEAD_FIRE_CORAL_WALL_FAN.get(), "DEAD_FIRE_CORAL_WALL_FAN"));
    public static final Material DEAD_HORN_CORAL_WALL_FAN = new Material(new BlockMaterial(BlockTypes.DEAD_HORN_CORAL_WALL_FAN.get(), "DEAD_HORN_CORAL_WALL_FAN"));
    public static final Material TUBE_CORAL_WALL_FAN = new Material(new BlockMaterial(BlockTypes.TUBE_CORAL_WALL_FAN.get(), "TUBE_CORAL_WALL_FAN"));
    public static final Material BRAIN_CORAL_WALL_FAN = new Material(new BlockMaterial(BlockTypes.BRAIN_CORAL_WALL_FAN.get(), "BRAIN_CORAL_WALL_FAN"));
    public static final Material BUBBLE_CORAL_WALL_FAN = new Material(new BlockMaterial(BlockTypes.BUBBLE_CORAL_WALL_FAN.get(), "BUBBLE_CORAL_WALL_FAN"));
    public static final Material FIRE_CORAL_WALL_FAN = new Material(new BlockMaterial(BlockTypes.FIRE_CORAL_WALL_FAN.get(), "FIRE_CORAL_WALL_FAN"));
    public static final Material HORN_CORAL_WALL_FAN = new Material(new BlockMaterial(BlockTypes.HORN_CORAL_WALL_FAN.get(), "HORN_CORAL_WALL_FAN"));
    public static final Material BAMBOO_SAPLING = new Material(new BlockMaterial(BlockTypes.BAMBOO_SAPLING.get(), "BAMBOO_SAPLING"));
    public static final Material POTTED_BAMBOO = new Material(new BlockMaterial(BlockTypes.POTTED_BAMBOO.get(), "POTTED_BAMBOO"));
    public static final Material VOID_AIR = new Material(new BlockMaterial(BlockTypes.VOID_AIR.get(), "VOID_AIR"));
    public static final Material CAVE_AIR = new Material(new BlockMaterial(BlockTypes.CAVE_AIR.get(), "CAVE_AIR"));
    public static final Material BUBBLE_COLUMN = new Material(new BlockMaterial(BlockTypes.BUBBLE_COLUMN.get(), "BUBBLE_COLUMN"));
    public static final Material SWEET_BERRY_BUSH = new Material(new BlockMaterial(BlockTypes.SWEET_BERRY_BUSH.get(), "SWEET_BERRY_BUSH"));
    public static final Material WEEPING_VINES_PLANT = new Material(new BlockMaterial(BlockTypes.WEEPING_VINES_PLANT.get(), "WEEPING_VINES_PLANT"));
    public static final Material TWISTING_VINES_PLANT = new Material(new BlockMaterial(BlockTypes.TWISTING_VINES_PLANT.get(), "TWISTING_VINES_PLANT"));
    public static final Material CRIMSON_WALL_SIGN = new Material(new BlockMaterial(BlockTypes.CRIMSON_WALL_SIGN.get(), "CRIMSON_WALL_SIGN"));
    public static final Material WARPED_WALL_SIGN = new Material(new BlockMaterial(BlockTypes.WARPED_WALL_SIGN.get(), "WARPED_WALL_SIGN"));
    public static final Material POTTED_CRIMSON_FUNGUS = new Material(new BlockMaterial(BlockTypes.POTTED_CRIMSON_FUNGUS.get(), "POTTED_CRIMSON_FUNGUS"));
    public static final Material POTTED_WARPED_FUNGUS = new Material(new BlockMaterial(BlockTypes.POTTED_WARPED_FUNGUS.get(), "POTTED_WARPED_FUNGUS"));
    public static final Material POTTED_CRIMSON_ROOTS = new Material(new BlockMaterial(BlockTypes.POTTED_CRIMSON_ROOTS.get(), "POTTED_CRIMSON_ROOTS"));
    public static final Material POTTED_WARPED_ROOTS = new Material(new BlockMaterial(BlockTypes.POTTED_WARPED_ROOTS.get(), "POTTED_WARPED_ROOTS"));
    public static final Material UNKNOWN = new Material(new UnknownMaterial());

    private final BongeMaterial material;

    public Material(BongeMaterial material) {
        this.material = material;
    }

    public BongeMaterial getWrapper() {
        return this.material;
    }

    public String name() {
        return this.material.name();
    }

    public int getMaxStackSize() {
        return this.material.getMaxStackSize();
    }

    public short getMaxDurability() {
        return this.material.getMaxDurability();
    }

    @Deprecated
    public Class<? extends MaterialData> getData() {
        return this.material.getData();
    }

    @Deprecated
    public MaterialData getNewData(final byte raw) {
        return this.material.getNewData(raw);
    }

    public boolean isBlock() {
        return this.material.isBlock();
    }

    public boolean isEdible() {
        return this.material.isEdible();
    }

    public boolean isRecord() {
        return this.material.isRecord();
    }

    public boolean isSolid() {
        return this.material.isSolid();
    }

    @Deprecated
    public boolean isTransparent() {
        return this.material.isTransparent();
    }

    public boolean isFlammable() {
        return this.material.isFlammable();
    }

    public boolean isBurnable() {
        return this.material.isBurnable();
    }

    public boolean isFuel() {
        return this.material.isFuel();
    }

    public boolean isOccluding() {
        return this.material.isOccluding();
    }

    public boolean hasGravity() {
        return this.material.hasGravity();
    }

    public boolean isItem() {
        return this.material.isItem();
    }

    public boolean isInteractable() {
        return this.material.isInteractable();
    }

    public float getHardness() {
        return this.material.getHardness();
    }

    public float getBlastResistance() {
        return this.material.getBlastResistance();
    }

    @Deprecated
    public int getId() {
        return -1;
    }

    @Deprecated
    public boolean isLegacy() {
        return false;
    }

    public BlockData createBlockData() {
        return this.material.createBlockData();
    }

    public BlockData createBlockData(Consumer<BlockData> consumer) {
        return this.material.createBlockData(consumer);
    }

    public BlockData createBlockData(String data) throws IllegalArgumentException {
        return this.material.createBlockData(data);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Material)) {
            return false;
        }
        Material material = (Material) obj;
        return material.name().equalsIgnoreCase(this.name());
    }

    @Override
    public String toString() {
        return this.name();
    }

    public static @Nullable Material getMaterial(final String name) {
        return getMaterial(name, false);
    }

    public static @Nullable Material getMaterial(String name, boolean legacyName) {
        return valueOf(name.toUpperCase());
    }

    public static @Nullable Material matchMaterial(String name) {
        return matchMaterial(name, false);
    }

    public static @Nullable Material matchMaterial(String name, boolean legacyName) {
        return valueOf(m -> {
            NamespacedKey key = m.getKey();
            if (key.toString().equalsIgnoreCase(name)) {
                return true;
            }
            return key.getNamespace().equalsIgnoreCase(name);
        }).orElse(null);
    }

    public static @NotNull Material valueOf(BongeMaterial material) {
        return valueOf(m -> m.material.equals(material))
                .orElseThrow(() -> new IllegalStateException("Unknown Bonge Material."));

    }

    public static @NotNull Material valueOf(ItemMaterial material) {
        return valueOf(m -> m.material.equals(material))
                .orElseThrow(() -> new IllegalStateException("Unknown Bonge Material."));
    }

    public static @NotNull Material valueOf(ItemType type) {
        return valueOf(m -> {
            if (!m.material.isItem()) {
                return false;
            }
            Optional<BongeMaterial.Item> opItem = m.material.toItem();
            return opItem.map(item -> item.getSpongeItemType().equals(type)).orElse(false);
        }).orElseThrow(() -> new IllegalStateException(
                "Sponge itemtype of " +
                        PlainTextComponentSerializer.plainText().serializeOr(type.asComponent(), "Unknown") +
                        " cannot be converted to Bukkit, is it registered? The following ItemTypes are: " +
                        ArrayUtils.toString(",", Material::name,
                                Stream.of(values())
                                        .filter(Material::isItem)
                                        .collect(Collectors.toList()))));
    }

    public static @NotNull Material valueOf(BlockType type) {
        return valueOf(m -> {
            if (!m.material.isBlock()) {
                return false;
            }
            Optional<BongeMaterial.Block> opBlock = m.material.toBlock();
            return opBlock.map(block -> block.getSpongeBlockType().equals(type)).orElse(false);
        }).orElseThrow(() -> new IllegalStateException("Sponge blocktype of " + PlainTextComponentSerializer.plainText().serializeOr(type.asComponent(), "Unknown") + " cannot be converted to Bukkit, is it registered?"));
    }

    public static Optional<Material> valueOf(Predicate<Material> material) {
        return Stream.of(values()).filter(material).findAny();
    }

    public static @Nullable Material valueOf(String name) {
        return valueOf(m -> m.name().equals(name)).orElse(null);
    }

    public static Material[] values() {
        Set<BongeMaterial> set = Bonge.getInstance().getMaterials();
        return set.parallelStream().map(Material::new).toArray(Material[]::new);
    }

    @Override
    public @NotNull NamespacedKey getKey() {
        return this.material.getKey();
    }
}
