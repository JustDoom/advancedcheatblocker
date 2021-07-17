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

public class InvalidMove implements Listener{
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        if(! PlayerUtil.canBypass( p) && ChecksManager.invalidmove ){
            boolean Ground = p.isOnGround();
            boolean Math = GroundChecker.isOnGroundMath( event.getTo().getY() ) && GroundChecker.isOnGroundMath( event.getFrom().getY() );
            if(Ground && !Math && (event.getFrom().getY() == event.getTo().getY() || !(event.getTo().getY()+1.5D > event.getFrom().getY()))
            &&  !GroundChecker.isStandingBoat( p ) ){
                FlagUtil.sendFlag( p , CheckNames.InvalidMove);
                if(ChecksManager.invalidmoveFlag){
                    event.setTo( event.getFrom() );
                }
            }
        }
    }
}
