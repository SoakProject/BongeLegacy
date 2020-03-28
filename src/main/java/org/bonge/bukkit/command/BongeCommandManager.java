package org.bonge.bukkit.command;

import org.bonge.bukkit.world.BongeLocation;
import org.bonge.convert.InterfaceConvert;
import org.bonge.launch.BongeLaunch;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandMapping;
import org.spongepowered.api.command.CommandResult;

import java.util.*;
import java.util.stream.Collectors;

public class BongeCommandManager implements CommandMap {

    private Set<CommandState> commands = new HashSet<>();

    public Set<PluginCommand> getCommands(Plugin plugin){
        Set<PluginCommand> commands = new HashSet<>();
        this.commands.stream().filter(c -> c.getCmd() instanceof PluginCommand).filter(c -> ((PluginCommand)c.getCmd()).getPlugin().equals(plugin)).forEach(c -> commands.add((PluginCommand) c.getCmd()));
        return commands;
    }

    public void registerWithSponge(){
        this.commands.stream().forEach(s -> {
            registerWithSponge(s);
        });
    }

    public void registerWithSponge(CommandState state){
            @NotNull List<String> list = new ArrayList<>(state.getCmd().getAliases());
            for(int A = list.size() - 1; A >= 0; A--){
                String alis = list.get(A);
                if (Sponge.getCommandManager().get(alis).isPresent()){
                    list.remove(A);
                }
            }
            list.add(0, state.getLabel());
            list.add(1, state.getPluginTag() + ":" + state.getLabel());
            String[] commands = new String[list.size()];
            for(int A = 0; A < list.size(); A++){
                commands[A] = list.get(A);
            }
            Sponge.getCommandManager().register(BongeLaunch.getInstance(), new SpongeCommandWrapping(state), commands);
        }

    public void register(PluginCommand command){
        this.commands.add(new CommandState(command));
    }

    @Override
    public void registerAll(@NotNull String fallbackPrefix, @NotNull List<Command> commands) {
        commands.stream().forEach(c -> {
            this.register(fallbackPrefix, c);
        });
    }

    @Override
    public boolean register(@NotNull String label, @NotNull String fallbackPrefix, @NotNull Command command) {
        boolean check = this.commands.add(new CommandState(label, fallbackPrefix, command));
        return check;
    }

    @Override
    public boolean register(@NotNull String fallbackPrefix, @NotNull Command command) {
        return this.commands.add(new CommandState(command.getLabel(), fallbackPrefix, command));
    }

    @Override
    public boolean dispatch(@NotNull CommandSender sender, @NotNull String cmdLine) throws CommandException {
        CommandResult result = Sponge.getCommandManager().process(InterfaceConvert.getSource(sender), cmdLine);
        return result.equals(CommandResult.empty()) ? false : true;
    }

    @Override
    public void clearCommands() {
        Sponge.getPluginManager().getPlugins().stream().forEach(p -> {
            Sponge.getCommandManager().getOwnedBy(p).stream().forEach(m -> {
                Sponge.getCommandManager().removeMapping(m);
            });
        });
        this.commands.clear();
    }

    @Override
    public @Nullable Command getCommand(@NotNull String name) {
        Optional<CommandState> opcmd = this.commands.stream().filter(c -> c.getLabel().equalsIgnoreCase(name)).findAny();
        if(opcmd.isPresent()){
            return opcmd.get().getCmd();
        }
        Optional<CommandState> opAlis = this.commands.stream().filter(c -> c.getCmd().getAliases().stream().anyMatch(s -> s.equalsIgnoreCase(name))).findAny();
        if(opAlis.isPresent()){
            return opAlis.get().getCmd();
        }
        return null;
    }

    @Override
    public @Nullable List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine) throws IllegalArgumentException {
        return Sponge.getCommandManager().getSuggestions(InterfaceConvert.getSource(sender), cmdLine,  ((sender instanceof Player) ? new BongeLocation(((Player)sender).getLocation()).getSpongeLocation() : null));
    }

    @Override
    public @Nullable List<String> tabComplete(@NotNull CommandSender sender, @NotNull String cmdLine, @Nullable Location location) throws IllegalArgumentException {
        return Sponge.getCommandManager().getSuggestions(InterfaceConvert.getSource(sender), cmdLine,  new BongeLocation(location).getSpongeLocation());
    }
}
