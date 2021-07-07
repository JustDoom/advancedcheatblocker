package com.advancedcheatblocker.acb.pluginanticheat.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GroundChecker {
    public static boolean isOnGroundMath(double y) {
        return y % (1D / 64D) == 0;
    }
    public static boolean isOnGroundAround(Location loc) {
        int radius = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {
                    Block block = loc.getWorld().getBlockAt(loc.clone().add(x, y, z));
                    if (block.getType().isSolid()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
