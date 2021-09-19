package org.bonge.convert.entity;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bonge.bukkit.r1_16.entity.BongeAbstractEntity;
import org.bonge.bukkit.r1_16.entity.ForgeEntity;
import org.bonge.bukkit.r1_16.entity.living.animal.*;
import org.bonge.bukkit.r1_16.entity.living.animal.horse.BongeHorse;
import org.bonge.bukkit.r1_16.entity.living.animal.horse.Llama.BongeTraderLlama;
import org.bonge.bukkit.r1_16.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_16.entity.living.monster.BongeCreeper;
import org.bonge.bukkit.r1_16.entity.living.monster.BongeEnderman;
import org.bonge.bukkit.r1_16.entity.living.monster.skeleton.BongeTypicalSkeleton;
import org.bonge.bukkit.r1_16.entity.living.monster.spider.BongeCaveSpider;
import org.bonge.bukkit.r1_16.entity.living.monster.spider.BongeTypicalSpider;
import org.bonge.bukkit.r1_16.entity.living.monster.zombie.BongeTypicalZombie;
import org.bonge.bukkit.r1_16.entity.living.other.armorstand.BongeArmorStandEntity;
import org.bonge.bukkit.r1_16.entity.living.other.bat.BongeBat;
import org.bonge.bukkit.r1_16.entity.living.other.squid.BongeSquid;
import org.bonge.bukkit.r1_16.entity.living.other.trader.BongeVillager;
import org.bonge.bukkit.r1_16.entity.other.item.BongeItem;
import org.bonge.bukkit.r1_16.entity.vehicle.minecart.BongeMinecartChest;
import org.bonge.convert.Converter;
import org.bukkit.entity.Entity;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.entity.living.animal.Cat;
import org.spongepowered.api.entity.living.animal.Fox;
import org.spongepowered.api.entity.living.animal.Wolf;
import org.spongepowered.api.entity.living.animal.cow.Cow;
import org.spongepowered.api.entity.living.animal.horse.Horse;
import org.spongepowered.api.entity.living.animal.horse.llama.TraderLlama;
import org.spongepowered.api.entity.living.aquatic.Squid;
import org.spongepowered.api.entity.living.monster.skeleton.Skeleton;
import org.spongepowered.api.entity.living.monster.spider.CaveSpider;
import org.spongepowered.api.entity.living.monster.spider.Spider;
import org.spongepowered.api.entity.living.monster.zombie.Zombie;
import org.spongepowered.api.entity.living.trader.Villager;
import org.spongepowered.api.entity.vehicle.minecart.carrier.ChestMinecart;
import org.spongepowered.api.registry.RegistryTypes;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EntityConverter implements Converter<Entity, org.spongepowered.api.entity.Entity> {

    static Set<String> FAILED_TO_CONVERT = new HashSet<>();

    @Override
    public Class<org.spongepowered.api.entity.Entity> getSpongeClass() {
        return org.spongepowered.api.entity.Entity.class;
    }

    @Override
    public Class<Entity> getBukkitClass() {
        return Entity.class;
    }

    @Override
    public org.spongepowered.api.entity.Entity from(Entity value) throws IOException {
        if (value instanceof BongeAbstractEntity) {
            return ((BongeAbstractEntity<?>) value).getSpongeValue();
        }
        throw new IOException("Entity is not BongeEntity");
    }

    @Override
    public Entity to(org.spongepowered.api.entity.Entity entity) throws IOException {
        if (entity instanceof org.spongepowered.api.entity.living.player.Player) {
            return BongePlayer.getPlayer((org.spongepowered.api.entity.living.player.Player) entity);
        }
        if (entity instanceof org.spongepowered.api.entity.living.ArmorStand) {
            return new BongeArmorStandEntity((org.spongepowered.api.entity.living.ArmorStand) entity);
        }
        if (entity instanceof org.spongepowered.api.entity.living.animal.Sheep) {
            return new BongeSheep((org.spongepowered.api.entity.living.animal.Sheep) entity);
        }
        if (entity instanceof org.spongepowered.api.entity.living.animal.Pig) {
            return new BongePig((org.spongepowered.api.entity.living.animal.Pig) entity);
        }
        if (entity instanceof Cow) {
            return new BongeCow((Cow) entity);
        }
        if (entity instanceof org.spongepowered.api.entity.living.animal.Chicken) {
            return new BongeChicken((org.spongepowered.api.entity.living.animal.Chicken) entity);
        }
        if (entity instanceof CaveSpider) {
            return new BongeCaveSpider((CaveSpider) entity);
        }
        if (entity instanceof Spider) {
            return new BongeTypicalSpider((Spider) entity);
        }
        if (entity instanceof Zombie) {
            return new BongeTypicalZombie((Zombie) entity);
        }
        if (entity instanceof Skeleton) {
            return new BongeTypicalSkeleton((Skeleton) entity);
        }
        if (entity instanceof org.spongepowered.api.entity.living.monster.Creeper) {
            return new BongeCreeper((org.spongepowered.api.entity.living.monster.Creeper) entity);
        }
        if (entity instanceof org.spongepowered.api.entity.living.monster.Enderman) {
            return new BongeEnderman((org.spongepowered.api.entity.living.monster.Enderman) entity);
        }
        if (entity instanceof Squid) {
            return new BongeSquid((Squid) entity);
        }
        if (entity instanceof org.spongepowered.api.entity.living.Bat) {
            return new BongeBat((org.spongepowered.api.entity.living.Bat) entity);
        }
        if (entity instanceof org.spongepowered.api.entity.Item) {
            return new BongeItem((org.spongepowered.api.entity.Item) entity);
        }
        if (entity instanceof org.spongepowered.api.entity.living.animal.horse.Horse) {
            return new BongeHorse((Horse) entity);
        }
        if (entity instanceof org.spongepowered.api.entity.living.animal.horse.llama.TraderLlama) {
            return new BongeTraderLlama((TraderLlama) entity);
        }
        if (entity instanceof Villager) {
            return new BongeVillager((Villager) entity);
        }
        if (entity instanceof Wolf) {
            return new BongeWolf((Wolf) entity);
        }
        if (entity instanceof Cat) {
            return new BongeCat((Cat) entity);
        }
        if(entity instanceof ChestMinecart){
            return new BongeMinecartChest((ChestMinecart) entity);
        }
        if(entity instanceof Fox){
            return new BongeFox((Fox) entity);
        }

        String entityId = entity.type().findKey(RegistryTypes.ENTITY_TYPE).map(ResourceKey::asString).orElse(PlainTextComponentSerializer.plainText().serialize(entity.type().asComponent()));

        if(!FAILED_TO_CONVERT.contains(entityId)){
            System.err.println("Could not convert entity: '" + entityId + "' using forge adapter for this entity.");
            FAILED_TO_CONVERT.add(entityId);
        }
        return new ForgeEntity<>(entity);
    }
}
