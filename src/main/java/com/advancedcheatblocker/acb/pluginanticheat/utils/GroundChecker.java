package com.advancedcheatblocker.acb.pluginanticheat.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
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

    public static boolean isGroundAround2(Location loc){
        int radius = 3;
        int radiusY = 2;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radiusY; y < radiusY; y++) {
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

    public static boolean isStandingBoat(Player p){
        boolean r = false;
        for(Entity e : p.getWorld().getEntities()){
            double distance = e.getLocation().distance( p.getLocation() );
            if(distance <= 1.5 ){
                r = true;
            }
        }
        return r;
    }
}
