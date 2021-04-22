package org.bonge.bukkit.r1_16.entity.living.animal.horse.Llama;

import org.bonge.bukkit.r1_16.entity.living.animal.horse.IHorse;
import org.bonge.util.exception.NotImplementedException;
import org.bukkit.entity.Llama;
import org.bukkit.inventory.LlamaInventory;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.entity.living.animal.horse.llama.LlamaLike;

public interface BongeAbstractLlama<L extends LlamaLike> extends IHorse<L>, Llama {

    @Override
    default @NotNull LlamaInventory getInventory() {
        throw new NotImplementedException("Not looked at yet");
    }

    @NotNull
    @Override
    default Color getColor() {
        throw new NotImplementedException("Not looked at yet");
    }

    @Override
    default void setColor(@NotNull Llama.Color color) {
        throw new NotImplementedException("Not looked at yet");
    }

    @Override
    default int getStrength() {
        throw new NotImplementedException("Not looked at yet");

    }

    @Override
    default void setStrength(int i) {
        throw new NotImplementedException("Not looked at yet");

    }

    @Override
    default boolean isCarryingChest() {
        throw new NotImplementedException("Not looked at yet");
    }

    @Override
    default void setCarryingChest(boolean b) {
        throw new NotImplementedException("Not looked at yet");

    }

}
