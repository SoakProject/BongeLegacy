package org.bonge.listeners;

import com.flowpowered.math.vector.Vector3d;
import org.bonge.Bonge;
import org.bonge.bukkit.r1_13.block.BongeBlock;
import org.bonge.bukkit.r1_13.block.BongeBlockSnapshot;
import org.bonge.bukkit.r1_13.entity.BongeAbstractEntity;
import org.bonge.bukkit.r1_13.entity.EntityManager;
import org.bonge.bukkit.r1_13.entity.living.human.BongePlayer;
import org.bonge.bukkit.r1_13.inventory.inventory.InventorySnapshot;
import org.bonge.bukkit.r1_13.inventory.inventory.entity.living.human.PlayerInventorySnapshot;
import org.bonge.bukkit.r1_13.server.BongeServer;
import org.bonge.convert.InventoryConvert;
import org.bonge.convert.text.TextConverter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockSnapshot;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.action.InteractEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.entity.*;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.world.World;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PlayerListener {

    @Listener
    public void onDamageEvent(DamageEntityEvent event){
        if(!event.willCauseDeath()){
            return;
        }
        if(!(event.getTargetEntity() instanceof Player)){
            return;
        }
        Player player = (Player)event.getTargetEntity();
        BongePlayer bPlayer = BongePlayer.getPlayer(player);
        try {
            bPlayer.getData().putOrReplace(EntityManager.INVENTORY, new PlayerInventorySnapshot(bPlayer.getInventory(), true));
            bPlayer.getData().putOrReplace(EntityManager.LOCATION, Bonge.getInstance().convert(Location.class, player.getLocation()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Listener
    public void onDeathEvent(DestructEntityEvent.Death event){
        if(!(event.getTargetEntity() instanceof Player)){
            return;
        }
        Player player = (Player)event.getTargetEntity();
        for (Inventory inventory : player.getInventory().slots()){
            inventory.peek().ifPresent(i -> System.out.println(i.getType().getName()));
        }
        BongePlayer bPlayer = BongePlayer.getPlayer(player);
        List<ItemStack> list = new ArrayList<>();
        int exp = 0;
        String deathMessage = TextConverter.CONVERTER.to(event.getMessage());
        if(!event.getKeepInventory()){
            list.addAll(Arrays.asList(bPlayer.getInventory().getContents()));
            exp = bPlayer.getTotalExperience();
        }
        PlayerDeathEvent dEvent = new PlayerDeathEvent(bPlayer, list, exp, deathMessage);
        Bukkit.getPluginManager().callEvent(dEvent);
        event.setMessage(TextConverter.CONVERTER.from(dEvent.getDeathMessage()));
        event.setKeepInventory(dEvent.getKeepInventory());
        EntityManager.KeyHashMap data = bPlayer.getData();
        data.remove(EntityManager.LOCATION);
        data.remove(EntityManager.INVENTORY);
    }

    @Listener
    public void onPlayerRotate(RotateEntityEvent event){
        if(!(event.getTargetEntity() instanceof Player)){
            return;
        }
        BongePlayer player = BongePlayer.getPlayer((Player)event.getTargetEntity());
        try {
            Location from = Bonge.getInstance().convert(Location.class, event.getFromTransform());
            Location to = Bonge.getInstance().convert(Location.class, event.getToTransform());
            PlayerMoveEvent event1 = new PlayerMoveEvent(player, from, to);
            Bukkit.getPluginManager().callEvent(event1);
            event.setToTransform(Bonge.getInstance().convert(event1.getTo(), Transform.class));
            event.setCancelled(event1.isCancelled());
        } catch (IOException ignored) {
        }
    }

    @Listener
    public void onPlayerMove(MoveEntityEvent event){
        if(!(event.getTargetEntity() instanceof Player)){
            return;
        }
        BongePlayer player = BongePlayer.getPlayer((Player)event.getTargetEntity());
        try {
            Location from = Bonge.getInstance().convert(Location.class, event.getFromTransform());
            Location to = Bonge.getInstance().convert(Location.class, event.getToTransform());
            PlayerMoveEvent event1 = new PlayerMoveEvent(player, from, to);
            Bukkit.getPluginManager().callEvent(event1);
            event.setToTransform(Bonge.getInstance().convert(event1.getTo(), Transform.class));
            event.setCancelled(event1.isCancelled());
        } catch (IOException ignored) {
        }
    }

    @Listener
    public void onPlayerInteractWithEntity(InteractEntityEvent event, @Root org.spongepowered.api.entity.living.player.Player player){
        BongePlayer bPlayer = BongePlayer.getPlayer(player);
        try {
        Entity entity = Bonge.getInstance().convert(Entity.class, event.getTargetEntity());
        PlayerInteractEntityEvent piee = new PlayerInteractEntityEvent(bPlayer, entity);
        Bukkit.getPluginManager().callEvent(piee);
        event.setCancelled(piee.isCancelled());
        if(event.isCancelled()){
            return;
        }
        if(!event.getInteractionPoint().isPresent()){
            return;
        }
            PlayerInteractAtEntityEvent piaee = new PlayerInteractAtEntityEvent(bPlayer, entity, Bonge.getInstance().convert(Vector.class, event.getInteractionPoint().get()));
        } catch (IOException e) {

        }
    }

    @Listener
    public void onAirClick(InteractEvent event, @Root org.spongepowered.api.entity.living.player.Player player){
        if(event instanceof InteractBlockEvent){
            return;
        }
        if(event instanceof InteractEntityEvent){
            return;
        }
        Action action = Action.RIGHT_CLICK_AIR;
        org.spongepowered.api.item.inventory.ItemStack stack = player.getItemInHand(HandTypes.OFF_HAND).get();
        if(event instanceof InteractBlockEvent.Primary){
            action = Action.LEFT_CLICK_AIR;
            stack = player.getItemInHand(HandTypes.MAIN_HAND).get();
        }
        org.spongepowered.api.world.Location<World> loc;
        Optional<Vector3d> opVector = event.getInteractionPoint();
        if(opVector.isPresent()){
            loc = player.getWorld().getLocation(opVector.get());
        }else{
            loc = player.getLocation();
        }
        BongeBlock bbs = new BongeBlock(loc);
        try {
            BongePlayer bPlayer = BongePlayer.getPlayer(player);
            ItemStack stack2 = Bonge.getInstance().convert(ItemStack.class, stack);
            PlayerInteractEvent bEvent = new PlayerInteractEvent(bPlayer, action, stack2, bbs, BlockFace.SELF);
            Bukkit.getServer().getPluginManager().callEvent(bEvent);
            if(bEvent.isCancelled()){
                event.setCancelled(true);
            }
        } catch (IOException ignored) {
        }
    }

    @Listener
    public void onBlockClick(InteractBlockEvent event, @Root org.spongepowered.api.entity.living.player.Player player){
        BongePlayer bPlayer = BongePlayer.getPlayer(player);
        Action action = Action.RIGHT_CLICK_BLOCK;
        org.spongepowered.api.item.inventory.ItemStack stack = player.getItemInHand(HandTypes.OFF_HAND).get();
        if(event instanceof InteractBlockEvent.Primary){
            action = Action.LEFT_CLICK_BLOCK;
            stack = player.getItemInHand(HandTypes.MAIN_HAND).get();
        }
        org.spongepowered.api.world.Location<World> loc;
        BlockSnapshot snapshot = event.getTargetBlock();
        if(snapshot.getLocation().isPresent()){
            loc = snapshot.getLocation().get();
        }else{
            loc = player.getWorld().getLocation(snapshot.getPosition());
        }
        BongeBlock bbs = new BongeBlock(loc);
        try {
            BlockFace face = Bonge.getInstance().convert(BlockFace.class, event.getTargetSide());
            ItemStack stack2 = Bonge.getInstance().convert(ItemStack.class, stack);
            PlayerInteractEvent bEvent = new PlayerInteractEvent(bPlayer, action, stack2, bbs, face);
            Bukkit.getServer().getPluginManager().callEvent(bEvent);
            if(bEvent.isCancelled()){
                event.setCancelled(true);
            }
        } catch (IOException ignored) {
        }
    }
}
