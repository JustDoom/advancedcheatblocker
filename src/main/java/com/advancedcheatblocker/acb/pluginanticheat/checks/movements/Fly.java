package com.advancedcheatblocker.acb.pluginanticheat.checks.movements;

import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.GroundChecker;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Fly implements Listener{
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer( );
        if(!PlayerUtil.canBypass( p )){
            double v = Math.abs( p.getVelocity().getY() );
            if(p.getVehicle() == null ){
                if(v >= 0.26 && event.getFrom().getY() == event.getTo( ).getY() &&
                        !GroundChecker.isOnGroundAround( event.getTo( ) ) && !GroundChecker.isOnGroundMath( event.getTo( ).getY() )){
                    FlagUtil.sendFlag( p, CheckNames.FlyA);
                }
            }
        }
    }
    @EventHandler
    public void onMove1(PlayerMoveEvent event){
        Player p = event.getPlayer( );
        if(!PlayerUtil.canBypass( p )){
            double v = Math.abs( p.getVelocity().getY() );
            double y = event.getFrom().getY() - event.getTo().getY();
            if(p.getVehicle() == null ){
                if(v >= 0.26 &&
                        !GroundChecker.isOnGroundAround( event.getTo( ) ) && !GroundChecker.isOnGroundMath( event.getTo( ).getY() ) ){
                    if(event.getFrom().getY() > event.getTo( ).getY()){
                        if(y < 0.15D){
                            FlagUtil.sendFlag( p, CheckNames.FlyB);
                        }
                    }

                }
            }
        }
    }
}
