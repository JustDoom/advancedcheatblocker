package com.advancedcheatblocker.acb.pluginanticheat.checks.movements;

import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.GroundChecker;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Speed implements Listener{
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer( );
        double x = p.getVelocity().getX();
        double z = p.getVelocity().getZ();
        if(!GroundChecker.isOnGroundAround( event.getTo( ) ) )return;
        if(x >= 0.24 || z >= 0.24) {
            FlagUtil.sendFlag( p,CheckNames.SpeedA);
        }
    }
    @EventHandler
    public void onMove2(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if( !PlayerUtil.canBypass( p ) ){
            double speed = e.getTo().distance( e.getFrom() );
            double speedWalk = p.getWalkSpeed();
            if(! GroundChecker.isOnGroundMath( e.getTo().getY() ) && !GroundChecker.isOnGroundMath( e.getFrom().getY() )){
                speedWalk = p.getFlySpeed();
            }

            //p.sendMessage( "speed: "+speed+"\nWalkSpeed: "+speedWalk );

            if(e.getTo().getY() == e.getFrom().getY() && speed > speedWalk+0.5 && !p.isFlying()){
                FlagUtil.sendFlag( p,CheckNames.SpeedB );
            }
        }
    }
}
