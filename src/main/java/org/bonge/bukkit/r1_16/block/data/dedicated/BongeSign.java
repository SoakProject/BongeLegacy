package org.bonge.bukkit.r1_16.block.data.dedicated;

import org.bonge.bukkit.r1_16.block.data.BongeRotatable;
import org.bonge.bukkit.r1_16.block.data.BongeWaterLogged;
import org.bonge.bukkit.r1_16.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Sign;

public interface BongeSign extends IBongeBlockData, Sign, BongeRotatable, BongeWaterLogged {
}
