package org.bukkit.material;

import org.bukkit.Material;

@Deprecated
public class Dispenser extends FurnaceAndDispenser{

    public Dispenser(){
        this(Material.DISPENSER);
    }

    public Dispenser(Material type) {
        super(type);
    }

    public Dispenser(Material type, byte data) {
        super(type, data);
    }
}
