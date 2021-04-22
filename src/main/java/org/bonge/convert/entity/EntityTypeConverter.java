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
            case DROPPED_ITEM: return EntityTypes.ITEM.get();
            case EXPERIENCE_ORB: return EntityTypes.EXPERIENCE_ORB.get();
            case AREA_EFFECT_CLOUD: return EntityTypes.AREA_EFFECT_CLOUD.get();
            case ELDER_GUARDIAN: return EntityTypes.ELDER_GUARDIAN.get();
            case WITHER_SKELETON: return EntityTypes.WITHER_SKELETON.get();
            case STRAY: return EntityTypes.STRAY.get();
            case EGG: return EntityTypes.EGG.get();
            case LEASH_HITCH: return EntityTypes.LEASH_KNOT.get();
            case PAINTING: return EntityTypes.PAINTING.get();
            case ARROW: return EntityTypes.ARROW.get();
            case SNOWBALL: return EntityTypes.SNOWBALL.get();
            case FIREBALL: return EntityTypes.FIREBALL.get();
            case SMALL_FIREBALL: return EntityTypes.SMALL_FIREBALL.get();
            case ENDER_PEARL: return EntityTypes.ENDER_PEARL.get();
            case ENDER_SIGNAL: throw new NotImplementedException("Unknown EntityType of Ender_Signal"); //1.13?
            case SPLASH_POTION: return EntityTypes.POTION.get();
            case THROWN_EXP_BOTTLE: return EntityTypes.EXPERIENCE_BOTTLE.get();
            case ITEM_FRAME: return EntityTypes.ITEM_FRAME.get();
            case WITHER_SKULL: return EntityTypes.WITHER_SKULL.get();
            case PRIMED_TNT: return EntityTypes.TNT.get();
            case FALLING_BLOCK: return EntityTypes.FALLING_BLOCK.get();
            case FIREWORK: return EntityTypes.FIREWORK_ROCKET.get();
            case HUSK: return EntityTypes.HUSK.get();
            case SPECTRAL_ARROW: return EntityTypes.SPECTRAL_ARROW.get();
            case SHULKER_BULLET: return EntityTypes.SHULKER_BULLET.get();
            case DRAGON_FIREBALL: return EntityTypes.DRAGON_FIREBALL.get();
            case ZOMBIE_VILLAGER: return EntityTypes.ZOMBIE_VILLAGER.get();
            case SKELETON_HORSE: return EntityTypes.SKELETON_HORSE.get();
            case ZOMBIE_HORSE: return EntityTypes.ZOMBIE_HORSE.get();
            case ARMOR_STAND: return EntityTypes.ARMOR_STAND.get();
            case DONKEY: return EntityTypes.DONKEY.get();
            case MULE: return EntityTypes.MULE.get();
            case EVOKER_FANGS: return EntityTypes.EVOKER_FANGS.get();
            case EVOKER: return EntityTypes.EVOKER.get();
            case VEX: return EntityTypes.VEX.get();
            case VINDICATOR: return EntityTypes.VINDICATOR.get();
            case ILLUSIONER: return EntityTypes.ILLUSIONER.get();
            case MINECART_COMMAND: return EntityTypes.COMMAND_BLOCK_MINECART.get();
            case BOAT: return EntityTypes.BOAT.get();
            case MINECART: return EntityTypes.MINECART.get();
            case MINECART_CHEST: return EntityTypes.CHEST_MINECART.get();
            case MINECART_FURNACE: return EntityTypes.FURNACE_MINECART.get();
            case MINECART_TNT: return EntityTypes.TNT_MINECART.get();
            case MINECART_HOPPER: return EntityTypes.HOPPER_MINECART.get();
            case MINECART_MOB_SPAWNER: return EntityTypes.SPAWNER_MINECART.get();
            case CREEPER: return EntityTypes.CREEPER.get();
            case SKELETON: return EntityTypes.SKELETON.get();
            case SPIDER: return EntityTypes.SPIDER.get();
            case GIANT: return EntityTypes.GIANT.get();
            case ZOMBIE: return EntityTypes.ZOMBIE.get();
            case SLIME: return EntityTypes.SLIME.get();
            case GHAST: return EntityTypes.GHAST.get();
            //case PIG_ZOMBIE: return EntityTypes.ZOMBIE_PIGMAN.get();
            case ENDERMAN: return EntityTypes.ENDERMAN.get();
            case CAVE_SPIDER: return EntityTypes.CAVE_SPIDER.get();
            case SILVERFISH: return EntityTypes.SILVERFISH.get();
            case BLAZE: return EntityTypes.BLAZE.get();
            case MAGMA_CUBE: return EntityTypes.MAGMA_CUBE.get();
            case ENDER_DRAGON: return EntityTypes.ENDER_DRAGON.get();
            case WITHER: return EntityTypes.WITHER.get();
            case BAT: return EntityTypes.BAT.get();
            case WITCH: return EntityTypes.WITCH.get();
            case ENDERMITE: return EntityTypes.ENDERMITE.get();
            case GUARDIAN: return EntityTypes.GUARDIAN.get();
            case SHULKER: return EntityTypes.SHULKER.get();
            case PIG: return EntityTypes.PIG.get();
            case SHEEP:
            case COW:
            case CHICKEN:
            case SQUID:
            case PLAYER:
            case LIGHTNING:
            case FISHING_HOOK:
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
                throw new IOException("Unknown EntityType of " + value.name());
        }
    }

    @Override
    public EntityType to(org.spongepowered.api.entity.EntityType value) throws IOException {
        throw new NotImplementedException("Not got to yet");
    }
}
