package org.bonge.bukkit.r1_14.block.data.dedicated;

import org.bonge.bukkit.r1_14.block.data.BongeRotatable;
import org.bonge.bukkit.r1_14.block.data.BongeWaterLogged;
import org.bonge.bukkit.r1_14.block.data.IBongeBlockData;
import org.bukkit.block.data.type.Sign;

public interface BongeSign extends IBongeBlockData, Sign, BongeRotatable, BongeWaterLogged {
}
