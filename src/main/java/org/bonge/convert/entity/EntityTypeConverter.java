package org.bonge.convert.entity;

import org.bonge.convert.Converter;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;

import java.io.IOException;

public class EntityTypeConverter implements Converter<EntityType, org.spongepowered.api.entity.EntityType> {
    @Override
    public Class<org.spongepowered.api.entity.EntityType> getSpongeClass() {
        return org.spongepowered.api.entity.EntityType.class;
    }

    @Override
    public Class<EntityType> getBukkitClass() {
        return EntityType.class;
    }

    @Override
    public org.spongepowered.api.entity.EntityType from(EntityType value) throws IOException {
        switch (value){
            case DROPPED_ITEM: return EntityTypes.ITEM;
            case EXPERIENCE_ORB: return EntityTypes.EXPERIENCE_ORB;
            case AREA_EFFECT_CLOUD: return EntityTypes.AREA_EFFECT_CLOUD;
            case ELDER_GUARDIAN: return EntityTypes.ELDER_GUARDIAN;
            case WITHER_SKELETON: return EntityTypes.WITHER_SKELETON;
            case STRAY: return EntityTypes.STRAY;
            case EGG: return EntityTypes.EGG;
            case LEASH_HITCH: return EntityTypes.LEASH_HITCH;
            case PAINTING: return EntityTypes.PAINTING;
            case ARROW: return EntityTypes.TIPPED_ARROW;
            case SNOWBALL: return EntityTypes.SNOWBALL;
            case FIREBALL: return EntityTypes.FIREBALL;
            case SMALL_FIREBALL: return EntityTypes.SMALL_FIREBALL;
            case ENDER_PEARL: return EntityTypes.ENDER_PEARL;
            case ENDER_SIGNAL: throw new NotImplementedException("Unknown EntityType of Ender_Signal"); //1.13?
            case SPLASH_POTION: return EntityTypes.SPLASH_POTION;
            case THROWN_EXP_BOTTLE: return EntityTypes.THROWN_EXP_BOTTLE;
            case ITEM_FRAME: return EntityTypes.ITEM_FRAME;
            case WITHER_SKULL: return EntityTypes.WITHER_SKULL;
            case PRIMED_TNT: return EntityTypes.PRIMED_TNT;
            case FALLING_BLOCK: return EntityTypes.FALLING_BLOCK;
            case FIREWORK: return EntityTypes.FIREWORK;
            case HUSK: return EntityTypes.HUSK;
            case SPECTRAL_ARROW: return EntityTypes.SPECTRAL_ARROW;
            case SHULKER_BULLET: return EntityTypes.SHULKER_BULLET;
            case DRAGON_FIREBALL: return EntityTypes.DRAGON_FIREBALL;
            case ZOMBIE_VILLAGER: return EntityTypes.ZOMBIE_VILLAGER;
            case SKELETON_HORSE: return EntityTypes.SKELETON_HORSE;
            case ZOMBIE_HORSE: return EntityTypes.ZOMBIE_HORSE;
            case ARMOR_STAND: return EntityTypes.ARMOR_STAND;
            case DONKEY: return EntityTypes.DONKEY;
            case MULE: return EntityTypes.MULE;
            case EVOKER_FANGS: return EntityTypes.EVOCATION_FANGS;
            case EVOKER: return EntityTypes.EVOCATION_ILLAGER;
            case VEX: return EntityTypes.VEX;
            case VINDICATOR: return EntityTypes.VINDICATION_ILLAGER;
            case ILLUSIONER: return EntityTypes.ILLUSION_ILLAGER;
            case MINECART_COMMAND: return EntityTypes.COMMANDBLOCK_MINECART;
            case BOAT: return EntityTypes.BOAT;
            case MINECART: return EntityTypes.RIDEABLE_MINECART;
            case MINECART_CHEST: return EntityTypes.CHESTED_MINECART;
            case MINECART_FURNACE: return EntityTypes.FURNACE_MINECART;
            case MINECART_TNT: return EntityTypes.TNT_MINECART;
            case MINECART_HOPPER: return EntityTypes.HOPPER_MINECART;
            case MINECART_MOB_SPAWNER: return EntityTypes.MOB_SPAWNER_MINECART;
            case CREEPER: return EntityTypes.CREEPER;
            case SKELETON: return EntityTypes.SKELETON;
            case SPIDER: return EntityTypes.SPIDER;
            case GIANT: return EntityTypes.GIANT;
            case ZOMBIE: return EntityTypes.ZOMBIE;
            case SLIME: return EntityTypes.SLIME;
            case GHAST: return EntityTypes.GHAST;
            case PIG_ZOMBIE: return EntityTypes.PIG_ZOMBIE;
            case ENDERMAN: return EntityTypes.ENDERMAN;
            case CAVE_SPIDER: return EntityTypes.CAVE_SPIDER;
            case SILVERFISH: return EntityTypes.SILVERFISH;
            case BLAZE: return EntityTypes.BLAZE;
            case MAGMA_CUBE: return EntityTypes.MAGMA_CUBE;
            case ENDER_DRAGON: return EntityTypes.ENDER_DRAGON;
            case WITHER: return EntityTypes.WITHER;
            case BAT: return EntityTypes.BAT;
            case WITCH: return EntityTypes.WITCH;
            case ENDERMITE: return EntityTypes.ENDERMITE;
            case GUARDIAN: return EntityTypes.GUARDIAN;
            case SHULKER: return EntityTypes.SHULKER;
            case PIG: return EntityTypes.PIG;
            case SHEEP:
            case COW:
            case CHICKEN:
            case SQUID:
            case TIPPED_ARROW:
            case COMPLEX_PART:
            case PLAYER:
            case WEATHER:
            case LIGHTNING:
            case FISHING_HOOK:
            case LINGERING_POTION:
            case DOLPHIN:
            case DROWNED:
            case TROPICAL_FISH:
            case PUFFERFISH:
            case SALMON:
            case COD:
            case TRIDENT:
            case PHANTOM:
            case TURTLE:
            case ENDER_CRYSTAL:
            case VILLAGER:
            case PARROT:
            case LLAMA_SPIT:
            case LLAMA:
            case POLAR_BEAR:
            case RABBIT:
            case HORSE:
            case IRON_GOLEM:
            case OCELOT:
            case SNOWMAN:
            case MUSHROOM_COW:
            case WOLF:
                throw new NotImplementedException("Not got to yet");
            default:
                throw new IllegalStateException("Unknown EntityType of " + value.name());
        }
    }

    @Override
    public EntityType to(org.spongepowered.api.entity.EntityType value) throws IOException {
        throw new NotImplementedException("Not got to yet");
    }
}
