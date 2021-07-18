package com.advancedcheatblocker.acb.pluginanticheat.checks.player;

import com.advancedcheatblocker.acb.pluginanticheat.checks.CheckNames;
import com.advancedcheatblocker.acb.pluginanticheat.utils.ChecksManager;
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
        if ( ! PlayerUtil.canBypass( p ) && ChecksManager.nofall ) {
            if(getFallDist(  p ) >= 2 ){
                if(event.getFrom().getY() > event.getTo().getY()){
                    if(p.isOnGround() && !GroundChecker.isOnGroundMath( event.getTo( ).getY() )){
                        FlagUtil.sendFlag( p, CheckNames.NoFall);
                    }
                }

            }
        }
    }
    public double getFallDist(Player p){
        return p.getFallDistance();
    }
}
