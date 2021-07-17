package com.advancedcheatblocker.acb.pluginanticheat.checks.movements;

import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.ChecksManager;
import com.advancedcheatblocker.acb.pluginanticheat.utils.FlagUtil;
import com.advancedcheatblocker.acb.pluginanticheat.utils.GroundChecker;
import com.advancedcheatblocker.acb.pluginanticheat.utils.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class Step implements Listener{

    @EventHandler
    public void onMove2(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(!PlayerUtil.canBypass( p ) && ChecksManager.step ){
            double y = event.getTo().getY() - event.getFrom().getY();
            //p.sendMessage( "Ve: "+p.getVelocity().getY() +"\ny: "+y+"\n  ");
            double ve = Math.abs( p.getVelocity().getY() );
            if(ve >= 0.078){
                if(event.getFrom().getY() < event.getTo().getY()){
                    if(y >= 1.6){
                        FlagUtil.sendFlag( p,CheckNames.Step );
                        if(ChecksManager.stepFlag){
                            event.setTo( event.getFrom() );
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(! PlayerUtil.canBypass( p) && ChecksManager.step){
            if(p.getVehicle() != null)return;
            double yv = p.getVelocity().getY();
            double from = event.getFrom().getY();
            double to = event.getTo().getY();
            if(!p.hasPotionEffect( PotionEffectType.JUMP )){
                if( from+1.45 < to  && GroundChecker.isOnGroundMath( event.getTo( ).getY( ) )){
                    if(yv >= 0.13){
                        FlagUtil.sendFlag( p,CheckNames.Step );
                        if(ChecksManager.stepFlag){
                            event.setTo( event.getFrom() );
                        }
                    }
                }
            }
        }
    }
}
