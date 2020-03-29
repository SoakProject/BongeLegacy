package org.bonge.bukkit.toremove;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.UnsafeValues;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.PluginDescriptionFile;

@Deprecated
public class BongeUnsafeValues implements UnsafeValues {
    @Override
    public Material toLegacy(Material material) {
        return null;
    }

    @Override
    public Material fromLegacy(Material material) {
        return null;
    }

    @Override
    public Material fromLegacy(MaterialData materialData) {
        return null;
    }

    @Override
    public Material fromLegacy(MaterialData materialData, boolean b) {
        return null;
    }

    @Override
    public BlockData fromLegacy(Material material, byte b) {
        return null;
    }

    @Override
    public int getDataVersion() {
        return 0;
    }

    @Override
    public ItemStack modifyItemStack(ItemStack itemStack, String s) {
        return null;
    }

    @Override
    public void checkSupported(PluginDescriptionFile pluginDescriptionFile) {

    }

    @Override
    public byte[] processClass(PluginDescriptionFile pluginDescriptionFile, String s, byte[] bytes) {
        return new byte[0];
    }

    @Override
    public Advancement loadAdvancement(NamespacedKey namespacedKey, String s) {
        return null;
    }

    @Override
    public boolean removeAdvancement(NamespacedKey namespacedKey) {
        return false;
    }
}
