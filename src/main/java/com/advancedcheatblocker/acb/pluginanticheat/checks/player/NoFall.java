package com.advancedcheatblocker.acb.pluginanticheat.checks.player;

import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.GroundChecker;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoFall implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer( );
        if ( ! PlayerUtil.canBypass( p ) ) {
            if(getFallDist(  p ) >= 2 ){
                if(p.isOnGround() && !GroundChecker.isOnGroundAround( p.getLocation() ) ){
                    FlagUtil.sendFlag( p, CheckNames.NoFallA);
                }
                if(p.isOnGround() && !GroundChecker.isOnGroundMath( p.getLocation().getY() )){
                    FlagUtil.sendFlag( p, CheckNames.NoFallB);
                }
            }
        }
    }
    public double getFallDist(Player p){
        return p.getFallDistance();
    }
}
